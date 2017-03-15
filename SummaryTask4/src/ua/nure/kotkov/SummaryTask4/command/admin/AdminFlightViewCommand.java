package ua.nure.kotkov.SummaryTask4.command.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ua.nure.kotkov.SummaryTask4.Path;
import ua.nure.kotkov.SummaryTask4.command.Command;
import ua.nure.kotkov.SummaryTask4.db.DBManager;
import ua.nure.kotkov.SummaryTask4.db.bean.FlightBean;
import ua.nure.kotkov.SummaryTask4.exception.AppException;

/**
 * Prepares adminFlightView
 * 
 * @author M.Kotkov
 *
 */
public class AdminFlightViewCommand extends Command {

	private static final long serialVersionUID = 6945670196773656443L;

	private static final Logger LOG = LogManager.getLogger(AdminFlightViewCommand.class);
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		LOG.traceEntry();
		String sort = request.getParameter("sortFlight");
		LOG.trace("Got parameter: sorting --> " + sort);
		List<FlightBean> flights = DBManager.getInstance().getFlightBeans(sort);
		LOG.trace("Found in DB: flights --> " + flights);
		request.setAttribute("flights", flights);
		LOG.trace("Set the request attribute: flights --> " + flights);
		LOG.traceExit();
		return Path.PAGE_ADMIN_FLIGHT_VIEW;
	}

}
