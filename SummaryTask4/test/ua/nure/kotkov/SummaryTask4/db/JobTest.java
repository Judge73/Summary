package ua.nure.kotkov.SummaryTask4.db;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.mock;
import static org.junit.Assert.*;

import org.junit.Test;

import ua.nure.kotkov.SummaryTask4.db.bean.EmployeeBean;

public class JobTest {

	@Test
	public void testInstance() {
		assertTrue(Job.PILOT.equals(Job.valueOf("PILOT")));
	}
	
	@Test
	public void testName() {
		EmployeeBean emp = mock(EmployeeBean.class);
		expect(emp.getJobId()).andReturn(0);
		assertTrue(Job.PILOT.equals(Job.getJob(emp)));
	}
	
	@Test
	public void getName() {
		assertTrue("pilot".equals(Job.PILOT.getName()));
	}
	
	
}
