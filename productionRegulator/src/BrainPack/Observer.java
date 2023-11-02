package BrainPack;

import java.awt.Color;

import guiPack.Gui;
import logPack.Log;

public class Observer implements Runnable {
	
	private Gui gui;
	private boolean isBelow10 = false;
	private boolean isAbove90 = false;
	private boolean isRunning = true;
	private Facade facade;

	
	public Observer(Gui gui, Facade facade) {
		this.gui = gui;
		this.facade = facade;
	}
	
	public void bufferUpdate(int i) {
		// When buffer creates or removes item this will update the new amount to gui.
		notifyGuiProductAmount(i);
	}
	
	public void notifyGuiProductAmount(int i) {
		// Sets product amount to grogressbar in gui.
		gui.setProgressValue(i);
		// If the amount is changed from 10-90 to below 10 or above 90 it will update log and display red or green in gui
		if (!isAbove90 && !isBelow10) {
			if (i<10) {
				isBelow10 = true;
				Log.getLog().addToLog("Warning items are below 10");
				gui.setBarColors(Color.red);
				displayLogInGui();
			}else if (i > 90) {
				isAbove90 = true;
				Log.getLog().addToLog("Warning items are above 90");
				gui.setBarColors(Color.green);
				displayLogInGui();
			}
		}else if (isAbove90 || isBelow10) {
			if (i>10 && i<90) {
				isAbove90 = false;
				isBelow10 = false;
				Log.getLog().addToLog("Item amount are within 10-90");
				gui.setBarColors(Color.cyan);
				displayLogInGui();
			}
		}
	}
	
	public void producerAddedUpdate(int producerAmount, int productionInterval) {
		// When a new producer is added this will update log and gui.
		Log.getLog().addToLog("Producer added: interval = " + productionInterval + ". Producers = " + producerAmount);
		displayLogInGui();
	}
	public void producerRemovedUpdate(int producerAmount) {
		// When a new producer is removed this will update log and gui.
		Log.getLog().addToLog("Producer removed. Producers = "+producerAmount);
		displayLogInGui();
	}
	
	public void displayLogInGui() {
		// Updates gui to show log
		gui.setTextToArea(Log.getLog().getText());
	}
	
	@Override
	public void run() {
		while (isRunning) {
			try {
				// Saves the log each 10 second 
				Thread.sleep(10000);
				notifyLogAverageProduction();
				Log.getLog().saveLog();
				displayLogInGui();

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void notifyLogAverageProduction() {
		double averageConsum = facade.getAverageConsumption();
		double averageProd = facade.getAverageProduction();
		String averageProductionText = "Average production is " + ((averageProd-averageConsum)/10)+ " a second";
		Log.getLog().addToLog(averageProductionText);
	}

}
