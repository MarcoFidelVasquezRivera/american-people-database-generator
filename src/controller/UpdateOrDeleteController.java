package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class UpdateOrDeleteController implements Initializable{

	private MenuController mc;
	private Stage updateDelete;
	
    @FXML
    private TextField name;

    @FXML
    private TextField lastName;

    @FXML
    private TextField gender;

    @FXML
    private TextField dataBirth;

    @FXML
    private TextField height;

    @FXML
    private TextField nationality;

    @FXML
    private Label code;

    @FXML
    private TextField photo;

    @Override
	public void initialize(URL location, ResourceBundle resources) {
    	mc = new MenuController();
		updateDelete = new Stage();
	}
    
    @FXML
    void deleteData(ActionEvent event) {

    }

    @FXML
    void menuMainBack(ActionEvent event) throws Exception {
    	updateDelete.close();
		mc.getMain().start(mc.getStageEscoger());
    }

    @FXML
    void updateData(ActionEvent event) {

    }

	

}

