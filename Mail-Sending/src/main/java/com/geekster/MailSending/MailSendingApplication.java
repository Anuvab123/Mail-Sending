package com.geekster.MailSending;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@SpringBootApplication
public class MailSendingApplication {

	public static void main(String[] args) {

		System.out.println("prepearing to send message...");
		String message="Hello, Dear this is message for security check.";
		String subject="CodersArea : Confirmation";
		String to="ghoshanuvab2000@gmail.com";
		String from="ghoshanuvab2000@gmail.com";

		sendEmail(message,subject,to,from);
	}

	//this is responsible to send email..
	private static void sendEmail(String message, String subject, String to, String from) {

		//variable for gmail
		String host="smtp.gmail.com";

		//get the system properties
		Properties properties=System.getProperties();
		System.out.println("PROPERTIES"+properties);

		//setting properties information to properties object
		//set host
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port","465");
		properties.put("mail.smtp.ssl.enable","true");
		properties.put("mail.smtp.auth","true");

		//step 1: to get the session object.
		Session session=Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("ghoshanuvab2000@gmail.com","gmrrvgmwwxxwswyf");
			}
		});

		session.setDebug(true);

		//step 2:compose the message
		MimeMessage m=new MimeMessage(session);

		try{
			//form email
			m.setFrom(from);

			//adding recipent
			m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

			//adding subject to message
			m.setSubject(subject);

            //adding text to message
            m.setText(message);

            //send

            //step 3: send the message using Transpose class
            Transport.send(m);

            System.out.println("Sent successfully....");
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

}
