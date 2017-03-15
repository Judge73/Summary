package ua.nure.kotkov.SummaryTask4.command.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ua.nure.kotkov.SummaryTask4.Path;
import ua.nure.kotkov.SummaryTask4.command.Command;
import ua.nure.kotkov.SummaryTask4.db.DBManager;
import ua.nure.kotkov.SummaryTask4.db.FlightStatus;
import ua.nure.kotkov.SummaryTask4.db.bean.FlightBean;
import ua.nure.kotkov.SummaryTask4.exception.AppException;

/**
 * Prepares flight configuration page
 * 
 * @author M.Kotkov
 *
 */
public class ConfigureFlightPageCommand extends Command {

	private static final long serialVersionUID = -1688208138082225283L;

	private static final Logger LOG = LogManager.getLogger(ConfigureFlightPageCommand.class);
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		LOG.traceEntry();
		int flightId = Integer.parseInt(request.getParameter("flightId"));
		LOG.trace("Got parameter: flightId --> " + flightId);
		FlightBean flight = DBManager.getInstance().getFlightBean(flightId);
		LOG.trace("Found in DB: flight --> " + flight);
		int statuses = FlightStatus.values().length;
		request.setAttribute("flightStatuses", statuses);
		request.setAttribute("flight", flight);
		LOG.trace("Set attribute: flightStatuses --> " + statuses);
		LOG.trace("Set attribute: flight --> " + flight);
		LOG.traceExit();
		return Path.PAGE_CONFIGURE_FLIGHT;
	}

}
