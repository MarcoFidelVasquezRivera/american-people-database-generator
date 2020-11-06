package threads;

import controller.GenerationDataController;
import javafx.application.Platform;
import model.Database;

public class ProgressBarThread extends Thread{

	private GenerationDataController controller;
	private Database database;
	private double i;

	public ProgressBarThread(GenerationDataController controller, Database database) {
		this.controller = controller;
		this.database = database;
		i = 0.0;
	}

	@Override
	public void run() {		
		while(i < 1.0) {
			
			
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
			
			i += ;
		}
		
	}
}
