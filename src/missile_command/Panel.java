package missile_command;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.sound.sampled.AudioSystem;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Panel class that draws all the images, handles all the events, and handles game logic.
 * @author rvclam
 *
 */
public class Panel extends JPanel implements Runnable, MouseListener, MouseMotionListener, KeyListener, ActionListener {
	
	/**
	 * Cities
	 */
	private City[] cities = new City[6];
	/**
	 * Batteries
	 */
	private Battery[] batteries = new Battery[3];
	/**
	 * Mouse point location
	 */
	private Point mouse;
	/**
	 * All missiles instantiated
	 */
	private ArrayList<Missile> missiles;
	/**
	 * All explosions instantiated
	 */
	private ArrayList<Explosion> explosions;
	/**
	 * Current round number
	 */
	private int round;
	/**
	 * Current missiles fired
	 */
	private int missilesFired;
	/**
	 * The game status
	 */
	private boolean pause;
	/**
	 * Start button
	 */
	private JButton btnStart;
	/**
	 * Next round button
	 */
	private JButton btnNextRound;
	/**
	 * Play again button
	 */
	private JButton btnPlayAgain;
	/**
	 * Attacker thread
	 */
	private Thread attacker;
	/**
	 * Ground image
	 */
	private BufferedImage ground;
	/**
	 * Ground tile image
	 */
	private BufferedImage tile;
	/**
	 * Background image
	 */
	private BufferedImage background;
	
