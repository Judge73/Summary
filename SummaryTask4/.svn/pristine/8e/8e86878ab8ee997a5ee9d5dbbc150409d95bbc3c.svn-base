package ua.nure.kotkov.SummaryTask4.comparator;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.mock;
import static org.easymock.EasyMock.replay;
import static org.junit.Assert.*;

import org.junit.Test;

import ua.nure.kotkov.SummaryTask4.db.bean.FlightBean;

public class IdComparatorTest {

	@Test
	public void test() {
		IdComparator comparator = mock(IdComparator.class);
		FlightBean fb1 = mock(FlightBean.class);
		FlightBean fb2 = mock(FlightBean.class);
		expect(comparator.compare(fb1, fb2)).andReturn(1);
		replay(comparator);
		assertEquals(1, comparator.compare(fb1, fb2));
	}

}
