package ua.nure.kotkov.SummaryTask4.command.dispatcher;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ua.nure.kotkov.SummaryTask4.Path;
import ua.nure.kotkov.SummaryTask4.MailSender.MailSender;
import ua.nure.kotkov.SummaryTask4.command.Command;
import ua.nure.kotkov.SummaryTask4.db.DBManager;
import ua.nure.kotkov.SummaryTask4.db.bean.EmployeeBean;
import ua.nure.kotkov.SummaryTask4.exception.AppException;

/**
 * Forms flight crew
 * 
 * @author M.Kotkov
 *
 */
public class FormCrewCommand extends Command {

	private static final long serialVersionUID = 7799697200622472095L;
	
	private static final Logger LOG = LogManager.getLogger(FormCrewCommand.class);
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		LOG.debug("Command starts");
		DBManager manager = DBManager.getInstance();
		int flightId = Integer.parseInt(request.getParameter("flightId"));
		LOG.trace("Got the request parameter: flight --> " + flightId);
		int pilotId = Integer.parseInt(request.getParameter("pilots"));
		LOG.trace("Got the request parameter: pilot --> " + pilotId);
		int navigatorId = Integer.parseInt(request.getParameter("navigators"));
		LOG.trace("Got the request parameter: navigator --> " + navigatorId);
		int operatorId = Integer.parseInt(request.getParameter("operators"));
		LOG.trace("Got the request parameter: operator --> " + operatorId);
		int stewardessId = Integer.parseInt(request.getParameter("stewardessess"));
		LOG.trace("Got the request parameter: stewardess --> " + stewardessId);
		boolean notifyByMail = (boolean) request.getSession().getAttribute("mailNotification");
		LOG.trace("Got the request parameter: notifyByMail --> " + notifyByMail);
		List<EmployeeBean> listEmployees = manager.findEmployees(new int[]{pilotId, navigatorId, operatorId, stewardessId});
		LOG.trace("Got employees --> " + listEmployees);
		boolean exist = manager.checkIfFlightExists(flightId);
		LOG.trace("crew №" + flightId + " exists: -->" + exist);
		int rows = 0;
		int success = 0;
		if(exist){
			rows = manager.updateFlightCrew(pilotId, navigatorId, operatorId, stewardessId, flightId);
		} else {
			rows = manager.addFlightCrew(pilotId, navigatorId, operatorId, stewardessId, flightId);
		}
		if(notifyByMail){
			MailSender.getInstance().sendMail(listEmployees, "Flight Assigned", "Dear sir/madame, you have been assigned to flight №" + flightId);
		}
		if(rows > 0){
			success = 1;
		}
		LOG.debug("Command finished");
		return Path.REDIRECT_FLIGHT + success;
	}

}
