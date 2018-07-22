package src.main.java.service;

import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.sun.mail.util.MailSSLSocketFactory;

import src.main.java.model.User;

public class EmailService {
	
	private static String GMAIL_MAIL;
	private static String GMAIL_PASSWORD;
	
	public static void sendMail(User user) {
		
		String body = "<a href=\"http://localhost:8080/Breizhlink/verify/" + user.getId() + "\">Verify your account</a>";
		
		Properties properties = System.getProperties();

        properties.setProperty("mail.smtp.host", "smtp.gmail.com");
        properties.setProperty("mail.smtp.socketFactory.port", "465");
        properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.port", "465");
        
		try {
			MailSSLSocketFactory sf = new MailSSLSocketFactory();
			sf.setTrustAllHosts(true); 
	        properties.put("mail.smtp.ssl.trust", "*");
	        properties.put("mail.smtp.ssl.socketFactory", sf);
		} catch (GeneralSecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Properties prop = new Properties();
    	InputStream input = null;
    	
		try {
			String filename = "config.properties";
			input = EmailService.class.getClassLoader().getResourceAsStream("src/main/resources/" + filename);
			if (input == null) {
				System.out.println("Sorry, unable to find " + filename);
				return;
			}

			// load a properties file from class path, inside static method
			prop.load(input);
			
			GMAIL_MAIL = prop.getProperty("gmail.email");
			GMAIL_PASSWORD = prop.getProperty("gmail.password");

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
        

        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(GMAIL_MAIL, GMAIL_PASSWORD);
            }
        });
        try {
            MimeMessage message = new MimeMessage(session); // email message
            message.setFrom(new InternetAddress(GMAIL_MAIL)); // setting header fields
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(user.getMail()));
            message.setSubject("Breizhlink Account Verification"); // subject line
            message.setContent(body, "text/html; charset=UTF-8");
            message.setHeader("charset", "UTF-8");

            Transport.send(message);
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
	    
	}
	

}
