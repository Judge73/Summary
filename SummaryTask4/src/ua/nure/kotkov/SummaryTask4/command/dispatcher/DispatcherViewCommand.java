package ua.nure.kotkov.SummaryTask4.command.dispatcher;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ua.nure.kotkov.SummaryTask4.Path;
import ua.nure.kotkov.SummaryTask4.command.Command;
import ua.nure.kotkov.SummaryTask4.comparator.*;
import ua.nure.kotkov.SummaryTask4.db.DBManager;
import ua.nure.kotkov.SummaryTask4.db.FlightStatus;
import ua.nure.kotkov.SummaryTask4.db.Job;
import ua.nure.kotkov.SummaryTask4.db.bean.EmployeeBean;
import ua.nure.kotkov.SummaryTask4.db.bean.FlightBean;
import ua.nure.kotkov.SummaryTask4.exception.AppException;

/**
 * Prepares dispatcher view
 * 
 * @author M.Kotkov
 *
 */
public class DispatcherViewCommand extends Command {

	private static final long serialVersionUID = -7600503651357064286L;
	
	private static final Logger LOG = LogManager.getLogger(DispatcherViewCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		
		LOG.debug("Command starts");
		List<EmployeeBean> employees = DBManager.getInstance().getFreeEmployees();
		LOG.trace("Found in DB: employees --> " + employees);
		List<FlightBean> flights = DBManager.getInstance().getScheduledFlightBeans();
		LOG.trace("Found in DB: flights --> " + flights);
		List<EmployeeBean> pilots = new ArrayList<>();
		List<EmployeeBean> navigators = new ArrayList<>();
		List<EmployeeBean> operators = new ArrayList<>();
		List<EmployeeBean> stewardessess = new ArrayList<>();
		String sort = (String) request.getAttribute("sortFlight");
		LOG.trace("Got attribute: sorting --> " + sort);
		for(EmployeeBean e : employees){
			switch(Job.getJob(e)){
			case PILOT:
				pilots.add(e);
				break;
			case NAVIGATOR:
				navigators.add(e);
				break;
			case OPERATOR:
				operators.add(e);
				break;
			case STEWARDESS:
				stewardessess.add(e);
				break;
			}
		}
		int statuses = FlightStatus.values().length;
		if(sort!=null){
			switch(sort){
				case "Id": Collections.sort(flights, new IdComparator());
				break;
				case "Origin": Collections.sort(flights, new OriginComparator());
				break;
				case "Destination": Collections.sort(flights, new DestinationComparator());
				break;
				case "Date": Collections.sort(flights, new DateComparator());
				break;
			}
		}
		request.setAttribute("statuses", statuses);
		request.setAttribute("flights", flights);
		request.setAttribute("pilots", pilots);
		request.setAttribute("navigators", navigators);
		request.setAttribute("operators", operators);
		request.setAttribute("stewardessess", stewardessess);
		
		LOG.trace("Set the request attribute: statuses --> " + statuses);
		LOG.trace("Set the request attribute: pilots --> " + pilots);
		LOG.trace("Set the request attribute: navigators --> " + navigators);
		LOG.trace("Set the request attribute: operators --> " + operators);
		LOG.trace("Set the request attribute: stewardessess --> " + stewardessess);
		LOG.debug("Command finished");
		return Path.PAGE_DISP_VIEW;
	}

}
