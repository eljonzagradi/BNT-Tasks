package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class SecondaryMainController implements Initializable {
	
	@FXML
	private CheckBox cb1;
	@FXML
	private CheckBox cb2;
	@FXML
	private CheckBox cb3;
	@FXML
	private CheckBox cb4;
	@FXML
	private Label lbl1;
	@FXML
	private Label lbl2;
	@FXML
	private RadioButton rb1;
	@FXML
	private RadioButton rb2;
	@FXML
	private Label lbl3;
	
	@FXML private TableView<Student> table;
	@FXML private TableColumn<Student, Integer> id;
	@FXML private TableColumn<Student, String> name;
	@FXML private TableColumn<Student, String> surname;
	@FXML private TableColumn<Student, Integer> age;
	
	public ObservableList<Student> list = FXCollections.observableArrayList(
			new Student(1, "01", "1", 22),
			new Student(2, "02", "2", 23),
			new Student(3, "03", "3", 24),
			new Student(4, "04", "4", 25),
			new Student(5, "05", "5", 26));
			
		
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		id.setCellValueFactory(new PropertyValueFactory<Student, Integer>("ID"));
		name.setCellValueFactory(new PropertyValueFactory<Student, String>("Name"));
		surname.setCellValueFactory(new PropertyValueFactory<Student, String>("Surname"));
		age.setCellValueFactory(new PropertyValueFactory<Student, Integer>("Age"));

		table.setItems(list);
		
		
	}
	
	public void radioSelect(ActionEvent event) {
		
		String message = "";
		if(rb1.isSelected()) {
			message += rb1.getText() + "\n" ; 
		}
		
		if(rb2.isSelected()) {
			message += rb2.getText() + "\n" ; 
		}
		
		lbl3.setText(message);
		
	}
	
	public void checkEvent(ActionEvent event) {
		
		int count = 0;
		String message = "";
		if(cb1.isSelected()) {
			count ++;
			message += cb1.getText() + "\n";
		}
		
		if(cb2.isSelected()) {
			count ++;
			message += cb2.getText() + "\n";
		}
		
		if(cb3.isSelected()) {
			count ++;
			message += cb3.getText() + "\n";
		}
		
		if(cb4.isSelected()) {
			count ++;
			message += cb4.getText() + "\n";
		}
		
		lbl2.setText("Items Selected: " + count);
		lbl1.setText(message);

		
		
	}
	
	public void CloseApp(ActionEvent event) {
		
		Platform.exit();
		System.exit(0);
		
	}

}
