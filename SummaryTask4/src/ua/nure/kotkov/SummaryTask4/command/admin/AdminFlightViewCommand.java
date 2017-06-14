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
import ua.nure.kotkov.SummaryTask4.db.FlightStatus;
import ua.nure.kotkov.SummaryTask4.exception.AppException;

/**
 * Prepares adminFlightView
 * 
 * @author M.Kotkov
 *
 */
public class AdminFlightViewCommand extends Command {

	private static final long serialVersionUID = 6945670196773656443L;

	private static final Logger LOG = LogManager.getLogger(AdminFlightViewCommand.class);
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		LOG.traceEntry();
		String[] statusNames = FlightStatus.names();
		ObjectMapper objectMapper = new ObjectMapper();
    	objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    	String jsonStatuses = null;
    	try {
			jsonStatuses = objectMapper.writeValueAsString(statusNames);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		request.setAttribute("jsonStatuses", jsonStatuses);
		LOG.trace("Set attribute: jsonStatuses --> " + jsonStatuses);
		LOG.traceExit();
		return Path.PAGE_ADMIN_FLIGHT_VIEW;
	}

}
