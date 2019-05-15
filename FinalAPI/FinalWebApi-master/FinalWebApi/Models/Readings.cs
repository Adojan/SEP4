using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace FinalWebApi.Models
{

    public class Readings
    {

        private float co2;
        private float humidity;
        private float temperature;

        public void setCo2(float co2)
        {
            this.co2 = co2;
        }

        public float getCo2()
        {
            return co2;
        }

        public void setHumidity(float humidity)
        {
            this.humidity = humidity;
        }

        public float getHumidity()
        {
            return humidity;
        }

        public void setTemperature(float temperature)
        {
            this.temperature = temperature;
        }

        public float getTemperature()
        {
            return temperature;
        }


    }
}