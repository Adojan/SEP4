/*
 * init_hw.h
 *
 * Created: 5/14/2019 5:45:01 PM
 *  Author: Ibi,AlexDima
 */ 


#ifndef INIT_HW_H_
#define INIT_HW_H_

#include <ihal.h>
#include <lora_driver.h>
#include <avr/io.h>
#include <avr/sfr_defs.h>
#include <ATMEGA_FreeRTOS.h>
#include <lora_driver.h>
#include <task.h>

void init_sens();
void init_lora();


#endif /* INIT_HW_H_ */