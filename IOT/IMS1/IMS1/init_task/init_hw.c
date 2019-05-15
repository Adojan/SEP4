/*
 * init_hw_tsk.c
 *
 * Created: 5/14/2019 5:48:06 PM
 *  Author: AlexDima
 */ 

#include <init_hw.h>
#include <iled.h>
#include <ihal.h>
#include <hih8120.h>
#include <mh_z19.h>
#include <co2_task.h>

#define LED_TASK_PRIORITY 7


static char _out_buf[100];
static e_LoRa_return_code_t rc;



void init_sens()
{
	if ( HIH8120_OK == hih8120Create() )
	{
		// Driver created OK
		// Always check what hih8120Create() returns
		printf("Driver created OK\n");
	}
	else{
		printf("Driver not created BAD\n");
	}
	mh_z19_create(ser_USART3, my_co2_call_back);
	
}


void init_lora()
{
hal_create(LED_TASK_PRIORITY); // Must be called first!! LED_TASK_PRIORITY must be a high priority in your system
lora_driver_create(ser_USART1); // The parameter is the USART port the RN2483 module is connected to - in this case USART1	
}
