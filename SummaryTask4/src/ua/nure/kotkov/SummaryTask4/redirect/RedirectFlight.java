package ua.nure.kotkov.SummaryTask4.redirect;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ua.nure.kotkov.SummaryTask4.Path;
import ua.nure.kotkov.SummaryTask4.db.Role;

@WebServlet("/RedirectFlight")
public class RedirectFlight extends HttpServlet {

	private static final long serialVersionUID = 2032293788192551118L;

	private static final Logger LOG = LogManager.getLogger(RedirectFlight.class);
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOG.traceEntry();
		int success = Integer.parseInt(request.getParameter("s"));
		LOG.trace("Got parameter: success --> " + success);
		if (success == 1){
			request.setAttribute("result", "Operation successfull");
		} else {
			request.setAttribute("result",
					"Operation unsuccessfull");
		}
		String forward = null;
		Role role = (Role) request.getSession().getAttribute("userRole");
		switch(role){
			case ADMIN: forward = Path.COMMAND_ADMIN_FLIGHT_VIEW;
			break;
			case DISPATCHER: forward = Path.COMMAND_DISP_VIEW;
			break;
		}
		LOG.traceExit();
		request.getRequestDispatcher(forward).forward(request, response);
}
}
