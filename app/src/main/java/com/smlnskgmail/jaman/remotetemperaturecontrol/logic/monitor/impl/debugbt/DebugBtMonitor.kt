package com.smlnskgmail.jaman.remotetemperaturecontrol.logic.monitor.impl.debugbt

import com.smlnskgmail.jaman.remotetemperaturecontrol.logic.monitor.api.BtMonitor
import com.smlnskgmail.jaman.remotetemperaturecontrol.logic.monitor.api.BtMonitorSignalType
import com.smlnskgmail.jaman.remotetemperaturecontrol.logic.monitor.api.BtMonitorTarget

class DebugBtMonitor(
    private val btMonitorTarget: BtMonitorTarget
) : BtMonitor {

    override fun onNewDataAvailable(
        btMonitorSignalType: BtMonitorSignalType,
        rawData: String
    ) {
        @Suppress("UseCheckOrError")
        when (btMonitorSignalType) {
            BtMonitorSignalType.Temperature -> {
                btMonitorTarget.temperatureAvailable(
                    rawData
                )
            }
            BtMonitorSignalType.TemperatureMaximum -> {
                btMonitorTarget.temperatureMaximumAvailable(
                    rawData
                )
            }
            BtMonitorSignalType.TemperatureMinimum -> {
                btMonitorTarget.temperatureMinimumAvailable(
                    rawData
                )
            }
            BtMonitorSignalType.Humidity -> {
                btMonitorTarget.humidityAvailable(
                    rawData
                )
            }
            BtMonitorSignalType.HumidityMaximum -> {
                btMonitorTarget.humidityMaximumAvailable(
                    rawData
                )
            }
            BtMonitorSignalType.HumidityMinimum -> {
                btMonitorTarget.humidityMinimumAvailable(
                    rawData
                )
            }
            else -> throw IllegalStateException(
                "DebugMonitor cannot support $btMonitorSignalType!"
            )
        }
    }

}
