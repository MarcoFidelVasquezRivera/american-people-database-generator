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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.AVLTree;
import model.Database;
import threads.ProgressBarThread;

public class GenerationDataController{
	
	private MenuController mc;
	private Database database;
	private Stage generetionData;
	
    @FXML
    private TextField nGenerate;

    @FXML
    private ProgressBar progressBar;
    
    public GenerationDataController(MenuController mc, Database database) {
    	this.mc = mc;
    	this.database = database;
    	
    }

    public void initialize(URL arg0, ResourceBundle arg1) {
		generetionData = new Stage();	
	}

    public Stage getGeneretionData() {
  		return generetionData;
  	}

  	public void setGeneretionData(Stage generetionData) {
  		this.generetionData = generetionData;
  	}

    @FXML
    void generationData(ActionEvent event) throws InterruptedException {

    	try {
    		int peopleToGenerate = Integer.parseInt(nGenerate.getText());
    		ProgressBarThread thread = new ProgressBarThread(this, database, peopleToGenerate);
    		thread.start();    	    
    	  	} catch (NumberFormatException e1) {
			Alert a = new Alert(AlertType.WARNING, "Input is not a number");
			a.show();
		}

    }
  
	@FXML
    public void saveDataGeneration(ActionEvent event) {
		//mc.getDatabase().saveData();
    }
	
	public ProgressBar getProgressBar() {
		return progressBar;
	} 

	public void setProgressBar(double i) {
		progressBar.setProgress(i);
	}
}

