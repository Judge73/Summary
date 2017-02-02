package ua.nure.kotkov.SummaryTask4.command;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.mock;
import static org.easymock.EasyMock.replay;
import static org.junit.Assert.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;

import ua.nure.kotkov.SummaryTask4.Path;

public class NoCommandTest {

	@Test
	public void test() {
		NoCommand noCommand = mock(NoCommand.class);
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		expect(noCommand.execute(request, response)).andReturn(Path.PAGE_ERROR_PAGE);
		replay(noCommand);
		assertEquals(Path.PAGE_ERROR_PAGE, noCommand.execute(request, response));
	}

}
