package com.smlnskgmail.jaman.remotetemperaturecontrol.api.fakemonitor

import com.smlnskgmail.jaman.remotetemperaturecontrol.logic.monitor.api.BtMonitorTarget

class FakeMonitorHandleTarget : BtMonitorTarget {

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
