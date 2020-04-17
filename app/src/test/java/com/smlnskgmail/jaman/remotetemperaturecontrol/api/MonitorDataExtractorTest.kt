package com.smlnskgmail.jaman.remotetemperaturecontrol.api

import com.smlnskgmail.jaman.remotetemperaturecontrol.logic.monitor.api.monitor.BtMonitorSignalType
import com.smlnskgmail.jaman.remotetemperaturecontrol.logic.monitor.impl.devicebt.DeviceBtDataExtractor
import org.junit.Assert.assertEquals
import org.junit.Test

class MonitorDataExtractorTest {

    companion object {

        private const val defaultResult = "25.5"

    }

    @Test
    fun validateTemperatureParse() {
        checkValidation(
            BtMonitorSignalType.Temperature
        )
    }

    private fun checkValidation(
        btMonitorSignalType: BtMonitorSignalType,
        result: String = defaultResult
    ) {
        val rawData = rawData(
            DeviceBtDataExtractor.signalOf(
                btMonitorSignalType
            ),
            result
        )

        val parsedSignalType = DeviceBtDataExtractor.signalType(rawData)
        assertEquals(
            parsedSignalType,
            btMonitorSignalType
        )

        val parsedData = DeviceBtDataExtractor.data(rawData)
        assertEquals(
            parsedData,
            result
        )
    }

    private fun rawData(
        signalType: String,
        result: String
    ): String {
        return String.format(
            "%s%s",
            signalType,
            result
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

    @Test
    fun validateOther() {
        checkValidation(
            BtMonitorSignalType.Other,
            ""
        )
    }

}
