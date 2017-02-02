package ua.nure.kotkov.SummaryTask4.command;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.mock;
import static org.easymock.EasyMock.replay;
import static org.junit.Assert.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;

public class ViewSettingsCommandTest {

	@Test
	public void test() {
		ViewSettingsCommand viewSettings = mock(ViewSettingsCommand.class);
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		expect(viewSettings.execute(request, response)).andReturn("Right");
		replay(viewSettings);
		assertEquals("Right", viewSettings.execute(request, response));
	}

}
