cmake_minimum_required(VERSION 3.12)
project(IMS1 C)

set(CMAKE_C_STANDARD 11)

include_directories(co2_task)
include_directories(DataStructures)
include_directories(hum_task)
include_directories(init_task)
include_directories(lora_task)
include_directories(temp_task)

add_executable(IMS1
        co2_task/co2_task.h
        co2_task/co2_task_impl.c
        DataStructures/sensor_sample.h
        hum_task/hum_task.h
        hum_task/hum_task_impl.c
        init_task/init_hw.c
        init_task/init_hw.h
        lora_task/lora_task.h
        lora_task/lora_task_impl.c
        temp_task/temp_task.h
        temp_task/temp_task_impl.c
        main.c)
