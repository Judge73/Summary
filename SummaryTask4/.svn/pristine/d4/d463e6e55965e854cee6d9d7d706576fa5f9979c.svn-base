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
import ua.nure.kotkov.SummaryTask4.db.bean.RequestBean;
import ua.nure.kotkov.SummaryTask4.exception.AppException;

/**
 * Prepares page with requests
 * 
 * @author M.Kotkov
 *
 */
public class OpenRequestsPageCommand extends Command {

	private static final long serialVersionUID = 2000291259043842571L;

	private static final Logger LOG = LogManager.getLogger(OpenRequestsPageCommand.class);
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		LOG.traceEntry();
		List<RequestBean> requests = DBManager.getInstance().getNewRequestBeans();
		LOG.trace("Found in DB: requests --> " + requests);
		request.setAttribute("requests", requests);
		LOG.traceExit();
		return Path.PAGE_REQUESTS;
	}

}
