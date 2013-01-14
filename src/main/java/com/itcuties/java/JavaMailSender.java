package com.itcuties.java;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * itcuties mail example
 * 
 * @author itcuties
 *
 */
public class JavaMailSender {

	// Run the mail example
	public static void main(String[] args) {
		// Send email
		sendEmail();
		
	}
	
	/**
	 * Send the email via SMTP using TLS and SSL
	 */
	private static void sendEmail() {
 
		// Create all the needed properties
		Properties connectionProperties = new Properties();
		// SMTP host
		connectionProperties.put("mail.smtp.host", "smtp.itcuties.com");
		// Is authentication enabled
		connectionProperties.put("mail.smtp.auth", "true");
		// Is TLS enabled
		connectionProperties.put("mail.smtp.starttls.enable", "true");
		// SSL Port
		connectionProperties.put("mail.smtp.socketFactory.port", "465");
		// SSL Socket Factory class
		connectionProperties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		// SMTP port, the same as SSL port :)
		connectionProperties.put("mail.smtp.port", "465");
		
		System.out.print("Creating the session...");
		
		// Create the session
		Session session = Session.getDefaultInstance(connectionProperties,
				new javax.mail.Authenticator() {	// Define the authenticator
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication("coding@itcuties.com","P@ssw0rd");
					}
				});
		
		System.out.println("done!");
		
		// Create and send the message
		try {
			// Create the message 
			Message message = new MimeMessage(session);
			// Set sender
			message.setFrom(new InternetAddress("coding@itcuties.com"));
			// Set the recipients
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("team@itcuties.com"));
			// Set message subject
			message.setSubject("Hello from Team ITCuties");
			// Set message text
			message.setText("Java is easy when you watch our tutorials ;)");
			
			System.out.print("Sending message...");
			// Send the message
			Transport.send(message);
			
			System.out.println("done!");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
