package com.smlnskgmail.jaman.remotetemperaturecontrol.monitor.btmonitor.connection.targets

interface BtConnectTarget {

    fun onBtDeviceSelected(name: String, address: String)

}
