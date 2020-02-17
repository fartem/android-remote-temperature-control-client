package com.smlnskgmail.jaman.remotetemperaturecontrol.logic.monitor.api

interface BtMonitor {

    fun onNewDataAvailable(
        signalType: BtMonitorSignalType,
        rawData: String
    )

}
