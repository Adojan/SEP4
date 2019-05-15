
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
#include <timers.h>
#include <task.h>
#include <temp_task.h>
#include <co2_task.h>
#include <lora_task.h>
#include <sensor_sample.h>
#include <hum_task.h>
#include <init_hw.h>


const TickType_t RELOAD_PERIOD= 4000;// 1 minute= 4000*15ms(tick time)

SemaphoreHandle_t semphTemp ,semphCO2,semphHum;
QueueHandle_t xPayloadQueue;
QueueHandle_t xTelegramQueue;

static void prvAutoReloadTimerCallback( TimerHandle_t xTimer )
{
TickType_t xTimeNow;
 /* Obtain the current tick count. */
 xTimeNow = xTaskGetTickCount();
 /* Output a string to show the time at which the callback was executed. */
 printf( "Auto-reload timer callback executing:  %d \n", xTimeNow );
 //ulCallCount++;
 xSemaphoreGive(semphTemp);
 xSemaphoreGive(semphCO2);
 xSemaphoreGive(semphHum);

}
/************************************************************************/
/* Start of main                                                        */
/************************************************************************/
int main(void)
{	
	xPayloadQueue = xQueueCreate( 5, sizeof( sample_t ) );	xTelegramQueue = xQueueCreate(1, sizeof(lora_payload_t));
	TimerHandle_t xAutoReloadTimer;
	
	semphTemp = xSemaphoreCreateBinary();
	semphCO2 = xSemaphoreCreateBinary();
	semphHum = xSemaphoreCreateBinary();
	xAutoReloadTimer = xTimerCreate( "AutoReload",RELOAD_PERIOD, pdTRUE,0,prvAutoReloadTimerCallback ); 	xTimerStart(xAutoReloadTimer,0);
	
	if( xPayloadQueue != NULL ) 	{			xTaskCreate(temp_tsk,"Task1",configMINIMAL_STACK_SIZE,NULL,3,NULL);
			xTaskCreate(co2_tsk,"Task2",configMINIMAL_STACK_SIZE,NULL,3,NULL);
			xTaskCreate(hum_tsk,"Task3",configMINIMAL_STACK_SIZE,NULL,3,NULL);
			xTaskCreate(lora_data_manager_tsk,"Receiver",configMINIMAL_STACK_SIZE+200,NULL,3,NULL);			
	}	stdioCreate(0);
	sei();
	init_lora();
	init_sens();
	vTaskStartScheduler();		
	//puts("Program Stared");
	
	while (1)
	{
		;
	}
}
/************************************************************************/
/*  End of main                                                         */
/************************************************************************/
