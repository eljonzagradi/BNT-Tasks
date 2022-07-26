package application;

import java.time.LocalDate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;

public class ThirdMainController {
	
	@FXML PieChart pieCh;
	@FXML private LineChart<String, Number> lch;
	@FXML private Label lbl,lbl2;
	@FXML private DatePicker dp;
	@FXML private Label showDate;
	
	public void ShowDate(ActionEvent event) {
		
		LocalDate ld = dp.getValue();
		showDate.setText(ld.toString());
		
	}
	
	public void btn(ActionEvent event) {
		ObservableList<Data> list = FXCollections.observableArrayList(
		new PieChart.Data("Java", 50),
		new PieChart.Data("c++", 20),
		new PieChart.Data("python", 10),
		new PieChart.Data("C#", 5),
		new PieChart.Data("c", 15)
		);
		
		pieCh.setData(list);
		
		for(final PieChart.Data data : pieCh.getData()) {
			
			data.getNode().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent arg0) {
					
					lbl.setText(String.valueOf(data.getPieValue()) + "%");
					
				}
				
			});
			
		}
		
		lch.getData().clear();
		
		XYChart.Series<String, Number> series1 = new XYChart.Series<String, Number>();
		series1.getData().add(new XYChart.Data<String,Number>("Jan",200));
		series1.getData().add(new XYChart.Data<String,Number>("Feb",500));
		series1.getData().add(new XYChart.Data<String,Number>("Mar",822));
		series1.getData().add(new XYChart.Data<String,Number>("Apr",150));
		series1.getData().add(new XYChart.Data<String,Number>("May",622));
		series1.setName("Pay 1");
		
//		XYChart.Series<String, Number> series2 = new XYChart.Series<String, Number>();
//		series2.getData().add(new XYChart.Data<String,Number>("Jan",300));
//		series2.getData().add(new XYChart.Data<String,Number>("Feb",50));
//		series2.getData().add(new XYChart.Data<String,Number>("Mar",800));
//		series2.getData().add(new XYChart.Data<String,Number>("Apr",100));
//		series2.getData().add(new XYChart.Data<String,Number>("May",15));
//		series2.setName("Pay 2");
		
		lch.getData().add(series1); 		//lch.getData().add(series2);
		
for(final XYChart.Data<String, Number> data : series1.getData()) {
			
			data.getNode().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent arg0) {
					
					lbl2.setText("X :" + data.getXValue() + "\n Y : " + String.valueOf(data.getYValue()));
					Tooltip.install(data.getNode(), new Tooltip("X :" + data.getXValue() + "\n Y : " + String.valueOf(data.getYValue())));
					
				}
				
			});
			
		}

		

		
	}
	
}
