package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import org.hamcrest.core.IsEqual;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import model.AVLTree;
import model.Person;

public class SearchDataController implements Initializable {

	private MenuController mc;
	private Stage searchData;
	public static final String CODE = "Code";
	public static final String NAME = "Name";
	public static final String LASTNAME = "Last Name";
	public static final String FULL_NAME = "Full name";
	private ArrayList<String> listaB;
	
	@FXML
	private MenuButton selecDataType;

	@FXML
	private Label numberMatches;

	@FXML
	private TextField textSearch;

	@FXML
	private TableView<Person> tableInfo;

	@FXML
	private TableColumn<Person, String> columAtributo;

	@FXML
	private TableColumn<Person, Button> columButton;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		mc = new MenuController();
		searchData = new Stage();
		selecDataType = new MenuButton();
//		textSearch.setOnKeyTyped(event -> searchOption(event));
		listaB = new ArrayList<>();
	}

	private void metodoA() {
		Button boton = null;
		boton.setText("Edit");
		boton.setOnAction(e -> {
			try {
				Parent root = FXMLLoader.load(getClass().getResource("/application/updateOrDelete.fxml"));
				Scene scene = new Scene(root);
				this.getSearchData().setScene(scene);
				this.getSearchData().setTitle("Data Search");
				this.getSearchData().show();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		
		});
		for (int i = 0; i < 20; i++) {
//			tableInfo.add(listaB.get(i),boton);
		}
	}
	
	private void searchOption(KeyEvent event) {
		if (textSearch.getText().equals(CODE)) {
			listaB = mc.getAvlTree().searchDataAttribute(textSearch.getText(), CODE);
		} else if (textSearch.getText().equals(FULL_NAME)) {
			listaB = mc.getAvlTree().searchDataAttribute(textSearch.getText(), FULL_NAME);
		} else if (textSearch.getText().equals(NAME)) {
			listaB = mc.getAvlTree().searchDataAttribute(textSearch.getText(), NAME);
		} else if (textSearch.getText().equals(LASTNAME)) {
			listaB = mc.getAvlTree().searchDataAttribute(textSearch.getText(), LASTNAME);
		} else if (textSearch.getText() == "") {
			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
			alert.setHeaderText(null);
			alert.setTitle("Heyyy");
			alert.setContentText("You have not selected what type of search to do!!!");
			Optional<ButtonType> action = alert.showAndWait();
		}
	}

	@FXML
	void searchDataBase(ActionEvent event) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("/application/updateOrDelete.fxml"));
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

	@FXML // metodo para iniciar la busqueda
	public void selecDataTypeee(ActionEvent event) {
		selecDataType.getId();
	}

	@FXML
	public void searchCode(ActionEvent event) {
		textSearch.setText("Code");
	}

	@FXML
	public void searchFull(ActionEvent event) {
		textSearch.setText("Full Name");
	}

	@FXML
	public void searchName(ActionEvent event) {
		textSearch.setText("Name");
	}

	@FXML
	public void searchLastName(ActionEvent event) {
		textSearch.setText("Last Name");
	}

}
