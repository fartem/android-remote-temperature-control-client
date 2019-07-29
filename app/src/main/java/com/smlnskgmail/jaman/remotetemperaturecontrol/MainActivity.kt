package com.smlnskgmail.jaman.remotetemperaturecontrol

import android.bluetooth.BluetoothAdapter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.smlnskgmail.jaman.remotetemperaturecontrol.components.bottomsheets.BaseBottomSheet
import com.smlnskgmail.jaman.remotetemperaturecontrol.components.bottomsheets.deviceslist.BtDevicesBottomSheet
import com.smlnskgmail.jaman.remotetemperaturecontrol.components.bottomsheets.deviceslist.OnBluetoothDeviceSelected
import com.smlnskgmail.jaman.remotetemperaturecontrol.components.bottomsheets.settings.OnDisconnectListener
import com.smlnskgmail.jaman.remotetemperaturecontrol.components.bottomsheets.settings.SettingsBottomSheet
import com.smlnskgmail.jaman.remotetemperaturecontrol.connection.MonitorBluetoothConnection
import com.smlnskgmail.jaman.remotetemperaturecontrol.entities.signal.SignalType
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), OnBluetoothDeviceSelected, OnDisconnectListener {

    private lateinit var monitorBluetoothConnection: MonitorBluetoothConnection
    private lateinit var bluetoothAdapter: BluetoothAdapter

    private lateinit var temperatureDataController: TemperatureDataController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        temperatureDataController = TemperatureDataController(this, findViewById(android.R.id.content))
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()

        showDevicesList()
    }

    private fun showDevicesList() {
        val devicesBottomSheet = BtDevicesBottomSheet()
        devicesBottomSheet.setBluetoothAdapter(bluetoothAdapter)
        devicesBottomSheet.setBluetoothDeviceSelectCallback(this)
        devicesBottomSheet.isCancelable = false
        showBottomSheet(devicesBottomSheet)
    }

    override fun onBluetoothDeviceSelect(address: String) {
        monitorBluetoothConnection = MonitorBluetoothConnection(bluetoothAdapter, address, temperatureDataController)
        monitorBluetoothConnection.connect()
        monitorBluetoothConnection.start()
        initializeButtons()
    }



    private fun initializeButtons() {
        btn_reset_monitor.setOnClickListener {
            monitorBluetoothConnection.send(SignalType.Reset)
        }
        btn_main_options.setOnClickListener {
            val settingsBottomSheet = SettingsBottomSheet()
            settingsBottomSheet.setBluetoothDisconnectListener(this)
            showBottomSheet(settingsBottomSheet)
        }
    }

    private fun showBottomSheet(bottomSheet: BaseBottomSheet) {
        bottomSheet.show(supportFragmentManager, bottomSheet::class.java.name)
    }

    override fun onConnectionClosed() {
        monitorBluetoothConnection.disconnect()
    }

}
