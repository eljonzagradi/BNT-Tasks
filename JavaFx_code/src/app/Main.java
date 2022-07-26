package app;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
	
	
	
	Stage window;
	Scene scene_1;
	Scene scene_2;
	
	Button button_1;
	Button button_2;
	Button button_3;
	Button button_4;
	Button button_5;
	Button button_6;
	
	MenuBar menuBar;
	Menu fileMenu;
	Menu editMenu;
	Menu diff;
	
	CheckMenuItem ch;
	
	RadioMenuItem r1;
	RadioMenuItem r2;
	RadioMenuItem r3;
	
	ToggleGroup tg;

	BorderPane layout;
	
	Label label_1;
	Label label_2;
	
	public int counter = 0;

	public static void main(String [] args) {
		launch(args);
	}

	@Override
	public void start(Stage window) throws Exception {
		
		
		window.setTitle("MyApp");
		
		window.setOnCloseRequest(e -> {
			e.consume();
			//if(ConfirmBox.display("Close", "Do you want to close ?")) {
			window.close();
			//}
			
		});
		
		label_1 = new Label("Welcome to Fist Scene");
		button_1 = new Button();
		button_1.setText("Go to Scene 2");
		
		button_1.setOnAction( e -> {
			counter++;
				System.out.println("Loading ..." + counter +"%...");
				window.setScene(scene_2);
				
		});
		button_3 = new Button("AlertBox");
		button_3.setOnAction(e -> AlertBox.display("Alert", "WoW") 	
			
			
		);
		
		button_4 = new Button("ConfirmationBox");
		button_4.setOnAction(e -> {
		
		boolean result = ConfirmBox.display("Complete", "Do you want to win money ?"); 	
			System.out.println(result);
			
		});
		
		button_5 = new Button("Sign Up Window");
		button_5.setOnAction(e -> { new SignUpWindow();
		
						
		});
		
		button_6 = new Button("TableView");
		button_6.setOnAction(e -> { new TableWindow();
						
		});
		
		fileMenu = new Menu("_File");
		
		fileMenu.getItems().addAll(new MenuItem("Item 1"));
		fileMenu.getItems().addAll(new MenuItem("Item 2"));
		fileMenu.getItems().addAll(new MenuItem("Item 3"));
		fileMenu.getItems().add(new SeparatorMenuItem());
		fileMenu.getItems().addAll(new MenuItem("Item 4"));
		fileMenu.getItems().addAll(new MenuItem("Item 5"));
		fileMenu.getItems().add(new SeparatorMenuItem());
		fileMenu.getItems().addAll(new MenuItem("Item 6"));
		
		editMenu = new Menu("_Edit");
		ch = new CheckMenuItem("Check");
		ch.setOnAction(e -> { 
			if(ch.isSelected()) {
				
				button_3.setDisable(true);
				} 
			else {
				   button_3.setDisable(false);
				}
						
		});
		
		editMenu.getItems().add(ch);
		
		diff = new Menu("Diff");
		
		r1 = new RadioMenuItem("1");
		r2 = new RadioMenuItem("2");
		r3 = new RadioMenuItem("3");
		
		tg = new ToggleGroup();
		
		r1.setToggleGroup(tg);
		r2.setToggleGroup(tg);
		r3.setToggleGroup(tg);
		
		diff.getItems().addAll(r1,r2,r3);	

		menuBar = new MenuBar();
		menuBar.getMenus().addAll(fileMenu,editMenu,diff);
		
		
		
		VBox layout_1 = new VBox(15);
		layout_1.setAlignment(Pos.BASELINE_CENTER);
		layout_1.getChildren().addAll(label_1, button_1, button_3,button_4,button_5,button_6);
				
		layout = new BorderPane();
		layout.setTop(menuBar);
		layout.setCenter(layout_1);
		
		scene_1 = new Scene(layout,400,400);

		
		label_2 = new Label("Welcome to Second Scene");
		button_2 = new Button();
		button_2.setText("Go to Scene 1");
		button_2.setOnAction( e -> {
			counter++;
			System.out.println("Loading ..." + counter +"%...");
			window.setScene(scene_1);
	});
		
		VBox layout_2 = new VBox(25);
		layout_2.setAlignment(Pos.TOP_RIGHT);
		layout_2.getChildren().addAll(label_2,button_2);
		scene_2 = new Scene(layout_2,400,400);
		
		scene_1.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		window.setScene(scene_1);
		window.show();

	}
	
}
	
