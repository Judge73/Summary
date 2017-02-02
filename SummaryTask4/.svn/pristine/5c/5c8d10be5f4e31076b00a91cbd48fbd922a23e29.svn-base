package ua.nure.kotkov.SummaryTask4.db;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import ua.nure.kotkov.SummaryTask4.db.bean.EmployeeBean;
import ua.nure.kotkov.SummaryTask4.db.bean.FlightBean;
import ua.nure.kotkov.SummaryTask4.db.entity.User;
import ua.nure.kotkov.SummaryTask4.exception.DBException;

public class DBManagerTest {

	DBManagerDelegate manager;
	
	@Before
	public void init(){
		manager = mock(DBManagerDelegate.class);
	}
	
	@Test
	public void addEmployee() throws DBException {
		EmployeeBean emp = mock(EmployeeBean.class);
		expect(manager.addEmployee(emp)).andReturn(1);
		replay(manager);
		assertEquals(1, manager.addEmployee(emp));
	}
	
	@Test
	public void addFlight() throws DBException {
		FlightBean fb = mock(FlightBean.class);
		expect(manager.addFlight(fb)).andReturn(1);
		replay(manager);
		assertEquals(1, manager.addFlight(fb));
	}
	
	@Test
	public void addFlightCrew() throws DBException {
		expect(manager.addFlightCrew(1, 4, 7, 10, 1)).andReturn(1);
		replay(manager);
		assertEquals(1, manager.addFlightCrew(1, 4, 7, 10, 1));
	}
	
	@Test
	public void addRequest() throws DBException {
		expect(manager.addRequest(1, "header", "description")).andReturn(1);
		replay(manager);
		assertEquals(1, manager.addRequest(1, "header", "description"));
	}
	
	@Test
	public void addFlightCheck() throws DBException {
		expect(manager.checkIfFlightExists(1)).andReturn(true);
		replay(manager);
		assertTrue(manager.checkIfFlightExists(1));
	}
	
	@Test
	public void findEmployee() throws DBException {
		EmployeeBean emp = mock(EmployeeBean.class);
		expect(manager.findEmployee(1)).andReturn(emp);
		replay(manager);
		assertTrue(manager.findEmployee(1) instanceof EmployeeBean);
	}
	
	@Test
	public void findEmployees() throws DBException {
		expect(manager.findEmployees(new int[]{1, 2})).andReturn(new ArrayList<EmployeeBean>());
		replay(manager);
		assertTrue(manager.findEmployees(new int[]{1, 2}) instanceof ArrayList);
	}
	
	@Test
	public void findUser() throws DBException {
		User usr = mock(User.class);
		expect(manager.findUser(1)).andReturn(usr);
		replay(manager);
		assertTrue(manager.findUser(1) instanceof User);
	}
	
	@Test
	public void findUserByLogin() throws DBException {
		User usr = mock(User.class);
		expect(manager.findUserByLogin("Vasili")).andReturn(usr);
		replay(manager);
		assertTrue(manager.findUserByLogin("Vasili") instanceof User);
	}
	
	@Test
	public void freeCrew() throws DBException {
		expect(manager.freeFlightCrew(1)).andReturn(new ArrayList<EmployeeBean>());
		replay(manager);
		assertTrue(manager.freeFlightCrew(1) instanceof ArrayList);
	}
	
	@Test
	public void getConnection() throws DBException {
		Connection con = mock(Connection.class);
		expect(manager.getConnection()).andReturn(con);
		replay(manager);
		assertTrue(manager.getConnection() instanceof Connection);
	}
	
}
