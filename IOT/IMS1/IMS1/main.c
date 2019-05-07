
#include <stdio.h>
#include <stdio_driver.h>
#include <avr/interrupt.h>
#include <util/delay.h>
#include <avr/io.h>
#include <avr/sfr_defs.h>
#include <ATMEGA_FreeRTOS.h>
#include <semphr.h>
#include <mh_z19.h> // co2
#include <hih8120.h>//temp-humid
#include <string.h>
#include <assert.h>
#include <timers.h>
#include <ihal.h>
#include <lora_driver.h>
#include <stddef.h>
#include <lora_driver.h>
#include <iled.h>
#include <math.h>

//#define "The method or operation is not implemented." 1
//#define "The method or operation is  implemented." 0


#define LORA_appEUI "eeafd0b9c6819d9a"
#define LORA_appKEY "42f8f2b3db80e73bd177cb88dbba67e4"
#define task1_TASK_PRIORITY ( tskIDLE_PRIORITY + 5 )
#define task2_TASK_PRIORITY ( tskIDLE_PRIORITY + 5 )
#define task3_TASK_PRIORITY ( tskIDLE_PRIORITY + 5 )
#define task4_TASK_PRIORITY ( tskIDLE_PRIORITY + 5 )
#define task5_TASK_PRIORITY ( tskIDLE_PRIORITY + 5 )
#define task6_TASK_PRIORITY ( tskIDLE_PRIORITY + 5 )
#define LED_TASK_PRIORITY 7
//#define lora_handler_task_priority 
TimerHandle_t xTimer1 ;
SemaphoreHandle_t xSemaphore1;
TaskHandle_t x1Handle = NULL;
TaskHandle_t x2Handle = NULL;
TaskHandle_t x3Handle = NULL;
TaskHandle_t x4Handle = NULL;
TaskHandle_t x5Handle = NULL;
TaskHandle_t x6Handle = NULL;
//int var = 0;
uint16_t ppm= 100;
mh_z19_return_code_t rc;
float temperature;
float humidity;
		
 uint16_t hum  ; // test humidity
 uint16_t temp ; // test temp
 uint16_t co2_ppm ; // test CO2
 



/*A callback function to use the output of the CO2 sensor*/
void my_co2_call_back(uint16_t ppm)
{	
	printf("CO2 is %d \n ",(int)ppm);
}

/*initialize temperature and humidity driver*/
void init_T_H_sens (){
	if ( HIH8120_OK == hih8120Create() )
	{
		// Driver created OK
		// Always check what hih8120Create() returns
		printf("Driver created OK\n");
	}
}

/*initialize CO2 driver*/
void init_CO2_sens(){
		mh_z19_create(ser_USART3, my_co2_call_back);

}

void init_Lux_sens()
{
	assert(!"The method or operation is not implemented.");
}




void init_LoRa_module()
{
hal_create(LED_TASK_PRIORITY);
lora_driver_create(ser_USART1);
}

void vTimerCallback1(TimerHandle_t pxTimer)
{
	xSemaphoreGive(xSemaphore1);
}

