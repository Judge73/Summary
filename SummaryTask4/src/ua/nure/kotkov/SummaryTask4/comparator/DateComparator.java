package ua.nure.kotkov.SummaryTask4.comparator;

import java.util.Comparator;

import ua.nure.kotkov.SummaryTask4.db.bean.FlightBean;

/**
 * Compares flight dates
 * 
 * @author M.Kotkov
 *
 */
public class DateComparator implements Comparator<FlightBean> {

	@Override
	public int compare(FlightBean o1, FlightBean o2) {
		return o1.getDepartureDate().compareTo(o2.getDepartureDate());
	}

}
