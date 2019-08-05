package com.smlnskgmail.jaman.remotetemperaturecontrol.entities.signal

interface SignalTarget {

    fun onDataAvailable(signalType: SignalType, data: String)

}