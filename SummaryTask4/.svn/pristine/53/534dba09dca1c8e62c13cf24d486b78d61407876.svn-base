package ua.nure.kotkov.SummaryTask4.comparator;

import java.util.Comparator;

import ua.nure.kotkov.SummaryTask4.db.bean.FlightBean;

/**
 * Compares flight ids
 * 
 * @author M.Kotkov
 *
 */
public class IdComparator implements Comparator<FlightBean> {

	@Override
	public int compare(FlightBean o1, FlightBean o2) {
		int id1 = o1.getId();
		int id2 = o2.getId();
		return id1 < id2 ? -1 : (id1 == id2 ? 0 : 1);
	}

}
