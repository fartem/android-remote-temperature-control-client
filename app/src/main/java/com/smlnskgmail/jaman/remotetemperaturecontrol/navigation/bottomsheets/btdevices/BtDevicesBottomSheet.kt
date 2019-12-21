package com.smlnskgmail.jaman.remotetemperaturecontrol.navigation.bottomsheets.btdevices

import com.smlnskgmail.jaman.remotetemperaturecontrol.R
import com.smlnskgmail.jaman.remotetemperaturecontrol.monitor.btmonitor.connection.entities.BtDevice
import com.smlnskgmail.jaman.remotetemperaturecontrol.monitor.btmonitor.connection.targets.BtConnectTarget
import com.smlnskgmail.jaman.remotetemperaturecontrol.navigation.bottomsheets.BaseBottomSheet
import com.smlnskgmail.jaman.remotetemperaturecontrol.navigation.bottomsheets.btdevices.list.BtDevicesAdapter
import kotlinx.android.synthetic.main.bottom_sheet_bt_devices.*

class BtDevicesBottomSheet : BaseBottomSheet() {

    private var btConnectTarget: BtConnectTarget? = null
    private val btDevices = mutableListOf<BtDevice>()

    override fun initialize() {
        devices.adapter = BtDevicesAdapter(btDevices)
        select_device.setOnClickListener {
            val adapter = devices.adapter as BtDevicesAdapter
            btConnectTarget?.onBtDeviceSelected(
                adapter.getSelectedDeviceName(),
                adapter.getSelectedDeviceMacAddress()
            )
            dismiss()
        }
    }

    fun setBtDevices(btDevices: List<BtDevice>) {
        this.btDevices.clear()
        this.btDevices.addAll(btDevices)
    }

    fun setBtDeviceSelectCallback(btConnectTarget: BtConnectTarget) {
        this.btConnectTarget = btConnectTarget
    }

    override fun getLayoutResId() = R.layout.bottom_sheet_bt_devices

}
