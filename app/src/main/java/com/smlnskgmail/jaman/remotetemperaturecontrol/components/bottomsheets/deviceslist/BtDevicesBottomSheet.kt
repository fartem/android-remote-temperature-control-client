package com.smlnskgmail.jaman.remotetemperaturecontrol.components.bottomsheets.deviceslist

import android.bluetooth.BluetoothAdapter
import com.smlnskgmail.jaman.remotetemperaturecontrol.R
import com.smlnskgmail.jaman.remotetemperaturecontrol.components.bottomsheets.BaseBottomSheet
import com.smlnskgmail.jaman.remotetemperaturecontrol.components.bottomsheets.deviceslist.list.DevicesAdapter
import com.smlnskgmail.jaman.remotetemperaturecontrol.entities.bluetooth.BluetoothDevice
import kotlinx.android.synthetic.main.bottom_sheet_bluetooth_devices.*

class BtDevicesBottomSheet : BaseBottomSheet() {

    private var bluetoothDeviceSelectListener: OnBluetoothDeviceSelected? = null

    private var bluetoothAdapter: BluetoothAdapter? = null

    override fun initialize() {
        devices.adapter = DevicesAdapter(getBluetoothDevices())
        select_device.setOnClickListener {
            bluetoothDeviceSelectListener?.onBluetoothDeviceSelect((devices.adapter as DevicesAdapter)
                .getSelectedDeviceMacAddress())
            dismiss()
        }
    }

    private fun getBluetoothDevices() : List<BluetoothDevice> {
        val bondedDevices= bluetoothAdapter!!.bondedDevices
        if (bondedDevices.isNotEmpty()) {
            val devices = arrayListOf<BluetoothDevice>()
            for (bondedDevice in bondedDevices) {
                devices.add(
                    BluetoothDevice(
                        bondedDevice.name,
                        bondedDevice.address
                    )
                )
            }
            return devices
        }
        return emptyList()
    }

    fun setBluetoothAdapter(bluetoothAdapter: BluetoothAdapter) {
        this.bluetoothAdapter = bluetoothAdapter
    }

    fun setBluetoothDeviceSelectCallback(bluetoothDeviceSelectListener: OnBluetoothDeviceSelected) {
        this.bluetoothDeviceSelectListener = bluetoothDeviceSelectListener
    }

    override fun getLayoutResId() = R.layout.bottom_sheet_bluetooth_devices

}