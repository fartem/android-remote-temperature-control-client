package com.smlnskgmail.jaman.remotetemperaturecontrol.monitor.entities.signaltype

enum class SignalType {

    Temperature,
    TemperatureMinimum,
    TemperatureMaximum,
    Humidity,
    HumidityMinimum,
    HumidityMaximum,
    Reset,
    Other;

    companion object {

        fun fromRawData(rawData: String): SignalType {
            return if (rawData.isNotEmpty()) {
                when (rawData[0].toString()) {
                    "t" -> Temperature
                    "i" -> TemperatureMinimum
                    "m" -> TemperatureMaximum
                    "h" -> Humidity
                    "q" -> HumidityMinimum
                    "w" -> HumidityMaximum
                    "r" -> Reset
                    else -> Other
                }
            } else {
                Other
            }
        }

        fun signalOf(signalType: SignalType): String {
            return when (signalType) {
                Temperature -> "t"
                TemperatureMinimum -> "i"
                TemperatureMaximum -> "m"
                Humidity -> "h"
                HumidityMinimum -> "q"
                HumidityMaximum -> "w"
                Reset -> "r"
                else -> ""
            }
        }

    }

}