package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.util.Random;

public class MainController {
	
	@FXML
	private Label myMsg;
	
	public void generateRandom(ActionEvent event) {
		
		Random rnd = new Random();
		int  myNum = rnd.nextInt(50) + 1;
		myMsg.setText(Integer.toString(myNum));
				
		
	}

}
