package com.smlnskgmail.jaman.remotetemperaturecontrol.logic.monitor.btmonitor.connection.targets

interface BtConnectTarget {

    fun onBtDeviceSelected(name: String, address: String)

}
