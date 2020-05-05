package com.smlnskgmail.jaman.remotetemperaturecontrol.api.entities

import com.smlnskgmail.jaman.remotetemperaturecontrol.logic.monitor.api.entities.BtDevice
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals

class BtDeviceTest : BaseEntityTest() {

    private val name = "HC-06"
    private val address = "-"
    private val isSelected = false

    private val btDevice = BtDevice(
        name,
        address,
        isSelected
    )

    override fun `Validate fields`() {
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
    }

    override fun `Validate equals()`() {
        assertEquals(
            btDevice,
            btDevice
        )
        assertEquals(
            BtDevice(
                name,
                address,
                isSelected
            ),
            btDevice
        )

        assertNotEquals(
            BtDevice(
                "HC-05",
                address,
                isSelected
            ),
            btDevice
        )
        assertNotEquals(
            BtDevice(
                name,
                "",
                isSelected
            ),
            btDevice
        )
        assertNotEquals(
            BtDevice(
                name,
                address,
                true
            ),
            btDevice
        )
        assertNotEquals(
            btDevice,
            null
        )
        assertNotEquals(
            btDevice,
            "String"
        )
    }

    override fun `Validate hashCode()`() {
        assertEquals(
            btDevice.hashCode(),
            btDevice.hashCode()
        )
        assertEquals(
            BtDevice(
                name,
                address,
                isSelected
            ).hashCode(),
            btDevice.hashCode()
        )

        assertNotEquals(
            BtDevice(
                "HC-05",
                address,
                isSelected
            ).hashCode(),
            btDevice.hashCode()
        )
    }

}
