package com.smlnskgmail.jaman.remotetemperaturecontrol.logic.deviceselector

import com.smlnskgmail.jaman.remotetemperaturecontrol.R
import com.smlnskgmail.jaman.remotetemperaturecontrol.components.BaseBottomSheet
import com.smlnskgmail.jaman.remotetemperaturecontrol.logic.deviceselector.recycler.BtDevicesAdapter
import com.smlnskgmail.jaman.remotetemperaturecontrol.logic.monitor.api.entities.BtDevice
import com.smlnskgmail.jaman.remotetemperaturecontrol.logic.monitor.api.entities.targets.BtConnectTarget
import kotlinx.android.synthetic.main.bottom_sheet_bt_devices.*

class BtDevicesBottomSheet : BaseBottomSheet() {

    private var btConnectTarget: BtConnectTarget? = null
    private val btDevices = mutableListOf<BtDevice>()

    override fun initialize() {
        devices.adapter = BtDevicesAdapter(
            btDevices
        )
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

    fun setBtDeviceSelectCallback(
        btConnectTarget: BtConnectTarget
    ) {
        this.btConnectTarget = btConnectTarget
    }

    override fun getLayoutResId() = R.layout.bottom_sheet_bt_devices

}
