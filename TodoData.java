package todo;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TodoData {
	private StringProperty Date;
	private StringProperty Description;
	private StringProperty Priority;
	private StringProperty Status;
	//private StringProperty User;
	
	public TodoData(String date, String description, String priority, String status){
		this.Date = new SimpleStringProperty(date);
		this.Description = new SimpleStringProperty(description);
		this.Priority = new SimpleStringProperty(priority);
		this.Status = new SimpleStringProperty(status);
		//this.User = new SimpleStringProperty(user);
	}
	
	public String getDate(){
		return Date.get();
	}
	
	public StringProperty DateProperty(){
		return Date;
	}
	
	public void setDate(String Date){
		this.Date.set(Date);
	}
	
	public String getDescription(){
		return Description.get();
	}
	
	public StringProperty DescriptionProperty(){
		return Description;
	}
	
	public void setDescription(String Description){
		this.Description.set(Description);
	}

	public String getPriority(){
		return Priority.get();
	}
	
	public StringProperty PriorityProperty(){
		return Priority;
	}
	
	public void setPriority(String Priority){
		this.Priority.set(Priority);
	}
	
	public String getStatus(){
		return Status.get();
	}
	
	public StringProperty StatusProperty(){
		return Status;
	}
	
	public void setStatus(String Status){
		this.Status.set(Status);
	}
	/*
	public String getUser(){
		return User.get();
	}
	
	public StringProperty UserProperty(){
		return User;
	}
	
	public void setUser(String User){
		this.User.set(User);
	}*/
}
