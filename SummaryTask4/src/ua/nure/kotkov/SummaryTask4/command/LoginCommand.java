package ua.nure.kotkov.SummaryTask4.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.nure.kotkov.SummaryTask4.exception.*;
import ua.nure.kotkov.SummaryTask4.Path;
import ua.nure.kotkov.SummaryTask4.db.DBManager;
import ua.nure.kotkov.SummaryTask4.db.Role;
import ua.nure.kotkov.SummaryTask4.db.entity.User;

/**
 * Login command.
 * 
 * @author D.Kolesnikov
 * @author M.Kotkov
 */
public class LoginCommand extends Command {

	private static final Logger LOG = LogManager.getLogger(LoginCommand.class);
	
	private static final long serialVersionUID = -3071536593627692473L;


	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) 
			throws IOException, ServletException, AppException {
		LOG.traceEntry();

		HttpSession session = request.getSession();

		// obtain login and password from a request
		DBManager manager = DBManager.getInstance();
		String login = request.getParameter("login");
		LOG.trace("Request parameter: login --> " + login);

		String password = request.getParameter("password");
		if (login == null || password == null || login.isEmpty() || password.isEmpty()) {
			throw new AppException("Login/password cannot be empty");
		}

		User user = manager.findUserByLogin(login);
		LOG.trace("Found in DB: user --> " + user);

		if (user == null || !password.equals(user.getPassword())) {
			throw new AppException("Cannot find user with such login/password");
		}

		Role userRole = Role.getRole(user);
		LOG.trace("userRole --> " + userRole);
		
		String forward = Path.PAGE_ERROR_PAGE;

		if (userRole == Role.ADMIN) {
			forward = Path.COMMAND_ADMIN_FLIGHT_VIEW;
		}

		if (userRole == Role.DISPATCHER) {
			forward = Path.COMMAND_DISP_VIEW;
		}

		session.setAttribute("user", user);
		LOG.trace("Set the session attribute: user --> " + user);
		session.setAttribute("mailNotification", false);
		LOG.trace("Set the session attribute: mailNotification --> " + false);
		session.setAttribute("userRole", userRole);
		LOG.trace("Set the session attribute: userRole --> " + userRole);
		LOG.info("User " + user + " logged as " + userRole.toString().toLowerCase());
		LOG.traceExit();
		return forward;
	}
}