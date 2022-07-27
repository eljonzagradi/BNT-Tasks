package application;


import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Room {
	
	private SimpleIntegerProperty number;
	private int pricePerNight;
	
	public Room(int number, String type,int pricePerNight) {
        this.number = new SimpleIntegerProperty(number);
        this.pricePerNight =  pricePerNight;
	}

	public Room() {
		this.number = new SimpleIntegerProperty();
		this.pricePerNight = 0;
	}

	public int getNumber() {
		return number.get();
	}

	public int getPricePerNight() {
		return pricePerNight;
	}

	public void setNumber( int number) {
		this.number = new SimpleIntegerProperty(number);
	}

	public void setPricePerNight(int pricePerNight) {
		this.pricePerNight = pricePerNight;
	}
	
	
	
	
	
	
	
	
	
	

}
