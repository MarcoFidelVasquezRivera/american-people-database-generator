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
    
    @FXML
    private ImageView photoo;

    @Override
	public void initialize(URL location, ResourceBundle resources) {
    	mc = new MenuController();
		updateDelete = new Stage();
//		name.setText(mc.getAvlTree().getRoot();
		name.setText(" ");
		gender.setText(" ");
		dataBirth.setText(" ");
		height.setText(" ");
		lastName.setText(" ");
		nationality.setText(" ");
		code.setText("001");
		photoo.setId(" ");
		//edad falta
		
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

