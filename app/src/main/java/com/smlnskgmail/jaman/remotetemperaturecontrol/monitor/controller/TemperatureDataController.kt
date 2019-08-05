package com.smlnskgmail.jaman.remotetemperaturecontrol.monitor.controller

import android.annotation.SuppressLint
import com.smlnskgmail.jaman.remotetemperaturecontrol.entities.signaltype.SignalTarget
import com.smlnskgmail.jaman.remotetemperaturecontrol.entities.signaltype.SignalType

class TemperatureDataController(private val controllerTarget: ControllerTarget) : SignalTarget {

    override fun onDataAvailable(signalType: SignalType, data: String) {
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
                controllerTarget.needReset()
            }
        }
    }

    private fun setTemperature(data: String) {
        controllerTarget.temperatureAvailable(getTemperatureResult(data))
    }

    private fun setTemperatureMaximum(data: String) {
        controllerTarget.temperatureMaximumAvailable(getTemperatureResult(data))
    }

    private fun setTemperatureMinimum(data: String) {
        controllerTarget.temperatureMinimumAvailable(getTemperatureResult(data))
    }

    private fun getTemperatureResult(data: String) = getFormattedResult(data, "C")

    private fun setHumidity(data: String) {
        controllerTarget.humidityAvailable(getHumidityResult(data))
    }

    private fun setHumidityMaximum(data: String) {
        controllerTarget.humidityMaximumAvailable(getHumidityResult(data))
    }

    private fun setHumidityMinimum(data: String) {
        controllerTarget.humidityMinimumAvailable(getHumidityResult(data))
    }

    private fun getHumidityResult(data: String) = getFormattedResult(data, "%")

    @SuppressLint("SetTextI18n")
    private fun getFormattedResult(data: String, measure: String) = "$data $measure"

}