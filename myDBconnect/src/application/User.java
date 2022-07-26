package application;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class User {
	
	private final SimpleStringProperty name;
	private final SimpleStringProperty surname;
	private final SimpleStringProperty age;
	private final SimpleStringProperty gender;
	private final SimpleStringProperty job;
	
		
	public User(String name, String surname, String string, String gender, String job) {
		this.name = new SimpleStringProperty(name);
		this.surname = new SimpleStringProperty(surname);
		this.age = new SimpleStringProperty(string);
		this.gender = new SimpleStringProperty(gender);
		this.job = new SimpleStringProperty(job);
	}


	public String getName() {
		return name.get();
	}


	public String getSurname() {
		return surname.get();
	}

	public String getAge() {
		return age.get();
	}

	public String getGender() {
		return gender.get();
	}


	public String getJob() {
		return job.get();
	}

}
