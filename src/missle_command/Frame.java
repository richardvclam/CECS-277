package missle_command;

import javax.swing.JFrame;

public class Frame extends JFrame {
	
	private int frameX = 400;
	private int frameY = 200;
	private int frameWidth = 500;
	private int frameHeight = 600;
	private static Panel panel;
	
	public Frame() {
		setBounds(frameX, frameY, frameWidth, frameHeight);
		panel = new Panel();
		getContentPane().add(panel);
		Thread a = new Thread(panel);
		a.start();
	}
	
	public static void main(String[] args) {
		Frame f = new Frame();
		f.setTitle("Mission Command");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		f.setResizable(false);
	}
}
