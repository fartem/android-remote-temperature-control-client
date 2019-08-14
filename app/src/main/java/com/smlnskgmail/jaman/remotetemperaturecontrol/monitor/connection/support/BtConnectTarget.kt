package com.smlnskgmail.jaman.remotetemperaturecontrol.monitor.connection.support

interface BtConnectTarget {

    fun onDeviceSelected(name: String, address: String)

}