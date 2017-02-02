package ua.nure.kotkov.SummaryTask4.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ua.nure.kotkov.SummaryTask4.Path;

/**
 * View settings command.
 * 
 * @author D.Kolesnikov
 * 
 */
public class ViewSettingsCommand extends Command {
	
	private static final long serialVersionUID = -8966091367557795666L;
	
	private static final Logger LOG = LogManager.getLogger(ViewSettingsCommand.class);
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {		
		LOG.traceEntry();

		LOG.traceExit();
		return Path.PAGE_SETTINGS;
	}

}