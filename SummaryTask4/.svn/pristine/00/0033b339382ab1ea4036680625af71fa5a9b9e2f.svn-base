package ua.nure.kotkov.SummaryTask4.command.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ua.nure.kotkov.SummaryTask4.Path;
import ua.nure.kotkov.SummaryTask4.command.Command;
import ua.nure.kotkov.SummaryTask4.db.FlightStatus;
import ua.nure.kotkov.SummaryTask4.exception.AppException;

/**
 * Prepares form for adding new flight
 * 
 * @author M.Kotkov
 *
 */
public class AddFlightPageCommand extends Command {

	private static final long serialVersionUID = 959268266071908436L;
	
	private static final Logger LOG = LogManager.getLogger(AddFlightPageCommand.class);
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		LOG.traceEntry();
		int statuses = FlightStatus.values().length;
		request.setAttribute("flightStatuses", statuses);
		LOG.trace("Set attribute: flightStatuses --> " + statuses);
		LOG.traceExit();
		return Path.PAGE_ADD_FLIGHT;
	}

}
