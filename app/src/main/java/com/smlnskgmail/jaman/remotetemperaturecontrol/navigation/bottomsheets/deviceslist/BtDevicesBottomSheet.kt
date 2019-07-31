package com.smlnskgmail.jaman.remotetemperaturecontrol.navigation.bottomsheets.deviceslist

import android.bluetooth.BluetoothAdapter
import com.smlnskgmail.jaman.remotetemperaturecontrol.R
import com.smlnskgmail.jaman.remotetemperaturecontrol.entities.bluetooth.BluetoothDevice
import com.smlnskgmail.jaman.remotetemperaturecontrol.navigation.bottomsheets.BaseBottomSheet
import com.smlnskgmail.jaman.remotetemperaturecontrol.navigation.bottomsheets.deviceslist.list.DevicesAdapter
import kotlinx.android.synthetic.main.bottom_sheet_bluetooth_devices.*

class BtDevicesBottomSheet : BaseBottomSheet() {

    private var bluetoothDeviceSelectListener: OnConnectionSetup? = null

    private var bluetoothAdapter: BluetoothAdapter? = null

    override fun initialize() {
        devices.adapter =
            DevicesAdapter(
                getBluetoothDevices()
            )
        select_device.setOnClickListener {
            val adapter = (devices.adapter as DevicesAdapter)
            bluetoothDeviceSelectListener?.onConnectionSetup(
                adapter.getSelectedDeviceName(), adapter.getSelectedDeviceMacAddress()
            )
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

    fun setBluetoothDeviceSelectCallback(bluetoothDeviceSelectListener: OnConnectionSetup) {
        this.bluetoothDeviceSelectListener = bluetoothDeviceSelectListener
    }

    override fun getLayoutResId() = R.layout.bottom_sheet_bluetooth_devices

}