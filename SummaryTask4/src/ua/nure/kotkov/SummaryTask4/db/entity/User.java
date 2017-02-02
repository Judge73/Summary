package ua.nure.kotkov.SummaryTask4.db.entity;

public class User extends Entity{
	
	private static final long serialVersionUID = 9059547201495804393L;
	
	private long id;
	private String login;
	private String firstName;
	private String lastName;
	private String password;
	private long roleId;
	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public long getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	
	@Override
	public String toString() {
		return "User [login=" + login + ", firstName=" + firstName + ", lastName=" + lastName + ", roleId=" + roleId
				+ "]";
	}
	
}
