package factoryPack;


public class Producer implements Runnable {

	private Buffer<Item> buffer = null;
	
	private Boolean isRunning = true;
	private int interval;
	
	public Producer(Buffer<Item> buffer,int interval) {
		this.setBuffer(buffer);
		this.setInterval(interval);
	}
	
	public void stop() {
		// Stops the producer from running
		setIsRunning(false);
	}
	
	@Override
	public void run() {
		
		while (getIsRunning()) {
			try {
				// Adds an item from buffer each interval * second
				getBuffer().put(new Item(""+(char)(int)(Math.random()*100)));
				Thread.sleep(getInterval()*1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	//Getters and setters ---------------------------------------------------------------
	public int getInterval() {
		return interval;
	}

	public void setInterval(int interval) {
		this.interval = interval;
	}

	public Boolean getIsRunning() {
		return isRunning;
	}

	public void setIsRunning(Boolean isRunning) {
		this.isRunning = isRunning;
	}

	public Buffer<Item> getBuffer() {
		return buffer;
	}

	public void setBuffer(Buffer<Item> buffer) {
		this.buffer = buffer;
	}
}
