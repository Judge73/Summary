package ua.nure.kotkov.SummaryTask4.MailSender;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ua.nure.kotkov.SummaryTask4.db.bean.EmployeeBean;
import ua.nure.kotkov.SummaryTask4.exception.MailException;
import ua.nure.kotkov.SummaryTask4.exception.Messages;

/**
 * Sends mail notifications to employees
 * 
 * @author M.Kotkov
 *
 */
public class MailSender {
	
	private static MailSender instance;
	
	private static final Logger LOG = LogManager.getLogger(MailSender.class);
	
	private static final String SENDER = "mtest7658@gmail.com";
	private static final String PASSWORD = "judaspriest1992";
	private static final Properties PROPS = new Properties();
	
	public static synchronized MailSender getInstance(){
		if (instance == null) {
			instance = new MailSender();
		}
		return instance;
	}
	
	static {
		PROPS.put("mail.smtp.host", "smtp.gmail.com");
	    PROPS.put("mail.smtp.socketFactory.port", "465");
	    PROPS.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
	    PROPS.put("mail.smtp.auth", "true");
	    PROPS.put("mail.smtp.port", "465");
	}
	
	/**
	 * Send mail notification to employee
	 * 
	 * @param employee
	 * @param subject
	 * @param text
	 * @return true if sent successfully
	 * @throws MailException
	 */
	public boolean sendMail(EmployeeBean employee, String subject, String text) throws MailException{
		LOG.traceEntry();
        Session session = Session.getInstance(PROPS, new Authenticator() {

        	protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(SENDER, PASSWORD);
            }
        });
	    try {
	    	InternetAddress address = getAddress(employee);
	        LOG.trace("Got address --> " + address);
	        MimeMessage message = new MimeMessage(session);
	        message.setFrom(new InternetAddress(SENDER));
	        message.addRecipient(Message.RecipientType.TO, address);
	        message.setSubject(subject);
	        message.setText(text);
	        Transport.send(message);
	        LOG.trace("Sent message successfully");
	     }catch (MessagingException mex) {
	    	 throw new MailException(Messages.ERR_CANNOT_SEND_MAIL, mex);
	     }
	    LOG.traceExit();
	    return true;
	   }
	
	private InternetAddress getAddress(EmployeeBean employee) throws AddressException{
		InternetAddress address = new InternetAddress(employee.getEmail()); 
        return address;
	}

	/**
	 * Send mail notification to employees
	 * 
	 * @param employees
	 * @param subject
	 * @param text
	 * @return true if sent successfully
	 * @throws MailException
	 */
	public boolean sendMail(List<EmployeeBean> employees, String subject, String text) throws MailException{
		LOG.traceEntry();
        Session session = Session.getInstance(PROPS, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(SENDER, PASSWORD);
            }
        });
	    try {
	    	InternetAddress[] addressess = getAddressess(employees);
	        LOG.trace("Got addressess --> " + Arrays.toString(addressess));
	        MimeMessage message = new MimeMessage(session);
	        message.setFrom(new InternetAddress(SENDER));
	        message.addRecipients(Message.RecipientType.TO, addressess);
	        message.setSubject(subject);
	        message.setText(text);
	        Transport.send(message);
	        LOG.trace("Sent message successfully");
	     }catch (MessagingException mex) {
	    	 throw new MailException(Messages.ERR_CANNOT_SEND_MAIL, mex);
	     }
	    LOG.traceExit();
	    return true;
	   }
	
	private InternetAddress[] getAddressess(List<EmployeeBean> employees) throws AddressException{
		InternetAddress[] addrArr = new InternetAddress[employees.size()]; 
		int i = 0;
        for(EmployeeBean employee : employees){
        	addrArr[i] = new InternetAddress(employee.getEmail());
        	i++;
        }
        return addrArr;
	}
	}
