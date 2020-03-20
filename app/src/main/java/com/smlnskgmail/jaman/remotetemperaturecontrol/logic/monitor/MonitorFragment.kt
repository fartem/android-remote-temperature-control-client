package com.smlnskgmail.jaman.remotetemperaturecontrol.logic.monitor

import android.annotation.SuppressLint
import android.bluetooth.BluetoothAdapter
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.smlnskgmail.jaman.remotetemperaturecontrol.BuildConfig
import com.smlnskgmail.jaman.remotetemperaturecontrol.R
import com.smlnskgmail.jaman.remotetemperaturecontrol.components.AppDialog
import com.smlnskgmail.jaman.remotetemperaturecontrol.components.BaseFragment
import com.smlnskgmail.jaman.remotetemperaturecontrol.logic.deviceselector.BtDevicesBottomSheet
import com.smlnskgmail.jaman.remotetemperaturecontrol.logic.monitor.api.BtConnection
import com.smlnskgmail.jaman.remotetemperaturecontrol.logic.monitor.api.BtMonitor
import com.smlnskgmail.jaman.remotetemperaturecontrol.logic.monitor.api.BtMonitorSignalType
import com.smlnskgmail.jaman.remotetemperaturecontrol.logic.monitor.api.BtMonitorTarget
import com.smlnskgmail.jaman.remotetemperaturecontrol.logic.monitor.api.entities.BtDevice
import com.smlnskgmail.jaman.remotetemperaturecontrol.logic.monitor.api.entities.targets.BtConnectTarget
import com.smlnskgmail.jaman.remotetemperaturecontrol.logic.monitor.api.entities.targets.BtDisconnectTarget
import com.smlnskgmail.jaman.remotetemperaturecontrol.logic.monitor.impl.debugbt.DebugBtConnection
import com.smlnskgmail.jaman.remotetemperaturecontrol.logic.monitor.impl.debugbt.DebugBtMonitor
import com.smlnskgmail.jaman.remotetemperaturecontrol.logic.monitor.impl.devicebt.DeviceBtConnection
import com.smlnskgmail.jaman.remotetemperaturecontrol.logic.monitor.impl.devicebt.DeviceBtMonitor
import com.smlnskgmail.jaman.remotetemperaturecontrol.logic.settings.SettingsBottomSheet
import kotlinx.android.synthetic.main.fragment_monitor.*

@SuppressWarnings("TooManyFunctions")
class MonitorFragment : BaseFragment(), BtMonitorTarget, BtDisconnectTarget {

    private var monitorBtConnection: BtConnection? = null
    private var btAdapter: BluetoothAdapter? = null

    private var btMonitor: BtMonitor? = null

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        btAdapter = BluetoothAdapter.getDefaultAdapter()
        if (bluetoothIsEnabled()) {
            @Suppress("ConstantConditionIf")
            if (BuildConfig.API_IMPL == "DEVICE_BT") {
                val btDevices = getBtDevices()
                if (btDevices.isNotEmpty()) {
                    btMonitor = DeviceBtMonitor(this)
                    showDevicesList(btDevices)
                } else {
                    showBtDevicesNotFoundWarning()
                }
            } else {
                startInDebugMode()
                initializeButtons()
            }
        } else {
            showBluetoothErrorDialog()
        }
    }

    private fun bluetoothIsEnabled(): Boolean {
        return if (btAdapter != null) {
            btAdapter!!.isEnabled
        } else {
            false
        }
    }

    private fun showBluetoothErrorDialog() {
        AppDialog.show(
            context!!,
            R.string.bluetooth_error_dialog_title,
            R.string.bluetooth_error_dialog_message,
            R.string.bluetooth_error_dialog_button_text,
            DialogInterface.OnClickListener { dialog, _ ->
                dialog.cancel()
                activity!!.finish()
            }
        )
    }

    @SuppressLint("SetTextI18n")
    private fun startInDebugMode() {
        btMonitor = DebugBtMonitor(this)
        monitorBtConnection = DebugBtConnection(btMonitor!!)
        startMonitorThread()

        tv_connected_device_info.text = "DEBUG"
    }

    private fun startMonitorThread() {
        object : Thread() {
            override fun run() {
                super.run()
                monitorBtConnection!!.connect()
            }
        }.start()
    }

    private fun showDevicesList(btDevices: List<BtDevice>) {
        val devicesBottomSheet = BtDevicesBottomSheet()
        devicesBottomSheet.setBtDevices(btDevices)
        devicesBottomSheet.setBtDeviceSelectCallback(object : BtConnectTarget {
            override fun onBtDeviceSelected(
                name: String,
                address: String
            ) {
                monitorBtConnection = DeviceBtConnection(
                    btAdapter!!,
                    address,
                    btMonitor!!
                )
                startMonitorThread()
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
            BtDevice(
                it.name,
                it.address
            )
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

    private fun setTextOnUIThread(
        textView: TextView,
        text: String
    ) {
        activity!!.runOnUiThread {
            textView.text = text
        }
    }

    private fun setDeviceName(name: String) {
        tv_connected_device_info.text = name
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
            monitorBtConnection!!.send(BtMonitorSignalType.Reset)
        }
        btn_main_options.setOnClickListener {
            showSettings()
        }
    }

    private fun showSettings() {
        val settingsBottomSheet = SettingsBottomSheet()
        showBottomSheet(settingsBottomSheet)
    }
    override fun btDisconnect() {
        monitorBtConnection!!.disconnect()
        resetRequired()
    }

    override fun btOnPause() {
        monitorBtConnection!!.handleOnResume()
    }

    override fun getLayoutResId() = R.layout.fragment_monitor

}
