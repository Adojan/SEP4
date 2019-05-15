using MongoDB.Bson;
using MongoDB.Driver;
using System;
using System.Collections.Generic;
using System.Data;
using System.Data.SqlClient;
using System.Linq;
namespace Folder
{
    class Program
    {
        static void Main(string[] args)
        {


            var mongoClient = new MongoClient(@"mongodb+srv://sep4xyz:<123456Sepxyz>@sep4xyz-ye3jp.azure.mongodb.net/test");

            var mongoDatabase = mongoClient.GetDatabase("Sep4xyz");
            var Measurements = mongoDatabase.GetCollection<Readings>("Measurements");
            var Final = Measurements.AsQueryable<Readings>().ToList();

            var Read = new List<Readings>();

            Final.ForEach(read => Read.Add(read));


            SqlConnection SqlConnection;
            SqlCommand SqlCommand;


            string connection = @"Data Source = .\SQLEXPRESS ; Integrated Security = True;";
            SqlConnection = new SqlConnection(connection);

            try
            {

                for (int i = 0; i < Read.Count; i++)
                {


                    string sql = "Insert into [PrimaryForProject].[dbo].[Measurements] (CO2Percentage,Temperature,Humidity,Room,ID,DateAndTime)" +
                        " values (@CO2Percentage,@Temperature,@Humidity,@Room,@ID,@DateAndTime);";
                    SqlConnection.Open();
                    SqlCommand = new SqlCommand(sql, SqlConnection);
                    SqlCommand.Parameters.Add("@id", SqlDbType.Int).Value = Read[i].id;
                    SqlCommand.Parameters.Add("@roomID", SqlDbType.nchar).Value = Read[i].roomID;
                    SqlCommand.Parameters.Add("@humidity", SqlDbType.decimal128).Value = Read[i].humidity;
                    SqlCommand.Parameters.Add("@temperature", SqlDbType.decimal128).Value = Read[i].temperature;
                    SqlCommand.Parameters.Add("@co2", SqlDbType.Decimal128).Value = Read[i].co2;
                    SqlCommand.Parameters.Add("@dateTime", SqlDbType.DateTime).Value = Read[i].dateTime;
                    SqlCommand.ExecuteNonQuery();
                    SqlCommand.Dispose();
                    SqlConnection.Close();

                    Console.WriteLine("Data added succesfully");
                }



            }
            catch (System.Exception)
            {

                throw;
            }


        }
    }
}
