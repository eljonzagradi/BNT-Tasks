package application;

import java.sql.Date;
import java.time.Duration;
import java.time.LocalDate;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.DatePicker;

public class Reservation {
	
	private SimpleStringProperty  name,surname;
	private ObjectProperty<Date>  checkin;
	private ObjectProperty<Date> checkout;
	private LongProperty price;

	public Reservation(int roomNo,String name, String surname, 
			           Date checkin, Date checkout,
			           long price) {
		
	        this.name = new SimpleStringProperty(name);
	        this.surname = new SimpleStringProperty(surname);
	        this.checkin = new SimpleObjectProperty<Date>(checkin);
	        this.checkout = new SimpleObjectProperty<Date>(checkout);
	        this.price = new SimpleLongProperty(price);
    }

	

	public StringProperty  getName() {
	        return name;
	    }
	   
	   public StringProperty getSurname() {
	        return surname;
	    }
	   
	   public ObjectProperty<Date> getCheckin() {
	        return checkin;
	    }
	   
	   public ObjectProperty<Date> getCheckout() {
	        return checkout;
	    }
	   
	   public LongProperty getPrice() {
		return price;
		}

		public static long calcPrice(DatePicker in, DatePicker out,int pricePerNight) {
	    	
	    	long price = -1;
	    	long daysNo = -1;
	    		    	
	    	if(in != null && out != null) {
	    		
	    		daysNo = Duration.between(
	    				in.getValue().atStartOfDay(), 
	    				out.getValue().atStartOfDay()).toDays();
	    		
	    				price = pricePerNight * daysNo;
	    				
	    				return price;
	    	} else {
	    		
	    		return price;
	    		
	    	}
	    }
}
