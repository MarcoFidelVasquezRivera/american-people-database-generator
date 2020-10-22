package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class GenerationDataController implements Initializable {

	private MenuController mc;
	private Stage generetionData;
	
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

    }

    @FXML
    void returnMain(ActionEvent event) throws Exception {
    	generetionData.close();
		mc.getMain().start(mc.getStageEscoger());
    }

    @FXML
    void saveDataGeneration(ActionEvent event) {

    }

}

