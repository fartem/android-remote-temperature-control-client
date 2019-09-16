package com.smlnskgmail.jaman.remotetemperaturecontrol.datasend

import com.smlnskgmail.jaman.remotetemperaturecontrol.datasend.data.FakeMonitorTarget
import com.smlnskgmail.jaman.remotetemperaturecontrol.datasend.data.FakeSignalTarget
import com.smlnskgmail.jaman.remotetemperaturecontrol.monitor.entities.signaltype.SignalType
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class DataSendTest {

    private val monitorTarget = FakeMonitorTarget()
    private val signalTarget = FakeSignalTarget(monitorTarget)

    private val outputStream = ByteArrayOutputStream()

    @Before
    fun initializeOutput() {
        System.setOut(PrintStream(outputStream))
    }

    @Test
    fun validateTemperatureSend() {
        val validResult = "34.5"

        sendNewData(SignalType.Temperature, validResult)
        checkOutput(validResult)
    }

    @Test
    fun validateTemperatureMinimumSend() {
        val validResult = "29.1"

        sendNewData(SignalType.TemperatureMinimum, validResult)
        checkOutput(validResult)
    }

    @Test
    fun validateTemperatureMaximumSend() {
        val validResult = "35.9"

        sendNewData(SignalType.TemperatureMaximum, validResult)
        checkOutput(validResult)
    }

    @Test
    fun validateHumiditySend() {
        val validResult = "74.2"

        sendNewData(SignalType.Humidity, validResult)
        checkOutput(validResult)
    }

    @Test
    fun validateHumidityMinimumSend() {
        val validResult = "54.6"

        sendNewData(SignalType.HumidityMinimum, validResult)
        checkOutput(validResult)
    }

    @Test
    fun validateHumidityMaximumSend() {
        val validResult = "89.3"

        sendNewData(SignalType.HumidityMaximum, validResult)
        checkOutput(validResult)
    }

    @Test
    fun validateResetRequiredSend() {
        val validResult = ""

        sendNewData(SignalType.Reset, validResult)
        checkOutput(validResult)
    }

    private fun sendNewData(signalType: SignalType, data: String) {
        signalTarget.onNewDataAvailable(signalType, data)
    }

    private fun checkOutput(validOutput: String) {
        assertEquals(validOutput, outputStream.toString())
    }

}
