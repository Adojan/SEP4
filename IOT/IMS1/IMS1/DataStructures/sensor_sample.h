/*
* sensor_sample.h
*
* Created: 5/14/2019 11:59:59 AM
*  Author: AlexDima
*/




#ifndef SENSOR_SAMPLE_H_
#define SENSOR_SAMPLE_H_
#include <stdint.h>



typedef enum
{
	HUM =0,
	TEMP,
	CO2
}sens_t;




typedef struct
{
	sens_t s_src;
	uint16_t s_value ;
}sample_t;

#endif /* SENSOR_SAMPLE_H_ */