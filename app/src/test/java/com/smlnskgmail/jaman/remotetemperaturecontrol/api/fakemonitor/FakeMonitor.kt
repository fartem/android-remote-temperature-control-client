package com.smlnskgmail.jaman.remotetemperaturecontrol.api.fakemonitor

import com.smlnskgmail.jaman.remotetemperaturecontrol.logic.monitor.api.BtMonitor
import com.smlnskgmail.jaman.remotetemperaturecontrol.logic.monitor.api.BtMonitorSignalType
import com.smlnskgmail.jaman.remotetemperaturecontrol.logic.monitor.api.BtMonitorTarget

class FakeMonitor(private val btMonitorTarget: BtMonitorTarget) :
    BtMonitor {

    override fun onNewDataAvailable(
        btMonitorSignalType: BtMonitorSignalType,
        rawData: String
    ) {
        when (btMonitorSignalType) {
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
