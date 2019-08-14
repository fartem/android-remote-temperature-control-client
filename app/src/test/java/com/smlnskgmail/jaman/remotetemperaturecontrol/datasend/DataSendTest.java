package com.smlnskgmail.jaman.remotetemperaturecontrol.datasend;

import com.smlnskgmail.jaman.remotetemperaturecontrol.datasend.data.TestMonitorTarget;
import com.smlnskgmail.jaman.remotetemperaturecontrol.datasend.data.TestSignalTarget;
import com.smlnskgmail.jaman.remotetemperaturecontrol.monitor.entities.signaltype.SignalType;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class DataSendTest {

    private TestMonitorTarget monitorTarget = new TestMonitorTarget();
    private TestSignalTarget signalTarget = new TestSignalTarget(monitorTarget);

    private ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @Before
    public void initializeOutput() {
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    public void validateTemperatureSend() {
        String validResult = "34.5";

        sendNewData(SignalType.Temperature, validResult);
        checkOutput(validResult);
    }

    @Test
    public void validateTemperatureMinimumSend() {
        String validResult = "29.1";

        sendNewData(SignalType.TemperatureMinimum, validResult);
        checkOutput(validResult);
    }

    @Test
    public void validateTemperatureMaximumSend() {
        String validResult = "35.9";

        sendNewData(SignalType.TemperatureMaximum, validResult);
        checkOutput(validResult);
    }

    @Test
    public void validateHumiditySend() {
        String validResult = "74.2";

        sendNewData(SignalType.Humidity, validResult);
        checkOutput(validResult);
    }

    @Test
    public void validateHumidityMinimumSend() {
        String validResult = "54.6";

        sendNewData(SignalType.HumidityMinimum, validResult);
        checkOutput(validResult);
    }

    @Test
    public void validateHumidityMaximumSend() {
        String validResult = "89.3";

        sendNewData(SignalType.HumidityMaximum, validResult);
        checkOutput(validResult);
    }

    @Test
    public void validateResetRequiredSend() {
        String validResult = "";

        sendNewData(SignalType.Reset, validResult);
        checkOutput(validResult);
    }

    private void sendNewData(SignalType signalType, String data) {
        signalTarget.onNewDataAvailable(signalType, data);
    }

    private void checkOutput(String validOutput) {
        assertEquals(validOutput, outputStream.toString());
    }

}
