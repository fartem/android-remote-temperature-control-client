package com.smlnskgmail.jaman.remotetemperaturecontrol.handle.fakemonitor

import com.smlnskgmail.jaman.remotetemperaturecontrol.logic.monitor.Monitor
import com.smlnskgmail.jaman.remotetemperaturecontrol.logic.monitor.MonitorHandleTarget
import com.smlnskgmail.jaman.remotetemperaturecontrol.logic.monitor.MonitorSignalType

class FakeMonitor(private val monitorHandleTarget: MonitorHandleTarget) :
    Monitor {

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
