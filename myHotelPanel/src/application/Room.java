package application;


import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;
import javafx.scene.text.TextAlignment;

public class Room extends Button {
	
	private SimpleIntegerProperty number;
	private SimpleStringProperty type;
	private int pricePerNight;
	
	public Room(int number, String type,int pricePerNight) {
		
        this.number = new SimpleIntegerProperty(number);
        this.type = new SimpleStringProperty(type);
        this.pricePerNight =  pricePerNight;
        
        setTextAlignment(TextAlignment.CENTER);
		setMaxHeight(100);
		setMaxWidth(100);
		
		setText("ROOM \n" 
		        + "Number: " +number +"\n"
				+ "Type: " + type);
		
		setOnAction(e -> {
			
			System.out.println("Selected Room No: " + number + "\n" +
			"Cost " + pricePerNight + " $ per Night\n" );
			
		});

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


	public SimpleStringProperty getType() {
		return type;
	}
}
