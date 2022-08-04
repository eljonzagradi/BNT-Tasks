package application;

import java.time.LocalDate;

import javafx.scene.control.ToggleButton;

public class DayNode  extends ToggleButton{
	
    private LocalDate date;
    
    public DayNode() {
        super();
        this.setPrefHeight(100);
        this.setPrefWidth(100);
    }
    
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

}
