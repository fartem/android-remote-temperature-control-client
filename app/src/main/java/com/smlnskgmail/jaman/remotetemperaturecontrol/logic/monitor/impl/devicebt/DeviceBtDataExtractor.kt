package com.smlnskgmail.jaman.remotetemperaturecontrol.logic.monitor.impl.devicebt

import com.smlnskgmail.jaman.remotetemperaturecontrol.logic.monitor.api.BtMonitorSignalType

object DeviceBtDataExtractor {

    fun signalType(rawData: String): BtMonitorSignalType {
        if (rawData.isNotEmpty()) {
            return BtMonitorSignalType.fromRawData(rawData)
        }
        return BtMonitorSignalType.Other
    }

    fun data(rawData: String): String {
        if (rawData.isEmpty()) {
            return ""
        }
        return rawData.substring(1)
    }

}
