package com.smlnskgmail.jaman.remotetemperaturecontrol.navigation.fragments

import android.bluetooth.BluetoothAdapter
import android.content.DialogInterface
import android.view.View
import android.widget.TextView
import com.smlnskgmail.jaman.remotetemperaturecontrol.R
import com.smlnskgmail.jaman.remotetemperaturecontrol.monitor.MonitorHandleTarget
import com.smlnskgmail.jaman.remotetemperaturecontrol.monitor.MonitorSignalType
import com.smlnskgmail.jaman.remotetemperaturecontrol.monitor.btmonitor.BtMonitor
import com.smlnskgmail.jaman.remotetemperaturecontrol.monitor.btmonitor.connection.MonitorBtConnection
import com.smlnskgmail.jaman.remotetemperaturecontrol.monitor.btmonitor.connection.entities.BtDevice
import com.smlnskgmail.jaman.remotetemperaturecontrol.monitor.btmonitor.connection.targets.BtConnectTarget
import com.smlnskgmail.jaman.remotetemperaturecontrol.monitor.btmonitor.connection.targets.BtDisconnectTarget
import com.smlnskgmail.jaman.remotetemperaturecontrol.navigation.bottomsheets.SettingsBottomSheet
import com.smlnskgmail.jaman.remotetemperaturecontrol.navigation.bottomsheets.btdevices.BtDevicesBottomSheet
import com.smlnskgmail.jaman.remotetemperaturecontrol.navigation.dialogs.AppDialog
import kotlinx.android.synthetic.main.fragment_monitor.*

@SuppressWarnings("TooManyFunctions")
class MonitorHandleFragment : BaseFragment(), MonitorHandleTarget {

    private var monitorBtConnection: MonitorBtConnection? = null
    private var btAdapter: BluetoothAdapter? = null

    private var btMonitor: BtMonitor? = null

    override fun initializeFragment(view: View) {
        enableMonitor()
    }

    private fun enableMonitor() {
        if (btIsEnabled()) {
            btAdapter = BluetoothAdapter.getDefaultAdapter()
            val btDevices = getBtDevices()
            if (btDevices.isNotEmpty()) {
                btMonitor = BtMonitor(this)
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
        devicesBottomSheet.setBtDeviceSelectCallback(object : BtConnectTarget {
            override fun onBtDeviceSelected(name: String, address: String) {
                monitorBtConnection = MonitorBtConnection(
                    btAdapter!!,
                    address,
                    btMonitor!!
                )
                monitorBtConnection!!.connect()
                monitorBtConnection!!.start()
                setDeviceName(name)
                initializeButtons()
            }
        })
        devicesBottomSheet.isCancelable = false
        showBottomSheet(devicesBottomSheet)
    }

    private fun getBtDevices(): List<BtDevice> {
        val bondedDevices= btAdapter!!.bondedDevices
        return bondedDevices.mapTo(ArrayList(bondedDevices.size)) {
            BtDevice(it.name, it.address)
        }
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

    private fun setDeviceName(name: String) {
        tv_connected_device_info.text = name
    }

    private fun showBtDisabledWarning() {
        AppDialog.show(
            context!!,
            R.string.title_warning,
            R.string.message_bt_is_disabled,
            R.string.action_exit,
            DialogInterface.OnClickListener { _, _ ->
                activity!!.finish()
            }
        )
    }

    private fun showBtDevicesNotFoundWarning() {
        AppDialog.show(
            context!!,
            R.string.title_warning,
            R.string.message_no_available_devices,
            R.string.action_exit,
            DialogInterface.OnClickListener { _, _ ->
                activity!!.finish()
            }
        )
    }

    private fun initializeButtons() {
        btn_reset_monitor.setOnClickListener {
            monitorBtConnection!!.send(MonitorSignalType.Reset)
        }
        btn_main_options.setOnClickListener {
            showSettings()
        }
    }

    private fun showSettings() {
        val settingsBottomSheet = SettingsBottomSheet()
        settingsBottomSheet.setBtDisconnectListener(object : BtDisconnectTarget {
            override fun btDisconnect() {
                monitorBtConnection!!.disconnect()
                resetRequired()
                enableMonitor()
            }
        })
        showBottomSheet(settingsBottomSheet)
    }

    override fun handleBtInOnPause() {
        monitorBtConnection!!.handleOnResume()
    }

    override fun getLayoutResId() = R.layout.fragment_monitor

}
