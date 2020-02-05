package com.smlnskgmail.jaman.remotetemperaturecontrol.logic.monitor.api

enum class BtMonitorSignalType {

    Temperature,
    TemperatureMinimum,
    TemperatureMaximum,
    Humidity,
    HumidityMinimum,
    HumidityMaximum,
    Reset,
    Other;

    companion object {

        fun fromRawData(rawData: String): BtMonitorSignalType {
            return when (rawData[0].toString()) {
                "t" -> Temperature
                "i" -> TemperatureMinimum
                "m" -> TemperatureMaximum
                "h" -> Humidity
                "q" -> HumidityMinimum
                "w" -> HumidityMaximum
                "r" -> Reset
                else -> Other
            }
        }

        fun signalOf(btMonitorSignalType: BtMonitorSignalType): String {
            return when (btMonitorSignalType) {
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
