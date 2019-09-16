package com.smlnskgmail.jaman.remotetemperaturecontrol.monitor.connection

import com.smlnskgmail.jaman.remotetemperaturecontrol.monitor.entities.signaltype.SignalType

object DataValidation {

    fun signalType(rawData: String): SignalType {
        return SignalType.fromRawData(rawData[0].toString())
    }

    fun data(rawData: String): String {
        if (rawData.isEmpty()) {
            return ""
        }
        return rawData.substring(1)
    }

}