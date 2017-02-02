package ua.nure.kotkov.SummaryTask4.db.bean;

public class EmployeeBean {
	
	private int id;
	
	private String first_name;
	
	private String last_name;
	
	private int job_id;
	
	private String email;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return first_name;
	}
	public void setFirstName(String first_name) {
		this.first_name = first_name;
	}
	public String getLastName() {
		return last_name;
	}
	public void setLastName(String last_name) {
		this.last_name = last_name;
	}
	public int getJobId() {
		return job_id;
	}
	public void setJobId(int job_id) {
		this.job_id = job_id;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "EmployeeBean [id=" + id + ", first_name=" + first_name + ", last_name=" + last_name + ", job_id="
				+ job_id + " email=" + email +"]";
	}
	
}
