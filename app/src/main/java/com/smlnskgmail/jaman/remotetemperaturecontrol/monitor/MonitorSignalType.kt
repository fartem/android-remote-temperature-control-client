package com.smlnskgmail.jaman.remotetemperaturecontrol.monitor

enum class MonitorSignalType {

    Temperature,
    TemperatureMinimum,
    TemperatureMaximum,
    Humidity,
    HumidityMinimum,
    HumidityMaximum,
    Reset,
    Other;

    companion object {

        fun fromRawData(rawData: String): MonitorSignalType {
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

        fun signalOf(monitorSignalType: MonitorSignalType): String {
            return when (monitorSignalType) {
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
