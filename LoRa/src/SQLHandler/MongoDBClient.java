package SQLHandler;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;
import controller.WebSocketClient;
import org.bson.Document;


public class MongoDBClient {
    private WebSocketClient webSocketClient;
    private MongoDatabase database;


    public MongoDBClient() {

        MongoClientURI uri = new MongoClientURI(
                "mongodb+srv://data:datasep@cluster0-ye3jp.azure.mongodb.net/test?retryWrites=true");

        MongoClient mongoClient = new MongoClient(uri);
        database = mongoClient.getDatabase("temp");
        webSocketClient = new WebSocketClient();

    }


    public void setData(String collectionName) {
        System.out.println("not sent");
        if (webSocketClient.exportRoom().getMeasurements() == null) {
            double humidity = webSocketClient.exportRoom().getMeasurements().getHumidity();
            double temp = webSocketClient.exportRoom().getMeasurements().getTemperature();
            int co2 = webSocketClient.exportRoom().getMeasurements().getCo2();
            Document document = new Document()
                    .append("humidity", humidity)
                    .append("temperature ", temp)
                    .append("co2", co2);
            database.getCollection(collectionName).insertOne(document);
            System.out.println("Data Sent!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        }

    }

    public String room() {
        if (webSocketClient.exportRoom() != null) {
            return webSocketClient.exportRoom().toString();

        }
        return null;
    }
}