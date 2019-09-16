package com.smlnskgmail.jaman.remotetemperaturecontrol.monitor.entities.signaltype

interface SignalTarget {

    fun onNewDataAvailable(signalType: SignalType, rawData: String)

}