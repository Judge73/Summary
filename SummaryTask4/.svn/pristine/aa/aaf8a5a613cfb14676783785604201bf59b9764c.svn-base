package ua.nure.kotkov.SummaryTask4.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ua.nure.kotkov.SummaryTask4.command.Command;
import ua.nure.kotkov.SummaryTask4.Path;
import ua.nure.kotkov.SummaryTask4.command.CommandContainer;
import ua.nure.kotkov.SummaryTask4.exception.AppException;

/**
 * Controller for commands execution
 * 
 * @author M.Kotkov
 *
 */
public class Controller extends HttpServlet {

	private static final long serialVersionUID = -7198522776909646331L;

	private static final Logger LOG = LogManager.getLogger(Controller.class);
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	private void process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		LOG.traceEntry();
		LOG.debug("Controller starts");
		
		String commandName = request.getParameter("command");
		LOG.trace("Request parameter: command --> " + commandName);
		Command command = CommandContainer.get(commandName);
		LOG.trace("Obtained command --> " + command);
		String forward = Path.PAGE_ERROR_PAGE;
		try {
			forward = command.execute(request, response);
		} catch (AppException ex) {
			request.setAttribute("errorMessage", ex.getMessage());
		}
		LOG.trace("Forward address --> " + forward);
		LOG.debug("Controller finished, now go to forward address --> " + forward);
		if(forward.startsWith("redir/")){
			String redir = forward.substring(6, forward.length());
			LOG.trace("Redirecting --> " + redir);
			response.sendRedirect(redir);
		} else {
		// go to forward
			request.getRequestDispatcher(forward).forward(request, response);
		}
		LOG.traceExit();
	}
}
