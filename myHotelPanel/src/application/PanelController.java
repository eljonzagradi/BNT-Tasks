package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.TextAlignment;

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

	public void setrNo(int rNo) {
		this.rNo = rNo;
	}

	public void setrType(String rType) {
		this.rType = rType;
	}

	public void setrPPN(int rPPN) {
		this.rPPN = rPPN;
	}

	
	//GridPane Setup
    RowConstraints rowConstraints = new RowConstraints(100);
   

	private static final int COLUMN_COUNT = 5;
    private int nextColumnIndex = COLUMN_COUNT;
    private int currentRow = 0;
		
	public void clickAdd() {
	
	if(!room_x.getText().isBlank() 
			&& type_x.getValue() != "Room Types"
			&& !ppn_x.getText().isBlank())
		
		setrNo(Integer.parseInt(room_x.getText()));
		setrType(type_x.getValue());
		setrPPN(Integer.parseInt(ppn_x.getText()));
		
		if(!dublicatesCheck(getrNo())) {
		
		Room room = new Room(getrNo(), getrType(), getrPPN());
		
		 if (nextColumnIndex >= COLUMN_COUNT) {
	            nextColumnIndex = 0;
	            currentRow++;
	            grid.getRowConstraints().add(rowConstraints);
	        }
		 grid.addRow(currentRow, room);
	        nextColumnIndex++;
	        
	       }	
	}
	
	
	public static void openCalendar(Button btn, int number, String type, int ppn) {
		
		System.out.println("You have chosen room number:" + number);
		System.out.println("Type: " + type);
		System.out.println("PPN: " + ppn);

	
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
	}

}
