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
    void searchDataBase(ActionEvent event) throws Exception {
    	Parent root = FXMLLoader.load(getClass().getResource("/application/updateOrDelete.fxml"));
		Scene  scene = new Scene(root);
		this.getSearchData().setScene(scene);
		this.getSearchData().setTitle("Data Search");
		this.getSearchData().show();
    }

	public Stage getSearchData() {
		return searchData;
	}

	public void setSearchData(Stage searchData) {
		this.searchData = searchData;
	}


    
}

