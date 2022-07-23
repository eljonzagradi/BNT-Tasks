package application;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;

public class MainController {
	
	@FXML
	private GridPane grid;
	
	public void setDays() {
		
		for(int i = 1; i < 6; ++i) {
			for(int j = 0; j < 6; ++j) {
				
				ListView<String> lv = new ListView();
				grid.add(lv, j, i);
				
				for(int k = 1; k<31; k++) {
					lv.getItems().add(Integer.toString(k));
				}
				
			}
		}
		
		
	}

}
