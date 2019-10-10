package com.smlnskgmail.jaman.remotetemperaturecontrol

import com.smlnskgmail.jaman.remotetemperaturecontrol.datasend.DataSendTest
import com.smlnskgmail.jaman.remotetemperaturecontrol.datavalidation.MonitorDataExtractorTest
import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
    MonitorDataExtractorTest::class,
    DataSendTest::class
)
class TestSuite
