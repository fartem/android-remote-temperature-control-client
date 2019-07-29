package com.smlnskgmail.jaman.remotetemperaturecontrol.components.bottomsheets.deviceslist.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smlnskgmail.jaman.remotetemperaturecontrol.R
import com.smlnskgmail.jaman.remotetemperaturecontrol.entities.bluetooth.BluetoothDevice

class DevicesAdapter(private val bluetoothDevices: List<BluetoothDevice>)
    : RecyclerView.Adapter<DeviceHolder>() {

    private var selectedDeviceMacAddress: String = ""

    override fun onBindViewHolder(holder: DeviceHolder, position: Int) {
        holder.bind(bluetoothDevices[position])
    }

    fun selectDevice(bluetoothDevice: BluetoothDevice) {
        bluetoothDevices.forEach {
            it.isSelected = bluetoothDevice == it
            if (bluetoothDevice.isSelected) {
                selectedDeviceMacAddress = bluetoothDevice.address
            }
        }
        notifyDataSetChanged()
    }

    fun getSelectedDeviceMacAddress() = selectedDeviceMacAddress

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            = DeviceHolder(LayoutInflater.from(parent.context)
        .inflate(R.layout.item_bluetooth_device, parent, false), this)

    override fun getItemCount() = bluetoothDevices.size

}