package com.smlnskgmail.jaman.remotetemperaturecontrol.logic.monitor

interface Monitor {

    fun onNewDataAvailable(
        monitorSignalType: MonitorSignalType,
        rawData: String
    )

}
