package com.smlnskgmail.jaman.remotetemperaturecontrol.datavalidation;

import com.smlnskgmail.jaman.remotetemperaturecontrol.monitor.connection.DataValidation;
import com.smlnskgmail.jaman.remotetemperaturecontrol.monitor.entities.signaltype.SignalType;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DataValidationTest {

    private static final String DEFAULT_CLEAN_RESULT = "25.5";

    @Test
    public void validateTemperatureParse() {
        checkValidation(SignalType.Temperature);
    }

    @Test
    public void validateTemperatureMinimumParse() {
        checkValidation(SignalType.TemperatureMinimum);
    }

    @Test
    public void validateTemperatureMaximumParse() {
        checkValidation(SignalType.TemperatureMaximum);
    }

    @Test
    public void validateHumidityParse() {
        checkValidation(SignalType.Humidity);
    }

    @Test
    public void validateHumidityMinimumParse() {
        checkValidation(SignalType.HumidityMinimum);
    }

    @Test
    public void validateHumidityMaximumParse() {
        checkValidation(SignalType.HumidityMaximum);
    }

    @Test
    public void validateResetRequired() {
        checkValidation(SignalType.Reset, "");
    }

    private void checkValidation(SignalType signalType) {
        checkValidation(signalType, DEFAULT_CLEAN_RESULT);
    }

    private void checkValidation(SignalType signalType, String cleanResult) {
        String rawData = rawData(signalType, cleanResult);

        SignalType parsedSignalType = DataValidation.INSTANCE.signalType(rawData);
        assertEquals(parsedSignalType, signalType);

        String  parsedData = DataValidation.INSTANCE.data(rawData);
        assertEquals(parsedData, cleanResult);
    }

    private String rawData(SignalType signalType, String cleanResult) {
        return String.format("%s%s", signalType.getSignal(), cleanResult);
    }

}
