package ua.nure.kotkov.SummaryTask4.MailSender;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ua.nure.kotkov.SummaryTask4.db.bean.EmployeeBean;
import ua.nure.kotkov.SummaryTask4.exception.MailException;

public class MailSenderTest {

	MailSender ms;
	
	@Before
	public void init(){
		ms = mock(MailSender.class);
	}
	
	@Test
	public void testSendOneMail() throws MailException {
		EmployeeBean emp = mock(EmployeeBean.class);
		MailSender.getInstance();
		MailSender.getInstance();
		expect(emp.getEmail()).andReturn("Judge73@yandex.ru");
		expect(ms.sendMail(emp, "One", "Two")).andStubReturn(true);
		replay(ms);
		assertTrue(ms.sendMail(emp, "One", "Two"));
	}
	
	@Test
	public void testSendMails() throws MailException {
		EmployeeBean emp = mock(EmployeeBean.class);
		expect(emp.getEmail()).andReturn("Judge73@yandex.ru");
		List<EmployeeBean> emplist = new ArrayList<EmployeeBean>();
		emplist.add(emp);
		emplist.add(emp);
		expect(ms.sendMail(emplist, "One", "Two")).andReturn(true);
		replay(ms);
		assertTrue(ms.sendMail(emplist, "One", "Two"));
	}

}
