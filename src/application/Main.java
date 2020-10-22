package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application{

	private static Stage primStage;
	
	public static void main(String[] args) {
		launch(args);

	}
	
	@Override
	public void start(Stage primStage) throws Exception {
		this.primStage = primStage;
		try {
			AnchorPane root = FXMLLoader.load(getClass().getResource("menu.fxml"));
			Scene scene = new Scene(root);
			primStage.setTitle("MCD Data Generator");
			primStage.setScene(scene);
			primStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static Stage getPrimStage() {
		return primStage;
	}

	public static void setPrimStage(Stage primStage) {
		Main.primStage = primStage;
	}	

	
}
