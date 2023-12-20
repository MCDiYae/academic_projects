package com.appgestion.model;



import java.time.LocalDate;

public class Task {
	
	private String name ;
	private String description ;
	private LocalDate dueDate ;
	private int priority ;
	private boolean completed ;
	
	

	public Task(String name, String description, LocalDate dueDate, int priority, boolean completed) {
		
		this.name = name;
		this.description = description;
		this.dueDate = dueDate;
		this.priority = priority;
		this.completed = completed;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public boolean isCompleted() {
	    return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	
	
}
