package ua.nure.kotkov.SummaryTask4.command.admin;

import java.io.IOException;
import java.sql.Timestamp;

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
 * Adds new flight
 * 
 * @author M.Kotkov
 *
 */
public class AddFlightCommand extends Command{

	private static final long serialVersionUID = 4325541761090476453L;

	private static final Logger LOG = LogManager.getLogger(AddFlightCommand.class);
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		LOG.traceEntry();
		String origin = request.getParameter("origin");
		LOG.trace("Got param: origin -->" + origin);
		String destination = request.getParameter("destination");
		LOG.trace("Got param: destination -->" + destination);
		String dt = request.getParameter("date");
		LOG.trace("Got param: dt -->" + dt);
		Timestamp date = Timestamp.valueOf(dt + ":00");
		LOG.trace("Date: --> " + date);
		int statusId = Integer.parseInt(request.getParameter("statuses"));
		LOG.trace("Got param: statusId -->" + statusId);
		int rows = 0;
		int success = 0;
		FlightBean fb = new FlightBean();
		fb.setOrigin(origin);
		fb.setDestination(destination);
		fb.setDepartureDate(date);
		fb.setFlightStatusId(statusId);
		rows = DBManager.getInstance().addFlight(fb);
		if(rows > 0){
			success = 1;
		}
		LOG.traceExit();
		return Path.REDIRECT_FLIGHT + success;
	}

}
