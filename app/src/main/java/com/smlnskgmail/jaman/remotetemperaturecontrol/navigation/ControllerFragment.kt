package com.smlnskgmail.jaman.remotetemperaturecontrol.navigation

import android.bluetooth.BluetoothAdapter
import android.view.View
import android.widget.TextView
import com.smlnskgmail.jaman.remotetemperaturecontrol.R
import com.smlnskgmail.jaman.remotetemperaturecontrol.components.bottomsheets.deviceslist.BtDevicesBottomSheet
import com.smlnskgmail.jaman.remotetemperaturecontrol.components.bottomsheets.deviceslist.OnConnectionSetup
import com.smlnskgmail.jaman.remotetemperaturecontrol.components.bottomsheets.settings.OnDisconnectListener
import com.smlnskgmail.jaman.remotetemperaturecontrol.components.bottomsheets.settings.SettingsBottomSheet
import com.smlnskgmail.jaman.remotetemperaturecontrol.entities.signal.SignalType
import com.smlnskgmail.jaman.remotetemperaturecontrol.monitor.MonitorBluetoothConnection
import com.smlnskgmail.jaman.remotetemperaturecontrol.monitor.controller.ControllerSupport
import com.smlnskgmail.jaman.remotetemperaturecontrol.monitor.controller.TemperatureDataController
import kotlinx.android.synthetic.main.fragment_contriller.*

class ControllerFragment : BaseFragment(), ControllerSupport,
    OnConnectionSetup, OnDisconnectListener {

    private lateinit var monitorBluetoothConnection: MonitorBluetoothConnection
    private lateinit var bluetoothAdapter: BluetoothAdapter

    private lateinit var temperatureDataController: TemperatureDataController

    override fun initializeFragment(view: View) {
        temperatureDataController = TemperatureDataController(this)
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()

        showDevicesList()
    }

    private fun showDevicesList() {
        val devicesBottomSheet = BtDevicesBottomSheet()
        devicesBottomSheet.setBluetoothAdapter(bluetoothAdapter)
        devicesBottomSheet.setBluetoothDeviceSelectCallback(this)
        devicesBottomSheet.isCancelable = false
        showBottomSheet(devicesBottomSheet)
    }

    override fun newTemperature(result: String) {
        setTextOnUIThread(tv_temperature_value, result)
    }

    override fun newTemperatureMaximum(result: String) {
        setTextOnUIThread(tv_temperature_max_value, result)
    }

    override fun newTemperatureMinimum(result: String) {
        setTextOnUIThread(tv_temperature_min_value, result)
    }

    override fun newHumidity(result: String) {
        setTextOnUIThread(tv_humidity_value, result)
    }

    override fun newHumidityMaximum(result: String) {
        setTextOnUIThread(tv_humidity_max_value, result)
    }

    override fun newHumidityMinimum(result: String) {
        setTextOnUIThread(tv_humidity_min_value, result)
    }

    override fun reset() {

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
            val settingsBottomSheet = SettingsBottomSheet()
            settingsBottomSheet.setBluetoothDisconnectListener(this)
            showBottomSheet(settingsBottomSheet)
        }
    }

    override fun onConnectionClosed() {
        monitorBluetoothConnection.disconnect()
    }

    override fun getLayoutResId() = R.layout.fragment_contriller

}