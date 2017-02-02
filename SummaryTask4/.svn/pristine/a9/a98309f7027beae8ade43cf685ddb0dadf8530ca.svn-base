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
import ua.nure.kotkov.SummaryTask4.exception.AppException;

/**
 * Updates request status
 * 
 * @author M.Kotkov
 *
 */
public class UpdateRequestStatusCommand extends Command {

	private static final long serialVersionUID = -3745330995998752865L;

	private static final Logger LOG = LogManager.getLogger(UpdateRequestStatusCommand.class);
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		LOG.traceEntry();
		int requestId = Integer.parseInt(request.getParameter("requestId"));
		LOG.trace("Got parameter: requestId --> " + requestId);
		int status = Integer.parseInt(request.getParameter("status"));
		LOG.trace("Got parameter: status --> " + status);
		int rows = 0;
		int success = 0;
		rows = DBManager.getInstance().updateRequestStatus(requestId, status);
		if(rows > 0){
			success = 1;
		}
		LOG.traceExit();
		return Path.REDIRECT_REQUEST + success;
	}

}
