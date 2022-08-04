package application;

import java.net.URL;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

enum isActive {CHECK_IN,CHECK_OUT}

public class CalendarController implements Initializable {
	
	//Table FXML components:
    @FXML private TableView<Reservation> reservationsTable;
    
	@FXML private TableColumn<Reservation,String> name_c;
	@FXML private TableColumn<Reservation,String> lastname_c;
	@FXML private TableColumn<Reservation,Date> checkin_c;
	@FXML private TableColumn<Reservation,Date> checkout_c;
	@FXML private TableColumn<Reservation,Number> totalPrice_c;
	@FXML private TableColumn<Reservation,LocalDateTime> createdAt_c;
	
	//Calendar FXML components:
	@FXML private VBox view;
	@FXML private GridPane calendar;
	@FXML private GridPane weekDays;
	@FXML private Button prevMonth;
	@FXML private Button nextMonth;
	@FXML private Text calendarTitle;
    ToggleGroup toggleGroup = new ToggleGroup();

	
	//Calendar variables:
    private ArrayList<DayNode> allCalendarDays = new ArrayList<>(35);
    private YearMonth currentYearMonth;
    
    //New Reservation FXML components:
	@FXML private Label room_x;
    @FXML private TextField name_x;
	@FXML private TextField lastName_x;
    @FXML Label checkin_x;
    @FXML Label checkout_x;
    @FXML ToggleButton setCheckin_b;
    @FXML ToggleButton setCheckout_b;
	@FXML private Label totalPrice_x;
    @FXML Button addReservation_b;
    @FXML Button changeRoom_b;
        
    //New Reservation variables:
	ObservableList<Reservation> reservations = FXCollections.observableArrayList();
	ObservableList<DisabledRange> rangesToDisable = FXCollections.observableArrayList();
    ToggleGroup inANDout = new ToggleGroup();
    isActive current = null;
    private LocalDate checkin;
    private LocalDate  checkout;
	private long totalPrice;
	public static int priceNight;
	public static int selectedRoom;    
////////////////////////////////////////////////////////    
/*              Getters & Setters                     */  
////////////////////////////////////////////////////////
    public isActive getCurrent() {
		return current;
	}

	public LocalDate getCheckin() {
		return checkin;
	}

	public LocalDate getCheckout() {
		return checkout;
	}
	
	public int getPriceNight() {
		return priceNight;	
	}
	
	public int getSelectedRoom() {
		return selectedRoom;	
	}
	
	public long getTotalPrice() {
		return totalPrice;
	}
	
	public void setCurrent(isActive current ) {
	    	this.current = current;
	}
	
	public void setCheckin(LocalDate checkin) {
		
        this.checkin = checkin;
	}

	public void setCheckout(LocalDate checkout) {
		
        this.checkout = checkout;
	}
	
	public void setTotalPrice(long totalPrice) {
		this.totalPrice = totalPrice;
	}

/////////////////////////////////////////////////////////
	
	public void clickAddReservations() {
		Reservation reservation = new Reservation(
						getSelectedRoom(),
						name_x.getText(),
						lastName_x.getText(),
						Date.valueOf(getCheckin()),
						Date.valueOf(getCheckout()),
						getTotalPrice(),
						LocalDateTime.now(),
						true
						);
		
		if(!areEmpty() && !reservations.contains(reservation)) {
			try {
				PreparedStatement ps =
						Database.con().prepareStatement(""
								+ "INSERT INTO `hoteldatabase`.`reservations` "
								+ "(`name`, `surname`, `check_in`, `check_out`, `total_price`, `created_at`, `paid`, `number`) "
								+ "VALUES ('"
								+ name_x.getText()
								+ "', '"
								+ lastName_x.getText()
								+ "', '"
								+ java.sql.Date.valueOf(getCheckin()) 
								+ "', '"
								+ java.sql.Date.valueOf(getCheckout())
								+ "', '"
								+ getTotalPrice()
								+ "', '"
								+ LocalDateTime.now()
								+ "', '"
								+ "1"
								+ "', '"
								+ getSelectedRoom()
								+ "');\r\n"
								);

				ps.executeUpdate();

			}catch (SQLException e1) {
				e1.printStackTrace();
				
			}
		} else { System.err.println("Err"); }
		
		
		name_x.clear();
		lastName_x.clear();
		checkin_x.setText("<-- Click to choose");
		checkout_x.setText("<-- Click to choose");
		totalPrice_x.setText(" ^ Choose ^");
		reservations.clear();
		loadDataFromDB();
		
	}
		
	public void someInitalValues() {
		
		setCheckin_b.setToggleGroup(inANDout);
		setCheckout_b.setToggleGroup(inANDout);
		
		room_x.setText("ROOM: " + getSelectedRoom());

	}
	
	public long calcPrice(LocalDate checkin, LocalDate checkout) {
    	
    	long price = 0;
    	long daysNo = 0;
    		    	
    	if(checkin != null && checkout != null) {
    		
    		daysNo = Duration.between(
    				checkin.atStartOfDay(), 
    				checkout.atStartOfDay()).toDays();
    		
    				price = getPriceNight() * daysNo;
    				
    	} else {
    		
			totalPrice_x.setText(getTotalPrice()+" LEK");
    		
    	}
		return price;
    }
	
	public boolean areEmpty() {
		
		if (
				name_x.getText().isBlank()
			||	lastName_x.getText().isBlank()
			||  getCheckin() == null
			||  getCheckout() == null
			||  getTotalPrice() <= 0
		   ) {
			
			return true;
		} else {
			return false;
		}
		
	}
	
