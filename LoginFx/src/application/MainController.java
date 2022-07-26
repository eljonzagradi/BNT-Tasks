package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MainController  {
	
	@FXML
	private Label lblStatus;
	
	@FXML
	private TextField txtUsername;
	
	@FXML
	private TextField txtPassword;
	
	public void Login(ActionEvent event) throws Exception {
		
//		if(txtUsername.getText().equals("admin") && txtPassword.getText().equals("admin") ) {
//			
//			lblStatus.setText("Login Success");
//			Stage primaryStage = new Stage();
//			Parent root  = FXMLLoader.load(getClass().getResource("/application/Main.fxml"));
//			Scene scene = new Scene(root);
//			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
//			primaryStage.setScene(scene);
//			primaryStage.show();
//		} else {
//			lblStatus.setText("Login Failed");
//
//		}
		
		
	}
 
	

}
