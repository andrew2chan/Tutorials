package com.test.secondlesson.controllers;

import com.test.secondlesson.exceptions.DogNotFoundException;
import com.test.secondlesson.service.DogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DogController {
    private DogService dogService;

    public DogController(DogService dogService) {
        this.dogService = dogService;
    }

    @GetMapping("/retrieveBreeds")
    public ResponseEntity<List<String>> retrieveDogBreed() {
        return new ResponseEntity<>(dogService.retrieveDogBreed(), HttpStatus.OK);
    }

    @GetMapping("/retrieveById/{id}")
    public ResponseEntity<String> retrieveDogBreedById(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(dogService.retrieveDogBreedById(id), HttpStatus.OK);
        }
        catch(DogNotFoundException ex) {
            return new ResponseEntity<>(ex.getLocalizedMessage(), ex.getClass().getAnnotation(ResponseStatus.class).code());
        }
    }

    @GetMapping("/retrieveNames")
    public ResponseEntity<List<String>> retrieveDogNames() {
        return new ResponseEntity<>(dogService.retrieveDogNames(), HttpStatus.OK);
    }
}
