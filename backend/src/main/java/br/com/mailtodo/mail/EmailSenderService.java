package br.com.mailtodo.mail;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Singleton;

import br.com.mailtodo.dao.TaskDao;
import br.com.mailtodo.entity.Task;

@Singleton
public class EmailSenderService {
	
	private String recipient;
	
	@EJB
	private EmailSender sender;
	
	@EJB
	private TaskDao dao;
	
	@Schedule(hour = "*",minute = "*")
	public void sendEmail() {
		if(recipient != null) {
			List<Task> tasksToMail = dao.findTasksToMail();
			if (!tasksToMail.isEmpty()) {
				sender.sendEmail(recipient, tasksToMail);							
			}
		}
	}
	
	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

	public String getRecipient() {
		return this.recipient;
	}
	
}
