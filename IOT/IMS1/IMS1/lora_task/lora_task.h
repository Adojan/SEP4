/*
* lora_t.h
*
* Created: 5/13/2019 7:27:18 PM
*  Author: Ibi
*/


#ifndef LORA_T_H_
#define LORA_T_H_


#include <avr/io.h>
#include <avr/sfr_defs.h>
#include <ATMEGA_FreeRTOS.h>
#include <lora_driver.h>

#include "task.h"
#include "semphr.h"
#include "stdio_driver.h"
#include "queue.h"

void lora_data_manager_tsk(void *pvParameters);
//lora_payload_t make_uplink_payload(const uint16_t *values)
void lora_send_tsk(void *pvParameters);


#endif /* LORA_T_H_ */