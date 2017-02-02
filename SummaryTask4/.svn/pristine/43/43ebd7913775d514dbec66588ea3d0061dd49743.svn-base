package ua.nure.kotkov.SummaryTask4.command.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ua.nure.kotkov.SummaryTask4.Path;
import ua.nure.kotkov.SummaryTask4.command.Command;
import ua.nure.kotkov.SummaryTask4.db.Job;
import ua.nure.kotkov.SummaryTask4.exception.AppException;

/**
 * Prepares form for adding employee
 * 
 * @author M.Kotkov
 *
 */
public class AddEmployeePageCommand extends Command {

	private static final long serialVersionUID = 6625143874286947379L;

	private static final Logger LOG = LogManager.getLogger(AddEmployeePageCommand.class);
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		LOG.traceEntry();
		int jobs = Job.values().length;
		request.setAttribute("jobs", jobs);
		LOG.trace("Set attribute: jobs --> " + jobs);
		LOG.traceExit();
		return Path.PAGE_ADD_EMPLOYEE;
	}

}
