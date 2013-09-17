package com.pduda.timeexpert;

public class Main {

    public static void main(String[] args) {
        new Main().startTimeExpert();
    }

    private void startTimeExpert() {
        final TimeExpertServer timeExpert = new TimeExpertServer(new SystemClock());
        try {
            timeExpert.start();
            timeExpert.join();
        } finally {
            timeExpert.stop();
        }
    }
}
