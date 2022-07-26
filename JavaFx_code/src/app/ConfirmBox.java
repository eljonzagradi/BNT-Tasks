package app;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ConfirmBox {
	
	static boolean answer;
	
public static boolean display(String title, String message) {
		
		Stage window = new Stage();	
		
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(title);
		window.setMinWidth(300);
		window.setMinHeight(250);
		
		Label label = new Label(message);
		
		Button yes_btn = new Button("YES");
		yes_btn.setOnAction(e -> {
			answer = true;
			window.close();
			
		});
		
		Button no_btn = new Button("NO");
		no_btn.setOnAction(e -> {
			answer = false;
			window.close();
			
		});

		VBox layout = new VBox(15);
		layout.setAlignment(Pos.CENTER);
		layout.getChildren().addAll(label,yes_btn,no_btn);
		Scene scene = new Scene(layout);
		window.setScene(scene);
		window.showAndWait();
		
		return answer;
	}

}
