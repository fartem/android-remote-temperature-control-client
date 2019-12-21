package com.smlnskgmail.jaman.remotetemperaturecontrol.monitor.fakemonitor

import com.smlnskgmail.jaman.remotetemperaturecontrol.monitor.Monitor
import com.smlnskgmail.jaman.remotetemperaturecontrol.monitor.MonitorHandleTarget
import com.smlnskgmail.jaman.remotetemperaturecontrol.monitor.MonitorSignalType

class FakeMonitor(private val monitorHandleTarget: MonitorHandleTarget) : Monitor {

    override fun onNewDataAvailable(
        monitorSignalType: MonitorSignalType,
        rawData: String
    ) {
        when (monitorSignalType) {
            MonitorSignalType.Temperature -> {
                monitorHandleTarget.temperatureAvailable(rawData)
            }
            MonitorSignalType.TemperatureMinimum -> {
                monitorHandleTarget.temperatureMinimumAvailable(rawData)
            }
            MonitorSignalType.TemperatureMaximum -> {
                monitorHandleTarget.temperatureMaximumAvailable(rawData)
            }
            MonitorSignalType.Humidity -> {
                monitorHandleTarget.humidityAvailable(rawData)
            }
            MonitorSignalType.HumidityMinimum -> {
                monitorHandleTarget.humidityMinimumAvailable(rawData)
            }
            MonitorSignalType.HumidityMaximum -> {
                monitorHandleTarget.humidityMaximumAvailable(rawData)
            }
            MonitorSignalType.Reset -> {
                monitorHandleTarget.resetRequired()
            }
        }
    }

}
