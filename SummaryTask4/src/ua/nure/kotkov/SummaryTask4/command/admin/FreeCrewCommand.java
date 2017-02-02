package ua.nure.kotkov.SummaryTask4.command.admin;

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
 * Free crew
 * 
 * @author M.Kotkov
 *
 */
public class FreeCrewCommand extends Command {

	private static final long serialVersionUID = -2506844444351845028L;

	private static final Logger LOG = LogManager.getLogger(FreeCrewCommand.class);
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		LOG.traceEntry();
		int flightId = Integer.parseInt(request.getParameter("flightId"));
		LOG.trace("Got parameter: flightId --> " + flightId);
		boolean notifyByMail = (boolean) request.getSession().getAttribute("mailNotification");
		int success = 0;
		List<EmployeeBean> employees = DBManager.getInstance().freeFlightCrew(flightId);
		if(!employees.isEmpty()){
			success = 1;
		}
		if(notifyByMail){
			MailSender.getInstance().sendMail(employees, "Flight crew change", "You was unassigned from flight №" + flightId);
		}
		LOG.traceExit();
		return Path.REDIRECT_FLIGHT + success;
	}

}
