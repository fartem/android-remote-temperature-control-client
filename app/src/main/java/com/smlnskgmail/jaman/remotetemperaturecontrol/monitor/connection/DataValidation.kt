package com.smlnskgmail.jaman.remotetemperaturecontrol.monitor.connection

import com.smlnskgmail.jaman.remotetemperaturecontrol.monitor.entities.signaltype.SignalType

object DataValidation {

    fun signalType(rawData: String): SignalType {
        if (rawData.isEmpty()) {
            return SignalType.Nothing
        }
        return when (rawData[0].toString()) {
            SignalType.Temperature.signal -> SignalType.Temperature
            SignalType.TemperatureMaximum.signal -> SignalType.TemperatureMaximum
            SignalType.TemperatureMinimum.signal -> SignalType.TemperatureMinimum
            SignalType.Humidity.signal -> SignalType.Humidity
            SignalType.HumidityMaximum.signal -> SignalType.HumidityMaximum
            SignalType.HumidityMinimum.signal -> SignalType.HumidityMinimum
            else -> SignalType.Reset
        }
    }

    fun data(rawData: String): String {
        if (rawData.isEmpty()) {
            return ""
        }
        return rawData.substring(1)
    }

}