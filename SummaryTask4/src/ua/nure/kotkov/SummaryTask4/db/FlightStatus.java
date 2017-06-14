package ua.nure.kotkov.SummaryTask4.db;

import java.util.Arrays;

import ua.nure.kotkov.SummaryTask4.db.bean.FlightBean;

public enum FlightStatus {

	DELAYED, ARRIVED, EN_ROUTE, SCHEDULED, CANCELED;
	
	public static FlightStatus getStatus(FlightBean flight) {
		int statusId = flight.getFlightStatusId();
		return FlightStatus.values()[statusId];
	}
	
	public static String getStatusName(int id) {
		return FlightStatus.values()[id].getName();
	}
	
	public String getName() {
		return name().toLowerCase();
	}
	
	public static String[] names() {
	    return Arrays.toString(FlightStatus.values()).replaceAll("^.|.$", "").split(", ");
	}
	
}
