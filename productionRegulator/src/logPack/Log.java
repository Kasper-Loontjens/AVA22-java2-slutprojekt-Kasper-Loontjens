package logPack;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class Log{
	private static Log log = null;
	private String path = "src/saveFiles/log.dat";
	private String text = "";

	// Makes the class singleton
	public static Log getLog() {
			if (log == null) {
				log = new Log();	
			}
			return log;
	}
	
	// Will add new line of text to the log
	public void addToLog(String line) {
		setText((line+" \n "+getText()));
	}
	
	// Saves the log text as a .dat file
	public void saveLog() {
		try(ObjectOutputStream ous = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(path)))){
			ous.writeObject(getText());
			System.out.println("log saved");
		}catch (Exception e) {
			System.out.println(e);
			System.out.println("bollocks");
		}
	}

	public String getText() {
		return text;
	}

	private void setText(String text) {
		this.text = text;
	}
	
	
}
