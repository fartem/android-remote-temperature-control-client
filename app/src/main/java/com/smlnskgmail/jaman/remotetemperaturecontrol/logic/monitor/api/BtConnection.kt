package com.smlnskgmail.jaman.remotetemperaturecontrol.logic.monitor.api

interface BtConnection {

    fun connect()
    fun disconnect()
    fun handleOnResume()

    fun send(btMonitorSignalType: BtMonitorSignalType)

}
