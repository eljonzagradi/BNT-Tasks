package application;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

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
		
		setText("ROOM:" +number +"\n"
				+ "Type: " + type +"\n"
				+ "1 Night: " + pricePerNight + "$");
		                                                
		setOnAction(e -> {
			
			setCurrentPPN(pricePerNight);
			setSelectedRoom(number);
			
			Stage stage =  (Stage) getScene().getWindow();
			stage.close();
		
		Stage primaryStage = new Stage();
		
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/application/Main.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		
		});	
	}
	
	public void setCurrentPPN(int PPN) {
		
		MainController.currentPPN = PPN;
	}
	
    public void setSelectedRoom(int room) {
		
		MainController.selectedRoom = room;
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
