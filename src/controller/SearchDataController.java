package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import model.Database;
import model.Person;

public class SearchDataController {

	private MenuController mc;
	private Database database;
	private Stage searchData;
	private Boolean codeA;
	private Boolean nameA;
	private Boolean lastNameA;
	private Boolean fullNameA;
	private Boolean alert;
	private ArrayList<Person> listaB;
	private UpdateOrDeleteController updateOrDeleteController;

	@FXML
	private MenuButton selecDataType;

	@FXML
	private Label numberMatches;

	@FXML
	private TextField textSearch;

	@FXML
	private ComboBox<String> predict;

	public SearchDataController(MenuController mc, Database database) {
		searchData = new Stage();
		this.mc = mc;
		this.database = database;
		updateOrDeleteController = new UpdateOrDeleteController(mc, database, null);
		codeA = false;
		nameA = false;
		lastNameA = false;
		fullNameA = false;
		alert = false;
		listaB = new ArrayList<>();
		predict = new ComboBox<String>();
	}
	@FXML
	void  searchPredict(KeyEvent event) {
		String line = textSearch.getText();
		if (codeA) { // 1 code 2 nombre 3 apellido 4 full name
			listaB = mc.getDatabase().searchList(line, 1);
		} else if (fullNameA) {
			listaB = mc.getDatabase().searchList(line, 2);
		} else if (nameA) {
			listaB = mc.getDatabase().searchList(line, 3);
		} else if (lastNameA) {
			listaB = mc.getDatabase().searchList(line, 4);
		} else if (textSearch.getText().isEmpty() && alert == false) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setContentText("User has not selected search filter");
			textSearch.setText("");
		}
		
		if (codeA) { // 1 code 2 nombre 3 apellido 4 full name
			initload(listaB,1);
		} else if (fullNameA) {
			initload(listaB,2);
		} else if (nameA) {
			initload(listaB,3);
		} else if (lastNameA) {
			initload(listaB,4);
		}
		
		numberMatches.setText(listaB.size() + "");
	}
	
	private void initload(ArrayList<Person> listaB2, int mode) {

		switch(mode) {
		case 1: 
			for (int i = 0; i < 30 && i < listaB2.size(); i++) {
				predict.getItems().add(String.valueOf(listaB2.get(i).getCode()));
			}
			break;
		case 2: 
			for (int i = 0; i < 30 && i < listaB2.size(); i++) {
				predict.getItems().add(String.valueOf(listaB2.get(i).getName()));
			}

			break;
		case 3: 
			for (int i = 0; i < 30 && i < listaB2.size(); i++) {
				predict.getItems().add(String.valueOf(listaB2.get(i).getLastName()));
			}

			break;
		case 4: 
			for (int i = 0; i < 30 && i < listaB2.size(); i++) {
				predict.getItems().add(String.valueOf(listaB2.get(i).getName() + " " + listaB2.get(i).getLastName()));
			}

			break;
		}
		
	}

	@FXML
	void loadUpdate(ActionEvent event) throws IOException {
		Person n = database.search(predict.getSelectionModel().getSelectedItem(), 1);
		updateOrDeleteController = new UpdateOrDeleteController(mc, database, n);
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ui/updateOrDelete.fxml"));
		fxmlLoader.setController(updateOrDeleteController);
		Parent root = fxmlLoader.load();
		Scene scene = new Scene(root);
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

	@FXML
	public void searchCode(ActionEvent event) {
		textSearch.setPromptText("Code");
		codeA = true;
		alert = true;
	}

	@FXML
	public void searchFull(ActionEvent event) {
		textSearch.setPromptText("Full Name");
		fullNameA = true;
		alert = true;
	}

	@FXML
	public void searchName(ActionEvent event) {
		textSearch.setPromptText("Name");
		nameA = true;
		alert = true;
	}

	@FXML
	public void searchLastName(ActionEvent event) {
		textSearch.setPromptText("Last Name");
		lastNameA = true;
		alert = true;
	}

}
