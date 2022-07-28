package application;

import java.io.IOException;
import java.lang.System.Logger;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.scene.control.TextField;

import javafx.scene.control.ChoiceBox;

public class RoomPanelController implements Initializable {
	@FXML
	private Button add_b;
	@FXML
	private TextField room_x;
	@FXML
	private ChoiceBox<String> type_x;
	
	@FXML private GridPane grid;
	
	public int noOfRooms = 0;
	

	public void clickAdd() {
		
		if(!room_x.getText().isBlank() 
				&& type_x.getValue() != "Room Types") {
	noOfRooms ++;
	int no = Integer.parseInt(room_x.getText());
	String ty = type_x.getValue();
	
	Button room = 
			new Button("ROOM\n" + no + "\n" +ty);
	
	
	room.setOnAction(e -> {openCalendar(room,no,ty);});
	//room.setAlignment(Pos.TOP_CENTER);
	room.setTextAlignment(TextAlignment.CENTER);
	room.setMaxSize(100, 100);

	//	for(int i = 0;i<noOfRooms; i++) {
	//		for(int j = 0; j < ) {
	//		}}
	
  grid.getChildren().add(room);
		}
//		room_x.clear();
//		type_x.setValue("Room Types");
			
	}
	
	public void choiceBoxSetup() {
		
		type_x.setValue("Room Types");
		
		ObservableList<String> roomTypes = 
				FXCollections
				.observableArrayList(
						"Single","Double",
						"Twin","Suite");
		
		type_x.setItems(roomTypes);
	
	}
	
	public static void openCalendar(Button btn, int number, String type) {
		
		System.out.println("You have chosen room number:" + number);
		System.out.println("Type: " + type);	

	
	}


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		choiceBoxSetup();
	}
	
}
