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
 * Adds new employee
 * 
 * @author M.Kotkov
 *
 */
public class AddEmployeeCommand extends Command {

	private static final long serialVersionUID = -664294519316076139L;

	private static final Logger LOG = LogManager.getLogger(AddEmployeeCommand.class);
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		LOG.traceEntry();
		String firstName = request.getParameter("firstName");
		LOG.trace("Got param: firstName -->" + firstName);
		String lastName = request.getParameter("lastName");
		LOG.trace("Got param: lastName -->" + lastName);
		String email = request.getParameter("email");
		LOG.trace("Got param: email -->" + email);
		int jobId = Integer.parseInt(request.getParameter("job"));
		LOG.trace("Got param: jobId -->" + jobId);
		boolean notifyByMail = (boolean) request.getSession().getAttribute("mailNotification");
		EmployeeBean emp = new EmployeeBean();
		emp.setFirstName(firstName);
		emp.setLastName(lastName);
		emp.setJobId(jobId);
		emp.setEmail(email);
		int rows = 0;
		int success = 0;
		rows = DBManager.getInstance().addEmployee(emp);
		if(rows > 0){
			success = 1;
		}
		if(notifyByMail){
			MailSender.getInstance().sendMail(emp, "Welcome", "Welcome to your new job");
		}
		LOG.traceExit();
		return Path.REDIRECT_EMPLOYEE + success;
	}

}
