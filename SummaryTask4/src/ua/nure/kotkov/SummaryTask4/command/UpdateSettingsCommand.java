package ua.nure.kotkov.SummaryTask4.command;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ua.nure.kotkov.SummaryTask4.Path;
import ua.nure.kotkov.SummaryTask4.db.Role;
import ua.nure.kotkov.SummaryTask4.exception.AppException;


/**
 * Updates session settings
 * 
 * @author M.Kotkov
 *
 */
public class UpdateSettingsCommand extends Command {

	private static final long serialVersionUID = -4501185076104977121L;
	
	private static final Logger LOG = LogManager.getLogger(UpdateSettingsCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		LOG.traceEntry();
		String loc = request.getParameter("locale");
		Role role = (Role) request.getSession().getAttribute("userRole");
		HttpSession session=request.getSession();
		session.setAttribute("locale", Locale.forLanguageTag(loc));
		LOG.trace("Set session attribute: locale --> " + loc);
		boolean notification = Boolean.parseBoolean(request.getParameter("mailNotification"));
		session.setAttribute("mailNotification", notification);
		LOG.trace("Set session attribute: mailNotification --> " + notification);
		String forward = null;
		switch(role){
		case ADMIN: forward = Path.COMMAND_ADMIN_FLIGHT_VIEW;
			break;
		case DISPATCHER: forward = Path.COMMAND_DISP_VIEW;
			break;
	}
		LOG.traceExit();
		return forward;
	}

}