	public void clickChangeRoom() 
    {
		
		Stage stage = (Stage) changeRoom_b.getScene().getWindow();
	    stage.close();
		
		Stage primaryStage = new Stage();
		
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/application/Rooms.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void tableSetup() 
	{
		name_c.setCellValueFactory(name -> name.getValue().getName());
		lastname_c.setCellValueFactory(surname -> surname.getValue().getSurname());
		checkin_c.setCellValueFactory(checkin -> checkin.getValue().getCheckin());
		checkout_c.setCellValueFactory(checkout -> checkout.getValue().getCheckout());
		totalPrice_c.setCellValueFactory(totalPrice -> totalPrice.getValue().getTotalPrice());
		createdAt_c.setCellValueFactory(createdat -> createdat.getValue().getCreatedat());
		
		loadDataFromDB();
	}
	
public void loadDataFromDB() {
	
	Statement statement;
	ResultSet resultSet;
		
		try {
			statement = Database.con().createStatement();
			
			resultSet = statement.executeQuery("select *\r\n"
					           + "from hoteldatabase.reservations r\r\n"
					           + "where number = '"+getSelectedRoom()+"'\r\n"
					           + "order by created_at DESC");
		   
			while (resultSet.next()) {
		    	
		    	String name = resultSet.getString("name");
		    	String lastname  = resultSet.getString("surname");
		    	Date checkin  = resultSet.getDate("check_in");
		    	Date checkout  = resultSet.getDate("check_out");
		    	int totalPrice  = resultSet.getInt("total_price");
		    	Timestamp timestamp = resultSet.getTimestamp("created_at");
		    	
		    	rangesToDisable.add(
		    			new DisabledRange(checkin, checkout));

	    	    		
		    	reservations.add(
		    			new Reservation(    
		    					
		    					getSelectedRoom(),
		    					name,
		    					lastname,
		    				    checkin,
		    					checkout,
		    					totalPrice,
		    					timestamp.toLocalDateTime(),
		    					true));
		    	
				reservationsTable.setItems(reservations);

		    }
		    
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		
	}
	
    public void CalendarView(YearMonth yearMonth) 
    {
        currentYearMonth = yearMonth;
      
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 7; j++) {
            	DayNode dateCell = new DayNode();
            	dateCell.setToggleGroup(toggleGroup);
                calendar.add(dateCell,j,i);
                allCalendarDays.add(dateCell);
            }
        }
  
        populateCalendar(yearMonth);
    }

    public void populateCalendar(YearMonth yearMonth) 
    {
    	
        LocalDate calendarDate = LocalDate.of(yearMonth.getYear(), yearMonth.getMonthValue(), 1);
        
        while (!calendarDate.getDayOfWeek().toString().equals("MONDAY") ) {
            calendarDate = calendarDate.minusDays(1);
        }
        
        for (DayNode dateCell : allCalendarDays) {
        	
        	String txt = String.valueOf(calendarDate.getDayOfMonth());

        	dateCell.setDate(calendarDate);
        	dateCell.setText(txt);
        	select(dateCell);
        	//Need Work
//            LocalDate today = LocalDate.now();
//        	dateCell.setDisable(calendarDate.compareTo(today) < 0);
//        	dateCell.setDisable(disable(calendarDate));

            calendarDate = calendarDate.plusDays(1);
        }
       
        calendarTitle.setText(
        		yearMonth.getMonth().toString() 
        		+ " " +
        		String.valueOf(yearMonth.getYear()));
    }
    
    public boolean disable(LocalDate calendarDate ) {
    	
   	 boolean disable = rangesToDisable.stream()
	            .filter(r->r.getCheck_in().isBefore(calendarDate))
	            .filter(r->r.getCheck_out().isAfter(calendarDate))
	            .findAny()
	            .isPresent();
   	 
	return disable;
    	
    }
   
	public void select(DayNode selected) 
    {
    	
		selected.setOnMouseClicked(
    			
    			e -> {
    				
    				if(setCheckin_b.isSelected()) 
    				{
    					setCheckin(selected.getDate());
    					checkin_x.setText(getCheckin().toString());
    					
    					setCheckout_b.setOnMouseClicked(ex -> {
    				    	if(getCheckin() == null) {
    				    		checkout_x.setText("Please"
    									+ "\n"
    									+ " Select Check-in first");
    				    		
    				    	} else {
    				    		
    				    		selected.setToggleGroup(null);
    				    		
    				    		
    				    	}
    					});
    				} 
    				
    			
    				else if(setCheckout_b.isSelected() && getCheckin() != null)
    			   	{
    					setCheckout(selected.getDate());
        				checkout_x.setText(getCheckout().toString());
 
    				}	
    				
    			   	else if(!setCheckin_b.isSelected() && !setCheckout_b.isSelected()) {
						
						setCheckin(null);
						setCheckout(null);
						
						checkin_x.setText("Click the button "
								+ "\n"
								+ " to set check-in");
						
						checkout_x.setText("Click the button "
								+ "\n"
								+ " to set check-out");
						
    			   	}
    					setTotalPrice(calcPrice(getCheckin(),getCheckout()));
    					totalPrice_x.setText(getTotalPrice()+" LEK");

    			});
		
	}
	
    public void previousMonth() 
    {
        currentYearMonth = currentYearMonth.minusMonths(1);
        populateCalendar(currentYearMonth);
    }

    public void nextMonth() 
    {
        currentYearMonth = currentYearMonth.plusMonths(1);
        populateCalendar(currentYearMonth);
    }

    public ArrayList<DayNode> getAllCalendarDays() 
    {
        return allCalendarDays;
    }

    public void setAllCalendarDays(ArrayList<DayNode> allCalendarDays) {
        this.allCalendarDays = allCalendarDays;
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		tableSetup();
		CalendarView(YearMonth.now());
		someInitalValues();
	}
}
