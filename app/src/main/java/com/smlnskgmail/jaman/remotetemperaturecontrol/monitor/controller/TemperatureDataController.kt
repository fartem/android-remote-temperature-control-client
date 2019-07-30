package com.smlnskgmail.jaman.remotetemperaturecontrol.monitor.controller

import android.annotation.SuppressLint
import com.smlnskgmail.jaman.remotetemperaturecontrol.entities.signal.SignalCallback
import com.smlnskgmail.jaman.remotetemperaturecontrol.entities.signal.SignalType

class TemperatureDataController(

    private val controllerSupport: ControllerSupport

) : SignalCallback {

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
                controllerSupport.reset()
            }
        }
    }

    private fun setTemperature(value: String) {
        controllerSupport.newTemperature(getTemperatureResult(value))
    }

    private fun setTemperatureMaximum(value: String) {
        controllerSupport.newTemperatureMaximum(getTemperatureResult(value))
    }

    private fun setTemperatureMinimum(value: String) {
        controllerSupport.newTemperatureMinimum(getTemperatureResult(value))
    }

    private fun getTemperatureResult(value: String)
            = getFormattedResult(value, "C")

    private fun setHumidity(value: String) {
        controllerSupport.newHumidity(getHumidityResult(value))
    }

    private fun setHumidityMaximum(value: String) {
        controllerSupport.newHumidityMaximum(getHumidityResult(value))
    }

    private fun setHumidityMinimum(value: String) {
        controllerSupport.newHumidityMinimum(getHumidityResult(value))
    }

    private fun getHumidityResult(value: String) = getFormattedResult(value, "%")

    @SuppressLint("SetTextI18n")
    private fun getFormattedResult(value: String, parameter: String) = "$value $parameter"

}