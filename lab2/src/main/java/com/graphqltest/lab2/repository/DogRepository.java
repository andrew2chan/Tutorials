package com.graphqltest.lab2.repository;

import com.graphqltest.lab2.model.Dog;
import org.springframework.data.repository.CrudRepository;

public interface DogRepository extends CrudRepository<Dog, Long> {

}
