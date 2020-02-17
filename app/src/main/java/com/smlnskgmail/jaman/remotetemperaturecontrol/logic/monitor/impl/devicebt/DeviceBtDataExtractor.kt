package com.smlnskgmail.jaman.remotetemperaturecontrol.logic.monitor.impl.devicebt

import com.smlnskgmail.jaman.remotetemperaturecontrol.logic.monitor.api.BtMonitorSignalType

object DeviceBtDataExtractor {

    fun signalType(rawData: String): BtMonitorSignalType {
        if (rawData.isNotEmpty()) {
            return fromRawData(rawData)
        }
        return BtMonitorSignalType.Other
    }

    fun data(rawData: String): String {
        if (rawData.isEmpty()) {
            return ""
        }
        return rawData.substring(1)
    }

    private fun fromRawData(rawData: String): BtMonitorSignalType {
        return when (rawData[0].toString()) {
            "t" -> BtMonitorSignalType.Temperature
            "i" -> BtMonitorSignalType.TemperatureMinimum
            "m" -> BtMonitorSignalType.TemperatureMaximum
            "h" -> BtMonitorSignalType.Humidity
            "q" -> BtMonitorSignalType.HumidityMinimum
            "w" -> BtMonitorSignalType.HumidityMaximum
            "r" -> BtMonitorSignalType.Reset
            else -> BtMonitorSignalType.Other
        }
    }

    fun signalOf(
        btMonitorSignalType: BtMonitorSignalType
    ): String {
        return when (btMonitorSignalType) {
            BtMonitorSignalType.Temperature -> "t"
            BtMonitorSignalType.TemperatureMinimum -> "i"
            BtMonitorSignalType.TemperatureMaximum -> "m"
            BtMonitorSignalType.Humidity -> "h"
            BtMonitorSignalType.HumidityMinimum -> "q"
            BtMonitorSignalType.HumidityMaximum -> "w"
            BtMonitorSignalType.Reset -> "r"
            else -> ""
        }
    }

}
