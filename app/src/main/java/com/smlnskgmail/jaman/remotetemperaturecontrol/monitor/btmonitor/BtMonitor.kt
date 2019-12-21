package com.smlnskgmail.jaman.remotetemperaturecontrol.monitor.btmonitor

import android.annotation.SuppressLint
import com.smlnskgmail.jaman.remotetemperaturecontrol.monitor.Monitor
import com.smlnskgmail.jaman.remotetemperaturecontrol.monitor.MonitorHandleTarget
import com.smlnskgmail.jaman.remotetemperaturecontrol.monitor.MonitorSignalType

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
                setTemperature(rawData)
            }
            MonitorSignalType.TemperatureMaximum -> {
                setTemperatureMaximum(rawData)
            }
            MonitorSignalType.TemperatureMinimum -> {
                setTemperatureMinimum(rawData)
            }
            MonitorSignalType.Humidity -> {
                setHumidity(rawData)
            }
            MonitorSignalType.HumidityMaximum -> {
                setHumidityMaximum(rawData)
            }
            MonitorSignalType.HumidityMinimum -> {
                setHumidityMinimum(rawData)
            }
            MonitorSignalType.Reset -> {
                monitorHandleTarget.resetRequired()
            }
        }
    }

    private fun setTemperature(rawData: String) {
        monitorHandleTarget.temperatureAvailable(
            temperatureResult(rawData)
        )
    }

    private fun setTemperatureMaximum(rawData: String) {
        monitorHandleTarget.temperatureMaximumAvailable(
            temperatureResult(rawData)
        )
    }

    private fun setTemperatureMinimum(rawData: String) {
        monitorHandleTarget.temperatureMinimumAvailable(
            temperatureResult(rawData)
        )
    }

    private fun temperatureResult(rawData: String)
            = formattedResult(rawData, "C")

    private fun setHumidity(rawData: String) {
        monitorHandleTarget.humidityAvailable(
            humidityResult(rawData)
        )
    }

    private fun setHumidityMaximum(rawData: String) {
        monitorHandleTarget.humidityMaximumAvailable(
            humidityResult(rawData)
        )
    }

    private fun setHumidityMinimum(rawData: String) {
        monitorHandleTarget.humidityMinimumAvailable(
            humidityResult(rawData)
        )
    }

    private fun humidityResult(rawData: String)
            = formattedResult(rawData, "%")

    @SuppressLint("SetTextI18n")
    private fun formattedResult(
        rawData: String,
        measure: String
    ) = "$rawData $measure"

}
