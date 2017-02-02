package ua.nure.kotkov.SummaryTask4.comparator;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;

import org.junit.Test;

import ua.nure.kotkov.SummaryTask4.db.bean.FlightBean;

public class DateComparatorTest {

	@Test
	public void test() {
		DateComparator comparator = mock(DateComparator.class);
		FlightBean fb1 = mock(FlightBean.class);
		FlightBean fb2 = mock(FlightBean.class);
		expect(comparator.compare(fb1, fb2)).andReturn(1);
		replay(comparator);
		assertEquals(1, comparator.compare(fb1, fb2));
	}

}
