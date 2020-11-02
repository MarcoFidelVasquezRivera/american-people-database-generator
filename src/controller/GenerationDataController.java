package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.AVLTree;

public class GenerationDataController implements Initializable {
	
	private MenuController mc;
	private Stage generetionData;
	private AVLTree avlTree;
	
    @FXML
    private TextField nGenerate;

    @FXML
    private Label textNotification;

    @FXML
    private ProgressBar progressBar;

    public void initialize(URL arg0, ResourceBundle arg1) {
		mc = new MenuController();
		generetionData = new Stage();
		
	}
    
    @FXML
    void generationData(ActionEvent event) {
    	progressBar = new ProgressBar(0);
    	double i = 0;
    	try {
    		while(i <= 1) {
    		Thread.sleep(50);
    		progressBar.setProgress(i);
    		i += 0.1;
    		}
		} catch (Exception e) {
			
		} 
    	
    }

    public Stage getGeneretionData() {
		return generetionData;
	}

	public void setGeneretionData(Stage generetionData) {
		this.generetionData = generetionData;
	}

	@FXML
    void saveDataGeneration(ActionEvent event) {

    }

}

