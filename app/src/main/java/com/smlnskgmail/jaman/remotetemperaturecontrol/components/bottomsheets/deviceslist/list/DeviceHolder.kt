package com.smlnskgmail.jaman.remotetemperaturecontrol.components.bottomsheets.deviceslist.list

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.smlnskgmail.jaman.remotetemperaturecontrol.entities.BluetoothDevice
import kotlinx.android.synthetic.main.item_bluetooth_device.view.*

class DeviceHolder(itemView: View, private val recyclerAdapter: DevicesAdapter)
    : RecyclerView.ViewHolder(itemView) {

    fun bind(bluetoothDevice: BluetoothDevice) {
        itemView.bt_device_name.text = bluetoothDevice.name
        itemView.setOnClickListener {
            recyclerAdapter.selectDevice(bluetoothDevice)
        }
    }

}