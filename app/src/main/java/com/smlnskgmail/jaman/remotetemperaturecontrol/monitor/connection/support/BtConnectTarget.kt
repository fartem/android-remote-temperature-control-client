package com.smlnskgmail.jaman.remotetemperaturecontrol.monitor.connection.support

interface BtConnectTarget {

    fun onBtDeviceSelected(name: String, address: String)

}