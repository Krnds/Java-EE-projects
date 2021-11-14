package com.karinedias.cryptoprice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.ToString;
import lombok.Value;

import java.time.LocalDate;

@Value
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class BitcoinPrice {

    LocalDate date;
    Double price;
}
