package com.smlnskgmail.jaman.remotetemperaturecontrol.logic.monitor.api.connection

import com.smlnskgmail.jaman.remotetemperaturecontrol.logic.monitor.api.monitor.BtMonitorSignalType

interface BtConnection {

    fun connect()
    fun disconnect()
    fun handleOnResume()

    fun send(btMonitorSignalType: BtMonitorSignalType)

}
