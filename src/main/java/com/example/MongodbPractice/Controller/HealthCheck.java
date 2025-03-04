package com.example.MongodbPractice.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.SecureRandom;

@RestController
public class HealthCheck {
    @GetMapping("/Healthcheck")
    public String healthcheck(){
        return "OK";
    }
}
