package com.mongo.repository.impl;

import com.mongo.repository.ArtistQueryRepository;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class ArtistQueryRepositoryImpl implements ArtistQueryRepository {

    @Value("${mongo.collection-name}")
    private String collectionName;

    @Value("${mongo.search-index}")
    private String searchIndex;

    private final MongoDatabase mongoDatabase;

    @Override
    public List<Document> searchArtists(String query) {
        MongoCollection<Document> artistCollection = mongoDatabase.getCollection(collectionName);
        List<Document> results = new ArrayList<>();

        artistCollection.aggregate(List.of(
                new Document("$search", new Document()
                        .append("index", searchIndex)
                        .append("compound", new Document()
                                .append("should", List.of(
                                        new Document("text", new Document()
                                                .append("query", query)
                                                .append("path", "name")
                                                .append("score", new Document("constant", new Document("value", 10)))
                                        )
                                ))
                                .append("minimumShouldMatch", 1)
                        )
                ),
                new Document("$project", new Document()
                        .append("_id", 0)
                        .append("name", 1)
                        .append("score", new Document("$meta", "searchScore"))
                )
        )).forEach(results::add);

        return results;
    }
}
