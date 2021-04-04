package com.example.currencyconversion.proxy;

import com.example.currencyconversion.model.CurrencyConversion;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;

@FeignClient(name="currency-exchange-service")
public interface CurrencyExchangeProxy {

    @GetMapping("/ce/currency-exchange/from/{from}/to/{to}")
    public CurrencyConversion calculateCurrency(@PathVariable("from")String from, @PathVariable("to")String to);
}
