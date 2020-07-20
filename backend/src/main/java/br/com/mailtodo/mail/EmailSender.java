package br.com.mailtodo.mail;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.io.FileUtils;

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
			email.setContent(generateContent(tasks), "text/html");

			Transport.send(email, emailSession.getProperty(EMAIL_USER), emailSession.getProperty(EMAIL_PASSWORD));

		} catch (MessagingException | UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}

	public String generateContent(List<Task> tasks) {
		StringBuilder builder = new StringBuilder();
		try {
			builder.append(contentFrom("/mail-template/upper.html"));
			
			builder.append(tasks.stream().map(MailMessage::new).map(mm -> mm.toString()).collect(Collectors.joining("")));
			
			builder.append(contentFrom("/mail-template/bottom.html"));
			
		} catch (IOException e) {
			Logger.getLogger(this.getClass().getName()).info(e.getLocalizedMessage());
		}
		return builder.toString();
	}

	private String contentFrom(String filePath) throws IOException {
		String path = this.getClass().getClassLoader().getResource(filePath).getFile();			
		return FileUtils.readFileToString(new File(path));
	}

}
