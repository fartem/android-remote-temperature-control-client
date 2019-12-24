package com.smlnskgmail.jaman.remotetemperaturecontrol.handle

import com.smlnskgmail.jaman.remotetemperaturecontrol.handle.fakemonitor.FakeMonitor
import com.smlnskgmail.jaman.remotetemperaturecontrol.handle.fakemonitor.FakeMonitorHandleTarget
import com.smlnskgmail.jaman.remotetemperaturecontrol.logic.monitor.MonitorSignalType
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class FakeMonitorDataSendTest {

    private val monitorTarget =
        FakeMonitorHandleTarget()
    private val signalTarget =
        FakeMonitor(
            monitorTarget
        )

    private val outputStream = ByteArrayOutputStream()

    @Before
    fun initializeOutput() {
        System.setOut(PrintStream(outputStream))
    }

    @Test
    fun validateTemperatureSend() {
        val validResult = "34.5"

        sendNewData(MonitorSignalType.Temperature, validResult)
        checkOutput(validResult)
    }

    @Test
    fun validateTemperatureMinimumSend() {
        val validResult = "29.1"

        sendNewData(MonitorSignalType.TemperatureMinimum, validResult)
        checkOutput(validResult)
    }

    @Test
    fun validateTemperatureMaximumSend() {
        val validResult = "35.9"

        sendNewData(MonitorSignalType.TemperatureMaximum, validResult)
        checkOutput(validResult)
    }

    @Test
    fun validateHumiditySend() {
        val validResult = "74.2"

        sendNewData(MonitorSignalType.Humidity, validResult)
        checkOutput(validResult)
    }

    @Test
    fun validateHumidityMinimumSend() {
        val validResult = "54.6"

        sendNewData(MonitorSignalType.HumidityMinimum, validResult)
        checkOutput(validResult)
    }

    @Test
    fun validateHumidityMaximumSend() {
        val validResult = "89.3"

        sendNewData(MonitorSignalType.HumidityMaximum, validResult)
        checkOutput(validResult)
    }

    @Test
    fun validateResetRequiredSend() {
        val validResult = ""

        sendNewData(MonitorSignalType.Reset, validResult)
        checkOutput(validResult)
    }

    private fun sendNewData(monitorSignalType: MonitorSignalType, data: String) {
        signalTarget.onNewDataAvailable(monitorSignalType, data)
    }

    private fun checkOutput(validOutput: String) {
        assertEquals(validOutput, outputStream.toString())
    }

}
