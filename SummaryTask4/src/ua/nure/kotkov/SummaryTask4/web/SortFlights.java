package ua.nure.kotkov.SummaryTask4.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import ua.nure.kotkov.SummaryTask4.db.DBManager;
import ua.nure.kotkov.SummaryTask4.db.bean.FlightBean;
import ua.nure.kotkov.SummaryTask4.exception.DBException;

@WebServlet("/sortFlights")
public class SortFlights extends HttpServlet {

	private static final Logger LOG = LogManager.getLogger(SortFlights.class);
	
	private static final long serialVersionUID = -1654894490724866546L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response){
		LOG.traceEntry();
		String sort = request.getParameter("sort");
		LOG.trace("Got request parameter: sort --> " + sort);
		List<FlightBean> flights = null;
		try {
			flights = DBManager.getInstance().getFlightBeans(sort);
		} catch (DBException e) {
			e.printStackTrace();
		}
		LOG.trace("Found in DB: flights --> " + flights);
		ObjectMapper objectMapper = new ObjectMapper();
    	objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    	String jsonFlights = null;
		try {
			jsonFlights = objectMapper.writeValueAsString(flights);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
    	response.setContentType("application/json");
    	response.setCharacterEncoding("UTF-8");
    	try {
			response.getWriter().write(jsonFlights);
		} catch (IOException e) {
			e.printStackTrace();
		}
		LOG.trace("Set response --> " + jsonFlights);
		LOG.traceExit();
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response){
		doGet(request, response);
	}
}
