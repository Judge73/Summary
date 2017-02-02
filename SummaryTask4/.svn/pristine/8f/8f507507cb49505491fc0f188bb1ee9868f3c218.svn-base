package ua.nure.kotkov.SummaryTask4.command;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;

import ua.nure.kotkov.SummaryTask4.exception.AppException;

public class LoginCommandTest {

	@Test
	public void test() throws IOException, ServletException, AppException {
		LoginCommand login = mock(LoginCommand.class);
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		expect(login.execute(request, response)).andReturn("Right");
		replay(login);
		assertEquals("Right", login.execute(request, response));
	}

}
