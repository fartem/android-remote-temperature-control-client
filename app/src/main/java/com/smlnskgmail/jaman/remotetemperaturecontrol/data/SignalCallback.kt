package com.smlnskgmail.jaman.remotetemperaturecontrol.data

interface SignalCallback {

    fun onDataAvailable(signalType: SignalType, data: String)

}