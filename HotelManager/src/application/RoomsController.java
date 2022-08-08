package application;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

public class RoomsController implements Initializable {

	@FXML private TextField room_x;
	@FXML private TextField price_x;
	@FXML private ChoiceBox<String> type_x;
	@FXML private Button addRoom_b;
	@FXML private GridPane roomLayout;
	@FXML private Label currentDate_l;
	@FXML private Label status_l;

	
	List<Integer> roomList = new ArrayList<Integer>();
	
	public boolean areEmpty() {
		
		if(     room_x.getText().isBlank()
			||  price_x.getText().isBlank()
			||  type_x.getValue() == "Room Types") 
		
		{
			return true;
		}
		
		else {
			
			return false;
		}
		
	}
	
	public void clickAddReservation() {
		
        if(!areEmpty()) {
		int roomNum = Integer.parseInt(room_x.getText());
		int roomPrice = Integer.parseInt(price_x.getText());
		String roomType = type_x.getValue();
		
		if(!roomList.contains(roomNum)) {
			
			try {
				PreparedStatement ps =
						Database.con().prepareStatement
		      ("INSERT INTO `hoteldatabase`.`rooms` (`number`, `class`, `price`) "
		      		+ "VALUES ('"+roomNum+"', '"+roomType+"', '"+roomPrice+"');");
				
				int status = ps.executeUpdate();
				
				if(status != 0) {
					
					status_l.setText("!!!Success!!!");
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
			roomList.clear();
			loadRooms();
		}}
        
        else {
			status_l.setText("Please complete all \n the required fields");

		}
	}
	
	public void loadRooms() {
		
		Statement statement;
		ResultSet resultSet;
		
		try {
			statement = Database.con().createStatement();
			
			resultSet = statement.executeQuery (
					"SELECT *"
			      + "FROM hoteldatabase.rooms" );
			
			int i = 0;
			int j = 0;
			
		    while (resultSet.next()) {
		    	
		    	int roomNum = resultSet.getInt("number");
		    	String roomType = resultSet.getString("class");
		    	int roomPrice = resultSet.getInt("price");
		        
		    	Room room = new Room(roomNum, roomType, roomPrice);		                
		    	
		        roomList.add(roomNum);
		        
		        roomLayout.add(room, j, i);

		        if(j < 3) {
			        ++j;		    		
		    	} 
		        else {
		    		j = 0;
		            roomLayout.getRowConstraints().add(new RowConstraints(80));
		            ++i;
		    	}
		        

		    }
		    
		} catch (SQLException e) {
			e.printStackTrace();	
		}	
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

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		choiceBoxSetup();
		loadRooms();
	}
	
	
}
