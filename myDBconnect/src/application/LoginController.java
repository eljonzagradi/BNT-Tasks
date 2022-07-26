package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import connectivity.ConnectionClass;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.ListView;

import javafx.scene.control.Label;

import javafx.scene.control.TableView;

import javafx.scene.control.ChoiceBox;

public class LoginController implements Initializable {
	@FXML
	private MenuBar menu_bar;
	@FXML
	private TextField input_name;
	@FXML
	private TextField input_surname;
	@FXML
	private ChoiceBox<String> select_gender;
	@FXML
	private TextField input_age;
	@FXML
	private ListView<String> view_jobs;
	@FXML
	private Button submit_btn;
	@FXML
	private Label display_lbl;
	
	@FXML private TableView<User> view_db;
	
	@FXML TableColumn<User,String> nameColumn;
	@FXML TableColumn<User,String> surnameColumn;
	@FXML TableColumn<User,String> ageColumn;
	@FXML TableColumn<User,String> genderColumn;
	@FXML TableColumn<User,String> jobColumn;
	
	@FXML
	private Button delete_button;
	
	
		ObservableList<User> users= FXCollections.observableArrayList(
				new User("Eljon", "Zagradi","20","M","SE"));
		

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		surnameColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));
		ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));
		genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));
		jobColumn.setCellValueFactory(new PropertyValueFactory<>("job"));
		view_db.setItems(users);
		
		view_jobs.getItems().addAll("Software Enginner","Doctor","Lawyer","Student");
		
		select_gender.getItems().add("Male");
		select_gender.getItems().add("Female");
		
		select_gender.setValue("Gender:");
		
        try {
        	ConnectionClass connectionClass = new ConnectionClass();
            Connection connection = connectionClass.getConnection();
            
            Statement statement=connection.createStatement();
            String sql="SELECT * FROM userauth WHERE username = '"+input_name.getText()
            +"' AND password = '" +input_surname.getText()+"';";
            ResultSet resultSet = statement.executeQuery(sql);

//            if (resultSet.next()){
//                isConnected.setText("Connected");
//            }else {
//
//                isConnected.setText("Not Connected");
//            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
		
	}
	
	public void submitOnAction(ActionEvent event){
		
		if(!input_name.getText().isBlank() 
				&& !input_surname.getText().isBlank() 
				&& select_gender.getValue() != "Gender"
				&& !view_jobs.getSelectionModel().getSelectedItem().isEmpty()) {
			
			User user = new User(
					input_name.getText(),
					input_surname.getText(),
					input_age.getText(),
					select_gender.getValue(),
					view_jobs.getSelectionModel().getSelectedItem()
					);
	
			view_db.getItems().add(user);
			input_name.clear();
			input_surname.clear();
			input_age.clear();
			select_gender.setValue("Gender");
			view_jobs.getSelectionModel().select(-1);				
		
		}
		
	}
	
	public void delOnAction(ActionEvent event) {
		
		ObservableList<User> userSelected, allUsers;
		allUsers = view_db.getItems();
		userSelected = view_db.getSelectionModel().getSelectedItems();
		if(view_db.getSelectionModel().getSelectedIndex() != 0) {
		userSelected.forEach(allUsers::remove);
		}
	}
	
}
