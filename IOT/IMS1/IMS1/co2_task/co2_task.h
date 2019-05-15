/*
* co2_task.h
*
* Created: 5/13/2019 7:22:29 PM
*  Author: Ibi
*/


#ifndef CO2_TASK_H_
#define CO2_TASK_H_

#include <avr/io.h>
#include <avr/sfr_defs.h>
#include <ATMEGA_FreeRTOS.h>

#include "task.h"
#include "semphr.h"
#include "stdio_driver.h"
#include "queue.h"

void co2_tsk(void *pvParameters);
void my_co2_call_back(uint16_t ppm);



#endif /* CO2_TASK_H_ */