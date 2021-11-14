package com.karinedias.cryptoprice;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.karinedias.cryptoprice.api.FeignClient;
import com.karinedias.cryptoprice.model.BitcoinPrice;
import com.karinedias.cryptoprice.model.CryptoDeserializer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.http.HttpResponse;
import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DeserializeTest {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Mock
    FeignClient client;

    @Value("${api.key}")
    String API_KEY;

    @BeforeEach
    void setup() {
        SimpleModule module = new SimpleModule();
        module.addDeserializer(BitcoinPrice.class, new CryptoDeserializer());
        this.objectMapper.registerModule(module);
    }


    @Test
    @DisplayName("Testing deserializer with json file")
    void withJsonFile_cryptoDeserializer_shouldReturnExpectedValue() throws IOException {
        // Given
        BitcoinPrice expectedPrice = new BitcoinPrice(LocalDate.of(2021, 11, 12), 414384.840807);
        BitcoinPrice actualPrice = this.objectMapper.readValue(DeserializeTest.class.getClassLoader().getResourceAsStream("test.json"), BitcoinPrice.class);
        // Then
        assertThat(expectedPrice).isEqualTo(actualPrice);
    }

    @Test
    @DisplayName("Testing deserializer with json from the API response")
    void withAPIUrl_cryptoDeserializer_shouldReturnExpectedValue() throws IOException {
        // When
        String apiUrl = "https://www.alphavantage.co/query?function=DIGITAL_CURRENCY_DAILY&symbol=BTC&market=CNY&apikey=demo";
        InputStream is = readJsonFromURL(apiUrl);
        BitcoinPrice actualPrice = this.objectMapper.readValue(is, BitcoinPrice.class);
        BitcoinPrice expectedPrice = new BitcoinPrice(LocalDate.now(), 414964.89760000);
        // Then
        assertThat(expectedPrice).isEqualTo(actualPrice);
    }

    private static InputStream readJsonFromURL(String url) throws IOException {
        InputStream inputStream = new URL(url).openStream();
        return inputStream;
    }

    @Test
    @DisplayName("Testing deserializer when currency is modified")
    void whenCurrencyIsModified_cryptoDeserializer_shouldReturnExpectedValue() {

        BitcoinPrice expectedPrice;
        // Given
        //BitcoinPrice actualPrice = this.client.getDailyBtcPrice("", "EUR", "BTC", API_KEY);
       when(client.getDailyBtcPrice(anyString(), anyString(), anyString(), anyString())).thenReturn(new BitcoinPrice(LocalDate.now(), 0.0));

        // TODO: mocking the web server and the response to test the API Client
        // TODO: ne pas mocker le client, il faut le tester. Il faut mocker la réponse attendue.
        HttpResponse httpResponse = mock(HttpResponse.class);

    }
}
