package com.karinedias.cryptoprice;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.karinedias.cryptoprice.model.BitcoinPrice;
import com.karinedias.cryptoprice.model.CryptoDeserializer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class DeserializeTest {

    @BeforeEach
    void setup() {

    }


    @Test
    void cryptoDeserializer_shouldReturnExpected() throws IOException {

        // Given
        ObjectMapper objectMapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(BitcoinPrice.class, new CryptoDeserializer());
        objectMapper.registerModule(module);

        // Then
        BitcoinPrice expectedPrice = new BitcoinPrice(LocalDate.of(2021, 11, 12), 64831.71);
        BitcoinPrice actualPrice = objectMapper.readValue(DeserializeTest.class.getClassLoader().getResourceAsStream("test.json"), BitcoinPrice.class);

        assertThat(expectedPrice).isEqualTo(actualPrice);

    }

    @Test
    void testmethod() throws IOException {

        String apiUrl = "https://www.alphavantage.co/query?function=DIGITAL_CURRENCY_DAILY&symbol=BTC&market=CNY&apikey=demo";
        InputStream is = readJsonFromURL(apiUrl);

        ObjectMapper objectMapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(BitcoinPrice.class, new CryptoDeserializer());
        objectMapper.registerModule(module);
        BitcoinPrice actualPrice = objectMapper.readValue(is, BitcoinPrice.class);

        System.out.println(actualPrice.toString());

    }

    private static InputStream readJsonFromURL(String url) throws IOException {
        InputStream inputStream = new URL(url).openStream();
        System.out.println(inputStream.toString());
        return inputStream;

    }
}
