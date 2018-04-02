package com.airport.system.test.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.airport.system.test.unit.ApplicationShortestPathTest;
import com.airport.system.test.unit.FileBasedProcessingTest;

@RunWith(Suite.class)

@Suite.SuiteClasses({
   ApplicationShortestPathTest.class,
   FileBasedProcessingTest.class
})
public class AirportBaggageRoutingTestSuite {

}
