package application;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
	
	ObservableList<Reservation> room101;
	ObservableList<Reservation> room102;

	
	@FXML private TableView<Reservation> reservationsTable;
	
	@FXML private TableColumn<Reservation,String> name_c;
	@FXML private TableColumn<Reservation,String> lastname_c;
	@FXML private TableColumn<Reservation,LocalDate> checkin_c;
	@FXML private TableColumn<Reservation,LocalDate> checkout_c;
	@FXML private TableColumn<Reservation,Number> price_c;
	
	@FXML private ListView<Number> roomList;
	
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
	
	public long getCurrentPrice() {
		return currentPrice;
	}

	public void setCurrentPrice(long currentPrice) {
		this.currentPrice = currentPrice;
	}


	private Number roomNum;
	
	public void setRoomNum(Number number) {
		this.roomNum = number;
	}
	
	public int getRoomNum() {
		return roomNum.intValue();
	}
	
	public void disablePastDateCells() 
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
	
	public boolean areEmpty() {
			
		if (name_x.getText().isBlank()
				|| lastName_x.getText().isBlank()
				|| checkin_x == null
				|| checkout_x == null
				|| priceVal_x.getText().isBlank()
				|| roomNo_lbl.getText().isBlank())
			{ 
				return true; 
			} 
			
			else 	
			{
				return false; 
			}	
		}
	
	public void clickAdd() 
	{
		if(!areEmpty()) {
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
	}

	public void tableSetup() 
	{
		name_c.setCellValueFactory(name -> name.getValue().getName());
		lastname_c.setCellValueFactory(surname -> surname.getValue().getSurname());
		checkin_c.setCellValueFactory(checkin -> checkin.getValue().getCheckin());
		checkout_c.setCellValueFactory(checkout -> checkout.getValue().getCheckout());
		price_c.setCellValueFactory(price -> price.getValue().getPrice());
		
		room101 = FXCollections.observableArrayList(
				new Reservation("room101", "lastname",LocalDate.of(2022,7,27),LocalDate.of(2022,07,31), 10000),
				new Reservation("room101", "lastname",LocalDate.of(2022,7,27),LocalDate.of(2022,07,31), 10000),
				new Reservation("room101", "lastname",LocalDate.of(2022,7,27),LocalDate.of(2022,07,31), 10000),
				new Reservation("room101", "lastname",LocalDate.of(2022,7,27),LocalDate.of(2022,07,31), 10000),
				new Reservation("room101", "lastname",LocalDate.of(2022,7,27),LocalDate.of(2022,07,31), 10000));
		
		room102 = FXCollections.observableArrayList(
				new Reservation("room102", "lastname",LocalDate.of(2022,7,27),LocalDate.of(2022,07,31), 10000));
		
	}
	
	public void listViewSetup() {
		
		ObservableList<Number> rooms = 
				FXCollections
				.observableArrayList( 101,102);
		
		roomList.setItems(rooms);

		roomList.getSelectionModel().selectedItemProperty().addListener( e -> {
			
			setRoomNum(roomList.getSelectionModel().selectedItemProperty().getValue());
			roomNo_lbl.textProperty()
			.bind(new SimpleIntegerProperty(getRoomNum())
			.asString());
			
			if(getRoomNum() == 101) {
				
				reservationsTable.setItems(room101);
		
			}
			
			else if(getRoomNum() == 102) {
				
				reservationsTable.setItems(room102);
					
			}			
		});		
		
	}

	public void setPriceLabel() 
	{
		checkout_x.valueProperty().addListener( e -> {
			
			if(checkin_x != null) {
				
				setCurrentPrice(Reservation.calcPrice(checkin_x, checkout_x, 2000));
				
				priceVal_x.textProperty()
				.bind(new SimpleLongProperty(getCurrentPrice())
				.asString());
				
			}
		
		});		
	}
	
	public void clickClear()
	{
//		if(!areEmpty()) {
//			name_x.clear();
//			lastName_x.clear();
//			checkin_x.setValue(null);
//			checkout_x.setValue(null);
//			setCurrentPrice(0);
//			setRoomNum(0);
//			roomList.getSelectionModel().select(-1);
//			}
	}


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		disablePastDateCells();
		tableSetup();
		listViewSetup();
		setPriceLabel();
		

	}
}
