package com.amigoscode.bughunt.easy.bug09;

public class TemperatureTracker {

    private int min = Integer.MAX_VALUE;
    private int max = Integer.MIN_VALUE;

    public void record(int temperature) {
        if (temperature < min) {
            min = temperature;
        }
        if (temperature > max) {
            max = temperature;
        }
    }

    public int min() {
        return min;
    }

    public int max() {
        return max;
    }
}
