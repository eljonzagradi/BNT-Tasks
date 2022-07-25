package application;


import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Room {
	
	private SimpleIntegerProperty number;
	private SimpleStringProperty type;
	private SimpleIntegerProperty pricePerNight;
	
	public Room(int number, String type,int pricePerNight) {
        this.number = new SimpleIntegerProperty(number);
        this.type = new SimpleStringProperty(type);
        this.pricePerNight = new SimpleIntegerProperty(pricePerNight);
	}

	public Room() {
		
	}

	public int getNumber() {
		return number.get();
	}

	public String getType() {
		return type.get();
	}

	public int getPricePerNight() {
		return pricePerNight.get();
	}

	public void setNumber( int number) {
		this.number = new SimpleIntegerProperty(number);
	}

	public void setType(String type) {
		this.type = new SimpleStringProperty(type);;
	}

	public void setPricePerNight(int pricePerNight) {
		this.pricePerNight = new SimpleIntegerProperty(pricePerNight);
	}
	
	
	
	
	
	
	
	
	
	

}
