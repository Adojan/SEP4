/*
* hum_task.c
*
* Created: 5/13/2019 7:22:42 PM
*  Author: Ibi,AlexDima
*/

#include "hum_task.h"
#include <sensor_sample.h>
#include <math.h>
#include <hih8120.h>
extern SemaphoreHandle_t semphHum;
extern QueueHandle_t xPayloadQueue;
extern RELOAD_PERIOD;
static uint16_t default_hum = 0xffff;

void hum_tsk(void *pvParameters) {
	BaseType_t xStatus;	TickType_t xLastExecutionTime;	xLastExecutionTime = xTaskGetTickCount();
		sample_t lValueToSend;	lValueToSend.s_src=HUM;	lValueToSend.s_value=default_hum;
	
	

	
	while(1) {
		xSemaphoreTake(semphHum,0);
		{
		
		if(HIH8120_OK!=hih8120Wakeup())
		{
			//some errors check 
		}	
		vTaskDelay(pdMS_TO_TICKS(50UL));
		//*TODO: change delay to timer/interrupts */
		if ( HIH8120_OK !=  hih8120Meassure() )
		{
		// Something went wrong
		// Investigate the return code further
		// this return code it gives wrong values 
		}
		vTaskDelay(pdMS_TO_TICKS(50UL));
		float humidity = hih8120GetHumidity();
		lValueToSend.s_value = round(humidity*100); //  humidity round for precision 
		xStatus = xQueueSendToBack( xPayloadQueue, &lValueToSend, 0 );
			if( xStatus != pdPASS )
			{
						
				printf( "Task3 Could not send to the queue.\r\n" );
				vTaskDelayUntil(&xLastExecutionTime,RELOAD_PERIOD);
			}
			vTaskDelayUntil(&xLastExecutionTime,RELOAD_PERIOD);
			
		}

		
	}
	vTaskDelete(NULL);
}
