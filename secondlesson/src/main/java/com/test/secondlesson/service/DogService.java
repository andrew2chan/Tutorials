package com.test.secondlesson.service;

import com.test.secondlesson.exceptions.DogNotFoundException;
import com.test.secondlesson.model.Dog;
import com.test.secondlesson.repository.DogRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
public class DogService {
    private DogRepository dogRepository;

    public DogService(DogRepository dogRepository) {
        this.dogRepository = dogRepository;
    }

    public List<String> retrieveDogBreed() {
        return StreamSupport.stream(dogRepository.findAll().spliterator(), false)
                .map(Dog::getBreed)
                .toList();
    }

    public String retrieveDogBreedById(Long id) {
        Optional<Dog> dogFound = dogRepository.findById(id);

        if(dogFound.isPresent()) {
            return dogFound.get().getBreed();
        }
        else {
            throw new DogNotFoundException("Dog id not found");
        }
    }

    public List<String> retrieveDogNames() {
        return StreamSupport.stream(dogRepository.findAll().spliterator(), false)
                .map(Dog::getName)
                .toList();
    }
}
