package com.test.secondlesson.repository;

import com.test.secondlesson.model.Dog;
import org.springframework.data.repository.CrudRepository;

public interface DogRepository extends CrudRepository<Dog, Long> {

}
