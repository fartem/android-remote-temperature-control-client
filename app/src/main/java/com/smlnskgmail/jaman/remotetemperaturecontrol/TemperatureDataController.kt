package com.smlnskgmail.jaman.remotetemperaturecontrol

import android.annotation.SuppressLint
import android.app.Activity
import android.view.View
import android.widget.TextView
import com.smlnskgmail.jaman.remotetemperaturecontrol.entities.signal.SignalCallback
import com.smlnskgmail.jaman.remotetemperaturecontrol.entities.signal.SignalType
import kotlinx.android.synthetic.main.activity_main.view.*

class TemperatureDataController(

    private val activity: Activity,
    private val viewWithAdoptedElements: View

) : SignalCallback {

    override fun onDataAvailable(signalType: SignalType, data: String) {
        activity.runOnUiThread {
            when (signalType) {
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
                    resetData()
                }
            }
        }
    }

    private fun setTemperature(value: String) {
        setTemperatureValue(viewWithAdoptedElements.tv_temperature_value, value)
    }

    private fun setTemperatureMaximum(value: String) {
        setTemperatureValue(viewWithAdoptedElements.tv_temperature_max_value, value)
    }

    private fun setTemperatureMinimum(value: String) {
        setTemperatureValue(viewWithAdoptedElements.tv_temperature_min_value, value)
    }

    private fun setTemperatureValue(textView: TextView, value: String) {
        setValue(textView, value, "C")
    }

    private fun setHumidity(value: String) {
        setHumidityValue(viewWithAdoptedElements.tv_humidity_value, value)
    }

    private fun setHumidityMaximum(value: String) {
        setHumidityValue(viewWithAdoptedElements.tv_humidity_max_value, value)
    }

    private fun setHumidityMinimum(value: String) {
        setHumidityValue(viewWithAdoptedElements.tv_humidity_min_value, value)
    }

    private fun setHumidityValue(textView: TextView, value: String) {
        setValue(textView, value, "%")
    }

    @SuppressLint("SetTextI18n")
    private fun setValue(textView: TextView, value: String, parameter: String) {
        textView.text = "$value $parameter"
    }

    private fun resetData() {
        for (textView in arrayListOf(viewWithAdoptedElements.tv_temperature_value,
            viewWithAdoptedElements.tv_temperature_max_value,
            viewWithAdoptedElements.tv_temperature_min_value,
            viewWithAdoptedElements.tv_humidity_value,
            viewWithAdoptedElements.tv_humidity_max_value,
            viewWithAdoptedElements.tv_humidity_min_value)) {
            textView.text = activity.getString(R.string.message_empty_data)
        }
    }

}