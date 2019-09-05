package com.smlnskgmail.jaman.remotetemperaturecontrol.navigation.fragments.monitor

import android.bluetooth.BluetoothAdapter
import android.content.DialogInterface
import android.view.View
import android.widget.TextView
import com.smlnskgmail.jaman.remotetemperaturecontrol.R
import com.smlnskgmail.jaman.remotetemperaturecontrol.monitor.Monitor
import com.smlnskgmail.jaman.remotetemperaturecontrol.monitor.connection.MonitorBtConnection
import com.smlnskgmail.jaman.remotetemperaturecontrol.monitor.connection.support.BtConnectTarget
import com.smlnskgmail.jaman.remotetemperaturecontrol.monitor.connection.support.BtDisconnectTarget
import com.smlnskgmail.jaman.remotetemperaturecontrol.monitor.entities.BtDevice
import com.smlnskgmail.jaman.remotetemperaturecontrol.monitor.entities.signaltype.SignalType
import com.smlnskgmail.jaman.remotetemperaturecontrol.monitor.support.MonitorTarget
import com.smlnskgmail.jaman.remotetemperaturecontrol.navigation.bottomsheets.btdevices.BtDevicesBottomSheet
import com.smlnskgmail.jaman.remotetemperaturecontrol.navigation.bottomsheets.settings.SettingsBottomSheet
import com.smlnskgmail.jaman.remotetemperaturecontrol.navigation.dialogs.AlertDialog
import com.smlnskgmail.jaman.remotetemperaturecontrol.navigation.fragments.BaseFragment
import kotlinx.android.synthetic.main.fragment_monitor.*

class MonitorFragment : BaseFragment(), MonitorTarget, BtConnectTarget, BtDisconnectTarget {

    private lateinit var monitorBtConnection: MonitorBtConnection
    private lateinit var btAdapter: BluetoothAdapter

    private lateinit var monitor: Monitor

    override fun initializeFragment(view: View) {
        startMonitor()
    }

    private fun startMonitor() {
        if (btIsEnabled()) {
            btAdapter = BluetoothAdapter.getDefaultAdapter()
            val btDevices = getBtDevices()
            if (btDevices.isNotEmpty()) {
                monitor = Monitor(this)
                showDevicesList(btDevices)
            } else {
                showBtDevicesNotFoundWarning()
            }
        } else {
            showBtDisabledWarning()
        }
    }

    private fun btIsEnabled() = true

    private fun showDevicesList(btDevices: List<BtDevice>) {
        val devicesBottomSheet = BtDevicesBottomSheet()
        devicesBottomSheet.setBtDevices(btDevices)
        devicesBottomSheet.setBtDeviceSelectCallback(this)
        devicesBottomSheet.isCancelable = false
        showBottomSheet(devicesBottomSheet)
    }

    private fun getBtDevices(): List<BtDevice> {
        val bondedDevices= btAdapter.bondedDevices
        if (bondedDevices.isNotEmpty()) {
            val devices = arrayListOf<BtDevice>()
            for (bondedDevice in bondedDevices) {
                devices.add(BtDevice(bondedDevice.name, bondedDevice.address)
                )
            }
            return devices
        }
        return emptyList()
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

    override fun resetRequired() {
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

    override fun onBtDeviceSelected(name: String, address: String) {
        monitorBtConnection = MonitorBtConnection(btAdapter, address, monitor)
        monitorBtConnection.connect()
        monitorBtConnection.start()
        setDeviceName(name)
        initializeButtons()
    }

    private fun setDeviceName(name: String) {
        tv_connected_device_info.text = name
    }

    private fun showBtDisabledWarning() {
        AlertDialog.show(context!!, R.string.title_warning, R.string.message_bt_is_disabled,
            R.string.action_exit, DialogInterface.OnClickListener { _, _ ->
                activity!!.finish()
            })
    }

    private fun showBtDevicesNotFoundWarning() {
        AlertDialog.show(context!!, R.string.title_warning, R.string.message_no_available_devices,
            R.string.action_exit, DialogInterface.OnClickListener { _, _ ->
                activity!!.finish()
            })
    }

    private fun initializeButtons() {
        btn_reset_monitor.setOnClickListener {
            monitorBtConnection.send(SignalType.Reset)
        }
        btn_main_options.setOnClickListener {
            showSettings()
        }
    }

    private fun showSettings() {
        val settingsBottomSheet = SettingsBottomSheet()
        settingsBottomSheet.setBtDisconnectListener(this)
        showBottomSheet(settingsBottomSheet)
    }

    override fun btDisconnect() {
        monitorBtConnection.disconnect()
        resetRequired()
        startMonitor()
    }

    override fun fragmentPause() {
        monitorBtConnection.handleOnResume()
    }

    override fun getLayoutResId() = R.layout.fragment_monitor

}