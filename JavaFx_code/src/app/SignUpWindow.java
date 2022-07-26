package app;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class SignUpWindow extends TableWindow {
	
	Stage window;
	GridPane grid;
	
	Label label;
	Label nameLabel;
	Label surnameLabel;
	
	TextField nameInput;
	TextField surnameInput;
	
	ChoiceBox<String> gender;
	
	Label listViewTitle;
	ListView<String> jobs;
	
	CheckBox agree;
	
	Button button;
	
	Scene scene;

	SignUpWindow() {
		
		window = new Stage();
		window.setTitle("Login");
		
		grid = new GridPane();
		grid.setPadding(new Insets(10,10,10,10));
		grid.setVgap(8);
		grid.setHgap(10);
		
		nameLabel = new Label("Username: ");
		GridPane.setConstraints(nameLabel,0,0);
		
		nameInput = new TextField();		
		nameInput.setPromptText("Username");
		GridPane.setConstraints(nameInput,1,0);

		
		surnameLabel = new Label("surname: ");
		GridPane.setConstraints(surnameLabel,0,1);
		
		surnameInput = new TextField();
		surnameInput.setPromptText("Surname");
		GridPane.setConstraints(surnameInput,1,1);
		
		gender = new ChoiceBox<>();
		gender.getItems().add("Male");
		gender.getItems().add("Female");
		gender.getItems().add("Not idetified");
		
		gender.setValue("Select gender:");
		GridPane.setConstraints(gender,1,2);
		
		listViewTitle = new Label("Job Position: ");
		GridPane.setConstraints(listViewTitle,1,3);

		jobs = new ListView<>();
		jobs.setPrefHeight(100);
		jobs.setPrefWidth(50);
		jobs.getItems().addAll("Software Enginner","Doctor","Lawyer","Student");
		GridPane.setConstraints(jobs,1,4);

		agree = new CheckBox("Agree Terms & Conditions");
		GridPane.setConstraints(agree,1,5);

		button = new Button("Sign Up");
		GridPane.setConstraints(button,1,6);
		button.setOnAction(e -> {
			
			if(!nameInput.getText().isBlank() 
					&& !surnameInput.getText().isBlank() 
					&& agree.isSelected()
					&& gender.getValue() != "Select gender:"
					&& !jobs.getSelectionModel().getSelectedItem().isEmpty()) {
				
				    signUpButtonClicked();				
			
			}
		});
		
		grid.getChildren().addAll(nameLabel,nameInput,surnameLabel,surnameInput,gender,listViewTitle,jobs,agree,button);
		
		scene = new Scene(grid,400,400);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		window.setScene(scene);
		window.show();		
		
	}
	
     public void signUpButtonClicked() {
    	
    	User user = new User();
		user.setName(nameInput.getText());				
		user.setSurname(surnameInput.getText());
		user.setGender(gender.getValue());
		user.setJob(jobs.getSelectionModel().getSelectedItem());
		tableView.getItems().add(user);
		nameInput.clear();
		surnameInput.clear();
		gender.setValue("Select gender:");
		agree.setSelected(false);
		jobs.getSelectionModel().select(-1);
		}

}
