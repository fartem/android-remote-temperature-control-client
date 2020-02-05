package com.smlnskgmail.jaman.remotetemperaturecontrol.logic.deviceselector.recycler

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.smlnskgmail.jaman.remotetemperaturecontrol.logic.monitor.api.entities.BtDevice
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
