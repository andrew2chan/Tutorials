package com.example.graphqltest.services;

import com.example.graphqltest.model.Dog;
import com.example.graphqltest.repository.DogRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class DogService {
    private final DogRepository dogRepository;

    public DogService(DogRepository dogRepository) {
        this.dogRepository = dogRepository;
    }

    public List<String> retrieveDogBreeds() {
        List<String> breeds = new ArrayList<>();

        dogRepository.findAll().forEach(dog -> breeds.add(dog.getBreed()));

        return breeds;
    }

    public String retrieveDogBreedById(Long id) {
        try {
            Dog dogFound = dogRepository.findById(id).orElseThrow();
            return dogFound.getBreed();
        }
        catch(NoSuchElementException ex) {
            System.out.println("Dog id not found");
            return null;
        }
    }

    public List<String> retrieveDogNames() {
        List<String> names = new ArrayList<>();

        dogRepository.findAll().forEach(dog -> names.add(dog.getName()));

        return names;
    }
}
