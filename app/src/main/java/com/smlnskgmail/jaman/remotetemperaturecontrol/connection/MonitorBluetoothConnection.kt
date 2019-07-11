package com.smlnskgmail.jaman.remotetemperaturecontrol.connection

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothSocket
import com.smlnskgmail.jaman.remotetemperaturecontrol.entities.signal.SignalCallback
import com.smlnskgmail.jaman.remotetemperaturecontrol.entities.signal.SignalType
import java.io.InputStream
import java.io.OutputStream
import java.util.*

class MonitorBluetoothConnection(bluetoothAdapter: BluetoothAdapter,
                                 systemBluetoothMacAddress: String,
                                 private val signalCallback: SignalCallback
) : Thread() {

    private val BLUETOOTH_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB")

    private val SYMBOL_TEMP = "t"
    private val HUMID_SYMBOL = "h"
    private val MAXT_SYMBOL = "m"
    private val MINT_SYMBOL = "i"
    private val MAXH_SYMBOL = "w"
    private val MINH_SYMBOL = "q"
    private val REBOOT_SYMBOL = "r"

    private var bluetoothSocket: BluetoothSocket
    private lateinit var inputStream: InputStream
    private lateinit var outputStream: OutputStream

    init {
        val bluetoothDevice = bluetoothAdapter.getRemoteDevice(systemBluetoothMacAddress)
        bluetoothSocket = bluetoothDevice.createRfcommSocketToServiceRecord(BLUETOOTH_UUID)
    }

    override fun run() {

    }

    fun send(data: String) {
        outputStream.write(data.toByteArray())
    }

    fun read() {
        val data = inputStream.read()
        signalCallback.onDataAvailable(SignalType.Reset, "")
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
            bluetoothSocket.close()
        }
    }

}