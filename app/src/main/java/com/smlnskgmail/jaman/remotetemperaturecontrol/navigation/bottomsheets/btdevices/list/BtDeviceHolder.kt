package com.smlnskgmail.jaman.remotetemperaturecontrol.navigation.bottomsheets.btdevices.list

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.smlnskgmail.jaman.remotetemperaturecontrol.monitor.btmonitor.connection.entities.BtDevice
import kotlinx.android.synthetic.main.item_bt_device.view.*

class BtDeviceHolder(
    itemView: View,
    private val recyclerAdapterBt: BtDevicesAdapter
) : RecyclerView.ViewHolder(itemView) {

    fun bind(btDevice: BtDevice) {
        itemView.bt_device_name.text = btDevice.name
        validateSelectedStatus(btDevice.isSelected)
        itemView.setOnClickListener {
            recyclerAdapterBt.selectDevice(btDevice)
        }
    }

    private fun validateSelectedStatus(isSelected: Boolean) {
        itemView.bt_device_status.visibility = if (isSelected) View.VISIBLE else View.INVISIBLE
    }

}