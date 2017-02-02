package ua.nure.kotkov.SummaryTask4.command.admin;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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

public class FlightsStatusesCommand extends Command {

	private static final long serialVersionUID = 1350023117224629767L;

	private static final Logger LOG = LogManager.getLogger(FlightsStatusesCommand.class);
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		LOG.traceEntry();
		List<FlightBean> flights = DBManager.getInstance().getFlightBeans();
		LOG.trace("Found in DB: flights --> " + flights);
		boolean sortAsc = Boolean.parseBoolean(request.getParameter("sortType"));
		Map<String, Integer> statusesMap;
		if(sortAsc){
			statusesMap = new TreeMap<>(Collections.reverseOrder());
		} else {
			statusesMap = new TreeMap<>();
		}
		int i = 0;
		for(FlightBean flight : flights){
			String status = FlightStatus.getStatusName(flight.getStatusId());
			if(!statusesMap.containsKey(status)){
				statusesMap.put(status, 1);
			} else {
				i = statusesMap.get(status);
				statusesMap.put(status, ++i);
			}
		}
		request.setAttribute("sortAsc", !sortAsc);
		request.setAttribute("statMap", statusesMap);		
		LOG.trace("Set request attribute: statMap --> " + statusesMap);
		LOG.traceExit();
		return Path.PAGE_FLIGHTS_STATUSES;
	}

}
