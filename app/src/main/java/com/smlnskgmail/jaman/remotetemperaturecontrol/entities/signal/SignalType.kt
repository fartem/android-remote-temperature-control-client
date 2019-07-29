package com.smlnskgmail.jaman.remotetemperaturecontrol.entities.signal

enum class SignalType(val signal: String) {

    Temperature("t"),
    TemperatureMaximum("m"),
    TemperatureMinimum("i"),
    Humidity("h"),
    HumidityMaximum("w"),
    HumidityMinimum("q"),
    Reset("r"),
    Nothing("");

}