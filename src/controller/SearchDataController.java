package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SearchDataController implements Initializable{

	private MenuController mc;
	private Stage searchData;
	
    @FXML
    private MenuItem nameSearch;

    @FXML
    private MenuItem lastNameSearch;

    @FXML
    private MenuItem fullNameSearch;

    @FXML
    private MenuItem codeSearch;

    @FXML
    private TextField selectData;

    @FXML
    private Label numberMatches;

    @FXML
    private ListView<?> listPronostic;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		mc = new MenuController();
		searchData = new Stage();
	}
    
    @FXML
    void returnMain(ActionEvent event) throws Exception {
    	searchData.close();
		mc.getMain().start(mc.getStageEscoger());
    }
    
    @FXML
    void searchDataBase(ActionEvent event) {

    }


    
}

