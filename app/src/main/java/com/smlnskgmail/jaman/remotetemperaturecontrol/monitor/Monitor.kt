package com.smlnskgmail.jaman.remotetemperaturecontrol.monitor

interface Monitor {

    fun onNewDataAvailable(monitorSignalType: MonitorSignalType, rawData: String)

}