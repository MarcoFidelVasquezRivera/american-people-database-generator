package ui;

import java.io.IOException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

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
		primStage.setResizable(false);
		
		primStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
		    @Override
		    public void handle(WindowEvent t) {
		        Platform.exit();
		        System.exit(0);
		    }
		});
	}
}
