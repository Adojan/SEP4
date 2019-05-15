/*
* lora_task_impl.c
*
* Created: 5/13/2019 7:27:35 PM
*  Author: Ibi,ALexDima
*/

#include "lora_task.h"
#include <sensor_sample.h>
#include <lora_driver.h>
#include <stddef.h>
#include <stdio.h>
#include <ATMEGA_FreeRTOS.h>
#include <iled.h>

#define LORA_appEUI "eeafd0b9c6819d9a"
#define LORA_appKEY "42f8f2b3db80e73bd177cb88dbba67e4"


extern QueueHandle_t xTelegramQueue;
extern QueueHandle_t xPayloadQueue;
extern RELOAD_PERIOD;
const uint8_t LENGHT = 3;
static char _out_buf[100];
static void _lora_setup(void);
static e_LoRa_return_code_t rc;
void lora_data_manager_tsk(void *pvParameters)
{
	TickType_t xLastExecutionTime;	xLastExecutionTime = xTaskGetTickCount();	sample_t recValue ;
	BaseType_t xStatus;	uint16_t values[LENGHT];	uint8_t counter = 0;	lora_payload_t test_payload;	
		// Hardware reset of LoRaWAN transceiver
	lora_driver_reset_rn2483(1);
	vTaskDelay(2);
	lora_driver_reset_rn2483(0);
		// Give it a chance to wakeup
	vTaskDelay(150);
	lora_driver_flush_buffers(); // get rid of first version string from module after reset!
	_lora_setup();


	test_payload.len = 6;
	test_payload.port_no = 2;


	while(1)
	{
				
		if( uxQueueMessagesWaiting( xPayloadQueue ) != 0 )
		{
			xStatus = xQueueReceive( xPayloadQueue, &recValue, portMAX_DELAY );
			//put the value to the corresponding sensor position
			values[recValue.s_src]=recValue.s_value;
			
			test_payload.bytes[2*recValue.s_src] = values[recValue.s_src] >> 8;
			test_payload.bytes[2*recValue.s_src+1] = values[recValue.s_src] & 0xFF;
			counter++;
			if(counter==LENGHT){
				printf("uplink_payload is :%02x%02x %02x%02x %02x%02x \n" ,test_payload.bytes[0],test_payload.bytes[1],				test_payload.bytes[2],test_payload.bytes[3],test_payload.bytes[4],test_payload.bytes[5]);				
				led_short_puls(led_ST4);  // OPTIONAL
				printf("Upload Message >%s<\n", lora_driver_map_return_code_to_text(lora_driver_sent_upload_message(false, &test_payload)));
				counter = 0;
			}
			if( xStatus != pdPASS )
			{
				printf( "Could not receive.\r\n" );
			}
		}
	}
	vTaskDelete(NULL);
}

static void _lora_setup(void)
{
	e_LoRa_return_code_t rc;
	led_slow_blink(led_ST2); // OPTIONAL: Led the green led blink slowly while we are setting up LoRa

	// Factory reset the transceiver
	printf("FactoryReset >%s<\n", lora_driver_map_return_code_to_text(lora_driver_rn2483_factory_reset()));
	
	// Configure to EU868 LoRaWAN standards
	printf("Configure to EU868 >%s<\n", lora_driver_map_return_code_to_text(lora_driver_configure_to_eu868()));

	// Get the transceivers HW EUI
	rc = lora_driver_get_rn2483_hweui(_out_buf);
	printf("Get HWEUI >%s<: %s\n",lora_driver_map_return_code_to_text(rc), _out_buf);

	// Set the HWEUI as DevEUI in the LoRaWAN software stack in the transceiver
	printf("Set DevEUI: %s >%s<\n", _out_buf, lora_driver_map_return_code_to_text(lora_driver_set_device_identifier(_out_buf)));

	// Set Over The Air Activation parameters to be ready to join the LoRaWAN
	printf("Set OTAA Identity appEUI:%s appKEY:%s devEUI:%s >%s<\n", LORA_appEUI, LORA_appKEY, _out_buf, lora_driver_map_return_code_to_text(lora_driver_set_otaa_identity(LORA_appEUI,LORA_appKEY,_out_buf)));

	// Save all the MAC settings in the transceiver
	printf("Save mac >%s<\n",lora_driver_map_return_code_to_text(lora_driver_save_mac()));

	// Enable Adaptive Data Rate
	printf("Set Adaptive Data Rate: ON >%s<\n", lora_driver_map_return_code_to_text(lora_driver_set_adaptive_data_rate(LoRa_ON)));

	// Join the LoRaWAN
	uint8_t maxJoinTriesLeft = 20;
	do {
		rc = lora_driver_join(LoRa_OTAA);
		printf("Join Network TriesLeft:%d >%s<\n", maxJoinTriesLeft, lora_driver_map_return_code_to_text(rc));

		if ( rc != LoRa_ACCEPTED)
		{
			// Make the red led pulse to tell something went wrong
			led_long_puls(led_ST1); // OPTIONAL
			// Wait 5 sec and lets try again
			vTaskDelay(pdMS_TO_TICKS(20000UL));
		}
		else
		{
			break;
		}
	} while (--maxJoinTriesLeft);

	if (rc == LoRa_ACCEPTED)
	{
		// Connected to LoRaWAN :-)
		// Make the green led steady
		led_led_on(led_ST2); // OPTIONAL
	}
	else
	{
		// Something went wrong
		// Turn off the green led
		led_led_off(led_ST2); // OPTIONAL
		// Make the red led blink fast to tell something went wrong
		led_fast_blink(led_ST1); // OPTIONAL

		// Lets stay here
		while (1)
		{
			taskYIELD();
		}
	}
}