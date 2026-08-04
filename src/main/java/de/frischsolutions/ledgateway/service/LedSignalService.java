package de.frischsolutions.ledgateway.service;

import de.frischsolutions.ledgateway.client.WledClient;
import de.frischsolutions.ledgateway.model.LedSignal;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
public class LedSignalService {

    private final WledClient wledClient;
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    private final List<Map<String, String>> history = new ArrayList<>();

    public LedSignalService(WledClient wledClient) {
        this.wledClient = wledClient;
    }
    public void triggerSignal(LedSignal signal, Integer durationSeconds) {
        wledClient.sendSignal(signal);

        synchronized (history) {
            history.add(0,Map.of("signal", signal.name(), "timestamp", LocalDateTime.now().toString()));
        }
        //Timer für Automatische zurücksetzun:
        if (durationSeconds != null && durationSeconds > 0) {
            scheduler.schedule(() -> {
                System.out.println("Timer abgelaufen: Schalten automatisch auf OFF zurück.");
                wledClient.sendSignal(LedSignal.OFF);
                }, durationSeconds, TimeUnit.SECONDS);
            }
        }
            public List<Map<String, String>> getHistory() {
                synchronized (history) {
                    return new ArrayList<>(history);
            }
    }
}