/*
* temp_task_impl.c
*
* Created: 5/13/2019 6:41:03 PM
*  Author: Ibi
*/

#include "temp_task.h"
#include <sensor_sample.h>
#include <math.h>
#include <hih8120.h>

extern SemaphoreHandle_t semphTemp;
extern QueueHandle_t xPayloadQueue;
extern RELOAD_PERIOD;
static uint16_t default_temperature = 0xffff;


void temp_tsk(void *pvParameters) {
	
	TickType_t xLastExecutionTime;	xLastExecutionTime = xTaskGetTickCount();	sample_t lValueToSend ;
	BaseType_t xStatus;	lValueToSend.s_src=TEMP;	lValueToSend.s_value=default_temperature;	
	while(1) {
		xSemaphoreTake(semphTemp,0);
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
			float temp = hih8120GetTemperature();
			lValueToSend.s_value = round(temp*100); //  temp round for precision
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

