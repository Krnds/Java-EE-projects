package com.karinedias.animalfacts.repository;

import com.karinedias.animalfacts.model.AnimalFact;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface AnimalFactRepository extends MongoRepository<AnimalFact, String> {

}
