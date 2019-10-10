package com.smlnskgmail.jaman.remotetemperaturecontrol.datavalidation

import com.smlnskgmail.jaman.remotetemperaturecontrol.monitor.connection.MonitorDataExtractor
import com.smlnskgmail.jaman.remotetemperaturecontrol.monitor.entities.signaltype.SignalType
import org.junit.Assert.assertEquals
import org.junit.Test

class MonitorDataExtractorTest {

    @Test
    fun validateTemperatureParse() {
        checkValidation(SignalType.Temperature)
    }

    @Test
    fun validateTemperatureMinimumParse() {
        checkValidation(SignalType.TemperatureMinimum)
    }

    @Test
    fun validateTemperatureMaximumParse() {
        checkValidation(SignalType.TemperatureMaximum)
    }

    @Test
    fun validateHumidityParse() {
        checkValidation(SignalType.Humidity)
    }

    @Test
    fun validateHumidityMinimumParse() {
        checkValidation(SignalType.HumidityMinimum)
    }

    @Test
    fun validateHumidityMaximumParse() {
        checkValidation(SignalType.HumidityMaximum)
    }

    @Test
    fun validateResetRequired() {
        checkValidation(SignalType.Reset, "")
    }

    private fun checkValidation(signalType: SignalType, cleanResult: String = DEFAULT_CLEAN_RESULT) {
        val rawData = rawData(SignalType.signalOf(signalType), cleanResult)

        val parsedSignalType = MonitorDataExtractor.signalType(rawData)
        assertEquals(parsedSignalType, signalType)

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
