package com.smlnskgmail.jaman.remotetemperaturecontrol.logic.monitor.btmonitor.connection.entities

data class BtDevice(
    val name: String,
    val address: String,
    var isSelected: Boolean = false
)
