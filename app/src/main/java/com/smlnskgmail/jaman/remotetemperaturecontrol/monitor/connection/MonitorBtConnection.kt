package com.smlnskgmail.jaman.remotetemperaturecontrol.monitor.connection

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothSocket
import com.smlnskgmail.jaman.remotetemperaturecontrol.monitor.entities.signaltype.SignalTarget
import com.smlnskgmail.jaman.remotetemperaturecontrol.monitor.entities.signaltype.SignalType
import java.io.InputStream
import java.io.OutputStream
import java.util.*

class MonitorBtConnection(

    btAdapter: BluetoothAdapter,
    deviceMacAddress: String,
    private val signalTarget: SignalTarget

) : Thread() {

    private val BT_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB")

    private var btSocket: BluetoothSocket

    private lateinit var inputStream: InputStream
    private lateinit var outputStream: OutputStream

    private var isRunning = false

    init {
        val btDevice = btAdapter.getRemoteDevice(deviceMacAddress)
        btSocket = btDevice.createRfcommSocketToServiceRecord(BT_UUID)
    }

    override fun run() {
        runRead()
        while (canRead()) {
            read()
        }
    }

    private fun read() {
        if (inputStreamIsOpen()) {
            val rawData = inputStream.bufferedReader().readLine()
            val signalType = DataValidation.getSignalType(rawData)
            val data = DataValidation.getData(rawData)
            signalTarget.onNewDataAvailable(signalType, data)
        }
    }

    private fun inputStreamIsOpen() = inputStream.bufferedReader().ready()

    fun send(signalType: SignalType) {
        outputStream.write(signalType.signal.toByteArray())
    }

    fun connect() {
        if (!btSocket.isConnected) {
            btSocket.connect()
            inputStream = btSocket.inputStream
            outputStream = btSocket.outputStream
        }
    }

    fun disconnect() {
        if (btSocket.isConnected) {
            isRunning = false
            btSocket.close()
            inputStream.close()
            outputStream.close()
        }
    }

    fun handleOnResume() {

    }

    private fun runRead() {
        isRunning = true
    }

    private fun canRead() = isRunning

}