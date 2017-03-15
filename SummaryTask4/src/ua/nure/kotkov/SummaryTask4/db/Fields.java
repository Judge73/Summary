package ua.nure.kotkov.SummaryTask4.db;

/**
 * Holder for fields names of DB tables and beans.
 * 
 * @author D.Kolesnikov
 * @author M.Kotkov
 * 
 */
public final class Fields {
	
	// entities
	public static final String ENTITY_ID = "id";
	public static final String FIRST_NAME = "first_name";
	public static final String LAST_NAME = "last_name";
	
	public static final String USER_LOGIN = "login";
	public static final String USER_PASSWORD = "password";
	public static final String USER_ROLE_ID = "role_id";
	
	public static final String FLIGHT_ORIGIN = "origin";
	public static final String FLIGHT_DESTINATION = "destination";
	public static final String FLIGHT_DEPARTURE_DATE = "departure_date";
	public static final String FLIGHT_STATUS_ID = "status_id";

	public static final String EMPLOYEE_JOB_ID = "job_id";
	public static final String EMPLOYEE_EMAIL = "email";
	
	public static final String CREW_FLIGHT_ID = "flight_id";
	public static final String CREW_PILOT_ID = "pilot_id";
	public static final String CREW_NAVIGATOR_ID = "navigator_id";
	public static final String CREW_OPERATOR_ID = "operator_id";
	public static final String CREW_STEWARDESS_ID = "stewardess_id";

	// beans
	public static final String REQUEST_BEAN_ID = "id";	
	public static final String REQUEST_BEAN_USER_ID = "user_id";	
	public static final String REQUEST_BEAN_HEADER = "header";	
	public static final String REQUEST_BEAN_DESCRIPTION = "description";
	public static final String REQUEST_BEAN_STATUS_NAME = "status_name";	
}