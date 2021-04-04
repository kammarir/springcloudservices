package com.example.currencyexchange.controller;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CircuitBreakerController {

    private Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class);
    private static int count;
    //@CircuitBreaker(name = "default",fallbackMethod = "defaultResponse")
   //@Retry(name = "sample-api",fallbackMethod = "defaultResponse")
   // @RateLimiter(name = "default")
    @Bulkhead(name="default")
    @GetMapping("/sample-api")
    public String sampleAPI(){
        logger.info("Sample API Call Received");
//        ResponseEntity<String> responseEntity = new RestTemplate().getForEntity("http://localhost:8080/sample-test",String.class);
//        return responseEntity.getBody();
         count++;
        return "Sample API"+count;
    }
    public String defaultResponse(Exception exception){
        return "Fallback Response";
    }
}
