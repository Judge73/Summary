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
import ua.nure.kotkov.SummaryTask4.db.Job;
import ua.nure.kotkov.SummaryTask4.db.bean.EmployeeBean;
import ua.nure.kotkov.SummaryTask4.exception.AppException;

/**
 * Configured employee
 * 
 * @author M.Kotkov
 *
 */
public class ConfigureEmployeeCommand extends Command {

	private static final long serialVersionUID = 8176586778030507574L;

	private static final Logger LOG = LogManager.getLogger(ConfigureEmployeeCommand.class);
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		LOG.traceEntry();
		int employeeId = Integer.parseInt(request.getParameter("employeeId"));
		LOG.trace("Got param: employeeId -->" + employeeId);
		String firstName = request.getParameter("firstName");
		LOG.trace("Got param: firstName -->" + firstName);
		String lastName = request.getParameter("lastName");
		LOG.trace("Got param: lastName -->" + lastName);
		String email = request.getParameter("email");
		LOG.trace("Got param: email -->" + email);
		int jobId = Integer.parseInt(request.getParameter("job"));
		LOG.trace("Got param: job -->" + jobId);
		boolean notifyByMail = (boolean) request.getSession().getAttribute("mailNotification");
		EmployeeBean emp = new EmployeeBean();
		emp.setId(employeeId);
		emp.setFirstName(firstName);
		emp.setLastName(lastName);
		emp.setJobId(jobId);
		emp.setEmail(email);
		int rows = 0;
		int success = 0;
		rows = DBManager.getInstance().updateEmployee(emp);
		if(rows>0){
			success = 1;
		}
		if(notifyByMail){
			MailSender.getInstance().sendMail(emp, "Entry updated", "Your DB entry has been updated --> First Name: " + emp.getFirstName()
			+ " Last Name: " + emp.getLastName() + " Job: " + Job.getJob(emp).getName());
		}
		LOG.traceExit();
		return Path.REDIRECT_EMPLOYEE + success;
	}

}
