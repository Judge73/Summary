package ua.nure.kotkov.SummaryTask4.command.admin;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.mock;
import static org.easymock.EasyMock.replay;
import static org.junit.Assert.*;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;

import ua.nure.kotkov.SummaryTask4.exception.AppException;

public class OpenRequestCommandTest {

	@Test
	public void test() throws IOException, ServletException, AppException {
		OpenRequestCommand openRequest = mock(OpenRequestCommand.class);
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		expect(openRequest.execute(request, response)).andReturn("Right");
		replay(openRequest);
		assertEquals("Right", openRequest.execute(request, response));
	}

}
