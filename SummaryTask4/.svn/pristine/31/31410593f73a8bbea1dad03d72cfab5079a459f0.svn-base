package ua.nure.kotkov.SummaryTask4.db;

import ua.nure.kotkov.SummaryTask4.db.bean.EmployeeBean;

public enum Job {
PILOT, NAVIGATOR, OPERATOR, STEWARDESS;
	
	public static Job getJob(EmployeeBean employee) {
		int jobId = employee.getJobId();
		return Job.values()[jobId];
	}
	
	public String getName() {
		return name().toLowerCase();
	}
	
}
