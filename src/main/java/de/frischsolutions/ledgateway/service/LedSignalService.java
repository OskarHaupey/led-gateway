package de.frischsolutions.ledgateway.service;

import de.frischsolutions.ledgateway.client.WledClient;
import de.frischsolutions.ledgateway.model.LedSignal;
import org.springframework.stereotype.Service;

@Service
public class LedSignalService {

    private final WledClient wledClient;
    public LedSignalService(WledClient wledClient) {
        this.wledClient = wledClient;
    }
    public void triggerSignal(LedSignal signal) {
        wledClient.sendSignal(signal);
    }
}