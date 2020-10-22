package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import ui.Main;

public class MenuController implements Initializable{
	
	private static Main main;
	private Stage stageEscoger;
	
	@FXML
	private AnchorPane anchorMain;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		main = new Main();
		
	}
	
	public static Main getMain() {
		return main;
	}

	public static void setMain(Main main) {
		MenuController.main = main;
	}



	public Stage getStageEscoger() {
		return stageEscoger;
	}

	public void setStageEscoger(Stage stageEscoger) {
		this.stageEscoger = stageEscoger;
	}



	@FXML
	void generationDataGo(ActionEvent event) throws Exception {
		AnchorPane escoger = FXMLLoader.load(getClass().getResource("/controller/generationData.fxml"));
		Scene scene = new Scene(escoger);
		main.getPrimStage().close();
		stageEscoger.setScene(scene);
		stageEscoger.show();
	}

	@FXML
	void searchDataGo(ActionEvent event) throws Exception {
		AnchorPane escoger = FXMLLoader.load(getClass().getResource("/controller/searchData.fxml"));
		Scene scene = new Scene(escoger);
		main.getPrimStage().close();
		stageEscoger.setScene(scene);
		stageEscoger.show();
	}

	@FXML
	void updataDeleteGo(ActionEvent event) throws Exception{
		AnchorPane escoger = FXMLLoader.load(getClass().getResource("/controller/updateOrDelete.fxml"));
		Scene scene = new Scene(escoger);
		main.getPrimStage().close();
		stageEscoger.setScene(scene);
		stageEscoger.show();
	}

	

}
