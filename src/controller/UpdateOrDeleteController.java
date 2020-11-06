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

public class UpdateOrDeleteController{

	private MenuController mc;
	private Database database;
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
    
    @FXML
    private ImageView photoo;
    
    public UpdateOrDeleteController(MenuController mc, Database database) {
		this.mc = mc;
		this.database = database;
	}

   
    @FXML //eliminarlo y volverlo a insertar con la nueva info
    void updateData(ActionEvent event) {
    	int codeN = Integer.parseInt(code.getText());
    	String nameN = name.getText();
    	String LastNameN = lastName.getText();
//     	mc.getDatabase().updatePerson();
//    	mc.person.setName(name.getText());
//		person.setGender(gender.getText());
//		person.setDate(dataBirth.getText());
//		person.setHeight(height.getText());
//		person.setLastName(lastName.getText());
//		person.setNationality(nationality.getText());
//    	load();
    }
    
    @FXML
    void deleteData(ActionEvent event) {
    	// eliminar persona marco
//    	mc.getDatabase().deletePerson("llave");
    }
	
}

