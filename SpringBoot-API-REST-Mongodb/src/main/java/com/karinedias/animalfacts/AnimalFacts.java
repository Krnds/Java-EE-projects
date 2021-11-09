package com.karinedias.animalfacts;

import com.karinedias.animalfacts.controller.ApiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AnimalFacts {

    @Autowired
	ApiClient apiClient;

    public static void main(String[] args) {
        SpringApplication.run(AnimalFacts.class, args);
    }

}
