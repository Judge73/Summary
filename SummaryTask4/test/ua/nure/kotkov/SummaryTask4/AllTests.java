package ua.nure.kotkov.SummaryTask4;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import ua.nure.kotkov.SummaryTask4.MailSender.MailSenderTest;
import ua.nure.kotkov.SummaryTask4.command.AllCommandTests;
import ua.nure.kotkov.SummaryTask4.db.AllDBTests;

@RunWith(Suite.class)
@SuiteClasses({AllCommandTests.class, AllDBTests.class, MailSenderTest.class})
public class AllTests {

}
