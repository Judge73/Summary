package ua.nure.kotkov.SummaryTask4.command.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ua.nure.kotkov.SummaryTask4.Path;
import ua.nure.kotkov.SummaryTask4.command.Command;
import ua.nure.kotkov.SummaryTask4.db.DBManager;
import ua.nure.kotkov.SummaryTask4.db.Job;
import ua.nure.kotkov.SummaryTask4.db.bean.EmployeeBean;
import ua.nure.kotkov.SummaryTask4.exception.AppException;

/**
 * Prepares employee configuration page
 * 
 * @author M.Kotkov
 *
 */
public class ConfigureEmployeePageCommand extends Command {

	private static final long serialVersionUID = -1685820510908762862L;

	private static final Logger LOG = LogManager.getLogger(ConfigureEmployeePageCommand.class);
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		LOG.traceEntry();
		int employeeId = Integer.parseInt(request.getParameter("empId"));
		LOG.trace("Got parameter: employeeId --> " + employeeId);
		EmployeeBean employee = DBManager.getInstance().findEmployee(employeeId);
		LOG.trace("Got bean: employee --> " + employee);
		int jobs = Job.values().length;
		request.setAttribute("jobs", jobs);
		request.setAttribute("emp", employee);
		LOG.trace("Set attribute: emp --> " + employee);
		LOG.trace("Set attribute: jobs --> " + jobs);
		LOG.traceExit();
		return Path.PAGE_CONFIGURE_EMPLOYEE;
	}


}
