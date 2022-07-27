package application;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleLongProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;

import javafx.scene.control.DatePicker;

import javafx.scene.layout.GridPane;

import javafx.scene.control.TableColumn;

public class MainController implements Initializable {
	
	@FXML private TableView<Reservation> reservationsTable;
	
	@FXML private TableColumn<Reservation,String> name_c;
	@FXML private TableColumn<Reservation,String> lastname_c;
	@FXML private TableColumn<Reservation,LocalDate> checkin_c;
	@FXML private TableColumn<Reservation,LocalDate> checkout_c;
	@FXML private TableColumn<Reservation,Number> price_c;
	
	@FXML private ListView<Integer> roomList;
	
	@FXML private TextField name_x;
	@FXML private TextField lastName_x;
	
	@FXML private Label priceVal_x;
	@FXML private Label roomNo_lbl;
	
	@FXML private DatePicker checkout_x;
	@FXML private DatePicker checkin_x;
	
	@FXML private GridPane monthDaysGrid;
	@FXML private GridPane weekDaysGrid;
	
	@FXML private Button add_b;
	@FXML private Button clear_b;
	
	private long currentPrice;
	
	public void disableDateCells() 
	{
		
		checkin_x.setDayCellFactory(picker -> new DateCell() {
        
			public void updateItem(LocalDate date, boolean empty) {
            super.updateItem(date, empty);
            LocalDate today = LocalDate.now();

            setDisable(empty || date.compareTo(today) < 0 );
            } });
		
		checkin_x.valueProperty().addListener( e -> {
			
			checkout_x.setDayCellFactory(picker -> new DateCell() {
		        
				public void updateItem(LocalDate date, boolean empty) {
	            super.updateItem(date, empty);
	            LocalDate checkin = checkin_x.getValue();

	            setDisable(empty || date.compareTo(checkin) < 1 );
	            } });
        	
        	
        	
        });
		
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		disableDateCells();
		tableSetup();
		setPriceLabel();
	}
	
	public void clickAdd() 
	{
		Reservation reservation = 
				new Reservation(
						name_x.getText(),
						lastName_x.getText(),
						checkin_x.getValue(),
						checkout_x.getValue(),
						Reservation.calcPrice(checkin_x, checkout_x, 2000)
						);
		reservationsTable.getItems().add(reservation);
	}
	
	public void tableSetup() 
	{
		name_c.setCellValueFactory(name -> name.getValue().getName());
		lastname_c.setCellValueFactory(surname -> surname.getValue().getSurname());
		checkin_c.setCellValueFactory(checkin -> checkin.getValue().getCheckin());
		checkout_c.setCellValueFactory(checkout -> checkout.getValue().getCheckout());
		price_c.setCellValueFactory(price -> price.getValue().getPrice());
		reservationsTable.setItems(getReservationsList());
	}
	
	ObservableList<Reservation> getReservationsList() 
	{
		ObservableList<Reservation> reservations = FXCollections.observableArrayList();
		reservations.add(new Reservation("name", "lastname",LocalDate.of(2022,7,27),LocalDate.of(2022,07,31), 10000));
		return reservations;
	}
	
	public void setPriceLabel() 
	{
		checkout_x.valueProperty().addListener( e -> {
			
			if(checkin_x != null) {
				
				currentPrice = Reservation.calcPrice(checkin_x, checkout_x, 2000);
				
				priceVal_x.textProperty()
				.bind(new SimpleLongProperty(currentPrice)
				.asString());
				
			}
		});		
	}
	
	
	
	

}
