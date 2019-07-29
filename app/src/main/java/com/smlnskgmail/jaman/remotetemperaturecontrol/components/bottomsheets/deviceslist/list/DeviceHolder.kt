package com.smlnskgmail.jaman.remotetemperaturecontrol.components.bottomsheets.deviceslist.list

import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.smlnskgmail.jaman.remotetemperaturecontrol.R
import com.smlnskgmail.jaman.remotetemperaturecontrol.entities.bluetooth.BluetoothDevice
import kotlinx.android.synthetic.main.item_bluetooth_device.view.*

class DeviceHolder(itemView: View, private val recyclerAdapter: DevicesAdapter)
    : RecyclerView.ViewHolder(itemView) {

    fun bind(bluetoothDevice: BluetoothDevice) {
        itemView.bt_device_name.text = bluetoothDevice.name
        validateSelectedStatus(bluetoothDevice.isSelected)
        itemView.setOnClickListener {
            recyclerAdapter.selectDevice(bluetoothDevice)
        }
    }

    private fun validateSelectedStatus(isSelected: Boolean) {
        val icon = if (isSelected) {
            ContextCompat.getDrawable(itemView.context, R.drawable.ic_item_selected)
        } else {
            null
        }
        itemView.bt_device_name.setCompoundDrawablesRelativeWithIntrinsicBounds(
            null, null, icon, null
        )
    }

}