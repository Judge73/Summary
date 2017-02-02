package ua.nure.kotkov.SummaryTask4.command.admin;

import java.io.IOException;

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
 * Removes employee
 * 
 * @author M.Kotkov
 *
 */
public class RemoveEmployeeCommand extends Command {

	private static final long serialVersionUID = 2158876705394000825L;

	private static final Logger LOG = LogManager.getLogger(RemoveEmployeeCommand.class);
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		LOG.traceEntry();
		int employeeId = Integer.parseInt(request.getParameter("empId"));
		LOG.trace("Got param: employeeId -->" + employeeId);
		boolean notifyByMail = (boolean) request.getSession().getAttribute("mailNotification");
		int success = 0;
		EmployeeBean emp = DBManager.getInstance().removeEmployee(employeeId);
		if(emp != null){
			success = 1;
		}
		if(notifyByMail){
			MailSender.getInstance().sendMail(emp, "Status", "You are fired!");
		}
		LOG.traceExit();
		return Path.REDIRECT_EMPLOYEE + success;
	}

}
