package com.smlnskgmail.jaman.remotetemperaturecontrol.monitor

import android.annotation.SuppressLint
import com.smlnskgmail.jaman.remotetemperaturecontrol.monitor.entities.signaltype.SignalTarget
import com.smlnskgmail.jaman.remotetemperaturecontrol.monitor.entities.signaltype.SignalType

class Monitor(private val monitorTarget: MonitorTarget) : SignalTarget {

    override fun onNewDataAvailable(signalType: SignalType, data: String) {
        when(signalType) {
            SignalType.Temperature -> {
                setTemperature(data)
            }
            SignalType.TemperatureMaximum -> {
                setTemperatureMaximum(data)
            }
            SignalType.TemperatureMinimum -> {
                setTemperatureMinimum(data)
            }
            SignalType.Humidity -> {
                setHumidity(data)
            }
            SignalType.HumidityMaximum -> {
                setHumidityMaximum(data)
            }
            SignalType.HumidityMinimum -> {
                setHumidityMinimum(data)
            }
            else -> {
                monitorTarget.resetRequired()
            }
        }
    }

    private fun setTemperature(data: String) {
        monitorTarget.temperatureAvailable(getTemperatureResult(data))
    }

    private fun setTemperatureMaximum(data: String) {
        monitorTarget.temperatureMaximumAvailable(getTemperatureResult(data))
    }

    private fun setTemperatureMinimum(data: String) {
        monitorTarget.temperatureMinimumAvailable(getTemperatureResult(data))
    }

    private fun getTemperatureResult(data: String) = getFormattedResult(data, "C")

    private fun setHumidity(data: String) {
        monitorTarget.humidityAvailable(getHumidityResult(data))
    }

    private fun setHumidityMaximum(data: String) {
        monitorTarget.humidityMaximumAvailable(getHumidityResult(data))
    }

    private fun setHumidityMinimum(data: String) {
        monitorTarget.humidityMinimumAvailable(getHumidityResult(data))
    }

    private fun getHumidityResult(data: String) = getFormattedResult(data, "%")

    @SuppressLint("SetTextI18n")
    private fun getFormattedResult(data: String, measure: String) = "$data $measure"

}