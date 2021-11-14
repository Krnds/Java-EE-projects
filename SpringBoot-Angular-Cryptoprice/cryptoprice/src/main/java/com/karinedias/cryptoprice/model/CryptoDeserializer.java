package com.karinedias.cryptoprice.model;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.time.LocalDate;

public class CryptoDeserializer extends JsonDeserializer<BitcoinPrice> {

    @Override
    public BitcoinPrice deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        ObjectCodec oc = jp.getCodec();
        JsonNode root = oc.readTree(jp);
        String currency = root.path("Meta Data").findValue("4. Market Code").toPrettyString().replace("\"", "");
        String lastDatetime = root.path("Time Series (Digital Currency Daily)").fieldNames().next().toString();
        Double closedPrice = root.path("Time Series (Digital Currency Daily)").path(lastDatetime).get("4a. close (" + currency + ")" ).asDouble();

        LocalDate localDate = LocalDate.parse(lastDatetime);
        return new BitcoinPrice(localDate, closedPrice);
    }
}
