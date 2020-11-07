package controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.imageio.ImageIO;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
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
		photoo = new ImageView();
		initializeImageView();
	}
	
	public void initialize() {
		initializeImageView();
		initPerson();
	}
	
	public void initPerson() {
		textName.setText(person.getName());
		textLastName.setText(person.getLastName());
		textGender.setText(person.getGender());
		textDate.setText(person.getDate());
		textHeight.setText(person.getHeight() + "");
		textNationality.setText(person.getNationality());
		textAge.setText(person.getAge() + "");
	}

	@FXML // eliminarlo y volverlo a insertar con la nueva info
	void updateData(ActionEvent event) {
		long codeN = person.getCode();
		String nameN = person.getName();
		String LastNameN = person.getLastName(); //code, age, name, lastName, gender, date, height, nationality, photography
	//	Person n = new Person(Integer.parseInt(textCode.getText()), Integer.parseInt(textAge.getText()), textName.getText(), textLastName.getText(), textGender.getText(), textDate.getText(), Double.parseDouble(textHeight.getText()), textNationality.getText(), textImgg.getText());
	//	mc.getDatabase().eliminar(codeN); //aqui se elimina de los arboles
	//	mc.getDatabase().insertar(n); //aqui se inserta el "person"	
		initializeImageView();
	}

	@FXML
	void deleteData(ActionEvent event) {
		// eliminar persona marco
		// mc.getDatabase().eliminar(codeN); aqui se elimina de los arboles
	}


	public void initializeImageView() {
		try {
			URL url = new URL("https://thispersondoesnotexist.com/image");
			HttpURLConnection httpcon = (HttpURLConnection) url.openConnection(); 
	    	httpcon.addRequestProperty("User-Agent", ""); 
	    	BufferedImage image = ImageIO.read(httpcon.getInputStream());
	    	Image ald = SwingFXUtils.toFXImage(image,null);
	    	photoo.setImage(ald);
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
	}

}
