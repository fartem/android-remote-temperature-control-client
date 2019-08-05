package com.smlnskgmail.jaman.remotetemperaturecontrol.navigation.fragments

import android.bluetooth.BluetoothAdapter
import android.view.View
import android.widget.TextView
import com.smlnskgmail.jaman.remotetemperaturecontrol.R
import com.smlnskgmail.jaman.remotetemperaturecontrol.entities.signaltype.SignalType
import com.smlnskgmail.jaman.remotetemperaturecontrol.monitor.MonitorBluetoothConnection
import com.smlnskgmail.jaman.remotetemperaturecontrol.monitor.controller.ControllerTarget
import com.smlnskgmail.jaman.remotetemperaturecontrol.monitor.controller.TemperatureDataController
import com.smlnskgmail.jaman.remotetemperaturecontrol.navigation.bottomsheets.deviceslist.BluetoothDevicesBottomSheet
import com.smlnskgmail.jaman.remotetemperaturecontrol.navigation.bottomsheets.deviceslist.ConnectionSetupTarget
import com.smlnskgmail.jaman.remotetemperaturecontrol.navigation.bottomsheets.settings.BluetoothDisconnectTarget
import com.smlnskgmail.jaman.remotetemperaturecontrol.navigation.bottomsheets.settings.SettingsBottomSheet
import kotlinx.android.synthetic.main.fragment_contriller.*

class ControllerFragment : BaseFragment(), ControllerTarget, ConnectionSetupTarget,
    BluetoothDisconnectTarget {

    private lateinit var monitorBluetoothConnection: MonitorBluetoothConnection
    private lateinit var bluetoothAdapter: BluetoothAdapter

    private lateinit var temperatureDataController: TemperatureDataController

    override fun initializeFragment(view: View) {
        temperatureDataController = TemperatureDataController(this)
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
        showDevicesList()
    }

    private fun showDevicesList() {
        val devicesBottomSheet = BluetoothDevicesBottomSheet()
        devicesBottomSheet.setBluetoothAdapter(bluetoothAdapter)
        devicesBottomSheet.setBluetoothDeviceSelectCallback(this)
        devicesBottomSheet.isCancelable = false
        showBottomSheet(devicesBottomSheet)
    }

    override fun temperatureAvailable(data: String) {
        setTextOnUIThread(tv_temperature_value, data)
    }

    override fun temperatureMaximumAvailable(data: String) {
        setTextOnUIThread(tv_temperature_max_value, data)
    }

    override fun temperatureMinimumAvailable(data: String) {
        setTextOnUIThread(tv_temperature_min_value, data)
    }

    override fun humidityAvailable(data: String) {
        setTextOnUIThread(tv_humidity_value, data)
    }

    override fun humidityMaximumAvailable(data: String) {
        setTextOnUIThread(tv_humidity_max_value, data)
    }

    override fun humidityMinimumAvailable(data: String) {
        setTextOnUIThread(tv_humidity_min_value, data)
    }

    override fun needReset() {
        activity!!.runOnUiThread {
            val valueViews = listOf<TextView>(
                tv_temperature_value,
                tv_temperature_max_value,
                tv_temperature_min_value,
                tv_humidity_value,
                tv_humidity_max_value,
                tv_humidity_min_value
            )
            val defaultText = getString(R.string.default_parameter_value)
            for (textView in valueViews) {
                textView.text = defaultText
            }
        }
    }

    private fun setTextOnUIThread(textView: TextView, text: String) {
        activity!!.runOnUiThread {
            textView.text = text
        }
    }

    override fun onConnectionSetup(name: String, address: String) {
        monitorBluetoothConnection = MonitorBluetoothConnection(bluetoothAdapter,
            address, temperatureDataController)
        monitorBluetoothConnection.connect()
        monitorBluetoothConnection.start()
        setDeviceName(name)
        initializeButtons()
    }

    private fun setDeviceName(name: String) {
        tv_connected_device_info.text = name
    }

    private fun initializeButtons() {
        btn_reset_monitor.setOnClickListener {
            monitorBluetoothConnection.send(SignalType.Reset)
        }
        btn_main_options.setOnClickListener {
            showSettings()
        }
    }

    private fun showSettings() {
        val settingsBottomSheet = SettingsBottomSheet()
        settingsBottomSheet.setBluetoothDisconnectListener(this)
        showBottomSheet(settingsBottomSheet)
    }

    override fun disconnect() {
        monitorBluetoothConnection.disconnect()
        needReset()
        showDevicesList()
    }

    override fun getLayoutResId() = R.layout.fragment_contriller

}