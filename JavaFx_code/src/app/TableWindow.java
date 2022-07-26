package app;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TableWindow {
	
	Stage window;
	TableView<User> tableView;
	TableColumn<User,String> nameColumn;
	TableColumn<User,String> surnameColumn;
	TableColumn<User,String> genderColumn;
	TableColumn<User,String> jobColumn;
	
	Button add_btn;
	Button del_btn;
	
	VBox vBox;
	Scene scene;
	
	TableWindow() {
		
		window = new Stage();
		window.setTitle("Table");
		
		nameColumn = new TableColumn<>("Name");
		nameColumn.setMinWidth(75);
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		
		surnameColumn = new TableColumn<>("Surname");
		surnameColumn.setMinWidth(75);
		surnameColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));
		
		genderColumn = new TableColumn<>("Gender");
		genderColumn.setMinWidth(70);
		genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));
		
		jobColumn = new TableColumn<>("Job");
		jobColumn.setMinWidth(80);
		jobColumn.setCellValueFactory(new PropertyValueFactory<>("job"));
		
		tableView = new TableView<>();
		tableView.setItems(getUser());
		tableView.getColumns().addAll(nameColumn,surnameColumn,genderColumn,jobColumn);
		
		add_btn = new Button("Add");
		add_btn.setOnAction( e -> new SignUpWindow());
		del_btn = new Button("Delete");
		del_btn.setOnAction( e -> {
			
			ObservableList<User> userSelected, allUsers;
			allUsers = tableView.getItems();
			userSelected = tableView.getSelectionModel().getSelectedItems();
			userSelected.forEach(allUsers::remove);
		});
		
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10,10,10,10));
		grid.getChildren().addAll(add_btn,del_btn);
		grid.setVgap(10);
		grid.setHgap(10);
		GridPane.setConstraints(add_btn,0,0);
		GridPane.setConstraints(del_btn,18,0);

		
		
		vBox = new VBox();
		vBox.getChildren().addAll(tableView,grid);
		
		scene = new Scene(vBox,300,300);			
		
		window.setScene(scene);
		window.show();		
		
	}
	
	public ObservableList<User> getUser(){
		ObservableList<User> users= FXCollections.observableArrayList();
		users.add(new User("User1","Surname1","Male","Job"));
		users.add(new User("User2","Surname2","Male","Doctor"));
		users.add(new User("User3","Surname3","Female","Lawyer"));
		return users;
	}
	

}
