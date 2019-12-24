package com.smlnskgmail.jaman.remotetemperaturecontrol.logic.monitor.btmonitor

import android.annotation.SuppressLint
import com.smlnskgmail.jaman.remotetemperaturecontrol.logic.monitor.Monitor
import com.smlnskgmail.jaman.remotetemperaturecontrol.logic.monitor.MonitorHandleTarget
import com.smlnskgmail.jaman.remotetemperaturecontrol.logic.monitor.MonitorSignalType

class BtMonitor(
    private val monitorHandleTarget: MonitorHandleTarget
) : Monitor {

    @Suppress("NON_EXHAUSTIVE_WHEN")
    override fun onNewDataAvailable(
        monitorSignalType: MonitorSignalType,
        rawData: String
    ) {
        when(monitorSignalType) {
            MonitorSignalType.Temperature -> {
                monitorHandleTarget.temperatureAvailable(
                    temperatureResult(rawData)
                )
            }
            MonitorSignalType.TemperatureMaximum -> {
                monitorHandleTarget.temperatureMaximumAvailable(
                    temperatureResult(rawData)
                )
            }
            MonitorSignalType.TemperatureMinimum -> {
                monitorHandleTarget.temperatureMinimumAvailable(
                    temperatureResult(rawData)
                )
            }
            MonitorSignalType.Humidity -> {
                monitorHandleTarget.humidityAvailable(
                    humidityResult(rawData)
                )
            }
            MonitorSignalType.HumidityMaximum -> {
                monitorHandleTarget.humidityMaximumAvailable(
                    humidityResult(rawData)
                )
            }
            MonitorSignalType.HumidityMinimum -> {
                monitorHandleTarget.humidityMinimumAvailable(
                    humidityResult(rawData)
                )
            }
            MonitorSignalType.Reset -> {
                monitorHandleTarget.resetRequired()
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
