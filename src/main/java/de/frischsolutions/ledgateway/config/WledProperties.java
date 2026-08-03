package de.frischsolutions.ledgateway.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "wled")

public class WledProperties {
    private String baseUrl;
    private boolean simulation;

    public String getBaseUrl() {
        return baseUrl;
    }
    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }
    public boolean isSimulation() {
        return simulation;
    }
    public void setSimulation(boolean simulation) {
        this.simulation = simulation;
    }
}

// Liest später automatisch die Einstellung für die WLED-IP-Adresse und
// Simulationsmodus aus der Konfigurationsdatei application.yml