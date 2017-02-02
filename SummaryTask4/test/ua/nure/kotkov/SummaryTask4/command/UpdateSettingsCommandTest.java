package ua.nure.kotkov.SummaryTask4.command;

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

public class UpdateSettingsCommandTest {

	@Test
	public void test() throws IOException, ServletException, AppException {
		UpdateSettingsCommand updateSettings = mock(UpdateSettingsCommand.class);
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		expect(updateSettings.execute(request, response)).andReturn("Right");
		replay(updateSettings);
		assertEquals("Right", updateSettings.execute(request, response));
	}

}
