package com.smlnskgmail.jaman.remotetemperaturecontrol

import com.smlnskgmail.jaman.remotetemperaturecontrol.api.BtDeviceTest
import com.smlnskgmail.jaman.remotetemperaturecontrol.api.MonitorDataExtractorTest
import com.smlnskgmail.jaman.remotetemperaturecontrol.api.MonitorDataSendTest
import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
    MonitorDataExtractorTest::class,
    MonitorDataSendTest::class,
    BtDeviceTest::class
)
class TestSuite
