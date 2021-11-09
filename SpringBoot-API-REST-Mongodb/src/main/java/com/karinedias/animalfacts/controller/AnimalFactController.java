package com.karinedias.animalfacts.controller;


import com.karinedias.animalfacts.model.AnimalFact;
import com.karinedias.animalfacts.repository.AnimalFactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AnimalFactController {

    @Autowired
    private AnimalFactRepository animalFactRepository;
    @Autowired
    private ApiClient apiClient;

    @GetMapping(path = "/getAll")
    public List<AnimalFact> getAll() {
        return animalFactRepository.findAll();
    }

    @GetMapping(path = "/add")
    public String saveFact() {
        AnimalFact a = apiClient.getCatFact("dog");
        System.out.println(a.toString());
        animalFactRepository.save(a);
        return "Added fact !";
    }

    @PostMapping(path = "/add/{amount}")
    public String saveFactsFromAPI(@PathVariable("amount") String amount) {
        Iterable<AnimalFact> fetchedFacts = apiClient.getCatFacts(Integer.parseInt(amount));
        animalFactRepository.saveAll(fetchedFacts);
        return "All the fetched cat facts were added to the database!";
    }

}
