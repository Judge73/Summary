package ua.nure.kotkov.SummaryTask4.command.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import ua.nure.kotkov.SummaryTask4.Path;
import ua.nure.kotkov.SummaryTask4.command.Command;
import ua.nure.kotkov.SummaryTask4.db.Job;
import ua.nure.kotkov.SummaryTask4.exception.AppException;

/**
 * prepares adminEmployeeView
 * 
 * @author M.Kotkov
 *
 */
public class AdminEmployeeViewCommand extends Command {

	private static final long serialVersionUID = -3019269548551768424L;

	private static final Logger LOG = LogManager.getLogger(AdminEmployeeViewCommand.class);
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		LOG.traceEntry();
		String[] jobNames = Job.names();
		ObjectMapper objectMapper = new ObjectMapper();
    	objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    	String jsonJobs = null;
    	try {
			jsonJobs = objectMapper.writeValueAsString(jobNames);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		request.setAttribute("jsonJobs", jsonJobs);
		LOG.trace("Set attribute: jsonJobs --> " + jsonJobs);
		LOG.traceExit();
		return Path.PAGE_ADMIN_EMPLOYEE_VIEW;
	}

}
