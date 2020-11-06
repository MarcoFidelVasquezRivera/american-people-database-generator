package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.Database;
import model.Person;

public class UpdateOrDeleteController {

	private MenuController mc;
	private Database database;
	private Stage updateDelete;
	private Person person;

	@FXML
	private TextField textName;

	@FXML
	private TextField textLastName;

	@FXML
	private TextField textGender;

	@FXML
	private TextField textDate;

	@FXML
	private TextField textHeight;

	@FXML
	private TextField textNationality;

	@FXML
	private ImageView photoo;

	@FXML
	private TextField textImg;

	@FXML
	private TextField textAge;

	@FXML
	private Label textCode;

	@FXML
	private TextField textImgg;

	public UpdateOrDeleteController(MenuController mc, Database database, Person person) {
		this.mc = mc;
		this.database = database;
		this.person = person;
	}

	public void initPerson() {
		textName.setPromptText(person.getName());
		textLastName.setPromptText(person.getLastName());
		textGender.setPromptText(person.getGender());
		textDate.setPromptText(person.getDate());
		textHeight.setPromptText(person.getHeight() + "");
		textNationality.setPromptText(person.getNationality());
		textAge.setPromptText(person.getAge() + "");
	}

	@FXML // eliminarlo y volverlo a insertar con la nueva info
	void updateData(ActionEvent event) {
		long codeN = person.getCode();
		String nameN = person.getName();
		String LastNameN = person.getLastName(); //code, age, name, lastName, gender, date, height, nationality, photography
//		Person n = new Person(Integer.parseInt(textCode.getText()), Integer.parseInt(textAge.getText()), textName.getText(), textLastName.getText(), textGender.getText(), textDate.getText(), Double.parseDouble(textHeight.getText()), textNationality.getText(), textImgg.getText());
//		mc.getDatabase().eliminar(codeN); aqui se elimina de los arboles
//		mc.getDatabase().insertar(n); aqui se inserta el "person"	
	}

	@FXML
	void deleteData(ActionEvent event) {
		// eliminar persona marco
//		mc.getDatabase().eliminar(codeN); aqui se elimina de los arboles
	}

}
