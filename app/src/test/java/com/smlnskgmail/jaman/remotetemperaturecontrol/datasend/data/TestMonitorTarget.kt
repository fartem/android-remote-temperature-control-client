package com.smlnskgmail.jaman.remotetemperaturecontrol.datasend.data

import com.smlnskgmail.jaman.remotetemperaturecontrol.monitor.support.MonitorTarget

class TestMonitorTarget :
    MonitorTarget {

    override fun temperatureAvailable(data: String) {
        printText(data)
    }

    override fun temperatureMaximumAvailable(data: String) {
        printText(data)
    }

    override fun temperatureMinimumAvailable(data: String) {
        printText(data)
    }

    override fun humidityAvailable(data: String) {
        printText(data)
    }

    override fun humidityMaximumAvailable(data: String) {
        printText(data)
    }

    override fun humidityMinimumAvailable(data: String) {
        printText(data)
    }

    override fun resetRequired() {
        printText("")
    }

    private fun printText(text: String) {
        print(text)
    }

}
