package com.smlnskgmail.jaman.remotetemperaturecontrol.monitor

import android.annotation.SuppressLint
import com.smlnskgmail.jaman.remotetemperaturecontrol.monitor.entities.signaltype.SignalTarget
import com.smlnskgmail.jaman.remotetemperaturecontrol.monitor.entities.signaltype.SignalType
import com.smlnskgmail.jaman.remotetemperaturecontrol.monitor.support.MonitorTarget

class Monitor(private val monitorTarget: MonitorTarget) : SignalTarget {

    override fun onNewDataAvailable(signalType: SignalType, rawData: String) {
        when(signalType) {
            SignalType.Temperature -> setTemperature(rawData)
            SignalType.TemperatureMaximum -> setTemperatureMaximum(rawData)
            SignalType.TemperatureMinimum -> setTemperatureMinimum(rawData)
            SignalType.Humidity -> setHumidity(rawData)
            SignalType.HumidityMaximum -> setHumidityMaximum(rawData)
            SignalType.HumidityMinimum -> setHumidityMinimum(rawData)
            SignalType.Reset -> monitorTarget.resetRequired()
            else -> {}
        }
    }

    private fun setTemperature(rawData: String) {
        monitorTarget.temperatureAvailable(temperatureResult(rawData))
    }

    private fun setTemperatureMaximum(rawData: String) {
        monitorTarget.temperatureMaximumAvailable(temperatureResult(rawData))
    }

    private fun setTemperatureMinimum(rawData: String) {
        monitorTarget.temperatureMinimumAvailable(temperatureResult(rawData))
    }

    private fun temperatureResult(rawData: String) = formattedResult(rawData, "C")

    private fun setHumidity(rawData: String) {
        monitorTarget.humidityAvailable(humidityResult(rawData))
    }

    private fun setHumidityMaximum(rawData: String) {
        monitorTarget.humidityMaximumAvailable(humidityResult(rawData))
    }

    private fun setHumidityMinimum(rawData: String) {
        monitorTarget.humidityMinimumAvailable(humidityResult(rawData))
    }

    private fun humidityResult(rawData: String) = formattedResult(rawData, "%")

    @SuppressLint("SetTextI18n")
    private fun formattedResult(rawData: String, measure: String) = "$rawData $measure"

}