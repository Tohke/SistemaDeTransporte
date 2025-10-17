package dao;


import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import static org.bson.codecs.configuration.CodecRegistries.*;

public class MongoConn {
    private static MongoClient client;
    private static MongoDatabase mongoDB;

    public static MongoDatabase mongoDB() {
        if (mongoDB == null) {
            CodecRegistry pojoCodecRegistry = fromRegistries(
                    MongoClientSettings.getDefaultCodecRegistry(),
                    fromProviders(PojoCodecProvider.builder().automatic(true).build())
            );

            client = MongoClients.create("mongodb://localhost:27017");
            mongoDB = client.getDatabase("SistemaDeTransporte").withCodecRegistry(pojoCodecRegistry);
        }
        return mongoDB;
    }
}