package com.smlnskgmail.jaman.remotetemperaturecontrol.monitor

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothSocket
import com.smlnskgmail.jaman.remotetemperaturecontrol.entities.signal.SignalCallback
import com.smlnskgmail.jaman.remotetemperaturecontrol.entities.signal.SignalType
import com.smlnskgmail.jaman.remotetemperaturecontrol.monitor.utils.DataParser
import java.io.InputStream
import java.io.OutputStream
import java.util.*

class MonitorBluetoothConnection(

    bluetoothAdapter: BluetoothAdapter,
    deviceMacAddress: String,
    private val signalCallback: SignalCallback

) : Thread() {

    private val BLUETOOTH_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB")

    private var bluetoothSocket: BluetoothSocket

    private lateinit var inputStream: InputStream
    private lateinit var outputStream: OutputStream

    private var isRunning = false

    init {
        val bluetoothDevice = bluetoothAdapter.getRemoteDevice(deviceMacAddress)
        bluetoothSocket = bluetoothDevice.createRfcommSocketToServiceRecord(BLUETOOTH_UUID)
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
            val signalType = DataParser.getSignalType(rawData)
            val data = DataParser.getData(rawData)
            signalCallback.onDataAvailable(signalType, data)
        }
    }

    private fun inputStreamIsOpen() = inputStream.bufferedReader().ready()

    fun send(signalType: SignalType) {
        outputStream.write(signalType.signal.toByteArray())
    }

    fun connect() {
        if (!bluetoothSocket.isConnected) {
            bluetoothSocket.connect()
            inputStream = bluetoothSocket.inputStream
            outputStream = bluetoothSocket.outputStream
        }
    }

    fun disconnect() {
        if (bluetoothSocket.isConnected) {
            isRunning = false
            bluetoothSocket.close()
            inputStream.close()
            outputStream.close()
        }
    }

    private fun runRead() {
        isRunning = true
    }

    private fun canRead() = isRunning

}