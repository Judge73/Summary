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
 * Removes flight
 * 
 * @author M.Kotkov
 *
 */
public class RemoveFlightCommand extends Command {

	private static final long serialVersionUID = 1100025476460789066L;

	private static final Logger LOG = LogManager.getLogger(RemoveFlightCommand.class);
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		LOG.traceEntry();
		int flightId = Integer.parseInt(request.getParameter("flightId"));
		LOG.trace("Got param: flightId -->" + flightId);
		boolean notifyByMail = (boolean) request.getSession().getAttribute("mailNotification");
		int success = 0;
		List<EmployeeBean> employees = DBManager.getInstance().removeFlight(flightId);
		if(!employees.isEmpty()){
			success = 1;
		}
		if(notifyByMail){
			MailSender.getInstance().sendMail(employees, "Flight status", "Your flight was canceled");
		}
		LOG.traceExit();
		return Path.REDIRECT_FLIGHT + success;
	}

}
