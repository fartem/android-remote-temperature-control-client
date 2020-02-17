package com.smlnskgmail.jaman.remotetemperaturecontrol.logic.monitor.impl.debugbt

import android.util.Log
import com.smlnskgmail.jaman.remotetemperaturecontrol.logic.monitor.api.BtConnection
import com.smlnskgmail.jaman.remotetemperaturecontrol.logic.monitor.api.BtMonitor
import com.smlnskgmail.jaman.remotetemperaturecontrol.logic.monitor.api.BtMonitorSignalType

class DebugBtConnection(
    private val btMonitor: BtMonitor
) : BtConnection {

    companion object {

        private val temperature = arrayListOf(
            "25.3 C",
            "24.6 C",
            "23.5 C",
            "21.2 C",
            "22.4 C",
            "23.3 C",
            "24.7 C",
            "25.1 C",
            "25.6 C",
            "25.7 C"
        )

        private val humidity = arrayListOf(
            "78.1 %",
            "79.4 %",
            "77.3 %",
            "77.7 %",
            "77.8 %",
            "78.4 %",
            "79.5 %",
            "81.6 %",
            "79.4 %",
            "77.5 %"
        )

    }

    private var loopCounter = 0

    override fun connect() {
        writeLog(
            "Debug Monitor: connect()"
        )
        while (true) {
            @Suppress("MagicNumber")
            Thread.sleep(5_000)
            btMonitor.onNewDataAvailable(
                BtMonitorSignalType.Temperature,
                temperature[loopCounter]
            )
            btMonitor.onNewDataAvailable(
                BtMonitorSignalType.TemperatureMaximum,
                "25.7 C"
            )
            btMonitor.onNewDataAvailable(
                BtMonitorSignalType.TemperatureMinimum,
                "21.2 C"
            )
            btMonitor.onNewDataAvailable(
                BtMonitorSignalType.Humidity,
                humidity[loopCounter]
            )
            btMonitor.onNewDataAvailable(
                BtMonitorSignalType.HumidityMaximum,
                "81.6 %"
            )
            btMonitor.onNewDataAvailable(
                BtMonitorSignalType.HumidityMinimum,
                "77.3 %"
            )
            if (loopCounter < temperature.size - 1) {
                loopCounter++
            } else {
                loopCounter = 0
            }
        }
    }

    private fun writeLog(message: String) {
        Log.i(
            "MONITOR",
            message
        )
    }

    override fun disconnect() {
        writeLog(
            "Debug Monitor: disconnect()"
        )
    }

    override fun handleOnResume() {
        writeLog(
            "Debug Monitor: handleOnResume()"
        )
    }

    override fun send(btMonitorSignalType: BtMonitorSignalType) {
        writeLog(
            "Debug Monitor: send(${btMonitorSignalType})"
        )
    }

}
