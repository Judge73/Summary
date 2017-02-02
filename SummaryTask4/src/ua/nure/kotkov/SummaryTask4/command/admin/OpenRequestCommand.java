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
import ua.nure.kotkov.SummaryTask4.db.bean.RequestBean;
import ua.nure.kotkov.SummaryTask4.exception.AppException;

/**
 * Open page with request data
 * 
 * @author M.Kotkov
 *
 */
public class OpenRequestCommand extends Command {

	private static final long serialVersionUID = -2368321247460846571L;

	private static final Logger LOG = LogManager.getLogger(OpenRequestCommand.class);
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		LOG.traceEntry();
		int requestId = Integer.parseInt(request.getParameter("requestId"));
		LOG.trace("Got parameter:requestId --> " + requestId);
		RequestBean rb = DBManager.getInstance().getRequestBean(requestId);
		LOG.trace("Found in DB: request --> " + rb);
		request.setAttribute("request", rb);
		LOG.trace("Set the request attribute: request --> " + rb);
		LOG.traceExit();
		return Path.PAGE_OPEN_REQUEST;
	}

}
