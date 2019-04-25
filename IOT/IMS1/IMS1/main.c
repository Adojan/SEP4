
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



#define task1_TASK_PRIORITY ( tskIDLE_PRIORITY + 5 )
#define task2_TASK_PRIORITY ( tskIDLE_PRIORITY + 5 )
TaskHandle_t x1Handle = NULL;
TaskHandle_t x2Handle = NULL;
int var = 0;
uint16_t ppm= 100;
mh_z19_return_code_t rc;
float temperature;
float humidity;


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


/************************************************************************/
/* Task 1                                                               */
/************************************************************************/
void get_CO2_task(void*pvParameters)
{
	// Remove compiler warnings.
	(void)pvParameters;
	while(1)
	{
		rc = mh_z19_take_meassuring();
		if (rc != MHZ19_OK )
		{
			// Something went wrong
		}
	else{
		mh_z19_get_co2_ppm(&ppm);
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
	while(1)
	{	if(HIH8120_OK!=hih8120Wakeup())
		{
	//some errors check 
		}	
			_delay_ms(55);
			if ( HIH8120_OK !=  hih8120Meassure() )
			{
				// Something went wrong
				// Investigate the return code further
			}
			_delay_ms(2);
			temperature= hih8120GetTemperature();
			humidity = hih8120GetHumidity();
			printf("temp is %4.2fC  and humidity is %2.2f%% \n",temperature,humidity);
	}vTaskDelete(NULL);
}
/************************************************************************/
/* Start of main                                                        */
/************************************************************************/
int main(void)
{	xTaskCreate(get_CO2_task, "Measure CO2 ", configMINIMAL_STACK_SIZE, NULL, task1_TASK_PRIORITY, &x1Handle);
	xTaskCreate(get_T_and_H_task, "Measure Temp and Humidity", configMINIMAL_STACK_SIZE, NULL, task2_TASK_PRIORITY, &x2Handle);

	stdioCreate(0);
	sei();
	
	init_CO2_sens();
	init_T_H_sens();
	puts("Program Stared");
	vTaskStartScheduler();
	while (1)
	{
		;
	}
}
/************************************************************************/
/*  End of main                                                         */
/************************************************************************/
