package com.smlnskgmail.jaman.remotetemperaturecontrol.api

import com.smlnskgmail.jaman.remotetemperaturecontrol.logic.monitor.api.BtMonitorSignalType
import com.smlnskgmail.jaman.remotetemperaturecontrol.logic.monitor.impl.devicebt.DeviceBtDataExtractor
import org.junit.Assert.assertEquals
import org.junit.Test

class MonitorDataExtractorTest {

    @Test
    fun validateTemperatureParse() {
        checkValidation(
            BtMonitorSignalType.Temperature
        )
    }

    @Test
    fun validateTemperatureMinimumParse() {
        checkValidation(
            BtMonitorSignalType.TemperatureMinimum
        )
    }

    @Test
    fun validateTemperatureMaximumParse() {
        checkValidation(
            BtMonitorSignalType.TemperatureMaximum
        )
    }

    @Test
    fun validateHumidityParse() {
        checkValidation(
            BtMonitorSignalType.Humidity
        )
    }

    @Test
    fun validateHumidityMinimumParse() {
        checkValidation(
            BtMonitorSignalType.HumidityMinimum
        )
    }

    @Test
    fun validateHumidityMaximumParse() {
        checkValidation(
            BtMonitorSignalType.HumidityMaximum
        )
    }

    @Test
    fun validateResetRequired() {
        checkValidation(
            BtMonitorSignalType.Reset,
            ""
        )
    }

    private fun checkValidation(
        btMonitorSignalType: BtMonitorSignalType,
        cleanResult: String = DEFAULT_CLEAN_RESULT
    ) {
        val rawData = rawData(
            DeviceBtDataExtractor.signalOf(
                btMonitorSignalType
            ),
            cleanResult
        )

        val parsedSignalType = DeviceBtDataExtractor.signalType(rawData)
        assertEquals(
            parsedSignalType,
            btMonitorSignalType
        )

        val parsedData = DeviceBtDataExtractor.data(rawData)
        assertEquals(
            parsedData,
            cleanResult
        )
    }

    private fun rawData(
        signalType: String,
        cleanResult: String
    ): String {
        return String.format("%s%s", signalType, cleanResult)
    }

    companion object {

        private const val DEFAULT_CLEAN_RESULT = "25.5"

    }

}
