package com.smlnskgmail.jaman.remotetemperaturecontrol.monitor.connection

import com.smlnskgmail.jaman.remotetemperaturecontrol.monitor.entities.signaltype.SignalType

object MonitorDataExtractor {

    fun signalType(rawData: String): SignalType {
        return SignalType.fromRawData(rawData)
    }

    fun data(rawData: String): String {
        if (rawData.isEmpty()) {
            return ""
        }
        return rawData.substring(1)
    }

}