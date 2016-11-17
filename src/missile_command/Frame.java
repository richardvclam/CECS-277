package missile_command;

import javax.swing.JFrame;

/**
 * Frame class that draws the window with a panel, sets the window title, and other
 * additional options.
 * @author rvclam
 *
 */
public class Frame extends JFrame {
	
	/**
	 * Default x-coord window location
	 */
	private int frameX = 400;
	/**
	 * Default y-coord window location
	 */
	private int frameY = 200;
	/**
	 * Default window width
	 */
	private int frameWidth = 500;
	/**
	 * Default window height
	 */
	private int frameHeight = 600;
	/**
	 * Panel to draw onto the window
	 */
	private Panel panel;
	
	/**
	 * Constructor
	 * Sets the window bounds, instantiates the panel, and starts the draw thread.
	 */
	public Frame() {
		setBounds(frameX, frameY, frameWidth, frameHeight);
		panel = new Panel();
		getContentPane().add(panel);
		Thread a = new Thread(panel);
		a.start();
	}
	
	/**
	 * Main method
	 * @param args is the input arguments
	 */
	public static void main(String[] args) {
		Frame f = new Frame();
		f.setTitle("Missile Command");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		f.setResizable(false);
	}
}
