package application;

import java.net.URL;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;

public class RoomsController implements Initializable {

	@FXML private TextField room_x;
	@FXML private TextField price_x;
	@FXML private ChoiceBox<String> type_x;
	@FXML private Button addRoom_b;
	@FXML private GridPane roomLayout;
	@FXML private Label currentDate_l;
	@FXML private Label status_l;
	@FXML private Label today_l;
	@FXML private ToggleButton viewAllRooms_b;
	@FXML private ToggleButton viewAvailableRooms_b;
	ToggleGroup tg = new ToggleGroup();

	List<Integer> roomList = new ArrayList<Integer>();
	LocalDate todayDate = LocalDate.now();
	
	public void refresh() {
		roomLayout.getChildren().clear();
		roomList.clear();			
		loadRooms();
	}
	
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
	
	public void clickAddRoom() {
		
		if(!areEmpty()) {
			
			viewAllRooms_b.setSelected(true);
			refresh();
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
		    			roomList.add(roomNum);
		    			refresh();
		    			
		    		}
		    		
		    	} catch (SQLException e1) {
		    		
		    		e1.printStackTrace();
		    		
		    	}
		    	
		    } else {
		    	
		    	JOptionPane.showMessageDialog(null, "Room Exists",
				      "Hey!", JOptionPane.ERROR_MESSAGE);
		    	
		    }
		    
		    room_x.clear();
		    price_x.clear();
		    type_x.setValue("Room Types");
		    
		} else {
			
			status_l.setText("Please complete all \n the required fields");
			
		}
		
	}
	
	public void loadRooms() {

		int rowInx = 0;
		int columnInx = 0;
		ResultSet resultSet = null;
		
		try {
			PreparedStatement availableRooms = Database.con().prepareStatement
					("SELECT ro.number,class,price,check_in,check_out\r\n"
			+ "FROM  hoteldatabase.rooms ro\r\n"
			+ "LEFT JOIN hoteldatabase.reservations r\r\n"
			+ "USING (number)  \r\n"
			+ "where  ro.number NOT IN \r\n"
			+ "(SELECT number FROM  hoteldatabase.rooms ro \r\n"
			+ "LEFT JOIN hoteldatabase.reservations r "
			+ "USING (number) WHERE NOT check_in > '"+todayDate+"' AND check_out > '"+todayDate+"')\r\n"
			+ "ORDER BY ro.number,check_in");
			
			PreparedStatement allRooms = Database.con().prepareStatement
					( "SELECT ro.number,class,price,check_in, check_out \r\n"
			+ "FROM `hoteldatabase`.rooms ro\r\n"
		    + "LEFT JOIN `hoteldatabase`.reservations r \r\n"
			+ "ON ro.number = r.number \r\n"
			+ "ORDER BY ro.number,check_in");
			
			if( viewAllRooms_b.isSelected()) {
				resultSet = allRooms.executeQuery();
			} 
			else if( viewAvailableRooms_b.isSelected()) {
					 resultSet = availableRooms.executeQuery();
			}
			
			while(resultSet.next()) {
				
				Date checkin = resultSet.getDate("check_in");
			    Date checkout = resultSet.getDate("check_out");
			    int roomNum = resultSet.getInt("number");
			    String roomType = resultSet.getString("class");
			    int roomPrice = resultSet.getInt("price");
			    Room room = new Room (roomNum,roomType,roomPrice );
			    if(!roomList.contains(roomNum)) {


			    	if(checkin !=null && checkout !=null ) {
			    		
			    		if(checkin.toLocalDate().compareTo(todayDate)
			    				* todayDate.compareTo(checkout.toLocalDate()) >= 0) {
			    			
			    			room.setBackground(new Background(
			    					new BackgroundFill(
			    							Color.RED,
					    					new CornerRadii(0),
					    					new Insets(0))));
			    			}
			    		}
				    roomLayout.add(room, columnInx, rowInx);				    
				    if(columnInx < 3) {
				    	++columnInx;
				    	} else {
				    		columnInx = 0;
				    		roomLayout.getRowConstraints()
				    		.add(new RowConstraints(80));
				    		++rowInx;
				    		}
				    }
		    	roomList.add(roomNum);
			}

			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		
	 }

	
	
	public void choiceBoxSetup() {
		String addType = "Click to add new Room Type";
		
		type_x.setValue("Room Types");
				
		ObservableList<String> roomTypes = 
			    FXCollections
				.observableArrayList(
						"Single","Double",
						"Twin","Suite");
		
		type_x.setItems(roomTypes);
		roomTypes.add(addType);
		type_x.setOnAction(event -> {
			if(type_x.getValue() == addType) {

		    }
		});
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		tg.selectedToggleProperty().addListener((obsVal, oldVal, newVal) -> {
		    if (newVal == null)
		        oldVal.setSelected(true);
		});
		viewAllRooms_b.setToggleGroup(tg);
		viewAvailableRooms_b.setToggleGroup(tg);
		viewAllRooms_b.setSelected(true);
		today_l.setText("TODAY: " + LocalDate.now());
		loadRooms();
		choiceBoxSetup();
	}
	
//	public void setMenu(Room selectedRoom) {
//		
//		ContextMenu contextMenu = new ContextMenu();
//		MenuItem del_b = new MenuItem("Delete");
//		del_b.setOnAction((event) -> {
//			
//			try {
//				PreparedStatement ps =
//						Database.con().prepareStatement
//		             ("SET FOREIGN_KEY_CHECKS=0;"+
//		      		 "	DELETE FROM `hoteldatabase`.`rooms` WHERE (`number` = '"+selectedRoom.getNumber()+"');");
//				
//				int status = ps.executeUpdate();
//				
//				if(status != 0) {
//					
//					status_l.setText("!!!Room Deleted!!!");
//				}
//			} catch (SQLException e1) {
//				e1.printStackTrace();
//			}
//	
//		});
//		contextMenu.getItems().addAll(del_b);
//		selectedRoom.setContextMenu(contextMenu);
//		
//		
//	}
	
}
