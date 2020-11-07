package threads;

import java.util.ArrayList;

import controller.SearchDataController;
import javafx.application.Platform;
import model.Database;
import model.Person;

public class PredictionThread extends Thread{
	
	private SearchDataController searchControler;
	private Database database;
	private String point;
	private ArrayList<Person> list;
	private int mode = 0;
	
	public PredictionThread(SearchDataController sdc, Database db) {
		this.searchControler = sdc;
		this.database = db;
	}
	
	
	@Override
	public void run() {
		String toggleSelected = searchControler.getSelectedToggle();
		String searched = searchControler.getTSearchedText();
		point = searched;
		while(true) {
			
			if(!point.equalsIgnoreCase(searched) && !searched.equalsIgnoreCase("") && !toggleSelected.equalsIgnoreCase("")) {		
				if(toggleSelected.equalsIgnoreCase("code")) {
					mode = 1;
				}else if(toggleSelected.equalsIgnoreCase("name")) {
					mode = 2;
				}else if(toggleSelected.equalsIgnoreCase("last name")) {
					mode = 3;
				}else if(toggleSelected.equalsIgnoreCase("full name")) {
					mode = 4;
				}
				
				list = database.searchList(searched, mode);
				
				Platform.runLater(new Thread() {
					@Override
					public void run() {
						searchControler.initload(list, mode);
					}
				});
				point = searched;
			}
			
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			searched = searchControler.getTSearchedText();
			toggleSelected = searchControler.getSelectedToggle();
		}
	}
}
