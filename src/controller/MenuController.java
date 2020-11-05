package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.AVLTree;
import model.BinarySearchTree;
import model.Database;

public class MenuController implements Initializable {

	private Stage stageEscoger;
	private Database database;
	
	
	@FXML
	private BorderPane borderMain;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		stageEscoger = new Stage();
		database = new Database();
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
		Parent root = FXMLLoader.load(getClass().getResource("/ui/generationData.fxml"));
		Scene  scene = new Scene(root);
		this.getStageEscoger().setScene(scene);
		this.getStageEscoger().setTitle("Data Generation");
		this.getStageEscoger().show();
	}

	@FXML
	void searchDataGo(ActionEvent event) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("/ui/searchDate.fxml"));
		Scene  scene = new Scene(root);
		this.getStageEscoger().setScene(scene);
		this.getStageEscoger().setTitle("Data Search");
		this.getStageEscoger().show();
	}

}
