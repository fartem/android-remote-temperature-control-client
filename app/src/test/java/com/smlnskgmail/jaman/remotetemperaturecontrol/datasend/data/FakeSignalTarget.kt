package com.smlnskgmail.jaman.remotetemperaturecontrol.datasend.data

import com.smlnskgmail.jaman.remotetemperaturecontrol.monitor.entities.signaltype.SignalTarget
import com.smlnskgmail.jaman.remotetemperaturecontrol.monitor.entities.signaltype.SignalType
import com.smlnskgmail.jaman.remotetemperaturecontrol.monitor.support.MonitorTarget

class FakeSignalTarget(private val monitorTarget: MonitorTarget) : SignalTarget {

    override fun onNewDataAvailable(signalType: SignalType, rawData: String) {
        when (signalType) {
            SignalType.Temperature -> monitorTarget.temperatureAvailable(rawData)
            SignalType.TemperatureMinimum -> monitorTarget.temperatureMinimumAvailable(rawData)
            SignalType.TemperatureMaximum -> monitorTarget.temperatureMaximumAvailable(rawData)
            SignalType.Humidity -> monitorTarget.humidityAvailable(rawData)
            SignalType.HumidityMinimum -> monitorTarget.humidityMinimumAvailable(rawData)
            SignalType.HumidityMaximum -> monitorTarget.humidityMaximumAvailable(rawData)
            SignalType.Reset -> monitorTarget.resetRequired()
        }
    }

}
