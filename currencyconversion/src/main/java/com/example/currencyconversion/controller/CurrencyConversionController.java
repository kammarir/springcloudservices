package com.example.currencyconversion.controller;

import com.example.currencyconversion.model.CurrencyConversion;
import com.example.currencyconversion.proxy.CurrencyExchangeProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;

@RestController
public class CurrencyConversionController {

    @Autowired
    private CurrencyExchangeProxy currencyExchangeProxy;

    @GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
    public ResponseEntity<?> calculateCurrency(@PathVariable("from")String from, @PathVariable("to")String to, @PathVariable("quantity") BigDecimal quantity){
        HashMap<String,String> params=new HashMap<>();
        params.put("from",from);
        params.put("to",to);
        return new RestTemplate().getForEntity("http://localhost:8000/ce/currency-exchange/from/{from}/to/{to}", CurrencyConversion.class,params);
       // return new CurrencyConversion(1000,from,to,quantity,BigDecimal.ONE,BigDecimal.ONE,"");
    }

    @GetMapping("/currency-conversion-feign/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion calculateCurrencyFeign(@PathVariable("from")String from, @PathVariable("to")String to, @PathVariable("quantity") BigDecimal quantity){
        return currencyExchangeProxy.calculateCurrency(from,to);
    }
}
