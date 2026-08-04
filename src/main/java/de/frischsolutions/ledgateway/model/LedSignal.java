package de.frischsolutions.ledgateway.model;

public enum LedSignal {
    SUCCESS(1),
    ERROR(2),
    WARNING(3),
    RUNNING(4),
    OFF(5);

    private final int presetId;
    LedSignal(int presetId) {
        this.presetId = presetId;
    }

    public int getPresetId() {
        return presetId;
    }
}