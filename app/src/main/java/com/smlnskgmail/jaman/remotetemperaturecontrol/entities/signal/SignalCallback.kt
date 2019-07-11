package com.smlnskgmail.jaman.remotetemperaturecontrol.entities.signal

interface SignalCallback {

    fun onDataAvailable(signalType: SignalType, data: String)

}