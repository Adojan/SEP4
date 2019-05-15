/*
* co2_task.c
*
* Created: 5/13/2019 7:22:42 PM
*  Author: Ibi
*/

#include "co2_task.h"
#include <sensor_sample.h>
#include <mh_z19.h>

extern SemaphoreHandle_t semphCO2;
extern QueueHandle_t xPayloadQueue;
extern RELOAD_PERIOD;
static uint16_t default_co2 = 0xffff;
mh_z19_return_code_t rc;
void co2_tsk(void *pvParameters) {
	BaseType_t xStatus;	TickType_t xLastExecutionTime;	xLastExecutionTime = xTaskGetTickCount();
		sample_t lValueToSend;	lValueToSend.s_src=CO2;	lValueToSend.s_value=default_co2;
	
	

	
	while(1) {
		xSemaphoreTake(semphCO2,0);
		{
			rc = mh_z19_take_meassuring();
			if (rc != MHZ19_OK )
			{
			// something went wrong
			printf("no co2\n");
			}
			else{
			mh_z19_get_co2_ppm(default_co2);
			lValueToSend.s_value=default_co2;
			}
			xStatus = xQueueSendToBack( xPayloadQueue, &lValueToSend, 0 );
			if( xStatus != pdPASS )
			{
						
				printf( "Task2 Could not send to the queue.\r\n" );
				vTaskDelayUntil(&xLastExecutionTime,RELOAD_PERIOD);
			}
			//lValueToSend.s_value ++;
			vTaskDelayUntil(&xLastExecutionTime,RELOAD_PERIOD);
			
		}

		
	}
	vTaskDelete(NULL);
}
void my_co2_call_back(uint16_t ppm)
{
	default_co2=ppm;
	printf("co2 is %d\n",default_co2);
}
