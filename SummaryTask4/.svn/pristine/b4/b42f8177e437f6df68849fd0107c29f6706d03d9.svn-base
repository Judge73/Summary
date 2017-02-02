package ua.nure.kotkov.SummaryTask4.command.dispatcher;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;

import ua.nure.kotkov.SummaryTask4.exception.AppException;

public class DispatcherViewCommandTest {

	@Test
	public void test() throws IOException, ServletException, AppException {
		DispatcherViewCommand dispatcherView = mock(DispatcherViewCommand.class);
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		expect(dispatcherView.execute(request, response)).andReturn("Right");
		replay(dispatcherView);
		assertEquals("Right", dispatcherView.execute(request, response));
	}

}
