package com.smlnskgmail.jaman.remotetemperaturecontrol.monitor

import android.annotation.SuppressLint
import com.smlnskgmail.jaman.remotetemperaturecontrol.monitor.entities.signaltype.SignalTarget
import com.smlnskgmail.jaman.remotetemperaturecontrol.monitor.entities.signaltype.SignalType
import com.smlnskgmail.jaman.remotetemperaturecontrol.monitor.support.MonitorTarget

class Monitor(private val monitorTarget: MonitorTarget) : SignalTarget {

    override fun onNewDataAvailable(signalType: SignalType, data: String) {
        when(signalType) {
            SignalType.Temperature -> setTemperature(data)
            SignalType.TemperatureMaximum -> setTemperatureMaximum(data)
            SignalType.TemperatureMinimum -> setTemperatureMinimum(data)
            SignalType.Humidity -> setHumidity(data)
            SignalType.HumidityMaximum -> setHumidityMaximum(data)
            SignalType.HumidityMinimum -> setHumidityMinimum(data)
            SignalType.Reset -> monitorTarget.resetRequired()
            else -> {}
        }
    }

    private fun setTemperature(data: String) {
        monitorTarget.temperatureAvailable(temperatureResult(data))
    }

    private fun setTemperatureMaximum(data: String) {
        monitorTarget.temperatureMaximumAvailable(temperatureResult(data))
    }

    private fun setTemperatureMinimum(data: String) {
        monitorTarget.temperatureMinimumAvailable(temperatureResult(data))
    }

    private fun temperatureResult(data: String) = formattedResult(data, "C")

    private fun setHumidity(data: String) {
        monitorTarget.humidityAvailable(humidityResult(data))
    }

    private fun setHumidityMaximum(data: String) {
        monitorTarget.humidityMaximumAvailable(humidityResult(data))
    }

    private fun setHumidityMinimum(data: String) {
        monitorTarget.humidityMinimumAvailable(humidityResult(data))
    }

    private fun humidityResult(data: String) = formattedResult(data, "%")

    @SuppressLint("SetTextI18n")
    private fun formattedResult(data: String, measure: String) = "$data $measure"

}