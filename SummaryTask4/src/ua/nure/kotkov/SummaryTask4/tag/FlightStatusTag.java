package ua.nure.kotkov.SummaryTask4.tag;

import java.io.IOException;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ua.nure.kotkov.SummaryTask4.db.FlightStatus;

/**
 * Displays flight status name 
 * 
 * @author M.Kotkov
 *
 */
public class FlightStatusTag extends SimpleTagSupport{
	
	private static final Logger LOG = LogManager.getLogger(FlightStatusTag.class);
	
	public void setStatusId(int statusId) throws IOException {
		LOG.traceEntry();
		int id = statusId;
		String statusName = FlightStatus.values()[id].getName();
		LOG.trace("Flight status ==> " + statusName);
	    JspWriter out = getJspContext().getOut();
	    out.print(statusName);
	    LOG.traceExit();
	}

	}
