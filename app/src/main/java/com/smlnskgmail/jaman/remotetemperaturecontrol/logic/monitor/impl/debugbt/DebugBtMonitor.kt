package com.smlnskgmail.jaman.remotetemperaturecontrol.logic.monitor.impl.debugbt

import com.smlnskgmail.jaman.remotetemperaturecontrol.logic.monitor.api.monitor.BtMonitor
import com.smlnskgmail.jaman.remotetemperaturecontrol.logic.monitor.api.monitor.BtMonitorSignalType
import com.smlnskgmail.jaman.remotetemperaturecontrol.logic.monitor.api.monitor.BtMonitorTarget

class DebugBtMonitor(
    private val btMonitorTarget: BtMonitorTarget
) : BtMonitor {

    override fun onNewDataAvailable(
        signalType: BtMonitorSignalType,
        rawData: String
    ) {
        @Suppress("UseCheckOrError")
        when (signalType) {
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
                "DebugMonitor cannot support $signalType!"
            )
        }
    }

}
