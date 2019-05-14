/*
* hum_task.h
*
* Created: 5/13/2019 7:22:29 PM
*  Author: Ibi, AlexDima
*/


#ifndef HUM_TASK_H_
#define HUM_TASK_H_

#include <avr/io.h>
#include <avr/sfr_defs.h>
#include <ATMEGA_FreeRTOS.h>

#include "task.h"
#include "semphr.h"
#include "stdio_driver.h"
#include "queue.h"

void hum_tsk(void *pvParameters);

#endif /* HUM_TASK_H_ */