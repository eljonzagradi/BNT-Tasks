module myHotelPanel {
	requires javafx.controls;
	requires javafx.graphics;
	requires javafx.fxml;
	requires javafx.base;
	requires jfxtras.labs.samples;
	
	opens application to javafx.graphics, javafx.fxml;
}
