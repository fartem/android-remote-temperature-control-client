package com.smlnskgmail.jaman.remotetemperaturecontrol.logic.monitor.impl.devicebt

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothSocket
import com.smlnskgmail.jaman.remotetemperaturecontrol.logic.monitor.api.BtConnection
import com.smlnskgmail.jaman.remotetemperaturecontrol.logic.monitor.api.BtMonitor
import com.smlnskgmail.jaman.remotetemperaturecontrol.logic.monitor.api.BtMonitorSignalType
import java.io.InputStream
import java.io.OutputStream
import java.util.*

class DeviceBtConnection(
    btAdapter: BluetoothAdapter,
    deviceMacAddress: String,
    private val btMonitor: BtMonitor
) : BtConnection {

    private val btUuid = UUID.fromString(
        "00001101-0000-1000-8000-00805F9B34FB"
    )

    private var btSocket: BluetoothSocket?

    private var inputStream: InputStream? = null
    private var outputStream: OutputStream? = null

    private var isRunning = false

    init {
        val btDevice = btAdapter.getRemoteDevice(deviceMacAddress)
        btSocket = btDevice.createRfcommSocketToServiceRecord(btUuid)
    }

    private fun inputStreamIsOpen() = inputStream!!.bufferedReader().ready()

    override fun send(btMonitorSignalType: BtMonitorSignalType) {
        outputStream!!.write(
            DeviceBtDataExtractor.signalOf(
                btMonitorSignalType
            ).toByteArray()
        )
    }

    override fun connect() {
        if (!btSocket!!.isConnected) {
            btSocket!!.connect()
            inputStream = btSocket!!.inputStream
            outputStream = btSocket!!.outputStream
        }
        runRead()
        while (canRead()) {
            read()
        }
    }
    private fun read() {
        if (inputStreamIsOpen()) {
            val rawData = inputStream!!.bufferedReader().readLine()
            val signalType = DeviceBtDataExtractor.signalType(
                rawData
            )
            val data = DeviceBtDataExtractor.data(
                rawData
            )
            btMonitor.onNewDataAvailable(signalType, data)
        }
    }


    override fun disconnect() {
        if (btSocket!!.isConnected) {
            isRunning = false
            btSocket!!.close()
            inputStream!!.close()
            outputStream!!.close()

            btSocket = null
            inputStream = null
            outputStream = null
        }
    }

    override fun handleOnResume() {
        if (outputStream != null) {
            outputStream!!.flush()
        }
    }

    private fun runRead() {
        isRunning = true
    }

    private fun canRead() = isRunning

}
