package ua.nure.kotkov.SummaryTask4.command.dispatcher;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ DispatcherViewCommandTest.class, FormCrewCommandTest.class, RequestPageCommandTest.class,
		SetStatusCommandTest.class, WriteRequestCommandTest.class })
public class AllDispatcherCommandTests {

}
