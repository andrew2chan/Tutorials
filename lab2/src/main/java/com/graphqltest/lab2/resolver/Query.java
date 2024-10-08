package com.graphqltest.lab2.resolver;

import com.graphqltest.lab2.exceptions.DogNotFoundException;
import com.graphqltest.lab2.model.Dog;
import com.graphqltest.lab2.repository.DogRepository;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class Query implements GraphQLQueryResolver {
    private DogRepository dogRepository;

    public Query(DogRepository dogRepository) {
        this.dogRepository = dogRepository;
    }

    public Iterable<Dog> findDogBreeds() {
        return dogRepository.findAll();
    }

    public String findDogBreedById(Long id) {
        Optional<Dog> dog = dogRepository.findById(id);

        if(dog.isPresent()) {
            String breed = dog.get().getBreed();
            return breed;
        }
        else {
            throw new DogNotFoundException("Dog id not found", id);
        }
    }

    public Iterable<Dog> findAllDogNames() {
        return dogRepository.findAll();
    }
}
