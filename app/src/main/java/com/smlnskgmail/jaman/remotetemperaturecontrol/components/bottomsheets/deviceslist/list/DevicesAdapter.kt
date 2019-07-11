package com.smlnskgmail.jaman.remotetemperaturecontrol.components.bottomsheets.deviceslist.list

import android.bluetooth.BluetoothDevice
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smlnskgmail.jaman.remotetemperaturecontrol.R

class DevicesAdapter(private val bluetoothDevices: List<BluetoothDevice>)
    : RecyclerView.Adapter<DeviceHolder>() {

    override fun onBindViewHolder(holder: DeviceHolder, position: Int) {
        holder.bind(bluetoothDevices[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            = DeviceHolder(LayoutInflater.from(parent.context)
        .inflate(R.layout.item_bluetooth_device, parent, false))

    override fun getItemCount() = bluetoothDevices.size

}