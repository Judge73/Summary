package ua.nure.kotkov.SummaryTask4.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import ua.nure.kotkov.SummaryTask4.db.Job;

/**
 * Displays job name
 * 
 * @author M.Kotkov
 *
 */
public class JobTag extends SimpleTagSupport {
	
	private int jobId;
	
	public void setJobId(int id){
		jobId = id;
	}
	
	public void doTag() throws JspException, IOException {
		String jobName = Job.values()[jobId].getName();
	    JspWriter out = getJspContext().getOut();
	    out.println(jobName);
	  }
	}
