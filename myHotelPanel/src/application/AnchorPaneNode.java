package application;

import java.time.LocalDate;

import javafx.scene.control.ToggleButton;

/**
 * Create an anchor pane that can store additional data.
 */
public class AnchorPaneNode extends ToggleButton  {

    // Date associated with this pane
    private LocalDate date;

    /**
     * Create a anchor pane node. Date is not assigned in the constructor.
     * @param children children of the anchor pane
     */
    public AnchorPaneNode() {
        super();
               // Add action handler for mouse clicked
        this.setOnMouseClicked(e -> System.out.println("This pane's date is: " + date));
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}