package com.smlnskgmail.jaman.remotetemperaturecontrol.monitor.controller

interface ControllerSupport {

    fun newTemperature(result: String)
    fun newTemperatureMaximum(result: String)
    fun newTemperatureMinimum(result: String)
    fun newHumidity(result: String)
    fun newHumidityMaximum(result: String)
    fun newHumidityMinimum(result: String)
    fun reset()

}