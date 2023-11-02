package guiPack;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;

import BrainPack.Facade;

public class Gui {
	JFrame frame = new JFrame("Production Regulator");
	JPanel panel = new JPanel();
	JTextArea textArea = new JTextArea("LogArea");
	JButton addProducerButton = new JButton("+");
	JButton removeProducerButton = new JButton("-");
	JProgressBar progressBar = new JProgressBar();
	
	Facade facade;
	
	public Gui(Facade facade) {
		this.facade = facade;
	}
	
	
	public void createAndDisplay() {
		// Adds and positions GUI elements
		
		panel.setLayout(null);
				
		frame.setSize(600,600);
		
		progressBar.setValue(50);
		progressBar.setBounds(100, 50, 400, 50);
		progressBar.setStringPainted(true);
		
		textArea.setBounds(100, 110, 400, 300);
		textArea.setBorder(BorderFactory.createLineBorder(Color.black));
		textArea.setEditable(false);
		
		addProducerButton.setBounds(100, 420, 100, 30);
		removeProducerButton.setBounds(400, 420, 100, 30);
		addProducerButton.addActionListener(actionListener);
		removeProducerButton.addActionListener(actionListener);


		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
		panel.add(progressBar);
		panel.add(textArea);
		panel.add(addProducerButton);
		panel.add(removeProducerButton);
		frame.setVisible(true);		
		setTextToArea("Behold");

	}
	public void paintMe(){
		frame.repaint();
		frame.revalidate();
	}
	
	// To show log
	public void setTextToArea(String text) {
		textArea.setText(text);
	}
	// Changes % based on item amount in buffer
	public void setProgressValue(int valu) {
		progressBar.setValue(valu);
	}
	// While below 10% or above 90% colour of progressbar will change
	public void setBarColors(Color color) {
		progressBar.setForeground(color);
	}
	
	ActionListener actionListener = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			// Will add or remove producers from list when pressed.
			if (e.getSource()==addProducerButton) {
				facade.addProducer();
			}else if (e.getSource()==removeProducerButton) {
				facade.removeProducer();
			}
		}
	};
	
}
