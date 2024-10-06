package com.example.graphqltest.controllers;

import com.example.graphqltest.services.DogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DogController {
    private DogService dogService;

    public DogController(DogService dogService) {
        this.dogService = dogService;
    }

    @GetMapping("/allbreeds")
    public ResponseEntity<List<String>> getAllBreeds() {
        return new ResponseEntity<>(dogService.retrieveDogBreeds(), HttpStatus.OK);
    }

    @GetMapping("/breedById/{id}")
    public ResponseEntity<String> getBreedById(@PathVariable Long id) {
        String breed = dogService.retrieveDogBreedById(id);

        return breed != null ? new ResponseEntity<>(breed, HttpStatus.OK) : new ResponseEntity<>("Dog id not found", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/names")
    public ResponseEntity<List<String>> getAllNames() {
        return new ResponseEntity<>(dogService.retrieveDogNames(), HttpStatus.OK);
    }
}
