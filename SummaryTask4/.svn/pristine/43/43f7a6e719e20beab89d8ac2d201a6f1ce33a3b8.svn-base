package ua.nure.kotkov.SummaryTask4.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ua.nure.kotkov.SummaryTask4.db.bean.EmployeeBean;
import ua.nure.kotkov.SummaryTask4.db.bean.FlightBean;
import ua.nure.kotkov.SummaryTask4.db.bean.RequestBean;
import ua.nure.kotkov.SummaryTask4.db.entity.User;
import ua.nure.kotkov.SummaryTask4.exception.DBException;
import ua.nure.kotkov.SummaryTask4.exception.Messages;

/**
 * DB manager. Works with Apache Derby DB. Only the required DAO methods are
 * defined!
 * 
 * @author D.Kolesnikov
 * @author M.Kotkov
 *
 */
public final class DBManager {

	private static final Logger LOG = LogManager.getLogger(DBManager.class);

	// //////////////////////////////////////////////////////////
	// singleton
	// //////////////////////////////////////////////////////////

	private static DBManager instance;

	public static synchronized DBManager getInstance() throws DBException {
		if (instance == null) {
			instance = new DBManager();
		}
		return instance;
	}

	private DBManager() throws DBException {
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			ds = (DataSource) envContext.lookup("jdbc/ST4DB");
			LOG.trace("Data source ==> " + ds);
		} catch (NamingException ex) {
			LOG.error(Messages.ERR_CANNOT_OBTAIN_DATA_SOURCE, ex);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_DATA_SOURCE, ex);
		}
	}

	private DataSource ds;

	// //////////////////////////////////////////////////////////
	// SQL queries
	// //////////////////////////////////////////////////////////

	private static final String SQL_FIND_USER_BY_LOGIN = "SELECT * FROM users WHERE login=?";

	private static final String SQL_FIND_USER_BY_ID = "SELECT * FROM users WHERE id=?";

	private static final String SQL_UPDATE_USER = "UPDATE users SET login=?, password=?, first_name=?, last_name=?, "
			+ "role_id=? WHERE id=?";
	
	private static final String SQL_FIND_EMPLOYEE_BY_ID = "SELECT * FROM employees WHERE id=?";
	
	private static final String SQL_FIND_ALL_EMPLOYEES = "SELECT * FROM employees";
	
	private static final String SQL_FIND_ALL_FREE_EMPLOYEES = "SELECT * FROM employees WHERE avalible=true";
	
	private static final String SQL_ADD_REQUEST = "INSERT INTO requests values(DEFAULT, ?, ?, ?, 1)";
	
	private static final String SQL_UPDATE_REQUEST_STATUS = "UPDATE requests "
			+ "SET status_id=? WHERE id=?";

	private static final String SQL_FIND_FLIGHT_BY_STATUS = "SELECT * FROM flights WHERE "
			+ "status_id=(SELECT status_id FROM statuses WHERE status_name=?)";

	private static final String SQL_FIND_ALL_FLIGHTS = "SELECT * FROM flights";
	
	private static final String SQL_FIND_FLIGHT_BY_ID = "SELECT * FROM flights WHERE id=?";
	
	private static final String SQL_ADD_FLIGHT = "INSERT INTO flights VALUES(DEFAULT, ?, ?, ?, ?)";
	
	private static final String SQL_DELETE_FLIGHT = "DELETE FROM flights WHERE id=?";
	
	private static final String SQL_UPDATE_FLIGHT = "UPDATE flights SET origin=?, destination=?, departure_date=?, status_id=? WHERE id=?";
	
	private static final String SQL_CREW_EXISTS = "SELECT flight_id FROM crews WHERE flight_id=?";
	
	private static final String SQL_UPDATE_FLIGHT_STATUS = "UPDATE flights SET status_id=? WHERE id=?";
	
	private static final String SQL_ADD_EMPLOYEE = "INSERT INTO employees VALUES(DEFAULT, ?, ?, ?, DEFAULT, ?)";
	
	private static final String SQL_UPDATE_EMPLOYEE = "UPDATE employees SET first_name=?, last_name=?, job_id=?, email=? WHERE id=?";
	
	private static final String SQL_DELETE_EMPLOYEE = "DELETE FROM employees WHERE id=?";
	
	private static final String SQL_UPDATE_FLIGHT_CREW = "UPDATE crews SET pilot_id=?, navigator_id=?, "
			+ "operator_id=?, stewardess_id=? WHERE flight_id=?";
	
	private static final String SQL_FIND_CREW = "SELECT pilot_id, navigator_id, operator_id, stewardess_id FROM crews WHERE flight_id=?";
	
	private static final String SQL_ADD_FLIGHT_CREW = "INSERT INTO crews VALUES(?, ?, ?, ?, ?)";
	
	private static final String SQL_FREE_FLIGHT_CREW = "DELETE FROM crews WHERE flight_id=?";
	
	private static final String SQL_GET_REQUEST_BEANS = "SELECT r.id, u.first_name, u.last_name, "
			+ "r.header, r.description, s.status_name"
			+ "	FROM requests r, users u, request_statuses s"
			+ "	WHERE r.user_id=u.id AND r.status_id=s.id";

	private static final String SQL_GET_NEW_REQUEST_BEANS = "SELECT r.id, u.first_name, u.last_name, "
			+ "r.header, r.description, s.status_name"
			+ "	FROM requests r, users u, request_statuses s"
			+ "	WHERE r.user_id=u.id AND r.status_id=s.id AND s.id=1";
	
	private static final String SQL_GET_REQUEST_BEAN = "SELECT r.id, u.first_name, u.last_name, "
			+ "r.header, r.description, s.status_name"
			+ "	FROM requests r, users u, request_statuses s"
			+ "	WHERE r.user_id=u.id AND r.status_id=s.id AND r.id=?";
	/**
	 * Returns a DB connection from the Pool Connections. Before using this
	 * method you must configure the Date Source and the Connections Pool in
	 * your WEB_APP_ROOT/META-INF/context.xml file.
	 * 
	 * @return DB connection.
	 */
	public Connection getConnection() throws DBException {
		Connection con = null;
		try {
			con = ds.getConnection();
		} catch (SQLException ex) {
			LOG.error(Messages.ERR_CANNOT_OBTAIN_CONNECTION, ex);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_CONNECTION, ex);
		}
		return con;
	}

	// //////////////////////////////////////////////////////////s
	// Methods to obtain beans
	// //////////////////////////////////////////////////////////
	/**
	 * Returns all requests.
	 * 
	 * @return List of request beans.
	 */
	public List<RequestBean> getRequestBeans() throws DBException {
		List<RequestBean> RequestBeanList = new ArrayList<RequestBean>();
		Statement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(SQL_GET_REQUEST_BEANS);
			while (rs.next()) {
				RequestBeanList.add(extractRequestBean(rs));
			}
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			LOG.error(Messages.ERR_CANNOT_OBTAIN_REQUEST_BEANS, ex);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_REQUEST_BEANS, ex);
		} finally {
			close(con, stmt, rs);
		}
		return RequestBeanList;
	}
	
	/**
	 * Returns request with id
	 * 
	 * @param id
	 * @return RequestBean
	 * @throws DBException
	 */
	public RequestBean getRequestBean(int id) throws DBException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		RequestBean requestBean = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_GET_REQUEST_BEAN);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				requestBean = extractRequestBean(rs);
			}
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			LOG.error(Messages.ERR_CANNOT_OBTAIN_REQUEST_BEANS, ex);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_REQUEST_BEANS, ex);
		} finally {
			close(con, pstmt, rs);
		}
		return requestBean;
	}

	/**
	 * Returns new requests
	 * 
	 * @return List of new request beans
	 * @throws DBException
	 */
	public List<RequestBean> getNewRequestBeans() throws DBException {
		List<RequestBean> RequestBeanList = new ArrayList<RequestBean>();
		Statement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(SQL_GET_NEW_REQUEST_BEANS);
			while (rs.next()) {
				RequestBeanList.add(extractRequestBean(rs));
			}
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			LOG.error(Messages.ERR_CANNOT_OBTAIN_REQUEST_BEANS, ex);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_REQUEST_BEANS, ex);
		} finally {
			close(con, stmt, rs);
		}
		return RequestBeanList;
	}
	
	/**
	 * Adds new request
	 * 
	 * @param usrId
	 * @param hdr
	 * @param dscn
	 * @return 1 if request addedd
	 * @throws DBException
	 */
	public int addRequest(long usrId, String hdr, String dscn) throws DBException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		int result;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_ADD_REQUEST);
			int k = 1;
			pstmt.setLong(k++, usrId);
			pstmt.setString(k++, hdr);
			pstmt.setString(k, dscn);
			result = pstmt.executeUpdate();
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			LOG.error(Messages.ERR_CANNOT_ADD_REQUEST, ex);
			throw new DBException(Messages.ERR_CANNOT_ADD_REQUEST, ex);
		} finally {
			close(con, pstmt, rs);
		}
		return result;
	}
	
	/**
	 * Updates request status
	 * 
	 * @param requestId
	 * @param statusId
	 * @return 1 if request updated
	 * @throws DBException
	 */
	public int updateRequestStatus(int requestId, int statusId) throws DBException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		int result = 0;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_UPDATE_REQUEST_STATUS);
			int k = 1;
			pstmt.setInt(k++, statusId);
			pstmt.setInt(k, requestId);
			result = pstmt.executeUpdate();
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			LOG.error(Messages.ERR_CANNOT_ADD_REQUEST, ex);
			throw new DBException(Messages.ERR_CANNOT_ADD_REQUEST, ex);
		} finally {
			close(con, pstmt, rs);
		}
		return result;
	}
	// //////////////////////////////////////////////////////////
	// Entity access methods
	// //////////////////////////////////////////////////////////

	/**
	 * Returns all employees.
	 * 
	 * @return List of employee beans.
	 */
	public List<EmployeeBean> getEmployeeBeans() throws DBException {
		List<EmployeeBean> employeesList = new ArrayList<EmployeeBean>();
		Statement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(SQL_FIND_ALL_EMPLOYEES);
			while (rs.next()) {
				employeesList.add(extractEmployeeBean(rs));
			}
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			LOG.error(Messages.ERR_CANNOT_OBTAIN_EMPLOYEES, ex);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_EMPLOYEES, ex);
		} finally {
			close(con, stmt, rs);
		}
		return employeesList;
	}

	/**
	 * @return List of free employees
	 * @throws DBException
	 */
	public List<EmployeeBean> getFreeEmployees() throws DBException {
		List<EmployeeBean> employeesList = new ArrayList<EmployeeBean>();
		Statement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(SQL_FIND_ALL_FREE_EMPLOYEES);
			while (rs.next()) {
				employeesList.add(extractEmployeeBean(rs));
			}
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			LOG.error(Messages.ERR_CANNOT_OBTAIN_EMPLOYEES, ex);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_EMPLOYEES, ex);
		} finally {
			close(con, stmt, rs);
		}
		return employeesList;
	}
	/**
	 * Returns all flight entries.
	 * 
	 * @return List of flight entries.
	 */
	public List<FlightBean> getFlightBeans() throws DBException {
		List<FlightBean> listFlights = new ArrayList<>();
		Statement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(SQL_FIND_ALL_FLIGHTS);
			while (rs.next()) {
				listFlights.add(extractFlight(rs));
			}
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			LOG.error(Messages.ERR_CANNOT_OBTAIN_FLIGHT_BEANS, ex);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_FLIGHT_BEANS, ex);
		} finally {
			close(con, stmt, rs);
		}
		return listFlights;
	}
	
	/**
	 * @return List of scheduled flights
	 * @throws DBException
	 */
	public List<FlightBean> getScheduledFlightBeans() throws DBException {
		List<FlightBean> listFlights = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_FIND_FLIGHT_BY_STATUS);
			pstmt.setString(1, FlightStatus.SCHEDULED.getName());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				listFlights.add(extractFlight(rs));
			}
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			LOG.error(Messages.ERR_CANNOT_OBTAIN_FLIGHT_BEANS, ex);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_FLIGHT_BEANS, ex);
		} finally {
			close(con, pstmt, rs);
		}
		return listFlights;
	}
	
	/**
	 * Removes flight
	 * 
	 * @param flightId
	 * @return Crew of the removed flight
	 * @throws DBException
	 */
	public List<EmployeeBean> removeFlight(int flightId) throws DBException {
		List<EmployeeBean> employees = new ArrayList<EmployeeBean>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		int[] ids = null;
		try {
			con = getConnection();
			ids = findFlightCrewTransact(flightId, con);
			employees = findEmployeesTransact(ids, con);
			pstmt = con.prepareStatement(SQL_DELETE_FLIGHT);
			pstmt.setInt(1, flightId);
			pstmt.executeUpdate();
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			LOG.error(Messages.ERR_CANNOT_REMOVE_FLIGHT, ex);
			throw new DBException(Messages.ERR_CANNOT_REMOVE_FLIGHT, ex);
		} finally {
			close(con, pstmt, rs);
		}
		return employees;
	}
	
	
	/**
	 * Updates flight crew
	 * 
	 * @param pltId
	 * @param navId
	 * @param opId
	 * @param stewId
	 * @param fltId
	 * @return positive integer if crew updated
	 * @throws DBException
	 */
	public int updateFlightCrew(int pltId, int navId, int opId, int stewId, int fltId) throws DBException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		int result = 0;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_UPDATE_FLIGHT_CREW);
			int k = 1;
			pstmt.setInt(k++, pltId);
			pstmt.setInt(k++, navId);
			pstmt.setInt(k++, opId);
			pstmt.setInt(k++, stewId);
			pstmt.setInt(k, fltId);
			result = pstmt.executeUpdate();
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			LOG.error(Messages.ERR_CANNOT_UPDATE_FLIGHT_CREW, ex);
			throw new DBException(Messages.ERR_CANNOT_UPDATE_FLIGHT_CREW, ex);
		} finally {
			close(con, pstmt, rs);
		}
		return result;
	}

	/**
	 * Adds new flight crew
	 * 
	 * @param pltId
	 * @param navId
	 * @param opId
	 * @param stewId
	 * @param fltId
	 * @return positive integer if crew updated
	 * @throws DBException
	 */
	public int addFlightCrew(int pltId, int navId, int opId, int stewId, int fltId) throws DBException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		int result = 0;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_ADD_FLIGHT_CREW);
			int k = 1;
			pstmt.setInt(k++, fltId);
			pstmt.setInt(k++, pltId);
			pstmt.setInt(k++, navId);
			pstmt.setInt(k++, opId);
			pstmt.setInt(k, stewId);
			result = pstmt.executeUpdate();
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			LOG.error(Messages.ERR_CANNOT_UPDATE_FLIGHT_CREW, ex);
			throw new DBException(Messages.ERR_CANNOT_UPDATE_FLIGHT_CREW, ex);
		} finally {
			close(con, pstmt, rs);
		}
		return result;
	}
	
	/**
	 * Free flight crew
	 * 
	 * @param fltId
	 * @return List of free employees
	 * @throws DBException
	 */
	public List<EmployeeBean> freeFlightCrew(int fltId) throws DBException {
		List<EmployeeBean> employees = new ArrayList<EmployeeBean>();
		int[] crew;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			crew = findFlightCrewTransact(fltId, con);
			employees = findEmployeesTransact(crew, con);
			pstmt = con.prepareStatement(SQL_FREE_FLIGHT_CREW);
			pstmt.setInt(1, fltId);
			pstmt.executeUpdate();
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			LOG.error(Messages.ERR_CANNOT_UPDATE_FLIGHT_CREW, ex);
			throw new DBException(Messages.ERR_CANNOT_UPDATE_FLIGHT_CREW, ex);
		} finally {
			close(con, pstmt, rs);
		}
		return employees;
	}
	
	
	/**
	 * findFlightCrew for usage in transactions
	 * 
	 * @param fltId
	 * @param con
	 * @return array of crew members ids
	 * @throws DBException
	 */
	public int[] findFlightCrewTransact(int fltId, Connection con) throws DBException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int[] ids = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_FIND_CREW);
			pstmt.setInt(1, fltId);
			rs = pstmt.executeQuery();
			ids = extractCrew(rs);
		} catch (SQLException ex) {
			LOG.error(Messages.ERR_CANNOT_OBTAIN_EMPLOYEES, ex);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_EMPLOYEES, ex);
		} finally {
			close(pstmt);
			close(rs);
		}
		return ids;
	}
	
	/**
	 * Checks existence of flight
	 * 
	 * @param fltId
	 * @return true if flight exists
	 * @throws DBException
	 */
	public boolean checkIfFlightExists(int fltId) throws DBException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		boolean exist = false;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_CREW_EXISTS);
			pstmt.setInt(1, fltId);
			rs = pstmt.executeQuery();
			con.commit();
			exist = rs.next();
		} catch (SQLException ex) {
			rollback(con);
			LOG.error(Messages.ERR_CANNOT_UPDATE_FLIGHT_CREW, ex);
			throw new DBException(Messages.ERR_CANNOT_UPDATE_FLIGHT_CREW, ex);
		} finally {
			close(con, pstmt, rs);
		}
		return exist;
	}

	
	/**
	 * Updates status of flight
	 * 
	 * @param statusId
	 * @param flightId
	 * @return 1 if flight updated
	 * @throws DBException
	 */
	public int updateFlightStatus(int statusId, int flightId) throws DBException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		int result = 0;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_UPDATE_FLIGHT_STATUS);
			pstmt.setInt(1, statusId);
			pstmt.setInt(2, flightId);
			result = pstmt.executeUpdate();
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			throw new DBException(Messages.ERR_CANNOT_UPDATE_FLIGHT, ex);
		} finally {
			close(con, pstmt, rs);
		}
		return result;
	}
	
	/**
	 * Updates flight
	 * 
	 * @param flightId
	 * @param origin
	 * @param destination
	 * @param date
	 * @param statusId
	 * @return 1 if flight was updated
	 * @throws DBException
	 */
	public int updateFlight(int flightId, String origin, String destination, 
			Timestamp date, int statusId) throws DBException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		int result = 0;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_UPDATE_FLIGHT);
			int k=1;
			pstmt.setString(k++, origin);
			pstmt.setString(k++, destination);
			pstmt.setTimestamp(k++, date);
			pstmt.setInt(k++, statusId);
			pstmt.setInt(k, flightId);
			result = pstmt.executeUpdate();
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			throw new DBException(Messages.ERR_CANNOT_UPDATE_FLIGHT, ex);
		} finally {
			close(con, pstmt, rs);
		}
		return result;
	}

	/**
	 * Get flight by id
	 * 
	 * @param id
	 * @return flightBean
	 * @throws DBException
	 */
	public FlightBean getFlightBean(int id) throws DBException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		FlightBean flight = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_FIND_FLIGHT_BY_ID);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				flight = extractFlight(rs);
			}
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			throw new DBException(
					Messages.ERR_CANNOT_OBTAIN_FLIGHT_BEAN_BY_ID, ex);
		} finally {
			close(con, pstmt, rs);
		}
		return flight;
	}

	/**
	 * Adds new flight
	 * 
	 * @param fb
	 * @return 1 if flight was added
	 * @throws DBException
	 */
	public int addFlight(FlightBean fb) throws DBException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		int result;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_ADD_FLIGHT);
			int k = 1;
			pstmt.setString(k++, fb.getOrigin());
			pstmt.setString(k++, fb.getDestination());
			pstmt.setTimestamp(k++, fb.getDepartureDate());
			pstmt.setInt(k, fb.getStatusId());
			result = pstmt.executeUpdate();
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			throw new DBException(
					Messages.ERR_CANNOT_ADD_FLIGHT, ex);
		} finally {
			close(con, pstmt, rs);
		}
		return result;
	}

	/**
	 * Finds user with id
	 * 
	 * @param id
	 * @return User
	 * @throws DBException
	 */
	public User findUser(long id) throws DBException {
		User user = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_FIND_USER_BY_ID);
			pstmt.setLong(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				user = extractUser(rs);
			}
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_USER_BY_ID, ex);
		} finally {
			close(con, pstmt, rs);
		}
		return user;
	}

	/**
	 * Returns a user with the given login.
	 * 
	 * @param login
	 *            User login.
	 * @return User entity.
	 * @throws DBException
	 */
	public User findUserByLogin(String login) throws DBException {
		User user = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_FIND_USER_BY_LOGIN);
			pstmt.setString(1, login);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				user = extractUser(rs);
			}
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_USER_BY_LOGIN, ex);
		} finally {
			close(con, pstmt, rs);
		}
		return user;
	}

	/**
	 * Adds new employee
	 * 
	 * @param emp
	 * @return 1 if employee was added
	 * @throws DBException
	 */
	public int addEmployee(EmployeeBean emp) throws DBException {
		PreparedStatement pstmt = null;
		Connection con = null;
		int result = 0;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_ADD_EMPLOYEE);
			int k = 1;
			pstmt.setString(k++, emp.getFirstName());
			pstmt.setString(k++, emp.getLastName());
			pstmt.setInt(k++, emp.getJobId());
			pstmt.setString(k, emp.getEmail());
			result = pstmt.executeUpdate();
		} catch (SQLException ex) {
			rollback(con);
			throw new DBException(Messages.ERR_CANNOT_ADD_EMPLOYEE, ex);
		} finally {
			close(con);
			close(pstmt);
		}
		return result;
	}
	
	/**
	 * Find employee by id
	 * 
	 * @param id
	 * @return EmployeeBean
	 * @throws DBException
	 */
	public EmployeeBean findEmployee(int id) throws DBException {
		EmployeeBean emp = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_FIND_EMPLOYEE_BY_ID);
			pstmt.setLong(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				emp = extractEmployeeBean(rs);
			}
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_EMPLOYEE, ex);
		} finally {
			close(con, pstmt, rs);
		}
		return emp;
	}
	
	/**
	 * findEmployee for transaction usage
	 * 
	 * @param id
	 * @param con
	 * @return EmployeeBean
	 * @throws DBException
	 */
	public EmployeeBean findEmployeeTransact(int id, Connection con) throws DBException {
		EmployeeBean emp = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_FIND_EMPLOYEE_BY_ID);
			pstmt.setLong(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				emp = extractEmployeeBean(rs);
			}
		} catch (SQLException ex) {
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_EMPLOYEE, ex);
		} finally {
			close(pstmt);
			close(rs);
		}
		return emp;
	}
	
	/**
	 * find employees with ids
	 * 
	 * @param ids
	 * @return List of employee beans
	 * @throws DBException
	 */
	public List<EmployeeBean> findEmployees(int[] ids) throws DBException {
		List<EmployeeBean> employeeList = new ArrayList<EmployeeBean>();
		Statement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();

			StringBuilder query = new StringBuilder(
					"SELECT * FROM employees WHERE id IN (");
			for (int idAsString : ids) {
				query.append(idAsString).append(',');
			}
			query.deleteCharAt(query.length() - 1);
			query.append(')');

			stmt = con.createStatement();
			rs = stmt.executeQuery(query.toString());
			while (rs.next()) {
				employeeList.add(extractEmployeeBean(rs));
			}
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			throw new DBException(
					Messages.ERR_CANNOT_OBTAIN_EMPLOYEES, ex);
		} finally {
			close(con, stmt, rs);
		}
		return employeeList;
	}
	
	/**
	 * findEmployees for transaction usage
	 * 
	 * @param ids
	 * @param con
	 * @return List of employee beans
	 * @throws DBException
	 */
	public List<EmployeeBean> findEmployeesTransact(int[] ids, Connection con) throws DBException {
		List<EmployeeBean> employeeList = new ArrayList<EmployeeBean>();
		Statement stmt = null;
		ResultSet rs = null;
		try {
			StringBuilder query = new StringBuilder(
					"SELECT * FROM employees WHERE id IN (");
			for (int idAsString : ids) {
				query.append(idAsString).append(',');
			}
			query.deleteCharAt(query.length() - 1);
			query.append(')');

			stmt = con.createStatement();
			rs = stmt.executeQuery(query.toString());
			while (rs.next()) {
				employeeList.add(extractEmployeeBean(rs));
			}
		} catch (SQLException ex) {
			throw new DBException(
					Messages.ERR_CANNOT_OBTAIN_EMPLOYEES, ex);
		} finally {
			close(stmt);
			close(rs);
		}
		return employeeList;
	}
	
	/**
	 * Updates employee
	 * 
	 * @param employee
	 * @return 1 if employee was updated
	 * @throws DBException
	 */
	public int updateEmployee(EmployeeBean employee) throws DBException {
		PreparedStatement pstmt = null;
		Connection con = null;
		int result = 0;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_UPDATE_EMPLOYEE);
			int k = 1;
			pstmt.setString(k++, employee.getFirstName());
			pstmt.setString(k++, employee.getLastName());
			pstmt.setInt(k++, employee.getJobId());
			pstmt.setString(k++, employee.getEmail());
			pstmt.setInt(k, employee.getId());
			result = pstmt.executeUpdate();
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			throw new DBException(Messages.ERR_CANNOT_UPDATE_EMPLOYEE, ex);
		} finally {
			close(con);
		}
		return result;
	}
	/**
	 * Update user.
	 * 
	 * @param user
	 *            user to update.
	 * @throws DBException
	 */
	public int updateUser(User user) throws DBException {
		Connection con = null;
		int result = 0;
		try {
			con = getConnection();
			result = updateUser(con, user);
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			throw new DBException(Messages.ERR_CANNOT_UPDATE_USER, ex);
		} finally {
			close(con);
		}
		return result;
	}
	
	/**
	 * Remove employee
	 * @param empId
	 * @return removed employee
	 * @throws DBException
	 */
	public EmployeeBean removeEmployee(int empId) throws DBException {
		EmployeeBean emp = null;
		PreparedStatement pstmt = null;
		Connection con = null;
		try {
			con = getConnection();
			emp = findEmployeeTransact(empId, con);
			pstmt = con.prepareStatement(SQL_DELETE_EMPLOYEE);
			pstmt.setInt(1, empId);
			pstmt.executeUpdate();
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			throw new DBException(Messages.ERR_CANNOT_DELETE_EMPLOYEE, ex);
		} finally {
			close(con);
			close(pstmt);
		}
		return emp;
	}

	// //////////////////////////////////////////////////////////
	// Entity access methods (for transactions)
	// //////////////////////////////////////////////////////////

	/**
	 * Update user.
	 * 
	 * @param user
	 *            user to update.
	 * @throws SQLException
	 */
	private int updateUser(Connection con, User user) throws SQLException {
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt = con.prepareStatement(SQL_UPDATE_USER);
			int k = 1;
			pstmt.setString(k++, user.getLogin());
			pstmt.setString(k++, user.getPassword());
			pstmt.setString(k++, user.getFirstName());
			pstmt.setString(k++, user.getLastName());
			pstmt.setLong(k++, user.getRoleId());
			pstmt.setLong(k, user.getId());
			result = pstmt.executeUpdate();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	

	// //////////////////////////////////////////////////////////
	// DB util methods
	// //////////////////////////////////////////////////////////

	/**
	 * Closes a connection.
	 * 
	 * @param con
	 *            Connection to be closed.
	 */
	private void close(Connection con) {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException ex) {
				LOG.error(Messages.ERR_CANNOT_CLOSE_CONNECTION, ex);
			}
		}
	}

	/**
	 * Closes a statement object.
	 */
	private void close(Statement stmt) {
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException ex) {
				LOG.error(Messages.ERR_CANNOT_CLOSE_STATEMENT, ex);
			}
		}
	}

	/**
	 * Closes a result set object.
	 */
	private void close(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException ex) {
				LOG.error(Messages.ERR_CANNOT_CLOSE_RESULTSET, ex);
			}
		}
	}

	/**
	 * Closes resources.
	 */
	private void close(Connection con, Statement stmt, ResultSet rs) {
		close(rs);
		close(stmt);
		close(con);
	}

	/**
	 * Rollbacks a connection.
	 * 
	 * @param con
	 *            Connection to be rollbacked.
	 */
	private void rollback(Connection con) {
		if (con != null) {
			try {
				con.rollback();
			} catch (SQLException ex) {
				LOG.error("Cannot rollback transaction", ex);
			}
		}
	}

	// //////////////////////////////////////////////////////////
	// Other methods
	// //////////////////////////////////////////////////////////


	/**
	 * extract request from result set
	 * 
	 * @param rs
	 * @return RequestBean
	 * @throws SQLException
	 */
	private RequestBean extractRequestBean(ResultSet rs)
			throws SQLException {
		RequestBean bean = new RequestBean();
		bean.setId(rs.getInt(Fields.REQUEST_BEAN_ID));
		bean.setFirstName(rs.getString(Fields.FIRST_NAME));
		bean.setLastName(rs.getString(Fields.LAST_NAME));
		bean.setHeader(rs.getString(Fields.REQUEST_BEAN_HEADER));
		bean.setDescription(rs.getString(Fields.REQUEST_BEAN_DESCRIPTION));
		bean.setStatus(rs.getString(Fields.REQUEST_BEAN_STATUS_NAME));
		return bean;
	}

	/**
	 * Extracts a user entity from the result set.
	 * 
	 * @param rs
	 *            Result set from which a user entity will be extracted.
	 * @return User entity
	 */
	private User extractUser(ResultSet rs) throws SQLException {
		User user = new User();
		user.setId(rs.getLong(Fields.ENTITY_ID));
		user.setLogin(rs.getString(Fields.USER_LOGIN));
		user.setPassword(rs.getString(Fields.USER_PASSWORD));
		user.setFirstName(rs.getString(Fields.FIRST_NAME));
		user.setLastName(rs.getString(Fields.LAST_NAME));
		user.setRoleId(rs.getInt(Fields.USER_ROLE_ID));
		return user;
	}


	/**
	 * Extract employeeBean from result set
	 * 
	 * @param rs
	 * @return EmployeeBean
	 * @throws SQLException
	 */
	private EmployeeBean extractEmployeeBean(ResultSet rs) throws SQLException {
		EmployeeBean employee = new EmployeeBean();
		employee.setId(rs.getInt(Fields.ENTITY_ID));
		employee.setFirstName(rs.getString(Fields.FIRST_NAME));
		employee.setLastName(rs.getString(Fields.LAST_NAME));
		employee.setJobId(rs.getInt(Fields.EMPLOYEE_JOB_ID));
		employee.setEmail(rs.getString(Fields.EMPLOYEE_EMAIL));
		return employee;
	}

	/**
	 * Extract FlightBean from result set
	 * 
	 * @param rs
	 * @return FlightBean
	 * @throws SQLException
	 */
	private FlightBean extractFlight(ResultSet rs) throws SQLException {
		FlightBean flight = new FlightBean();
		flight.setId(rs.getInt(Fields.ENTITY_ID));
		flight.setOrigin(rs.getString(Fields.FLIGHT_ORIGIN));
		flight.setDestination(rs.getString(Fields.FLIGHT_DESTINATION));
		flight.setDepartureDate(rs.getTimestamp(Fields.FLIGHT_DEPARTURE_DATE));
		flight.setStatusId(rs.getInt(Fields.FLIGHT_STATUS_ID));
		return flight;
	}
	
	/**
	 * Extract crew ids from result set
	 * 
	 * @param rs
	 * @return array of crew ids
	 * @throws SQLException
	 */
	private int[] extractCrew(ResultSet rs) throws SQLException {
		int[] crew = new int[4];
		if(rs.next()){
			crew[0] = rs.getInt(Fields.CREW_PILOT_ID);
		}
		if(rs.next()){
			crew[1] = rs.getInt(Fields.CREW_NAVIGATOR_ID);
		}
		if(rs.next()){
			crew[2] = rs.getInt(Fields.CREW_OPERATOR_ID);
		}
		if(rs.next()){
			crew[3] = rs.getInt(Fields.CREW_STEWARDESS_ID);
		}
		return crew;
	}

}