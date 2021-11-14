package com.karinedias.cryptoprice.api;

import com.karinedias.cryptoprice.model.BitcoinPrice;
import org.springframework.beans.factory.annotation.Value;

public class BitcoinAPI implements FeignClient {

    @Value("${api.url}")
    private String API_URL;
    @Value("${api.key}")
    private String API_KEY;

    @Override
    public BitcoinPrice getDailyBtcPrice(String function, String cryptocurrency, String currency, String apiKey) {
        return null;
    }
}
