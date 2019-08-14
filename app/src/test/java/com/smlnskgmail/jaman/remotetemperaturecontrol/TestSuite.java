package com.smlnskgmail.jaman.remotetemperaturecontrol;

import com.smlnskgmail.jaman.remotetemperaturecontrol.datasend.DataSendTest;
import com.smlnskgmail.jaman.remotetemperaturecontrol.datavalidation.DataValidationTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        DataValidationTest.class,
        DataSendTest.class
})
public class TestSuite {}
