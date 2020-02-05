package com.smlnskgmail.jaman.remotetemperaturecontrol.logic.monitor.api

abstract class BtConnection : Thread() {

    open fun connect() {

    }

    open fun disconnect() {

    }

    open fun handleOnResume() {

    }

    open fun send(btMonitorSignalType: BtMonitorSignalType) {

    }

}
