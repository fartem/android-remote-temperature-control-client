package com.smlnskgmail.jaman.remotetemperaturecontrol.api.fakemonitor

import com.smlnskgmail.jaman.remotetemperaturecontrol.logic.monitor.api.monitor.BtMonitor
import com.smlnskgmail.jaman.remotetemperaturecontrol.logic.monitor.api.monitor.BtMonitorSignalType
import com.smlnskgmail.jaman.remotetemperaturecontrol.logic.monitor.api.monitor.BtMonitorTarget

class FakeMonitor(
    private val btMonitorTarget: BtMonitorTarget
) : BtMonitor {

    override fun onNewDataAvailable(
        signalType: BtMonitorSignalType,
        rawData: String
    ) {
        @Suppress("NON_EXHAUSTIVE_WHEN")
        when (signalType) {
            BtMonitorSignalType.Temperature -> {
                btMonitorTarget.temperatureAvailable(rawData)
            }
            BtMonitorSignalType.TemperatureMinimum -> {
                btMonitorTarget.temperatureMinimumAvailable(rawData)
            }
            BtMonitorSignalType.TemperatureMaximum -> {
                btMonitorTarget.temperatureMaximumAvailable(rawData)
            }
            BtMonitorSignalType.Humidity -> {
                btMonitorTarget.humidityAvailable(rawData)
            }
            BtMonitorSignalType.HumidityMinimum -> {
                btMonitorTarget.humidityMinimumAvailable(rawData)
            }
            BtMonitorSignalType.HumidityMaximum -> {
                btMonitorTarget.humidityMaximumAvailable(rawData)
            }
            BtMonitorSignalType.Reset -> {
                btMonitorTarget.resetRequired()
            }
        }
    }

}
