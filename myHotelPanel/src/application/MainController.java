package application;

import java.net.URL;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;

import database.DB;
import javafx.beans.property.SimpleLongProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class MainController implements Initializable {
		
	@FXML private TableView<Reservation> reservationsTable;
	
	ObservableList<Reservation> reservations = FXCollections.observableArrayList();
	ObservableList<DisabledRange> rangesToDisable = FXCollections.observableArrayList();

	
	@FXML private TableColumn<Reservation,String> name_c;
	@FXML private TableColumn<Reservation,String> lastname_c;
	@FXML private TableColumn<Reservation,Date> checkin_c;
	@FXML private TableColumn<Reservation,Date> checkout_c;
	@FXML private TableColumn<Reservation,Number> price_c;
		
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
	@FXML private Button back_b;
	
	private long currentPrice;
	public static int currentPPN;
	public static int selectedRoom;
	
	Statement st;
	ResultSet rt;

	
	public int getCurrentPPN() {
		return currentPPN;	
	}
	
	public int getSelectedRoom() {
		return selectedRoom;	
	}
	
	public long getCurrentPrice() {
		return currentPrice;
	}

	public void setCurrentPrice(long currentPrice) {
		this.currentPrice = currentPrice;
	}

	public void disablePastDateCells() 
	{
		
    
		
		checkin_x.setDayCellFactory(picker -> new DateCell() {
	     
			public void updateItem(LocalDate date, boolean empty) {
            super.updateItem(date, empty);
            
            boolean disable = rangesToDisable.stream()
    	            .filter(r->r.getCheck_in().minusDays(1).isBefore(date))
    	            .filter(r->r.getCheck_out().plusDays(0).isAfter(date))
    	            .findAny()
    	            .isPresent();

            LocalDate today = LocalDate.now();

            setDisable(empty || date.compareTo(today) < 0 || disable);
            } });
		
		checkin_x.valueProperty().addListener( e -> {
			
			checkout_x.setDayCellFactory(picker -> new DateCell() {
		        
				public void updateItem(LocalDate date, boolean empty) {
	            super.updateItem(date, empty);
	            
	            LocalDate lastDate = checkin_x.getValue();
	            
	            boolean disable = rangesToDisable.stream()
	    	            .filter(r->r.getCheck_in().isBefore(date))
	    	            .filter(r->r.getCheck_out().isAfter(date))
	    	            .findAny()
	    	            .isPresent();

	            setDisable(empty || disable || date.compareTo(lastDate) <= 0);
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
						getSelectedRoom(),
						name_x.getText(),
						lastName_x.getText(),
						java.sql.Date.valueOf(checkin_x.getValue()),
						java.sql.Date.valueOf(checkout_x.getValue()),
						Reservation.calcPrice(checkin_x, checkout_x, getCurrentPPN())
						);
			
			try {
				PreparedStatement ps =
						DB.con().prepareStatement
		      ("INSERT INTO `hoteldatabase`.`reservations` "
		      		+ "(`number`, `name`, `lastname`, `checkin`, `checkout`, `price`) "
		      		+ "VALUES ('"
		      		+getSelectedRoom()+
		      		"', '"
		      		+name_x.getText()+
		      		"', '"
		      		+lastName_x.getText()+
		      		"', '"
		      		+java.sql.Date.valueOf(checkin_x.getValue())+
		      		"', '"
		      		+java.sql.Date.valueOf(checkout_x.getValue())+
		      		"', '"
		      		+Reservation.calcPrice(checkin_x, checkout_x, getCurrentPPN())+
		      		"');");		
				
				ps.executeUpdate();

			}catch (SQLException e1) {
				e1.printStackTrace();
				
			}
			reservations.add(reservation);
			
			
		}
		
	}

	public void tableSetup() 
	{
		name_c.setCellValueFactory(name -> name.getValue().getName());
		lastname_c.setCellValueFactory(surname -> surname.getValue().getSurname());
		checkin_c.setCellValueFactory(checkin -> checkin.getValue().getCheckin());
		checkout_c.setCellValueFactory(checkout -> checkout.getValue().getCheckout());
		price_c.setCellValueFactory(price -> price.getValue().getPrice());
		

		
		try {
			st = DB.con().createStatement();
			rt = st.executeQuery("SELECT * \r\n"
					+ "FROM hoteldatabase.reservations\r\n"
					+ "WHERE number = '"+getSelectedRoom()+"'");
		    while (rt.next()) {
		    	
		    	String nameDB = rt.getString("name");
		    	String lastnameDB  = rt.getString("lastname");
		    	Date checkinDB  = rt.getDate("checkin");
		    	Date checkoutDB  = rt.getDate("checkout");
		    	int priceDB  = rt.getInt("price");
		    	
		    	rangesToDisable.add(new DisabledRange(checkinDB, checkoutDB));
	    	    		
		    	reservations.add(new Reservation(getSelectedRoom(), nameDB, lastnameDB, 
		    			checkinDB, checkoutDB, priceDB));
		    	
				reservationsTable.setItems(reservations);


		    }
		    
		} catch (SQLException e) {
			e.printStackTrace();
			
		}		
		
	}
		
	public void displayPrice_Room() 
	{
		checkout_x.valueProperty().addListener( e -> {
			
			if(checkin_x != null) {
				
				setCurrentPrice(Reservation.calcPrice(checkin_x, checkout_x, getCurrentPPN()));
				
				priceVal_x.textProperty()
				.bind(new SimpleLongProperty(getCurrentPrice())
				.asString());			
			}	
		});		
	
		roomNo_lbl.setText("ROOM: "+
				Integer.toString(getSelectedRoom()));
	}
		
	public void refresh()
	{
//		Stage primaryStage = (Stage) back_b.getScene().getWindow();
//		primaryStage.close();
//		
//		
//		try {
//			Parent root = FXMLLoader.load(getClass().getResource("/application/Main.fxml"));
//			Scene scene = new Scene(root);
//			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
//			primaryStage.setScene(scene);
//			primaryStage.show();
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
		reservations.clear();
		tableSetup();
		
	}
	
	public void goBack() {
		Stage stage = (Stage) back_b.getScene().getWindow();
	    stage.close();
		
		Stage primaryStage = new Stage();
		
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/application/Panel.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		disablePastDateCells();
		tableSetup();
		displayPrice_Room();
	}
}
