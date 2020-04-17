package com.smlnskgmail.jaman.remotetemperaturecontrol.api

import com.smlnskgmail.jaman.remotetemperaturecontrol.logic.monitor.api.entities.BtDevice
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test

class BtDeviceTest {

    @Test
    fun btDeviceTest() {
        val name = "HC-06"
        val address = "-"
        val isSelected = false

        val btDevice = BtDevice(
            name,
            address,
            isSelected
        )

        assertEquals(
            name,
            btDevice.name
        )
        assertEquals(
            address,
            btDevice.address
        )
        assertEquals(
            isSelected,
            btDevice.isSelected
        )

        val btDeviceCopy = BtDevice(
            name,
            address,
            isSelected
        )
        assertEquals(
            btDeviceCopy,
            btDevice
        )
        assertEquals(
            btDeviceCopy.hashCode(),
            btDevice.hashCode()
        )

        val anotherBtDevice = BtDevice(
            "HC-05",
            "-",
            true
        )

        assertNotEquals(
            anotherBtDevice,
            btDevice
        )
        assertNotEquals(
            anotherBtDevice.hashCode(),
            btDevice.hashCode()
        )
    }

}
