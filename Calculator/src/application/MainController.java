package application; 

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class MainController {
	
	@FXML
	private Label result;
	private long num1 = 0;
	private String op = "";
	private boolean start = true;
	private Modal modal = new Modal();
	
	@FXML
	public void processNums(ActionEvent event) {
		
		if(start) {
			
			result.setText("");
			start = false;
		}
				String value = ((Button)event.getSource()).getText();
				result.setText(result.getText() + value);
		
	}
	
	@FXML
    public void processOps(ActionEvent event) {
		
		String value = ((Button)event.getSource()).getText();
		
		if(!value.equals("=")) {
			
			if(!op.isEmpty())
				return;
			
			op = value;
			num1 = Long.parseLong(result.getText());
			result.setText("");
			} 
		else {
			
						
				if(op.isEmpty())
					return;
				
				long num2 = Long.parseLong(result.getText());
				float output= modal.calculate(num2, num2, op);
				result.setText(String.valueOf(output));
				op = "";
				start = true;
				
			
			
		}
		
		
				
		
	}

}
