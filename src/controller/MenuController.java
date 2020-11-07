package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Database;

public class MenuController implements Initializable {

	private Stage stageEscoger;
	private Database database;
	private GenerationDataController generationDataController;
	private SearchDataController searchDataController;
	
	@FXML
	private BorderPane borderMain;

	public MenuController() throws IOException{
		stageEscoger = new Stage();
		database = new Database();
		generationDataController = new GenerationDataController(this, database);
		searchDataController = new SearchDataController(this, database);
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	public Stage getStageEscoger() {
		return stageEscoger;
	}

	public void setStageEscoger(Stage stageEscoger) {
		this.stageEscoger = stageEscoger;
	}

	public Database getDatabase() {
		return database;
	}

	public void setDatabase(Database database) {
		this.database = database;
	}

	@FXML 
	void generationDataGo(ActionEvent event) throws Exception {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ui/generationData.fxml"));
		fxmlLoader.setController(generationDataController);
		Parent root = fxmlLoader.load();
		Scene  scene = new Scene(root);
		this.getStageEscoger().setScene(scene);
		this.getStageEscoger().setTitle("Data Generation");
		this.getStageEscoger().show();
	}

	@FXML
	void searchDataGo(ActionEvent event) throws Exception {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ui/searchDate.fxml"));
		fxmlLoader.setController(searchDataController);
		Parent root = fxmlLoader.load();
		Scene  scene = new Scene(root);
		this.getStageEscoger().setScene(scene);
		this.getStageEscoger().setTitle("Data Search");
		this.getStageEscoger().show();
		
	}

}
