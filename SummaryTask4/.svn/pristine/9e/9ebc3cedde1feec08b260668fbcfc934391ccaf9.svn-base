package ua.nure.kotkov.SummaryTask4.web.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Context listener.
 * 
 * @author D.Kolesnikov
 * 
 */
public class ContextListener implements ServletContextListener {

	private static final Logger LOG = LogManager.getLogger(ContextListener.class);
	
	public void contextDestroyed(ServletContextEvent event) {
		LOG.trace("Servlet context destruction starts");
		// no op
		LOG.trace("Servlet context destruction finished");
	}

	public void contextInitialized(ServletContextEvent event) {
		LOG.traceEntry();
		log("Servlet context initialization starts");
		System.out.println("here");
//		ServletContext servletContext = event.getServletContext();
//		initLog4J(servletContext);
		initCommandContainer();
	
		log("Servlet context initialization finished");
		LOG.traceExit();
	}

	/**
	 * Initializes CommandContainer.
	 * 
	 * @param servletContext
	 */
	private void initCommandContainer() {
		LOG.traceEntry();
		try {
			Class.forName("ua.nure.kotkov.SummaryTask4.command.CommandContainer");
		} catch (ClassNotFoundException ex) {
			throw new IllegalStateException("Cannot initialize Command Container");
		}
		LOG.traceExit();
	}
	
	private void log(String msg) {
		System.out.println("[ContextListener] " + msg);
	}
}