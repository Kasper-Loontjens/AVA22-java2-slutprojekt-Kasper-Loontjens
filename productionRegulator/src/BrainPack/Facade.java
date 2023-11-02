package BrainPack;

import java.util.LinkedList;

import factoryPack.Buffer;
import factoryPack.Consumer;
import factoryPack.Item;
import factoryPack.Producer;
import guiPack.Gui;
import logPack.Log;
import utilitiesPack.Utilities;

public class Facade {
	
	private double averageConsumption = 0;
	private double averageProduction = 0;
	
	Gui gui = new Gui(this);
	Observer observer = new Observer(gui, this);
	Buffer<Item> buffer = new Buffer<Item>(observer);
	Log log = Log.getLog();

	LinkedList<Consumer> consumers = new LinkedList<Consumer>();
	LinkedList<Producer> producers = new LinkedList<Producer>();
	
	public void addConsumers() {
		// Adds 1-15 consumer to a consumerList, each consumer will remove items from buffer between 1-10 seconds
		int consumerAmount = Utilities.getUtilities().getRandumInt(1, 15);
		int interval = Utilities.getUtilities().getRandumInt(1, 10);

		for (int i = 0; i <= consumerAmount; i++) {
			interval = Utilities.getUtilities().getRandumInt(1, 10);
			consumers.add(new Consumer(buffer, interval));
			Thread consumerThread = new Thread(consumers.get(i));
			consumerThread.start();
			System.out.println("Old consum = " + averageConsumption);
			averageConsumption += (10/(double)interval);
			System.out.println("adding 10/" + interval + "="+ (10/(double)interval) +". New consum = " + averageConsumption);
		}
	}
	public void addProducer() {
		// Adds one new producer to list then notifies Observer. 
		// Each producer will add one item to buffer each 1-10 second
		int interval = Utilities.getUtilities().getRandumInt(1, 10);
		producers.add(new Producer(buffer,interval));
		Thread producerThread = new Thread(producers.getLast());
		producerThread.start();
		
		observer.producerAddedUpdate(producers.size(), interval);
		countNewProductionRate();
	}
	public void countNewProductionRate() {
		averageProduction = 0;
		for (Producer producer : producers) {
			System.out.println("Old produce = " + averageProduction);
			averageProduction +=(10/(double)producer.getInterval());
			System.out.println("adding 10/" + producer.getInterval() + "="+ (10/(double)producer.getInterval()) +". New produce = " + averageProduction);
		}
	}
	public void removeProducer() {
		// Removes and stops one producer in list then notifies Observer. 
		if (producers.size()>0) {
			producers.get(0).stop();
			producers.remove(0);
			observer.producerRemovedUpdate(producers.size());
			countNewProductionRate();
		}
	}
	
	public void initialize() {
		// At the start of program this will run and initialise Gui, buffer and log.
		gui.createAndDisplay();
		buffer.createStartingAmount();
		addConsumers();
		addProducer();
		Thread observerThread = new Thread(observer);
		observerThread.start();
	}
	public double getAverageConsumption() {
		return averageConsumption;
	}
	public void setAverageConsumption(double averageConsumption) {
		this.averageConsumption = averageConsumption;
	}
	public double getAverageProduction() {
		return averageProduction;
	}
	public void setAverageProduction(double averageProduction) {
		this.averageProduction = averageProduction;
	}

}
