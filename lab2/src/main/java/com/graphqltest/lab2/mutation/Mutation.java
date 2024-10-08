package com.graphqltest.lab2.mutation;

import com.graphqltest.lab2.exceptions.DogNotFoundException;
import com.graphqltest.lab2.model.Dog;
import com.graphqltest.lab2.repository.DogRepository;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Component
public class Mutation implements GraphQLMutationResolver {
    private DogRepository dogRepository;

    public Mutation(DogRepository dogRepository) {
        this.dogRepository = dogRepository;
    }

    public Boolean deleteDogBreed(String breed) {
        List<Dog> dogs = StreamSupport.stream(dogRepository.findAll().spliterator(), false).filter(dog -> dog.getBreed().equals(breed)).toList();

        for(Dog dog : dogs) {
            dogRepository.delete(dog);
        }

        return true;
    }

    public Dog updateDogName(String name, Long id) {
        Optional<Dog> dog = dogRepository.findById(id);

        if(dog.isPresent()) {
            Dog d = dog.get();
            d.setName(name);
            dogRepository.save(d);
            return d;
        }
        else {
            throw new DogNotFoundException("Dog id not found", id);
        }
    }
}
