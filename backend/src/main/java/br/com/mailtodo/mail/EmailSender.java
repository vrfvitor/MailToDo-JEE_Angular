package br.com.mailtodo.mail;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import br.com.mailtodo.entity.Task;

@Stateless
public class EmailSender {

	@Resource(lookup = "java:jboss/mail/MyMailSession")
	private Session emailSession;
	private static String EMAIL_USER = "mail.smtp.user";
	private static String EMAIL_PASSWORD = "mail.smtp.pass";

	public void sendEmail(String recipient, List<Task> tasks) {
		try {
			MimeMessage email = new MimeMessage(emailSession);

			email.setFrom(new InternetAddress("no-reply@mailtodo.com", "MailToDo"));
			email.setRecipients(Message.RecipientType.TO, recipient);
			email.setSubject("Tasks to be Done");
			email.setContent("Txt sent", "text/html");

			Transport.send(email, emailSession.getProperty(EMAIL_USER), emailSession.getProperty(EMAIL_PASSWORD));

		} catch (MessagingException | UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}

}
