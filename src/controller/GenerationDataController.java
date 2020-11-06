package controller;

import java.io.IOException;
import java.net.MalformedURLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.Database;
import threads.ProgressBarThread;

public class GenerationDataController{
	
	private MenuController mc;
	private Database database;
	private Stage generetionData;
    @FXML
    private ImageView imageView;
	
    @FXML
    private TextField nGenerate;

    @FXML
    private ProgressBar progressBar;
    
    public GenerationDataController(MenuController mc, Database database) {
    	this.mc = mc;
    	this.database = database;
    	imageView = new ImageView(new Image("https://thispersondoesnotexist.com/image"));

    }

    public void initialize() {}

    public Stage getGeneretionData() {
  		return generetionData;
  	}

  	public void setGeneretionData(Stage generetionData) {
  		this.generetionData = generetionData;
  	}

    @FXML
    void generationData(ActionEvent event) throws InterruptedException, MalformedURLException, IOException {

    	try {
    		int peopleToGenerate = Integer.parseInt(nGenerate.getText());
    		ProgressBarThread thread = new ProgressBarThread(this, database, peopleToGenerate);
    		thread.start();   
    		thread.join();
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

