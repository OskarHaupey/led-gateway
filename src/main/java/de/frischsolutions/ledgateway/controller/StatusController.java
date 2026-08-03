package de.frischsolutions.ledgateway.controller;

import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

public class StatusController {

    @GetMapping("/api/status")
    public Map<String, String> getStatus() {
        return Map.of("status", "running");
    }
}