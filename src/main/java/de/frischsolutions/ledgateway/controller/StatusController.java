package de.frischsolutions.ledgateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController   // <-- WICHTIG: Wenn das fehlt, gibt es 404!
public class StatusController {

    @GetMapping("/api/status")
    public Map<String, String> getStatus() {
        return Map.of("status", "running");
    }
}