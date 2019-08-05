package com.smlnskgmail.jaman.remotetemperaturecontrol.monitor.controller

interface ControllerTarget {

    fun temperatureAvailable(data: String)
    fun temperatureMaximumAvailable(data: String)
    fun temperatureMinimumAvailable(data: String)
    fun humidityAvailable(data: String)
    fun humidityMaximumAvailable(data: String)
    fun humidityMinimumAvailable(data: String)
    fun needReset()

}