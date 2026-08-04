package de.frischsolutions.ledgateway.controller;

import de.frischsolutions.ledgateway.model.LedSignal;
import de.frischsolutions.ledgateway.model.LedSignalResponse;
import de.frischsolutions.ledgateway.service.LedSignalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/signals")
public class LedSignalController {

    private final LedSignalService service;

    public LedSignalController(LedSignalService service) {
        this.service = service;
    }
    @PostMapping("/{signal}")
    public ResponseEntity<?> triggerSignal(@PathVariable String signal, @RequestParam(required = false) Integer durationSeconds) {
        try {
            LedSignal ledSignal = LedSignal.valueOf(signal.toUpperCase());
            service.triggerSignal(ledSignal, durationSeconds);

            String msg = "LED signal was triggered";
            if (durationSeconds != null) {
                msg += " for " + durationSeconds + " seconds";
            }

            return ResponseEntity.ok(new LedSignalResponse(ledSignal.name(), "LED signal was triggered"));
        }catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error","Unknown LED signal"));
        }
    }
}
