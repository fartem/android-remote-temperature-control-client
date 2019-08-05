package com.smlnskgmail.jaman.remotetemperaturecontrol.entities.signaltype

interface SignalTarget {

    fun onDataAvailable(signalType: SignalType, data: String)

}