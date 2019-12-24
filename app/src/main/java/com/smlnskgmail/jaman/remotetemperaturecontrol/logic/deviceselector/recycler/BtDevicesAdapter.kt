package com.smlnskgmail.jaman.remotetemperaturecontrol.logic.deviceselector.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smlnskgmail.jaman.remotetemperaturecontrol.R
import com.smlnskgmail.jaman.remotetemperaturecontrol.logic.monitor.btmonitor.connection.entities.BtDevice

class BtDevicesAdapter(
    private val btDevices: List<BtDevice>
) : RecyclerView.Adapter<BtDeviceHolder>() {

    private var selectedDeviceMacAddress: String = ""
    private var selectedDeviceName: String = ""

    override fun onBindViewHolder(holderBt: BtDeviceHolder, position: Int) {
        holderBt.bind(btDevices[position])
    }

    fun selectDevice(btDevice: BtDevice) {
        btDevices.forEach {
            it.isSelected = btDevice == it
            if (btDevice.isSelected) {
                selectedDeviceName = btDevice.name
                selectedDeviceMacAddress = btDevice.address
            }
        }
        notifyDataSetChanged()
    }

    fun getSelectedDeviceName() = selectedDeviceName

    fun getSelectedDeviceMacAddress() = selectedDeviceMacAddress

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        BtDeviceHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_bt_device, parent, false),
            this
        )

    override fun getItemCount() = btDevices.size

}
