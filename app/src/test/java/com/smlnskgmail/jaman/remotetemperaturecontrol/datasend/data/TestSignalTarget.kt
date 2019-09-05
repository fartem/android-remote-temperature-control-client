package com.smlnskgmail.jaman.remotetemperaturecontrol.datasend.data

import com.smlnskgmail.jaman.remotetemperaturecontrol.monitor.entities.signaltype.SignalTarget
import com.smlnskgmail.jaman.remotetemperaturecontrol.monitor.entities.signaltype.SignalType
import com.smlnskgmail.jaman.remotetemperaturecontrol.monitor.support.MonitorTarget

class TestSignalTarget(private val monitorTarget: MonitorTarget) : SignalTarget {

    override fun onNewDataAvailable(signalType: SignalType, data: String) {
        when (signalType) {
            SignalType.Temperature -> monitorTarget.temperatureAvailable(data)
            SignalType.TemperatureMinimum -> monitorTarget.temperatureMinimumAvailable(data)
            SignalType.TemperatureMaximum -> monitorTarget.temperatureMaximumAvailable(data)
            SignalType.Humidity -> monitorTarget.humidityAvailable(data)
            SignalType.HumidityMinimum -> monitorTarget.humidityMinimumAvailable(data)
            SignalType.HumidityMaximum -> monitorTarget.humidityMaximumAvailable(data)
            SignalType.Reset -> monitorTarget.resetRequired()
        }
    }

}
