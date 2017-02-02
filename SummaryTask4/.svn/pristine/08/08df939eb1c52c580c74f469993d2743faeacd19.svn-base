package ua.nure.kotkov.SummaryTask4.db;

import java.sql.Connection;
import java.sql.Timestamp;
import java.util.List;

import ua.nure.kotkov.SummaryTask4.db.bean.EmployeeBean;
import ua.nure.kotkov.SummaryTask4.db.bean.FlightBean;
import ua.nure.kotkov.SummaryTask4.db.bean.RequestBean;
import ua.nure.kotkov.SummaryTask4.db.entity.User;
import ua.nure.kotkov.SummaryTask4.exception.DBException;

public class DBManagerDelegate {
	
	DBManager manager;

	public int hashCode() {
		return manager.hashCode();
	}

	public boolean equals(Object obj) {
		return manager.equals(obj);
	}

	public Connection getConnection() throws DBException {
		return manager.getConnection();
	}

	public List<RequestBean> getRequestBeans() throws DBException {
		return manager.getRequestBeans();
	}

	public RequestBean getRequestBean(int id) throws DBException {
		return manager.getRequestBean(id);
	}

	public List<RequestBean> getNewRequestBeans() throws DBException {
		return manager.getNewRequestBeans();
	}

	public int addRequest(long usrId, String hdr, String dscn) throws DBException {
		return manager.addRequest(usrId, hdr, dscn);
	}

	public int updateRequestStatus(int requestId, int statusId) throws DBException {
		return manager.updateRequestStatus(requestId, statusId);
	}

	public String toString() {
		return manager.toString();
	}

	public List<EmployeeBean> getEmployeeBeans() throws DBException {
		return manager.getEmployeeBeans();
	}

	public List<EmployeeBean> getFreeEmployees() throws DBException {
		return manager.getFreeEmployees();
	}

	public List<FlightBean> getFlightBeans() throws DBException {
		return manager.getFlightBeans();
	}

	public List<FlightBean> getScheduledFlightBeans() throws DBException {
		return manager.getScheduledFlightBeans();
	}

	public List<EmployeeBean> removeFlight(int flightId) throws DBException {
		return manager.removeFlight(flightId);
	}

	public int updateFlightCrew(int pltId, int navId, int opId, int stewId, int fltId) throws DBException {
		return manager.updateFlightCrew(pltId, navId, opId, stewId, fltId);
	}

	public int addFlightCrew(int pltId, int navId, int opId, int stewId, int fltId) throws DBException {
		return manager.addFlightCrew(pltId, navId, opId, stewId, fltId);
	}

	public List<EmployeeBean> freeFlightCrew(int fltId) throws DBException {
		return manager.freeFlightCrew(fltId);
	}

	public int[] findFlightCrewTransact(int fltId, Connection con) throws DBException {
		return manager.findFlightCrewTransact(fltId, con);
	}

	public boolean checkIfFlightExists(int fltId) throws DBException {
		return manager.checkIfFlightExists(fltId);
	}

	public int updateFlightStatus(int statusId, int flightId) throws DBException {
		return manager.updateFlightStatus(statusId, flightId);
	}

	public int updateFlight(int flightId, String origin, String destination, Timestamp date, int statusId)
			throws DBException {
		return manager.updateFlight(flightId, origin, destination, date, statusId);
	}

	public FlightBean getFlightBean(int id) throws DBException {
		return manager.getFlightBean(id);
	}

	public int addFlight(FlightBean fb) throws DBException {
		return manager.addFlight(fb);
	}

	public User findUser(long id) throws DBException {
		return manager.findUser(id);
	}

	public User findUserByLogin(String login) throws DBException {
		return manager.findUserByLogin(login);
	}

	public int addEmployee(EmployeeBean emp) throws DBException {
		return manager.addEmployee(emp);
	}

	public EmployeeBean findEmployee(int id) throws DBException {
		return manager.findEmployee(id);
	}

	public EmployeeBean findEmployeeTransact(int id, Connection con) throws DBException {
		return manager.findEmployeeTransact(id, con);
	}

	public List<EmployeeBean> findEmployees(int[] ids) throws DBException {
		return manager.findEmployees(ids);
	}

	public List<EmployeeBean> findEmployeesTransact(int[] ids, Connection con) throws DBException {
		return manager.findEmployeesTransact(ids, con);
	}

	public int updateEmployee(EmployeeBean employee) throws DBException {
		return manager.updateEmployee(employee);
	}

	public int updateUser(User user) throws DBException {
		return manager.updateUser(user);
	}

	public EmployeeBean removeEmployee(int empId) throws DBException {
		return manager.removeEmployee(empId);
	}
}
