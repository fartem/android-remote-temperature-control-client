package com.smlnskgmail.jaman.remotetemperaturecontrol

import android.bluetooth.BluetoothAdapter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.smlnskgmail.jaman.remotetemperaturecontrol.components.bottomsheets.deviceslist.BtDevicesBottomSheet
import com.smlnskgmail.jaman.remotetemperaturecontrol.components.listeners.OnBluetoothDeviceSelected
import com.smlnskgmail.jaman.remotetemperaturecontrol.connection.MonitorBluetoothConnection
import com.smlnskgmail.jaman.remotetemperaturecontrol.entities.signal.SignalCallback
import com.smlnskgmail.jaman.remotetemperaturecontrol.entities.signal.SignalType
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),
    SignalCallback, OnBluetoothDeviceSelected {

    private lateinit var monitorBluetoothConnection: MonitorBluetoothConnection

    private lateinit var bluetoothAdapter: BluetoothAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
        showDevicesList()
    }

    private fun showDevicesList() {
        val devicesBottomSheet = BtDevicesBottomSheet()
        devicesBottomSheet.setBluetoothAdapter(bluetoothAdapter)
        devicesBottomSheet.setBluetoothDeviceSelectCallback(this)
        devicesBottomSheet.show(supportFragmentManager, BtDevicesBottomSheet::class.java.name)
    }

    override fun onBluetoothDeviceSelect(address: String) {
        monitorBluetoothConnection = MonitorBluetoothConnection(bluetoothAdapter, address, this)
        monitorBluetoothConnection.connect()
        monitorBluetoothConnection.start()
    }

    override fun onDataAvailable(signalType: SignalType, data: String) {
        runOnUiThread {
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
                SignalType.Reset -> {
                    resetData()
                }
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
