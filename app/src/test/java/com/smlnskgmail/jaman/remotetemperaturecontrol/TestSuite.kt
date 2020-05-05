package com.smlnskgmail.jaman.remotetemperaturecontrol

import com.smlnskgmail.jaman.remotetemperaturecontrol.api.MonitorDataExtractorTest
import com.smlnskgmail.jaman.remotetemperaturecontrol.api.MonitorDataSendTest
import com.smlnskgmail.jaman.remotetemperaturecontrol.api.entities.BtDeviceTest
import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
    MonitorDataExtractorTest::class,
    MonitorDataSendTest::class,
    BtDeviceTest::class
)
class TestSuite
