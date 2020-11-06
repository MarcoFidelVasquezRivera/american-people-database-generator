package threads;

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
		while(i < 1.0) {
			
			
//			database.generatePerson(datasetNames, datasetLastNames, datasetHeights, datasetCountries, datasetAges);
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
