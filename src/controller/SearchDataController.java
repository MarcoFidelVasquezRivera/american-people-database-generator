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
	private ComboBox<Person> predict;

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
		predict = new ComboBox<Person>();
	}
	@FXML
	void  searchPredict(KeyEvent event) {
		String line = textSearch.getText();
		if (codeA) { // 1 code 2 nombre 3 apellido 4 full name
			listaB = mc.getDatabase().searchList(line, 1);
		} else if (fullNameA) {
			listaB = mc.getDatabase().searchList(line, 1);
		} else if (nameA) {
			listaB = mc.getDatabase().searchList(line, 1);
		} else if (lastNameA) {
			listaB = mc.getDatabase().searchList(line, 1);
		} else if (textSearch.getText().isEmpty() && alert == false) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setContentText("User has not selected search filter");
			Optional<ButtonType> action = alert.showAndWait();
			textSearch.setText("");
		}
		//initload(listaB);
		//numberMatches.setText(listaB.size() + "");
	}
	
	private void initload(ArrayList<Person> listaB2) {
		for (int i = 0; i < 20; i++) {
			predict.getItems().add(listaB2.get(i));
		}
	}

	@FXML
	void loadUpdate(ActionEvent event) throws IOException {
		Person n = predict.getSelectionModel().getSelectedItem();
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
