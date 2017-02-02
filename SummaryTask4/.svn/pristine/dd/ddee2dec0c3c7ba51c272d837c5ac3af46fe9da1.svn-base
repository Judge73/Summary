package ua.nure.kotkov.SummaryTask4.command.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ua.nure.kotkov.SummaryTask4.Path;
import ua.nure.kotkov.SummaryTask4.command.Command;
import ua.nure.kotkov.SummaryTask4.db.DBManager;
import ua.nure.kotkov.SummaryTask4.db.bean.EmployeeBean;
import ua.nure.kotkov.SummaryTask4.exception.AppException;

/**
 * prepares adminEmployeeView
 * 
 * @author M.Kotkov
 *
 */
public class AdminEmployeeViewCommand extends Command {

	private static final long serialVersionUID = -3019269548551768424L;

	private static final Logger LOG = LogManager.getLogger(AdminEmployeeViewCommand.class);
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		LOG.traceEntry();
		List<EmployeeBean> employees = DBManager.getInstance().getEmployeeBeans();
		LOG.trace("Found in DB: employees --> " + employees);
		request.setAttribute("employees", employees);
		LOG.trace("Set the request attribute: employees --> " + employees);
		LOG.traceExit();
		return Path.PAGE_ADMIN_EMPLOYEE_VIEW;
	}

}
