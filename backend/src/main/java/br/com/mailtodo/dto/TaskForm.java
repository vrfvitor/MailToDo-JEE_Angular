package br.com.mailtodo.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.mailtodo.entity.Priority;
import br.com.mailtodo.entity.Task;

public class TaskForm {

	@NotBlank
	private String title;
	@NotBlank
	private String description;
	@NotNull
	private Priority priority;

	public void transferDataTo(Task task) {
		task.setTitle(this.title);
		task.setDescription(this.description);
		task.setPriority(this.priority);
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}

}
