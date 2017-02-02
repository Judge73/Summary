package ua.nure.kotkov.SummaryTask4.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import ua.nure.kotkov.SummaryTask4.db.FlightStatus;

/**
 * Displays flight status name 
 * 
 * @author M.Kotkov
 *
 */
public class FlightStatusTag extends SimpleTagSupport {
	
	int statusId;
	
	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	public void doTag() throws JspException, IOException {
		String statusName = FlightStatus.values()[statusId].getName();
	    JspWriter out = getJspContext().getOut();
	    out.println(statusName);
	  }
	}
