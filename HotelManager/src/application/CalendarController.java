package application;

import java.net.URL;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

enum isActive {CHECK_IN,CHECK_OUT}

public class CalendarController implements Initializable {
	
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
    @FXML Label checkin_x;
    @FXML Label checkout_x;
    @FXML ToggleButton setCheckin_b;
    @FXML ToggleButton setCheckout_b;
    @FXML Button addReservation_b;
    
    //New Reservation variables:
    ToggleGroup inANDout = new ToggleGroup();
    isActive current = null;
    private LocalDate checkin = null;
    private LocalDate checkout = null;
    
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
	
	public void setCheckin(LocalDate checkin) {
		this.checkin = checkin;
	}

	public void setCheckout(LocalDate checkout) {
		this.checkout = checkout;
	}

/////////////////////////////////////////////////////////
	
    public void CalendarView(YearMonth yearMonth) {
        currentYearMonth = yearMonth;
      
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 7; j++) {
            	DayNode ap = new DayNode();
            	ap.setToggleGroup(toggleGroup);
                calendar.add(ap,j,i);
                allCalendarDays.add(ap);
            }
        }
  
        populateCalendar(yearMonth);
    }

    public void populateCalendar(YearMonth yearMonth) {
    	
        LocalDate calendarDate = LocalDate.of(yearMonth.getYear(), yearMonth.getMonthValue(), 1);
        
        while (!calendarDate.getDayOfWeek().toString().equals("MONDAY") ) {
            calendarDate = calendarDate.minusDays(1);
        }
        
        for (DayNode date : allCalendarDays) {

        	String txt = String.valueOf(calendarDate.getDayOfMonth());
        	date.setDate(calendarDate);
        	date.setText(txt);
        	date.setDisable(false);
        	select(date);
            calendarDate = calendarDate.plusDays(1);
        }
       
        calendarTitle.setText(
        		yearMonth.getMonth().toString() 
        		+ " " +
        		String.valueOf(yearMonth.getYear()));
    }
    
    public void setCurrent(isActive current ) {
    	this.current = current;
    } 
//    public  void isBusy(DayNode date) {
//	   
//	   if(getCheckin() != null) {
//		   
//		   if(date.getDate().isBefore(getCheckin())) {
//	    		
//	    		date.setDisable(true);
//		   }
//	   }
//    }
   
   
    
    


	public void select(DayNode selected) {
    	
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
    					
    				
    			});
		
	}
    
    public void refresh() {
        populateCalendar(currentYearMonth);
    }

    public void previousMonth() {
        currentYearMonth = currentYearMonth.minusMonths(1);
        populateCalendar(currentYearMonth);
    }

    public void nextMonth() {
        currentYearMonth = currentYearMonth.plusMonths(1);
        populateCalendar(currentYearMonth);
    }

    public ArrayList<DayNode> getAllCalendarDays() {
        return allCalendarDays;
    }

    public void setAllCalendarDays(ArrayList<DayNode> allCalendarDays) {
        this.allCalendarDays = allCalendarDays;
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		setCheckin_b.setToggleGroup(inANDout);
		setCheckout_b.setToggleGroup(inANDout);

		checkin_x.setText("Click the button "
				+ "\n"
				+ " to set check-in");
		
		checkout_x.setText("Click the button "
				+ "\n"
				+ " to set check-out");
		
   	
		CalendarView(YearMonth.now());
		
	}
}
