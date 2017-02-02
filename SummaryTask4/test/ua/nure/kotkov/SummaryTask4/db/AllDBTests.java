package ua.nure.kotkov.SummaryTask4.db;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ DBManagerTest.class, FlightStatusTest.class, JobTest.class, Request_statusTest.class, RoleTest.class })
public class AllDBTests {

}
