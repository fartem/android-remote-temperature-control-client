package com.smlnskgmail.jaman.remotetemperaturecontrol

import com.smlnskgmail.jaman.remotetemperaturecontrol.handle.FakeMonitorDataExtractorTest
import com.smlnskgmail.jaman.remotetemperaturecontrol.handle.FakeMonitorDataSendTest
import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
    FakeMonitorDataExtractorTest::class,
    FakeMonitorDataSendTest::class
)
class TestSuite