	/**
	 * Constructor
	 * Adds all of the buttons, instantiate objects, load graphical resources, and 
	 * creates an attacker thread that launches missiles in a set interval.
	 */
	public Panel() {
		round = 0;
		pause = false;
		
		// User Interface
		setBackground(Color.WHITE);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		// Start Button
		btnStart = new JButton("Start");
		btnStart.addActionListener(this);
		btnStart.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(btnStart);
		// Next Round Button
		btnNextRound = new JButton("Next Round");
		btnNextRound.addActionListener(this);
		btnNextRound.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(btnNextRound);
		btnNextRound.setVisible(false);
		// Play Again Button
		btnPlayAgain = new JButton("Play Again");
		btnPlayAgain.addActionListener(this);
		btnPlayAgain.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(btnPlayAgain);
		btnPlayAgain.setVisible(false);
		
		missiles = new ArrayList<Missile>();
		explosions = new ArrayList<Explosion>();
		
		// Load Resources
		try {
			ground = ImageIO.read(new File("src/missile_command/img/ground.png"));
			tile = ImageIO.read(new File("src/missile_command/img/tile.png"));
			background = ImageIO.read(new File("src/missile_command/img/back.png"));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
		// Starting Mouse Position
		mouse = new Point(this.getWidth()/2, this.getHeight()/2);

		// Create a thread to add new attacker missles
		attacker = new Thread() {
			public void run() {
				while (true) {
					System.out.println();
					if (!pause) {
						System.out.println("starting new round");
						while (missilesFired < 15) {
							System.out.println("misslesFired " + missilesFired);
							int missleSpeed = 3;
							int missleDelay = 1000;
							int randomX = (int) (Math.random()*501);
							int randCityBarrier = (int) (Math.random() * 2);
							int randStructure = randCityBarrier == 0 ? (int) (Math.random() * 6) : (int) (Math.random() * 3);
							do {
								if (randCityBarrier == 0 ? !cities[randStructure].isActive() : !batteries[randStructure].isActive()) {
									randCityBarrier = (int) (Math.random() * 2);
									randStructure = randCityBarrier == 0 ? (int) (Math.random() * 6) : (int) (Math.random() * 3);
								}
							} while (randCityBarrier == 0 ? !cities[randStructure].isActive() : !batteries[randStructure].isActive());
							
							missiles.add(new Missile(new Point(randomX, 0), randCityBarrier == 0 ? cities[randStructure].getLocation() : batteries[randStructure].getLocation(), missleSpeed, 1, Color.BLACK));
							try {
								Thread.sleep(missleDelay);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							missilesFired++;
						}
						pause = true;
						missilesFired = 0;
					}
				}
			}
		};
		
		// Event Listeners
		addMouseListener(this);
		addMouseMotionListener(this);
		addKeyListener(this);
		setFocusable(true);
	}
	
	/**
	 * Draws all of the objects onto the graphics panel.
	 * @param g is the graphics panel to draw onto
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (round != 0) {
			// Draw background
			for (int i = 0; i <= getWidth()/background.getWidth(); i++) {
				g.drawImage(background, background.getWidth() * i, -120, this);
			}
			if (pause && missiles.isEmpty() && citiesLeft() > 0) {
				btnNextRound.setVisible(true);
			} else if (pause && missiles.isEmpty() && citiesLeft() == 0 && !btnNextRound.isVisible()) {
				btnPlayAgain.setVisible(true);
				g.drawString("YOU LOSE", 200, 250);
			}
			// Draw ground
			for (int i = 0; i <= getWidth()/ground.getWidth(); i++) {
				g.drawImage(ground, ground.getWidth()*i, 430, this);
			}
			for (int i = 0; i <= getWidth()/tile.getWidth(); i++) {
				for (int j = 0; j <= (getHeight() - 430)/tile.getHeight(); j++) {
					g.drawImage(tile, tile.getWidth()*i, (430 + ground.getHeight()) + tile.getHeight() * j, this);
				}
			}
			// Draw Round numbers
			g.setColor(Color.WHITE);
			Font font = new Font("Arial", Font.BOLD, 15);
			g.setFont(font);
			g.drawString("Round " + round, 10, 20);
			
			// Draw cities
			g.setColor(Color.BLACK);
			for (City c : cities) {
				if (c.isActive()) {
					c.draw(g);
				}
			}
			
			// Draw batteries
			g.setColor(Color.BLUE);
			for (Battery b : batteries) {
				if (b.isActive()) {
					b.draw(g);
				}
			}
			
			// Draw crosshair
			g.setColor(Color.RED);
			g.drawLine((int) (mouse.getX() - 5), (int) mouse.getY(), (int) (mouse.getX() + 5), (int) mouse.getY());
			g.drawLine((int) mouse.getX(), (int) (mouse.getY() - 5), (int) mouse.getX(), (int) (mouse.getY() + 5));
		
			// Draw missles
			for (Missile m : missiles) {
				if (m.isActive()) {
					m.draw(g);
				}
			}
			
			// Draw explosions
			for (Explosion e : explosions) {
				if (e.isActive()) {
					e.draw(g);
				}
			}
		}
	}

	/**
	 * Event listener for when the user moves their mouse cursor. 
	 * This sets the current mouse position to the new mouse point.
	 * @param e is the mouse event
	 */
	@Override
	public void mouseMoved(MouseEvent e) {
		mouse = e.getPoint();
	}

	/**
	 * Event listener for when the user performs a mouse click.
	 * This shoots a missile from the closest locating battery from the mouse location.
	 * @param e is the mouse event
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		int shortestDistance = 1000;
		Battery battery = null;
		for (Battery b : batteries) {
			if (Math.abs(b.getLocPoint().getX() - e.getX()) < shortestDistance && b.isActive() && b.getNumMissles() > 0) {
				shortestDistance = (int) Math.abs(b.getLocPoint().getX() - e.getX());
				battery = b;
			} else {
				continue;
			}
		}
		try {
			if (battery.getNumMissles() > 0) {
				missiles.add(new Missile(battery.getLocation(), e.getPoint(), 10, 0, Color.RED));
				battery.removeMissle();
			}
		} catch (NullPointerException ex) {
		}
	}

	/**
	 * Event listener for when the user performs a key press.
	 * This shoots a missile from the closest locating battery from the mouse location,
	 * or shoots a missile from a specific battery.
	 * @param e is the key event
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		Battery battery = null;
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			int shortestDistance = 1000;
			for (Battery b : batteries) {
				if (Math.abs(b.getLocPoint().getX() - mouse.getX()) < shortestDistance && b.isActive() && b.getNumMissles() > 0) {
					shortestDistance = (int) Math.abs(b.getLocPoint().getX() - mouse.getX());
					battery = b;
				} else {
					continue;
				}
			}
		} else if (e.getKeyCode() == KeyEvent.VK_1) {
			if (batteries[0].isActive()) {
				battery = batteries[0];
			}
		} else if (e.getKeyCode() == KeyEvent.VK_2) {
			if (batteries[1].isActive()) {
				battery = batteries[1];
			}
		} else if (e.getKeyCode() == KeyEvent.VK_3) {
			if (batteries[2].isActive()) {
				battery = batteries[2];
			}
		}
		try {
			if (battery.getNumMissles() > 0) {
				missiles.add(new Missile(battery.getLocation(), mouse, 10, 0, Color.RED));
				battery.removeMissle();
			}
		} catch (NullPointerException ex) {
		}
	}

	/**
	 * Redraws the panel with coliision detection.
	 */
	@Override
	public void run() {
		while (true) {
			for (int i = 0; i < missiles.size(); i++) {
				if (missiles.get(i).isActive()) {
					missiles.get(i).move();
					for (int j = 0; j < cities.length; j++) {
						if (cities[j].isHit(missiles.get(i).getLocPoint())) {
						}
					}
					
					for (int j = 0; j < batteries.length; j++) {
						if (batteries[j].isHit(missiles.get(i).getLocPoint())) {
						}
					}
				} else {
					explosions.add(new Explosion(missiles.get(i).getLocPoint()));
					missiles.remove(i);
				}
			}
			
			for (int i = 0; i < explosions.size(); i++) {
				if (explosions.get(i).isActive()) {
					explosions.get(i).move();
					for (int j = 0; j < missiles.size(); j++) {
						if (explosions.get(i).contains(missiles.get(j).getLocPoint()) && missiles.get(j).getType() == 1) {
							explosions.add(new Explosion(missiles.get(j).getLocPoint()));
							missiles.remove(j);
						}
					}
				} else {
					explosions.remove(i);
				}
			}
						
			repaint();
			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Event handler for buttons.
	 * Start button starts a new round.
	 * Next round resets the round and starts the next round.
	 * Play again takes the game to the start tcreen.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnStart) {
			btnStart.setVisible(false);
			startNewRound();
			if (!attacker.isAlive()) {
				attacker.start();
			}
		} else if (e.getSource() == btnNextRound) {
			btnNextRound.setVisible(false);
			startNewRound();
			pause = false;
		} else if (e.getSource() == btnPlayAgain) {
			round = 0;
			btnPlayAgain.setVisible(false);
			btnStart.setVisible(true);
		}
	}
	
	/**
	 * Starts a new round. Instantiate city and battery objects, clears missiles list,
	 * and increments round.
	 */
	public void startNewRound() {
		if (round == 0) {
			// Create the City objects
			for (int i = 0; i < cities.length; i++) {
				cities[i] = new City(i*55 + 65 + (i > 2 ? 40 : 0), 385);
			}
		}
		// Create the Battery objects
		for (int i = 0; i < batteries.length; i++) {
			batteries[i] = new Battery(i * 200 + 20, 380);
		}
		pause = false;
		round++;
		missiles = new ArrayList<Missile>();
	}
	
	/**
	 * Checks if there are any cities remaining for the user to continue playing.
	 * @return number of cities left
	 */
	public int citiesLeft() {
		int left = cities.length;
		for (City c: cities) {
			if (!c.isActive()) {
				left--;
			}
		}
		return left;
	}
	
	/**
	 * Plays an audio clip.
	 * @param filename is the file location of the audio clip.
	 */
	public static void play(String filename) {
		try {
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(new File(filename)));
			clip.start();
		} catch (LineUnavailableException e) {
			System.err.println("Audio Error");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("File Not Found");
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			System.err.println("Wrong File Type");
			e.printStackTrace();
		}
	}
	
	/**
	 * Event handler for when a mouse click is dragged.
	 * @param e is the mouse event
	 */
	@Override
	public void mouseDragged(MouseEvent e) { }
	
	/**
	 * Event handler for when a mouse click is pressed.
	 * @param e is the mouse event
	 */
	@Override
	public void mousePressed(MouseEvent e) { }

	/**
	 * Event handler for when a mouse click is released.
	 * @param e is the mouse event
	 */
	@Override
	public void mouseReleased(MouseEvent e) { }

	/**
	 * Event handler for when a mouse enters an object.
	 * @param e is the mouse event
	 */
	@Override
	public void mouseEntered(MouseEvent e) { }

	/**
	 * Event handler for when a mouse exits an object.
	 * @param e is the mouse event
	 */
	@Override
	public void mouseExited(MouseEvent e) { }

	/**
	 * Event handler for when a key is pressed.
	 * @param e is the key event
	 */
	@Override
	public void keyTyped(KeyEvent e) { }
	
	/**
	 * Event handler for when a key is released.
	 * @param e is the key event
	 */
	@Override
	public void keyReleased(KeyEvent e) { }
}
