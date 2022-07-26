package app;

public class User {
	
	private String name;
	private String surname;
	private String gender;
	private String job;
	
	public User() {

		this.name = "";
		this.surname = "";
		this.gender = "";
		this.job = "";
	}

	public User(String name, String surname, String gender, String job) {
		
		this.name = name;
		this.surname = surname;
		this.gender = gender;
		this.job = job;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}
	
	
	
	

}
