package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
		Scene  scene = new Scene(root);
		primStage.setScene(scene);
		primStage.setTitle("MDC Data Generator");
		primStage.show();
	}
}