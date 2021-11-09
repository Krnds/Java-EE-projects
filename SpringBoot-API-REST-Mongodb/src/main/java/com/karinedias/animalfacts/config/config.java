package com.karinedias.animalfacts;

import feign.Logger;
import org.springframework.context.annotation.Bean;

public class config {
    public static class FeignConfig {
        @Bean
        Logger.Level feignLoggerLevel() {
            return Logger.Level.FULL;
        }
    }
}
