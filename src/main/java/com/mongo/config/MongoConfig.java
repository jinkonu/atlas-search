package com.mongo.config;

import com.mongo.domain.Artist;
import com.mongo.repository.ArtistRepository;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackageClasses = {
        ArtistRepository.class
})
public class MongoConfig extends AbstractMongoClientConfiguration {

    @Value("${mongo.datasource}")
    private String dataSource;

    @Value("${mongo.database-name}")
    private String databaseName;

    @Value("${mongo.collection-name}")
    private String collectionName;

    @Override
    protected String getDatabaseName() {
        return databaseName;
    }

    @Bean
    public MongoClient mongoClient() {
        return MongoClients.create(dataSource);
    }

    @Bean
    public MongoDatabase mongoDatabase(MongoClient mongoClient) {
        return mongoClient.getDatabase(databaseName);
    }

    @Bean
    public MongoCollection<Artist> artistCollection(MongoDatabase database) {
        return database.getCollection(collectionName, Artist.class);
    }
}

