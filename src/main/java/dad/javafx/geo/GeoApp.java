package dad.javafx.geo;

import dad.javafx.geo.fxml.GeoController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GeoApp extends Application {
	
	private GeoController geoController;

	@Override
	public void start(Stage primaryStage) throws Exception {
		geoController = new GeoController();
		
		Scene scene = new Scene(geoController.getView());
		
		primaryStage.setTitle("GeoFX");
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);

	}

}
