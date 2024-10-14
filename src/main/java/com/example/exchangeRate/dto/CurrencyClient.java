package com.example.exchangeRate.dto;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "currencyClient", url = "https://twelvedata.com/")
public interface CurrencyClient
{
    @GetMapping("/time_series")
    CurrencyRateResponse getCurrencyRate(@RequestParam("symbol") String symbol,
                                         @RequestParam("interval") String interval,
                                         @RequestParam("apikey") String apiKey);
}
