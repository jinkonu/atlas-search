package com.mongo.repository;

import com.mongo.domain.Artist;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ArtistRepository extends MongoRepository<Artist, String>, ArtistQueryRepository {

}
