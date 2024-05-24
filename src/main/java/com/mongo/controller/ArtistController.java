package com.mongo.controller;

import com.mongo.domain.Artist;
import com.mongo.service.ArtistService;
import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/v1/artists")
@RestController
public class ArtistController {

    private final ArtistService artistService;

    @PostMapping
    public String createArtist(
            @RequestParam("name") String name
    ) {
        return artistService.create(name);
    }

    @GetMapping
    public List<Document> getAllArtists(
            @RequestParam("query") String query
    ) {
        return artistService.findAllBySearchQuery(query);
    }

    @GetMapping("/{id}")
    public Artist getArtist(
            @PathVariable("id") String id
    ) {
        return artistService.findById(id);
    }

    @PatchMapping("/{id}")
    public void updateArtist(
            @PathVariable("id") String id,
            @RequestParam("name") String name
    ) {
        artistService.update(id, name);
    }

    @DeleteMapping("/{id}")
    public void deleteArtist(
            @PathVariable("id") String id
    ) {
        artistService.delete(id);
    }
}
