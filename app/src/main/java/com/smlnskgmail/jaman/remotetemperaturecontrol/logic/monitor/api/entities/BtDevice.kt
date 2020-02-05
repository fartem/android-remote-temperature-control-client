package com.smlnskgmail.jaman.remotetemperaturecontrol.logic.monitor.api.entities

data class BtDevice(
    val name: String,
    val address: String,
    var isSelected: Boolean = false
)
