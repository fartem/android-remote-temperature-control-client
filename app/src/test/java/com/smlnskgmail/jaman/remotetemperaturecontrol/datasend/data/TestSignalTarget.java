package com.smlnskgmail.jaman.remotetemperaturecontrol.datasend.data;

import com.smlnskgmail.jaman.remotetemperaturecontrol.monitor.MonitorTarget;
import com.smlnskgmail.jaman.remotetemperaturecontrol.monitor.entities.signaltype.SignalTarget;
import com.smlnskgmail.jaman.remotetemperaturecontrol.monitor.entities.signaltype.SignalType;
import org.jetbrains.annotations.NotNull;

public class TestSignalTarget implements SignalTarget {

    private MonitorTarget monitorTarget;

    public TestSignalTarget(MonitorTarget monitorTarget) {
        this.monitorTarget = monitorTarget;
    }

    @Override
    public void onNewDataAvailable(@NotNull SignalType signalType, @NotNull String data) {
        switch (signalType) {
            case Temperature:
                monitorTarget.temperatureAvailable(data);
                break;
            case TemperatureMinimum:
                monitorTarget.temperatureMinimumAvailable(data);
                break;
            case TemperatureMaximum:
                monitorTarget.temperatureMaximumAvailable(data);
                break;
            case Humidity:
                monitorTarget.humidityAvailable(data);
                break;
            case HumidityMinimum:
                monitorTarget.humidityMinimumAvailable(data);
                break;
            case HumidityMaximum:
                monitorTarget.humidityMaximumAvailable(data);
                break;
            case Reset:
                monitorTarget.resetRequired();
                break;
        }
    }

}
