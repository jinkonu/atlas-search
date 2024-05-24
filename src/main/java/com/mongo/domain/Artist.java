package com.mongo.domain;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Document(collection = "artists")
public class Artist {

    @Id
    private String id;

    private String name;

    public void update(String name) {
        this.name = name;
    }
}
