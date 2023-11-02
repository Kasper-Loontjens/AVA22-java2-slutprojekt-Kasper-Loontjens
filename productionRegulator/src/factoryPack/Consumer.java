package factoryPack;

public class Consumer implements Runnable {
	
	Buffer buffer;
	boolean isRunning = true;
	int interval;
	
	public Consumer(Buffer buffer, int interval) {
		this.buffer=buffer;
		this.interval = interval;
	}
	
	public void stop() {
		// Stops the consumer from running
		isRunning = false;
	}
	
	@Override
	public void run() {
		while (isRunning) {
			try {
				// Removes an item from buffer each interval * second
				buffer.remove(); 
				Thread.sleep(interval*1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
