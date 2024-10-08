package com.graphqltest.lab2.controllers;

import com.graphqltest.lab2.repository.DogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class DogController {
    @Autowired
    private DogRepository dogRepository;

    @GetMapping("/getbreeds")
    public ResponseEntity<List<String>> getBreeds() {
        List<String> breeds = new ArrayList<>();

        dogRepository.findAll().forEach(dog -> breeds.add(dog.getBreed()));

        return new ResponseEntity<>(breeds, HttpStatus.OK);
    }
}
