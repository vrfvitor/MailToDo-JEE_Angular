package br.com.mailtodo.mail;

import br.com.mailtodo.entity.Task;

public class MailMessage {

	private String title;
	private String description;

	public MailMessage(Task task) {
		this.title = task.getTitle();
		this.description = task.getDescription();
	}

	@Override
	public String toString() {
		return String.format(
				"<h4>%s</h4>"+
				"<p>%s</p>"  +
				"<hr>"
				, this.title, this.description);
	}


}