/************************************************************************/
/* Task 1                                                               */
/************************************************************************/
void get_CO2_task(void*pvParameters)
{
	// Remove compiler warnings.
	(void)pvParameters;
	while(1)
	{	xSemaphoreTake(xSemaphore1, 1000);
		xTimerStart(xTimer1,1000);
		rc = mh_z19_take_meassuring();
		if (rc != MHZ19_OK )
		{
			// Something went wrong
		}
	else{
		mh_z19_get_co2_ppm(&co2_ppm);
		}
	}vTaskDelete(NULL);
}
/************************************************************************/
/* Task 2                                                               */
/************************************************************************/
void get_T_and_H_task(void*pvParameters)
{
	// Remove compiler warnings.
	(void)pvParameters;
	uint16_t count=0;
	while(1)
	{	xSemaphoreTake(xSemaphore1,1000);
		xTimerStart(xTimer1,100);
		if(HIH8120_OK!=hih8120Wakeup())
		{
	//some errors check 
		}	
		
		/*TODO: change delay to timer/interrupts */
		if ( HIH8120_OK !=  hih8120Meassure() )
		{
			// Something went wrong
			// Investigate the return code further
			printf("ERROR\n");
		}
		temperature= hih8120GetTemperature();
		
		humidity = hih8120GetHumidity();
		//temp=(uint16_t)round(temperature);
	
		
		printf("measurement no: %d has temp is %4.2f humidity is %2.2f%% \n",count,temperature,humidity);
		count++;
	}vTaskDelete(NULL);
}
/************************************************************************/
/* Task 3                                                               */
/************************************************************************/
void get_Light_Intensity_task(void*pvParameters)
{
	// Remove compiler warnings.
	(void)pvParameters;
	while(1)
	{	
	}vTaskDelete(NULL);
}
/************************************************************************/
/* Task 4                                                               */
/************************************************************************/
void prepare_telegram_task(void*pvParameters)
{
	// Remove compiler warnings.
	(void)pvParameters;
	while(1)
	{
	}vTaskDelete(NULL);
}
/************************************************************************/
/* Task 5                                                              */
/************************************************************************/
void send_telegram_task(void*pvParameters)
{
	// Remove compiler warnings.
	(void)pvParameters;
	while(1)
	{
	}vTaskDelete(NULL);
}
/************************************************************************/
/* Task 6		                                                        */
/************************************************************************/
void LoRa_Task(void*pvParameters)
{
	// Remove compiler warnings.
	(void)pvParameters;
	while(1)
	{	printf("LoRa Task Started\n");
		_delay_ms(10);
		//lora_driver_reset_rn2483(1);
		//vTaskDelay(2);
		//lora_driver_reset_rn2483(0);
		//vTaskDelay(150);
		//lora_driver_flush_buffers();
	
		if (lora_driver_rn2483_factory_reset() != LoRA_OK)
		{
			printf("Set the module to factory set defaults:FAILED\n");
				_delay_ms(10);
		}else
		{
			printf("Set the module to factory set defaults:SUCCESS\n");
				_delay_ms(10);
		}

		if (lora_driver_configure_to_eu868() != LoRA_OK)
		{
			printf("Configure the module to use the EU868 frequency plan and settings: FAILED\n");
				_delay_ms(10);
		}else
		{
			printf("Configure the module to use the EU868 frequency plan and settings: SUCCESS\n");
				_delay_ms(10);
		}


		static char dev_eui[17]; // It is static to avoid it to occupy stack space in the task
		if (lora_driver_get_rn2483_hweui(dev_eui) != LoRA_OK)
		{
			printf("Get the RN2483 modules unique devEUI:FAILED\n");
				_delay_ms(10);
		}else
		{
			printf("Get the RN2483 modules unique devEUI:SUCCESS\n");	
				_delay_ms(10);
			printf("dev_eui is : ");
				_delay_ms(10);
			printf(dev_eui);
				_delay_ms(10);
			printf("\n");
				_delay_ms(10);
		}
		

		if (lora_driver_set_otaa_identity(LORA_appEUI,LORA_appKEY,dev_eui) != LoRA_OK)
		{
			printf("Set the necessary LoRaWAN parameters for an OTAA join: FAILED\n");
				_delay_ms(10);
		}
		if (lora_driver_save_mac() != LoRA_OK)
		{
			printf("Save all set parameters to the RN2483 modules EEPROM: FAILED\n");
				_delay_ms(10);
		}
// All parameters are now saved in the module

		if (lora_driver_join(LoRa_OTAA) == LoRa_ACCEPTED)
		{
			printf("Join LoRaWAN parameters with OTAA: SUCCESS\n");
				_delay_ms(10);
		}
		
	}vTaskDelete(NULL);
}



static char _out_buf[100];

void lora_handler_task( void *pvParameters );

static lora_payload_t _uplink_payload;

