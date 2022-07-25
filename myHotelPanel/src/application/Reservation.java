package application;

import java.time.LocalDate;

import javafx.beans.property.SimpleStringProperty;

public class Reservation extends Room {
	
	private SimpleStringProperty  name,surname;
	private LocalDate  checkin;
	private LocalDate  checkout;

	public Reservation(int number, String type,int pricePerNight,
			           String name, String surname, 
			           LocalDate checkin, LocalDate checkout) {
		
		    super(number, type, pricePerNight);
	        this.name = new SimpleStringProperty(name);
	        this.surname = new SimpleStringProperty(surname);
	        this.checkin = checkin;
	        this.checkout = checkout;
    }
	
	public Reservation(String name, String surname, 
			           LocalDate checkin, LocalDate checkout) {
		    super();
	        this.name = new SimpleStringProperty(name);
	        this.surname = new SimpleStringProperty(surname);
	        this.checkin = checkin;
	        this.checkout = checkout;
    }
	  
	   public String getName() {
	        return name.get();
	    }
	   
	   public String geSurname() {
	        return surname.get();
	    }
	   
	   public LocalDate getCheckin() {
	        return checkin;
	    }
	   
	   public LocalDate getCheckout() {
	        return checkout;
	    }
	   
	   
	    public void setName(String name) {
	        this.name = new SimpleStringProperty(name);
	    }

	    public void setSurame(String surname) {
	        this.surname = new SimpleStringProperty(surname);
	    }
   
	    public void setCheckin(LocalDate checkin) {
	        this.checkin = checkin;
	    }
	    
	    public void setCheckout(LocalDate checkout) {
	        this.checkout = checkout;

	    }
}
