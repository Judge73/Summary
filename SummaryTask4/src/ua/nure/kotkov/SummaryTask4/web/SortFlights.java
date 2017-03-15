package ua.nure.kotkov.SummaryTask4.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ua.nure.kotkov.SummaryTask4.db.DBManager;
import ua.nure.kotkov.SummaryTask4.db.bean.FlightBean;
import ua.nure.kotkov.SummaryTask4.exception.DBException;

@WebServlet("/SortFlights")
public class SortFlights extends HttpServlet {

	private static final Logger LOG = LogManager.getLogger(SortFlights.class);
	
	private static final long serialVersionUID = -1654894490724866546L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LOG.traceEntry();
		String sort = req.getParameter("sort");
		List<FlightBean> flights = null;
		try {
			flights = DBManager.getInstance().getFlightBeans(sort);
		} catch (DBException e) {
			e.printStackTrace();
		}
		LOG.trace("Found in DB: flights --> " + flights);
		req.setAttribute("flights", flights);
		LOG.trace("Set the request attribute: flights --> " + flights);
		LOG.traceExit();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
