package de.frischsolutions.ledgateway.client;

import de.frischsolutions.ledgateway.config.WledProperties;
import de.frischsolutions.ledgateway.model.LedSignal;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
public class WledClient {
    private final WledProperties properties;
    private final HttpClient httpClient;

    public WledClient(WledProperties properties) {
        this.properties = properties;
        this.httpClient = HttpClient.newHttpClient();
    }
    public void sendSignal(LedSignal signal) {
        if (properties.isSimulation()) {
            System.out.println("Simulation: Activate WLED preset " + signal.getPresetId() + "for signal " + signal.name());
            return;
        }

        try {
            String url = properties.getBaseUrl() + "/json/state";
            String jsonBody = "{\"ps\":" + signal.getPresetId() + "}";
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Content-Type", " application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                    .build();
            httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            System.err.println("Fehler beim Senden an WLED: " + e.getMessage());
        }
    }
}
