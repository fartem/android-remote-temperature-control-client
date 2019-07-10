package com.smlnskgmail.jaman.remotetemperaturecontrol

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.smlnskgmail.jaman.remotetemperaturecontrol.connections.MonitorBluetoothConnection
import com.smlnskgmail.jaman.remotetemperaturecontrol.data.SignalCallback
import com.smlnskgmail.jaman.remotetemperaturecontrol.data.SignalType
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), SignalCallback {

    private lateinit var monitorBluetoothConnection: MonitorBluetoothConnection

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onDataAvailable(signalType: SignalType, data: String) {
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
            SignalType.HimidityMaximum -> {
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

    private fun setTemperature(value: String) {
        tv_temperature_value.text = value
    }

    private fun setTemperatureMaximum(value: String) {
        tv_temperature_max_value.text = value
    }

    private fun setTemperatureMinimum(value: String) {
        tv_temperature_min_value.text = value
    }

    private fun setHumidity(value: String) {
        tv_humidity_value.text = value
    }

    private fun setHumidityMaximum(value: String) {
        tv_humidity_max_value.text = value
    }

    private fun setHumidityMinimum(value: String) {
        tv_humidity_min_value.text = value
    }

    private fun resetData() {
        for (textView in arrayListOf(tv_temperature_value, tv_temperature_max_value,
            tv_temperature_min_value, tv_humidity_value, tv_humidity_max_value,
            tv_humidity_min_value)) {
            textView.text = getString(R.string.message_empty_data)
        }
    }

}
