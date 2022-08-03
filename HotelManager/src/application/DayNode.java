package application;

import java.time.LocalDate;

import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;

public class DayNode  extends ToggleButton{
	
    private LocalDate date;
    
    public DayNode() {
        super();
        this.setPrefHeight(45);
        this.setPrefWidth(84);
    }
    
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

}
