package application;

import java.time.LocalDate;

import javafx.scene.control.ToggleButton;

public class DayNode  extends ToggleButton{
	
    private LocalDate date;
    private boolean busy = false;

    
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

	public boolean getBusy() {
		return busy;
	}

	public void setBusy(boolean busy) {
		this.busy = busy;
	}
}
