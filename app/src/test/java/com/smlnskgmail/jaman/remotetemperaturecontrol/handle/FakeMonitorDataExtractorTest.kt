package com.smlnskgmail.jaman.remotetemperaturecontrol.handle

import com.smlnskgmail.jaman.remotetemperaturecontrol.monitor.MonitorSignalType
import com.smlnskgmail.jaman.remotetemperaturecontrol.monitor.btmonitor.connection.MonitorDataExtractor
import org.junit.Assert.assertEquals
import org.junit.Test

class FakeMonitorDataExtractorTest {

    @Test
    fun validateTemperatureParse() {
        checkValidation(MonitorSignalType.Temperature)
    }

    @Test
    fun validateTemperatureMinimumParse() {
        checkValidation(MonitorSignalType.TemperatureMinimum)
    }

    @Test
    fun validateTemperatureMaximumParse() {
        checkValidation(MonitorSignalType.TemperatureMaximum)
    }

    @Test
    fun validateHumidityParse() {
        checkValidation(MonitorSignalType.Humidity)
    }

    @Test
    fun validateHumidityMinimumParse() {
        checkValidation(MonitorSignalType.HumidityMinimum)
    }

    @Test
    fun validateHumidityMaximumParse() {
        checkValidation(MonitorSignalType.HumidityMaximum)
    }

    @Test
    fun validateResetRequired() {
        checkValidation(MonitorSignalType.Reset, "")
    }

    private fun checkValidation(
        monitorSignalType: MonitorSignalType,
        cleanResult: String = DEFAULT_CLEAN_RESULT
    ) {
        val rawData = rawData(MonitorSignalType.signalOf(monitorSignalType), cleanResult)

        val parsedSignalType = MonitorDataExtractor.signalType(rawData)
        assertEquals(parsedSignalType, monitorSignalType)

        val parsedData = MonitorDataExtractor.data(rawData)
        assertEquals(parsedData, cleanResult)
    }

    private fun rawData(signalType: String, cleanResult: String): String {
        return String.format("%s%s", signalType, cleanResult)
    }

    companion object {

        private const val DEFAULT_CLEAN_RESULT = "25.5"

    }

}
