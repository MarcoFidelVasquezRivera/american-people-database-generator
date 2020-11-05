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
	
    @FXML
    private TextField nGenerate;

    @FXML
    private ProgressBar progressBar;

    public void initialize(URL arg0, ResourceBundle arg1) {
		mc = new MenuController();
		generetionData = new Stage();
		
	}

    public Stage getGeneretionData() {
  		return generetionData;
  	}

  	public void setGeneretionData(Stage generetionData) {
  		this.generetionData = generetionData;
  	}

    @FXML
    void generationData(ActionEvent event) {
//    	mc.getDatabase().generateData();
    	progressBar = new ProgressBar(0);
    	progressBar.setVisible(true);
    	progressBar.setProgress(50);
//    	int i = 1;
//    	try {
//    		while(i <= 1) {
//    		Thread.sleep(0);
//    		progressBar.setProgress(i/100);
//    		i += 1;
//    		}
//		} catch (Exception e) {
//			System.out.println("number of people not allowed");
//		} 
    }
  
	@FXML
    void saveDataGeneration(ActionEvent event) {
//		mc.getDatabase().saveData();
    }

}

