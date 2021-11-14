package com.karinedias.cryptoprice;

import com.karinedias.cryptoprice.api.FeignClient;
import com.karinedias.cryptoprice.model.BitcoinPrice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CryptopriceApplication implements CommandLineRunner {

    @Autowired
    FeignClient client;

    @Value("${api.key}")
    private String API_KEY;

    public static void main(String[] args) {
        SpringApplication.run(CryptopriceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        BitcoinPrice price = client.getDailyBtcPrice("DIGITAL_CURRENCY_DAILY", "ETC", "EUR", API_KEY);
        System.out.println(price.toString());
    }
}
