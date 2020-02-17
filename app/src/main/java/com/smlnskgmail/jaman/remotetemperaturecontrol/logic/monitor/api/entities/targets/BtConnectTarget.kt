package com.smlnskgmail.jaman.remotetemperaturecontrol.logic.monitor.api.entities.targets

interface BtConnectTarget {

    fun onBtDeviceSelected(
        name: String,
        address: String
    )

}
