package ua.nure.kotkov.SummaryTask4.command.dispatcher;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ua.nure.kotkov.SummaryTask4.Path;
import ua.nure.kotkov.SummaryTask4.command.Command;
import ua.nure.kotkov.SummaryTask4.exception.AppException;

/**
 * Open page for writing requests
 * 
 * @author M.Kotkov
 *
 */
public class RequestPageCommand extends Command {

	private static final long serialVersionUID = -7847647433320745786L;

	private static final Logger LOG = LogManager.getLogger(RequestPageCommand.class);
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		LOG.traceEntry();
		LOG.traceExit();
		return Path.PAGE_REQUEST_FORM;
	}

}
