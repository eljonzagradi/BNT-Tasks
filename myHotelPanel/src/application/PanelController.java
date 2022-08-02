package application;

import java.net.URL;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import database.DB;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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
import javafx.scene.paint.Color;

public class PanelController implements Initializable {
	
	ObservableList<DisabledRange> busyDates = FXCollections.observableArrayList();

	
	@FXML private TextField room_x;
	@FXML private TextField ppn_x;
	@FXML private ChoiceBox<String> type_x;
	@FXML private Button add_b;
	@FXML private GridPane grid;
	@FXML private Label today_lb;
	
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
      ("INSERT INTO `hoteldatabase`.`rooms` (`number`, `class`, `price`) "
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
		Statement st2;
		ResultSet rt2;
		
		try {
			st = DB.con().createStatement();
			rt = st.executeQuery("select *\r\n"
					+ "from hoteldatabase.rooms ro\r\n"
					);
			st2 = DB.con().createStatement();
			rt2 = st2.executeQuery("select number,check_in,check_out\r\n"
					              + "from hoteldatabase.reservations");
			
		    while (rt.next()) {
		    	
		    	int roomNo_db = rt.getInt("number");
		    	String roomType_db = rt.getString("class");
		    	int ppn_db = rt.getInt("price");
		    	
		    			    	
		    	
		    	x.add(roomNo_db);
		    	
		    	Room room = new Room(roomNo_db, roomType_db, ppn_db);
//		    	if(rt2.next() && roomNo_db == rt2.getInt("number")){
//				if(unavailableRooms(rt2.getDate("check_in"),rt2.getDate("check_out"))) {
//					 room.setStyle("-fx-background-color: #ff0000; "); 
//				}}
				updateGridPane(room);
				
		    }
		    
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		
	}
	
	//not working
	public boolean unavailableRooms(Date in ,Date out) {
		
		Date date = Date.valueOf(LocalDate.now());
		if(in.getTime() <= date.getTime()
				|| out.getTime() > date.getTime()) {
		 return true; 
		}
		
		else {
			return false;		
		}
	}
		
	//can be better
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
		addCurrentRooms();
		choiceBoxSetup();
		today_lb.setText(LocalDate.now().toString());
	}

}
