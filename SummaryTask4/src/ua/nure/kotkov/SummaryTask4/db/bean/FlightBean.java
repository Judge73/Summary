package ua.nure.kotkov.SummaryTask4.db.bean;

import java.sql.Timestamp;

public class FlightBean {

	private int id;
	private String origin;
	private String destination;
	private Timestamp departureDate;
	private int statusId;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public Timestamp getDepartureDate() {
		return departureDate;
	}
	public void setDepartureDate(Timestamp departureDate) {
		this.departureDate = departureDate;
	}
	public int getStatusId() {
		return statusId;
	}
	public void setStatusId(int status_id) {
		this.statusId = status_id;
	}
	public long getTime(){
		return departureDate.getTime();
	}
	@Override
	public String toString() {
		return "FlightBean [id=" + id + ", origin=" + origin + ", destination=" + destination + ", departureDate="
				+ departureDate + ", statusId=" + statusId + "]";
	}
	
}
