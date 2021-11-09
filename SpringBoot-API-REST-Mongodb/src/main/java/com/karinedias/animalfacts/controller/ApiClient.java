package com.karinedias.animalfacts.controller;

import com.karinedias.animalfacts.config;
import com.karinedias.animalfacts.model.AnimalFact;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "catfactsclient", url = "https://cat-fact.herokuapp.com", configuration = config.FeignConfig.class)
public interface ApiClient {

    @GetMapping(value = "/facts/random")
    AnimalFact getCatFact(@RequestParam("animal_type") String animal);

    @GetMapping(value = "/facts/random")
    List<AnimalFact> getCatFacts(@RequestParam("amount") int amount);
}
