package ui;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import controller.MenuController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.Database;

public class Main extends Application{
	private Database database;
	private MenuController menuController;

	
	public Main() throws IOException {
		database = new Database();
		try {
			chargeData();
		} catch (ClassNotFoundException | IOException e) {
			database = new Database();
		}
		menuController = new MenuController(database);
		//database = new Database();
		//menuController = new MenuController(database);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primStage) throws Exception {
		
		FXMLLoader a = new FXMLLoader(getClass().getResource("Menu.fxml"));
		a.setController(menuController);
        Parent root = a.load();
        Scene  scene = new Scene(root);
        primStage.setScene(scene);
        primStage.setTitle("MDC Data Generator");
        primStage.show();
        primStage.setResizable(false);
		
		primStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
		    @Override
		    public void handle(WindowEvent t) {
		    	try {
					saveData();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        Platform.exit();
		        System.exit(0);
		    }
		});
	}
	
	public void saveData() throws IOException {
		FileOutputStream fos = new FileOutputStream("data/status");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(database);
		oos.close();
		fos.close();
	}
	
	public void chargeData() throws IOException, ClassNotFoundException {
		FileInputStream file = new FileInputStream("data/status");
        ObjectInputStream ois = new ObjectInputStream(file);
        Database aux = (Database) ois.readObject();
        
        if(aux!=null) {
        	database = aux;
        }
        ois.close();
        file.close();
	}

}
