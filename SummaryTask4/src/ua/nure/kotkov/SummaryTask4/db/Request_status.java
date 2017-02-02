package ua.nure.kotkov.SummaryTask4.db;

public enum Request_status {
	NEW, APPROVED, DECLINED;
	
	public String getName() {
		return name().toLowerCase();
	}
}
