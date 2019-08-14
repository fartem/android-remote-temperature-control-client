package com.smlnskgmail.jaman.remotetemperaturecontrol.datasend.data;

import com.smlnskgmail.jaman.remotetemperaturecontrol.monitor.MonitorTarget;
import org.jetbrains.annotations.NotNull;

public class TestMonitorTarget implements MonitorTarget {

    @Override
    public void temperatureAvailable(@NotNull String data) {
        printText(data);
    }

    @Override
    public void temperatureMaximumAvailable(@NotNull String data) {
        printText(data);
    }

    @Override
    public void temperatureMinimumAvailable(@NotNull String data) {
        printText(data);
    }

    @Override
    public void humidityAvailable(@NotNull String data) {
        printText(data);
    }

    @Override
    public void humidityMaximumAvailable(@NotNull String data) {
        printText(data);
    }

    @Override
    public void humidityMinimumAvailable(@NotNull String data) {
        printText(data);
    }

    @Override
    public void resetRequired() {
        printText("");
    }

    private void printText(String text) {
        System.out.print(text);
    }

}
