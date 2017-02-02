package ua.nure.kotkov.SummaryTask4.db;

import static org.junit.Assert.*;
import static org.easymock.EasyMock.*;

import org.junit.Test;

import ua.nure.kotkov.SummaryTask4.db.bean.FlightBean;

public class FlightStatusTest {

	@Test
	public void testInstance() {
		assertEquals(FlightStatus.DELAYED, FlightStatus.valueOf("DELAYED"));
	}
	
	@Test
	public void testName() {
		assertEquals("arrived", equals(FlightStatus.getStatusName(1)));
	}
	
	@Test
	public void testGetStatus() {
		FlightBean fb = mock(FlightBean.class);
		expect(fb.getStatusId()).andReturn(1);
		FlightStatus.getStatus(fb).getName();
	}

}
