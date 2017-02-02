package ua.nure.kotkov.SummaryTask4;


/**
 * Path holder (jsp pages, controller commands).
 * 
 * @author M.Kotkov
 */
public final class Path {
	
	// pages
	public static final String PAGE_LOGIN = "/login.jsp";
	public static final String PAGE_ERROR_PAGE = "/WEB-INF/jsp/error_page.jsp";
	public static final String PAGE_DISP_VIEW = "/WEB-INF/jsp/dispatcher/dispatcher_View.jsp";
	public static final String PAGE_ADMIN_FLIGHT_VIEW = "/WEB-INF/jsp/admin/adminFlightView.jsp";
	public static final String PAGE_ADMIN_EMPLOYEE_VIEW = "/WEB-INF/jsp/admin/adminEmployeeView.jsp";
	public static final String PAGE_SETTINGS = "/WEB-INF/jsp/settings.jsp";
	public static final String PAGE_REQUEST_FORM = "/WEB-INF/jsp/dispatcher/requestForm.jsp";
	public static final String PAGE_CONFIGURE_FLIGHT = "/WEB-INF/jsp/admin/configureFlight.jsp";
	public static final String PAGE_ADD_FLIGHT = "/WEB-INF/jsp/admin/addFlight.jsp";
	public static final String PAGE_ADD_EMPLOYEE = "/WEB-INF/jsp/admin/addEmployee.jsp";
	public static final String PAGE_CONFIGURE_EMPLOYEE = "/WEB-INF/jsp/admin/configureEmployee.jsp";
	public static final String PAGE_REQUESTS = "/WEB-INF/jsp/admin/requestsPage.jsp";
	public static final String PAGE_OPEN_REQUEST = "/WEB-INF/jsp/admin/openRequest.jsp";
	public static final String PAGE_FLIGHTS_STATUSES = "/WEB-INF/jsp/admin/flightsStatuses.jsp";
	// commands
	public static final String COMMAND_DISP_VIEW = "/controller?command=dispatcherView";
	public static final String COMMAND_FORM_CREW = "/controller?command=formCrew";
	public static final String COMMAND_ADMIN_FLIGHT_VIEW = "/controller?command=adminFlightView";
	public static final String COMMAND_ADMIN_EMPLOYEE_VIEW = "/controller?command=adminEmployeeView";
	public static final String COMMAND_OPEN_REQUEST = "/controller?command=openRequest";
	public static final String COMMAND_REQUESTS = "/controller?command=openRequestsPage";
	
	public static final String REDIRECT_FLIGHT = "redir/RedirectFlight?s=";
	public static final String REDIRECT_EMPLOYEE = "redir/RedirectEmployee?s=";
	public static final String REDIRECT_REQUEST = "redir/RedirectRequest?s=";
}