void lora_handler_create(UBaseType_t lora_handler_task_priority)
{
	xTaskCreate(
	lora_handler_task
	,  (const portCHAR *)"LRHand"  // A name just for humans
	,  configMINIMAL_STACK_SIZE+200  // This stack size can be checked & adjusted by reading the Stack Highwater
	,  NULL
	,  lora_handler_task_priority  // Priority, with 3 (configMAX_PRIORITIES - 1) being the highest, and 0 being the lowest.
	,  NULL );
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
	uint8_t maxJoinTriesLeft = 100;
	do {
		rc = lora_driver_join(LoRa_OTAA);
		printf("Join Network TriesLeft:%d >%s<\n", maxJoinTriesLeft, lora_driver_map_return_code_to_text(rc));

		if ( rc != LoRa_ACCEPTED)
		{
			// Make the red led pulse to tell something went wrong
			led_long_puls(led_ST1); // OPTIONAL
			// Wait 5 sec and lets try again
			vTaskDelay(pdMS_TO_TICKS(5000UL));
		}
		else
		{	printf("connection error:>%s<\n", lora_driver_map_return_code_to_text(rc));

			break;
		}
	} while (--maxJoinTriesLeft);

	if (rc == LoRa_ACCEPTED)
	{
		// Connected to LoRaWAN :-)
		// Make the green led steady
		led_led_on(led_ST2); // OPTIONAL
		printf("Connected\n");
		vTaskDelay(pdMS_TO_TICKS(5000UL));
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

/*-----------------------------------------------------------*/
void lora_handler_task( void *pvParameters )
{
	static e_LoRa_return_code_t rc;
	printf("test : lora handler task -->setup<--started\n");
	_delay_ms(15);
	
	// Hardware reset of LoRaWAN transceiver
	lora_driver_reset_rn2483(1);
	

	
	vTaskDelay(2);
	lora_driver_reset_rn2483(0);
	// Give it a chance to wakeup
	
	vTaskDelay(150);

	lora_driver_flush_buffers(); // get rid of first version string from module after reset!

	_lora_setup();

	_uplink_payload.len = 6;
	_uplink_payload.port_no = 2;


	for(;;)
	{
		vTaskDelay(pdMS_TO_TICKS(15000UL));

		// Some dummy payload
		//uint16_t hum = 12345; // Dummy humidity
		//int16_t temp = 675; // Dummy temp
		//uint16_t co2_ppm = 1050; // Dummy CO2

		_uplink_payload.bytes[0] = hum >> 8;
		_uplink_payload.bytes[1] = hum & 0xFF;
		_uplink_payload.bytes[2] = temp >> 8;
		_uplink_payload.bytes[3] = temp & 0xFF;
		_uplink_payload.bytes[4] = co2_ppm >> 8;
		_uplink_payload.bytes[5] = co2_ppm & 0xFF;

		led_short_puls(led_ST4);  // OPTIONAL
		printf("Upload Message >%s<\n", lora_driver_map_return_code_to_text(lora_driver_sent_upload_message(false, &_uplink_payload)));
	}
}













/************************************************************************/
/* Start of main                                                        */
/************************************************************************/
int main(void)
{	
	xTimer1=xTimerCreate("Timer 1 ", (1000/portTICK_PERIOD_MS),pdTRUE,(void*)0,vTimerCallback1);
	xSemaphore1=xSemaphoreCreateMutex();
	//xTaskCreate(get_CO2_task, "Measure CO2 ", configMINIMAL_STACK_SIZE, NULL, task1_TASK_PRIORITY, &x1Handle);
	xTaskCreate(get_T_and_H_task, "Measure Temp and Humidity", configMINIMAL_STACK_SIZE, NULL, task2_TASK_PRIORITY, &x2Handle);
	//xTaskCreate(get_Light_Intensity_task, "Measure light intensity", configMINIMAL_STACK_SIZE,NULL,task3_TASK_PRIORITY,&x3Handle);
	//xTaskCreate(prepare_telegram_task, "Prepare telegram", configMINIMAL_STACK_SIZE,NULL,task3_TASK_PRIORITY,&x4Handle);
	//xTaskCreate(send_telegram_task, "Send telegram", configMINIMAL_STACK_SIZE,NULL,task3_TASK_PRIORITY,&x5Handle);
	//xTaskCreate(LoRa_Task, "initLORA", configMINIMAL_STACK_SIZE,NULL,task6_TASK_PRIORITY,&x6Handle);
	stdioCreate(0);
	sei();
	//hal_create(LED_TASK_PRIORITY);
	//lora_driver_create(ser_USART1);
	//lora_handler_create(3);
	//init_CO2_sens();
	init_T_H_sens();
	//init_Lux_sens();
	//init_LoRa_module();
	
	
	//puts("Program Stared");
	vTaskStartScheduler();
	while (1)
	{
		;
	}
}
/************************************************************************/
/*  End of main                                                         */
/************************************************************************/
