package ua.nure.kotkov.SummaryTask4.command;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.mock;
import static org.easymock.EasyMock.replay;
import static org.junit.Assert.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;

public class LogoutCommandTest {

	@Test
	public void test() {
		LogoutCommand logout = mock(LogoutCommand.class);
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		expect(logout.execute(request, response)).andReturn("Right");
		replay(logout);
		assertEquals("Right", logout.execute(request, response));
	}

}
