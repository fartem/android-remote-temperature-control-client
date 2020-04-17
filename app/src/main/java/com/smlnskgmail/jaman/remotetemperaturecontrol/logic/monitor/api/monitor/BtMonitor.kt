package com.smlnskgmail.jaman.remotetemperaturecontrol.logic.monitor.api.monitor

interface BtMonitor {

    fun onNewDataAvailable(
        signalType: BtMonitorSignalType,
        rawData: String
    )

}
