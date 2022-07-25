package application;

import java.net.URL;
import java.time.LocalDate;
import java.time.Month;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class CalendarController implements Initializable  {
	
	@FXML TableView<Reservation> table;
	
	@FXML TableColumn<Reservation,String> nameC;
	@FXML TableColumn<Reservation,String> surnameC;
	@FXML TableColumn<Reservation, LocalDate> checkinC;
	@FXML TableColumn<Reservation, LocalDate> checkoutC;
	@FXML TableColumn<Reservation, Double> priceC;
	@FXML TableColumn<Reservation, Integer> roomC;
	
	@FXML ListView<Integer> roomList;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		nameC.setCellValueFactory(new PropertyValueFactory<>("name"));
		surnameC.setCellValueFactory(new PropertyValueFactory<>("surname"));
	
		checkinC.setCellValueFactory(new PropertyValueFactory<Reservation, LocalDate>("checkin"));
		checkoutC.setCellValueFactory(new PropertyValueFactory<Reservation, LocalDate>("checkout"));
		
		priceC.setCellValueFactory(new PropertyValueFactory<Reservation, Double>("price"));
		roomC.setCellValueFactory(new PropertyValueFactory<Reservation, Integer>("room"));

	
		}
}
