package com.smlnskgmail.jaman.remotetemperaturecontrol.monitor.entities.signaltype

enum class SignalType(val signal: String) {

    Temperature("t"),
    TemperatureMinimum("i"),
    TemperatureMaximum("m"),
    Humidity("h"),
    HumidityMinimum("q"),
    HumidityMaximum("w"),
    Reset("r"),
    Nothing("");

}