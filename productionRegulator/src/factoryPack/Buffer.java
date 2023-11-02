package factoryPack;

import java.util.LinkedList;
import java.util.Queue;

import BrainPack.Observer;

public class Buffer<T> {
		
	Queue<T> buffer = new LinkedList<T>();
	
	Observer observer;
	
	public Buffer(Observer observer) {
		this.observer = observer;
	}
	
	public synchronized void put(T item) {
		// Puts new item into buffer if the size is below 101 and then notifies Observer
		if (buffer.size()<=100) {
			buffer.add(item);
			notify();
			observer.bufferUpdate(buffer.size());
		}
	}
	
	public synchronized void createStartingAmount() {
		// Adds 50 items as starting amount into buffer
		for (int i = 0; i < 50; i++) {
			put((T) new Item(""+(char)(int)(Math.random()*100)));
		}
	}
	
	public synchronized T remove() {
		// Removes one item from buffer then notifies Observer
		if (buffer.isEmpty()) {
			try {
				wait();
			} catch (InterruptedException error) {
				error.printStackTrace();
			}
		}
		T tItem = buffer.remove();
		observer.bufferUpdate(buffer.size());
		return tItem;
		
	}
}
