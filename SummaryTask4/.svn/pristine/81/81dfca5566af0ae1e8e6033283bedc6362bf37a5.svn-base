package ua.nure.kotkov.SummaryTask4.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ua.nure.kotkov.SummaryTask4.Path;
import ua.nure.kotkov.SummaryTask4.db.Role;
import ua.nure.kotkov.SummaryTask4.exception.AppException;


/**
 * 
 * Sets attribute for sorting flights in Admin/Dispatcher Views
 * 
 * @author M.Kotkov
 *
 */
public class SortFlightCommand extends Command {

	private static final long serialVersionUID = 7938944822717949305L;

	private static final Logger LOG = LogManager.getLogger(SortFlightCommand.class);
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		LOG.traceEntry();
		String sort = request.getParameter("sortFlight");
		request.setAttribute("sortFlight", sort);
		Role role = (Role) request.getSession().getAttribute("userRole");
		String forward = null;
		switch(role){
			case ADMIN: forward = Path.COMMAND_ADMIN_FLIGHT_VIEW;
				break;
			case DISPATCHER: forward = Path.COMMAND_DISP_VIEW;
				break;
		}
		LOG.traceExit();
		return forward;
	}

}
