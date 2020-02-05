package com.smlnskgmail.jaman.remotetemperaturecontrol.logic.monitor.api

interface BtMonitor {

    fun onNewDataAvailable(
        btMonitorSignalType: BtMonitorSignalType,
        rawData: String
    )

}
