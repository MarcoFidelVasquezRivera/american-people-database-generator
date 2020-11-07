package controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.imageio.ImageIO;

import customExceptions.ElementAlreadyExistException;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
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
	private TextField textAge;

	@FXML
	private Label textCode;


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
		textCode.setText(String.valueOf(person.getCode()));
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
		try {
		database.delete(person.getCode(), person.getName(), person.getLastName());
		Long a = Long.parseLong(textCode.getText());
		Integer b = Integer.parseInt(textAge.getText());
		Integer c = Integer.parseInt(textHeight.getText());
		database.addPerson(textName.getText(), textLastName.getText(), a, textGender.getText(), b, c, textDate.getText(), textNationality.getText(), textName.getText() + " " + textLastName.getText());
		}catch(NumberFormatException e) {
			Alert za = new Alert(AlertType.ERROR, "Invalid inputs");
			za.show();
		} catch (ElementAlreadyExistException e) {
			Alert s = new Alert(AlertType.ERROR, "Element already exists in database");
			s.show();
		}
		
		initializeImageView();
	}

	@FXML
	void deleteData(ActionEvent event) {
		database.delete(person.getCode(), person.getName(), person.getLastName());
		Alert a = new Alert(AlertType.CONFIRMATION, "Person deleted successfully");
		a.show();
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
