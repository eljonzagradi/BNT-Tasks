package application;

import java.net.URL;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import jfxtras.scene.control.CalendarPicker;

import javafx.scene.control.TableView;

import javafx.scene.control.TableColumn;

public class MainController implements Initializable { 
	
	@FXML private TableView<Reservation> table;
	
	@FXML private TableColumn<Reservation,String> nameC;
	@FXML private TableColumn<Reservation,String> surnameC;
	@FXML private TableColumn<Reservation, LocalDate> checkinC;
	@FXML private TableColumn<Reservation, LocalDate> checkoutC;
	@FXML private TableColumn<Reservation, Double> priceC;
	@FXML private TableColumn<Reservation, Integer> roomC;
	
	@FXML private ListView<Integer> roomList;
	
	@FXML private CalendarPicker datePicker;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
			
	}
	
	

}
