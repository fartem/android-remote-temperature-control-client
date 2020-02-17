package com.smlnskgmail.jaman.remotetemperaturecontrol.logic.monitor.impl.devicebt

import android.annotation.SuppressLint
import com.smlnskgmail.jaman.remotetemperaturecontrol.logic.monitor.api.BtMonitor
import com.smlnskgmail.jaman.remotetemperaturecontrol.logic.monitor.api.BtMonitorSignalType
import com.smlnskgmail.jaman.remotetemperaturecontrol.logic.monitor.api.BtMonitorTarget

class DeviceBtMonitor(
    private val btMonitorTarget: BtMonitorTarget
) : BtMonitor {

    @Suppress("NON_EXHAUSTIVE_WHEN")
    override fun onNewDataAvailable(
        signalType: BtMonitorSignalType,
        rawData: String
    ) {
        when(signalType) {
            BtMonitorSignalType.Temperature -> {
                btMonitorTarget.temperatureAvailable(
                    temperatureResult(rawData)
                )
            }
            BtMonitorSignalType.TemperatureMaximum -> {
                btMonitorTarget.temperatureMaximumAvailable(
                    temperatureResult(rawData)
                )
            }
            BtMonitorSignalType.TemperatureMinimum -> {
                btMonitorTarget.temperatureMinimumAvailable(
                    temperatureResult(rawData)
                )
            }
            BtMonitorSignalType.Humidity -> {
                btMonitorTarget.humidityAvailable(
                    humidityResult(rawData)
                )
            }
            BtMonitorSignalType.HumidityMaximum -> {
                btMonitorTarget.humidityMaximumAvailable(
                    humidityResult(rawData)
                )
            }
            BtMonitorSignalType.HumidityMinimum -> {
                btMonitorTarget.humidityMinimumAvailable(
                    humidityResult(rawData)
                )
            }
            BtMonitorSignalType.Reset -> {
                btMonitorTarget.resetRequired()
            }
        }
    }

    private fun temperatureResult(rawData: String)
            = formattedResult(rawData, "C")

    private fun humidityResult(rawData: String)
            = formattedResult(rawData, "%")

    @SuppressLint("SetTextI18n")
    private fun formattedResult(
        rawData: String,
        measure: String
    ) = "$rawData $measure"

}
