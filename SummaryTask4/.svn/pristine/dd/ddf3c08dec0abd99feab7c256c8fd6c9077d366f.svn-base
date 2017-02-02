package ua.nure.kotkov.SummaryTask4.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ua.nure.kotkov.SummaryTask4.Path;

/**
 * Logout command.
 * 
 * @author D.Kolesnikov
 * 
 */
public class LogoutCommand extends Command {

	private static final long serialVersionUID = -2785976616686657267L;

	private static final Logger LOG = LogManager.getLogger(LogoutCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		
		LOG.traceEntry();
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}
		LOG.traceExit();
		return Path.PAGE_LOGIN;
	}

}