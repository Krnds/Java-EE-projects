package com.karinedias.cryptoprice.api;


import com.karinedias.cryptoprice.config.FeignClientConfig;
import com.karinedias.cryptoprice.model.BitcoinPrice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@org.springframework.cloud.openfeign.FeignClient(name="bitcoinclient", url = "${api.url}", configuration = FeignClientConfig.class)
public interface FeignClient {


    @GetMapping(value = "/query")
    BitcoinPrice getDailyBtcPrice(@RequestParam(value = "function", required = false, defaultValue = "DIGITAL_CURRENCY_DAILY") String function,
                                  @RequestParam("symbol") String cryptocurrency,
                                  @RequestParam("market") String currency,
                                  @RequestParam("apikey") String apiKey);


}
