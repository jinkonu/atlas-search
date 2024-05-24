package com.mongo.repository;

import org.bson.Document;

import java.util.List;

public interface ArtistQueryRepository {
    List<Document> searchArtists(String query);
}
