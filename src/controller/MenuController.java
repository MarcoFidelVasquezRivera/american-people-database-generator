package controller;

import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
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

public class MenuController implements Initializable {

	private static Main main;
	private Stage stageEscoger;
	private AVLTree avlTree;
//	private BinarySearchTree<Comparable<K>, E> abbTree;
	
	@FXML
	private BorderPane borderMain;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
//		main = new Main();
		stageEscoger = new Stage();
		avlTree = new AVLTree<>();
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

	
	
	public AVLTree getAvlTree() {
		return avlTree;
	}

	public void setAvlTree(AVLTree avlTree) {
		this.avlTree = avlTree;
	}

	@FXML 
	void generationDataGo(ActionEvent event) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("/application/generationData.fxml"));
		Scene  scene = new Scene(root);
		this.getStageEscoger().setScene(scene);
		this.getStageEscoger().setTitle("Data Generation");
		this.getStageEscoger().show();
	}

	@FXML
	void searchDataGo(ActionEvent event) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("/application/searchDate.fxml"));
		Scene  scene = new Scene(root);
		this.getStageEscoger().setScene(scene);
		this.getStageEscoger().setTitle("Data Search");
		this.getStageEscoger().show();
	}

}
