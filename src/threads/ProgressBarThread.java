package threads;

import java.io.IOException;
import java.util.ArrayList;

import controller.GenerationDataController;
import javafx.application.Platform;
import model.Database;

public class ProgressBarThread extends Thread{

	private GenerationDataController controller;
	private Database database;
	private double i;
	private double peopleToGenerate;

	public ProgressBarThread(GenerationDataController controller, Database database, int peopleToGenerate) {
		this.controller = controller;
		this.database = database;
		this.peopleToGenerate = peopleToGenerate;
		i = 0.0;
	}

	@Override
	public void run() {	
		double toAdd = 1.0 / peopleToGenerate;
		ArrayList<String> dataNames = null;
		ArrayList<String> dataLastNames = null;
		ArrayList<String> dataHeights = null;
		ArrayList<String> dataCountries = null;
		ArrayList<String> countriesFilter = null;
		ArrayList<String> dataAges = null;
		
		try {
			dataNames = database.loadData("data/babynames-clean.csv",0);
			dataLastNames = database.loadData("data/Names_2010Census.csv",1);
			dataHeights = database.loadData("data/heights.txt", 0);
			dataCountries = database.loadCompleteData("data/population_by_country_2020.csv", 1);
			countriesFilter = database.loadData("data/population_filter.csv", 1);
			dataCountries = database.filterData(dataCountries, countriesFilter);
			dataCountries = database.generatePercentages(dataCountries);
			dataAges = database.loadData("data/ages-percents.txt", 0);
		}catch(IOException e) {
			
		}

		

		while(i < 1.0) {

			
			
			try {
				database.generatePerson(dataNames, dataLastNames, dataHeights, dataCountries, dataAges);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Platform.runLater(new Thread() {
				@Override
				public void run() {
					controller.setProgressBar(i);
				}
			});
			
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				System.out.println("Thread is dead");
			}
			
			i += toAdd;
		}
		
	}
}
