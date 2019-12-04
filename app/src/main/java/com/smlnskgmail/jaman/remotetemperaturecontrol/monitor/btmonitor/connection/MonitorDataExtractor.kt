package com.smlnskgmail.jaman.remotetemperaturecontrol.monitor.btmonitor.connection

import com.smlnskgmail.jaman.remotetemperaturecontrol.monitor.MonitorSignalType

object MonitorDataExtractor {

    fun signalType(rawData: String): MonitorSignalType {
        return MonitorSignalType.fromRawData(rawData)
    }

    fun data(rawData: String): String {
        if (rawData.isEmpty()) {
            return ""
        }
        return rawData.substring(1)
    }

}