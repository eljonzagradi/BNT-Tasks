package application;

import java.time.LocalDate;

import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.scene.control.ListView;

import javafx.scene.control.Label;

import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableColumn;

public class MainController {
	
	@FXML private TableView<Reservation> table;
	
	@FXML private TableColumn<Reservation,String> nameC;
	@FXML private TableColumn<Reservation,String> surnameC;
	@FXML private TableColumn<Reservation, LocalDate> checkinC;
	@FXML private TableColumn<Reservation, LocalDate> checkoutC;
	@FXML private TableColumn<Reservation, Double> priceC;
	@FXML private TableColumn<Reservation, Integer> roomC;
	
	@FXML private ListView<Integer> roomList;
	
	@FXML private Button btn_0_0;
	@FXML private Button btn_0_1;
	@FXML private Button btn_0_2;
	@FXML private Button btn_0_3;
	@FXML private Button btn_0_4;
	
	@FXML private Button btn_1_0;
	@FXML private Button btn_1_1;
	@FXML private Button btn_1_2;
	@FXML private Button btn_1_3;
	@FXML private Button btn_1_4;
	
	@FXML private Button btn_2_0;
	@FXML private Button btn_2_1;
	@FXML private Button btn_2_2;
	@FXML private Button btn_2_3;
	@FXML private Button btn_2_4;
	
	@FXML private Button btn_3_0;
	@FXML private Button btn_3_1;
	@FXML private Button btn_3_2;
	@FXML private Button btn_3_3;
	@FXML private Button btn_3_4;
	
	@FXML private Button btn_4_0;
	@FXML private Button btn_4_1;
	@FXML private Button btn_4_2;
	@FXML private Button btn_4_3;
	@FXML private Button btn_4_4;
	
	@FXML private Button btn_5_0;
	@FXML private Button btn_5_1;
	@FXML private Button btn_5_2;
	@FXML private Button btn_5_3;
	@FXML private Button btn_5_4;
	
	@FXML private Button btn_6_0;
	@FXML private Button btn_6_1;
	@FXML private Button btn_6_2;
	@FXML private Button btn_6_3;
	@FXML private Button btn_6_4;
	
	@FXML private Label lbl_month;
	@FXML private Label lbl_year;
	
	public Button[][] days = {
			
            {btn_0_0,btn_1_0,btn_2_0,btn_3_0,btn_4_0,btn_5_0,btn_6_0},
            {btn_0_1,btn_1_1,btn_2_1,btn_3_1,btn_4_1,btn_5_1,btn_6_1},
            {btn_0_2,btn_1_2,btn_2_2,btn_3_2,btn_4_2,btn_5_2,btn_6_2},
            {btn_0_3,btn_1_3,btn_2_3,btn_3_3,btn_4_3,btn_5_3,btn_6_3},
            {btn_0_4,btn_1_4,btn_2_4,btn_3_4,btn_4_4,btn_5_4,btn_6_3}
            
            };
	
	public void tableSetUp() {
		nameC.setCellValueFactory(new PropertyValueFactory<>("name"));
		surnameC.setCellValueFactory(new PropertyValueFactory<>("surname"));
		checkinC.setCellValueFactory(new PropertyValueFactory<Reservation, LocalDate>("checkin"));
		checkoutC.setCellValueFactory(new PropertyValueFactory<Reservation, LocalDate>("checkout"));
		priceC.setCellValueFactory(new PropertyValueFactory<Reservation, Double>("price"));
		roomC.setCellValueFactory(new PropertyValueFactory<Reservation, Integer>("room"));
	}

}
