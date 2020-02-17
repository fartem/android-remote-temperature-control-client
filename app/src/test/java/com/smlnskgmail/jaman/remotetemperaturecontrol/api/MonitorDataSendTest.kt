package com.smlnskgmail.jaman.remotetemperaturecontrol.api

import com.smlnskgmail.jaman.remotetemperaturecontrol.api.fakemonitor.FakeMonitor
import com.smlnskgmail.jaman.remotetemperaturecontrol.api.fakemonitor.FakeMonitorHandleTarget
import com.smlnskgmail.jaman.remotetemperaturecontrol.logic.monitor.api.BtMonitorSignalType
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class MonitorDataSendTest {

    private val monitorTarget = FakeMonitorHandleTarget()
    private val signalTarget = FakeMonitor(
        monitorTarget
    )

    private val outputStream = ByteArrayOutputStream()

    @Before
    fun initializeOutput() {
        System.setOut(
            PrintStream(
                outputStream
            )
        )
    }

    @Test
    fun validateTemperatureSend() {
        val validResult = "34.5"

        sendNewData(
            BtMonitorSignalType.Temperature,
            validResult
        )
        checkOutput(validResult)
    }

    @Test
    fun validateTemperatureMinimumSend() {
        val validResult = "29.1"

        sendNewData(
            BtMonitorSignalType.TemperatureMinimum,
            validResult
        )
        checkOutput(validResult)
    }

    @Test
    fun validateTemperatureMaximumSend() {
        val validResult = "35.9"

        sendNewData(
            BtMonitorSignalType.TemperatureMaximum,
            validResult
        )
        checkOutput(validResult)
    }

    @Test
    fun validateHumiditySend() {
        val validResult = "74.2"

        sendNewData(
            BtMonitorSignalType.Humidity,
            validResult
        )
        checkOutput(validResult)
    }

    @Test
    fun validateHumidityMinimumSend() {
        val validResult = "54.6"

        sendNewData(
            BtMonitorSignalType.HumidityMinimum,
            validResult
        )
        checkOutput(validResult)
    }

    @Test
    fun validateHumidityMaximumSend() {
        val validResult = "89.3"

        sendNewData(
            BtMonitorSignalType.HumidityMaximum,
            validResult
        )
        checkOutput(validResult)
    }

    @Test
    fun validateResetRequiredSend() {
        val validResult = ""

        sendNewData(
            BtMonitorSignalType.Reset,
            validResult
        )
        checkOutput(validResult)
    }

    private fun sendNewData(
        btMonitorSignalType: BtMonitorSignalType,
        data: String
    ) {
        signalTarget.onNewDataAvailable(
            btMonitorSignalType,
            data
        )
    }

    private fun checkOutput(validOutput: String) {
        assertEquals(
            validOutput,
            outputStream.toString()
        )
    }

}
