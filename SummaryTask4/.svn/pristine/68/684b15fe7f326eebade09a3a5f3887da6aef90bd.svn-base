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

@WebServlet("/RedirectEmployee")
public class RedirectEmployee extends HttpServlet {
       
	private static final long serialVersionUID = -7321277532309536252L;

	private static final Logger LOG = LogManager.getLogger(RedirectEmployee.class);
	
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
		LOG.traceExit();
		request.getRequestDispatcher(Path.COMMAND_ADMIN_EMPLOYEE_VIEW).forward(request, response);
}
}
