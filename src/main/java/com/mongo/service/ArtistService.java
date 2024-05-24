package com.mongo.service;

import com.mongo.domain.Artist;
import com.mongo.repository.ArtistRepository;
import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class ArtistService {

    private final ArtistRepository artistRepository;

    public String create(String name) {
        Artist artist = Artist.builder()
                .name(name)
                .build();

        return artistRepository.save(artist).getId();
    }

    public Artist findById(String id) {
        return artistRepository.findById(id)
                .orElseThrow(NoSuchElementException::new);
    }

    public List<Document> findAllBySearchQuery(String query) {
        return artistRepository.searchArtists(query);
    }

    public List<Artist> findAll() {
        return artistRepository.findAll();
    }

    public void update(String id, String name) {
        Artist artist = findById(id);

        assert artist != null;
        artist.update(name);
    }

    public void delete(String id) {
        artistRepository.deleteById(id);
    }
}
