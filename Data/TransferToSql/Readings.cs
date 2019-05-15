using System;
using System.Collections.Generic;
using System.Text;
using MongoDB.Bson;
using MongoDB.Bson.Serialization.Attributes;

namespace Folder
{
    public class Readings
    {
        [BsonRepresentation(BsonType.ObjectId)]
        public string _id
        {
            get; set;
        }

        [BsonRepresentation(BsonType.Int64)]
        public string Id
        {
            get; set;
        }


        [BsonRepresentation(BsonType.Int64)]
        public int roomID
        {
            get; set;
        }

        [BsonRepresentation(BsonType.Decimal128)]
        public string humidity
        {
            get; set;
        }

        [BsonRepresentation(BsonType.Decimal128)]
        public double temperature
        {
            get; set;
        }

        [BsonRepresentation(BsonType.Decimal128)]
        public double co2
        {
            get; set;
        }

        [BsonRepresentation(BsonType.DateTime)]
        public double dateTime
        {
            get; set;
        }


        public Readings(Int64 id, Int64 roomID, double humidity, double temperature, double co2, DateTime dateTime)
        {
            this.id = id;
            this.roomID = roomID;
            this.humidity = humidity;
            this.temperature = temperature;
            this.co2 = co2;
            this.dateTime = dateTime;
        }


    }
}
