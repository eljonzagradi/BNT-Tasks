package application;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import database.DB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

public class PanelController implements Initializable {
	
	@FXML private TextField room_x;
	@FXML private TextField ppn_x;
	@FXML private ChoiceBox<String> type_x;
	@FXML private Button add_b;
	@FXML private GridPane grid;
	
	//newRoom variables
	private int rNo;
	private String rType;
	private int rPPN;
	
	Statement st;
	ResultSet rt;
	
	List<Integer> x = new ArrayList<Integer>();	
	
	public boolean dublicatesCheck(int no ) {
		boolean exists = false;
		
		if(x.contains(no)) {
			
			exists = true;
			
		} else {
			
		x.add(no);
		
		}
		return exists;
	}
		
	//getters and setter for newRoom variables.
	public int getrNo() {
		return rNo;
	}

	public String getrType() {
		return rType;
	}

	public int getrPPN() {
		return rPPN;
	}

	public void setrNo() {
		this.rNo = Integer.parseInt(
				this.room_x.getText());
	}

	public void setrType() {
		this.rType = 
				this.type_x.getValue();
	}

	public void setrPPN() {
		this.rPPN = Integer.parseInt(
				this.ppn_x.getText());
	}
	
	private static final int COLUMN_COUNT = 5;
    private int nextColumnIndex = COLUMN_COUNT;
    private int currentRow = 0;
		
	public void clickAdd() {
	
		//Note: Optimize this Method
	if(!room_x.getText().isBlank() 
			&& type_x.getValue() != "Room Types"
			&& !ppn_x.getText().isBlank())
		
		setrNo();
		setrType();
		setrPPN();
		
		if(!dublicatesCheck(getrNo())) {
		
		Room room = new Room(getrNo(), getrType(), getrPPN());
		
		try {
		PreparedStatement ps =
				DB.con().prepareStatement
      ("INSERT INTO `hoteldatabase`.`room` (`roomNo`, `type`, `pricePerNight`) "
      		+ "VALUES ('"+getrNo()+"', '"+getrType()+"', '"+getrPPN()+"');");
		
		int status = ps.executeUpdate();
		
		if(status != 0) {
			updateGridPane(room);
			
		}
		
	}catch (SQLException e1) {
		e1.printStackTrace();
	
	}}}
		
	public void choiceBoxSetup() {
		
		type_x.setValue("Room Types");
		
		ObservableList<String> roomTypes = 
				FXCollections
				.observableArrayList(
						"Single","Double",
						"Twin","Suite");
		
		type_x.setItems(roomTypes);
	
	}
	
	public void addCurrentRooms() {
		
		try {
			st = DB.con().createStatement();
			rt = st.executeQuery("SELECT * FROM hoteldatabase.room;");
		    while (rt.next()) { //iterate over every row returned
		    	
		    	int roomNo_db = rt.getInt("roomNo");
		    	String roomType_db = rt.getString("type");
		    	int ppn_db = rt.getInt("pricePerNight");
		    	
		    	x.add(roomNo_db);
				Room room = new Room(roomNo_db, roomType_db, ppn_db);
				updateGridPane(room);
		    }
		    
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		
	}
		
	
	public void updateGridPane(Room room) {
		
		if (nextColumnIndex >= COLUMN_COUNT) {
            nextColumnIndex = 0;
            ++currentRow;
            grid.getRowConstraints().add(new RowConstraints(100));
        }
		
		grid.addRow(currentRow, room);
        nextColumnIndex++;	
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		DB.isDBconnected();
		addCurrentRooms();
		choiceBoxSetup();
	}

}
