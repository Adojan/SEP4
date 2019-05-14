package SQLHandler;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;


public class MongoDBClient {


    public MongoDBClient() {

        MongoClientURI uri = new MongoClientURI(
                "mongodb+srv://sep4xyz:<123456Sepxyz>@sep4xyz-ye3jp.azure.mongodb.net/test?retryWrites=true");

        MongoClient mongoClient = new MongoClient(uri);
        MongoDatabase database = mongoClient.getDatabase("test");


    }

    


}
