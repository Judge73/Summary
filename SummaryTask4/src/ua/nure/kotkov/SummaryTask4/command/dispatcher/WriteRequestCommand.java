package ua.nure.kotkov.SummaryTask4.command.dispatcher;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ua.nure.kotkov.SummaryTask4.Path;
import ua.nure.kotkov.SummaryTask4.command.Command;
import ua.nure.kotkov.SummaryTask4.db.DBManager;
import ua.nure.kotkov.SummaryTask4.db.entity.User;
import ua.nure.kotkov.SummaryTask4.exception.AppException;

/**
 * Inserts new request into DB
 * 
 * @author M.Kotkov
 *
 */
public class WriteRequestCommand extends Command {

	private static final long serialVersionUID = -5913079992712271769L;
	
	private static final Logger LOG = LogManager.getLogger(WriteRequestCommand.class);
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		LOG.traceEntry();
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		long userId = user.getId();
		String header = request.getParameter("header");
		String description = request.getParameter("description");
		LOG.trace("Obtained parameters --> user:" + userId + ", header: " + header);
		int rows = 0;
		int success = 0;
		rows = DBManager.getInstance().addRequest(userId, header, description);
		if(rows>0){
			success = 1;
		}
		LOG.traceExit();
		return Path.REDIRECT_REQUEST + success;
	}

}
