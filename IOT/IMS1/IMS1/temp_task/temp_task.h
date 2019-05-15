/*
* temp_task.h
*
* Created: 5/13/2019 6:32:57 PM
*  Author: Ibi
*/


#ifndef TEMP_TASK_H_
#define TEMP_TASK_H_

#include <avr/io.h>
#include <avr/sfr_defs.h>
#include <ATMEGA_FreeRTOS.h>

#include "task.h"
#include "semphr.h"
#include "queue.h"

#include "stdio_driver.h"


void temp_tsk(void *pvParameters);


#endif /* TEMP_TASK_H_ */