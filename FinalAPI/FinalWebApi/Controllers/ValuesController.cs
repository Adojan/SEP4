using FinalWebApi.Models;
using ReadingAccess;
using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;



namespace FinalWebApi.Controllers
{
    public class ValuesController : ApiController
    {

       List<Readings> readings = new List<Readings>();
        
        public String result_co2 = "";
        public String result_humidity = "";
        public String result_temperature = "";
      

       public ValuesController()
        {
            ForProjectEntities entities = new ForProjectEntities();



           

            foreach (AllCO2Facts f in entities.AllCO2Facts.ToList())
            {
                result_co2 = result_co2 + f.ToString();
            }

            foreach (AllHumidityFact f in entities.AllHumidityFacts.ToList())
            {
                result_humidity = result_humidity + f.ToString();
            }


            foreach (AllTemperatureFact f in entities.AllTemperatureFacts.ToList())
            {
                result_temperature = result_temperature + f.ToString();
            }

           

            int counter = 0;
            int x = 0;
            


            var c_co2 = result_co2.Split(',');
            var c_humidty = result_humidity.Split(',');
            var c_temperature = result_temperature.Split(',');
            

            for (int i = 0;i< c_co2.Length;i++)
            {
                Readings reading = new Readings();

                int.TryParse(c_co2[i], out x);
                reading.setCo2(x);

                int.TryParse(c_humidty[i], out x);
                reading.setHumidity(x);

                int.TryParse(c_temperature[i], out x);
                reading.setTemperature(x);



                readings.Add(reading);

            }

            

        }



        // GET api/values
        public String  Get()
        {
            String message="";
            int i=1;

            foreach (Readings f in readings)
            {
                    message = message + "ObjectID: " + i + " CO2 Percentage: " + f.getCo2() + " Humidity: " + f.getHumidity() + " Temperature: " + f.getTemperature() + "|||";
        
                i++;
            }

            return message;
        }

        // GET api/values/5
        public Readings Get(int id)
        {
            return readings[id];
        }

        // POST api/values
        public void Post([FromBody]string value)
        {

        }

        // PUT api/values/5
        public void Put(int id, [FromBody]string value)
        {

        }

        // DELETE api/values/5
        public void Delete(int id)
        {

        }
    }
}
