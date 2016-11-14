package missle_command;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Panel extends JPanel implements Runnable, MouseListener, MouseMotionListener, KeyListener, ActionListener {
	
	private City[] cities = new City[6];
	private Battery[] batteries = new Battery[3];
	private Point mouse;
	private ArrayList<Missle> missles;
	private ArrayList<Explosion> explosions;
	private int round;
	private int misslesFired;
	private int citiesLeft;
	private boolean pause;
	
	private JButton btnStart;
	private JButton btnNextRound;
	private JButton btnPlayAgain;
	private Thread attacker;
	
	public Panel() {
		round = 0;
		pause = false;
		
		// User Interface
		setBackground(Color.WHITE);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		btnStart = new JButton("Start");
		btnStart.addActionListener(this);
		btnStart.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(btnStart);
		
		btnNextRound = new JButton("Next Round");
		btnNextRound.addActionListener(this);
		btnNextRound.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(btnNextRound);
		btnNextRound.setVisible(false);
		
		btnPlayAgain = new JButton("Play Again");
		btnPlayAgain.addActionListener(this);
		btnPlayAgain.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(btnPlayAgain);
		btnPlayAgain.setVisible(false);
		
		// Create the City objects
		for (int i = 0; i < cities.length; i++) {
			cities[i] = new City(i*55 + 65 + (i > 2 ? 40 : 0), 400);
		}
		
		// Create the Battery objects
		for (int i = 0; i < batteries.length; i++) {
			batteries[i] = new Battery(i * 200 + 20, 390);
		}
		
		missles = new ArrayList<Missle>();
		explosions = new ArrayList<Explosion>();
		
		// Starting Mouse Position
		mouse = new Point(this.getWidth()/2, this.getHeight()/2);

		// Create a thread to add new attacker missles
		attacker = new Thread() {
			public void run() {
				while (true) {
					System.out.println();
					if (!pause) {
						System.out.println("starting new round");
						while (misslesFired < 15) {
							System.out.println("misslesFired " + misslesFired);
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
							
							missles.add(new Missle(new Point(randomX, 0), randCityBarrier == 0 ? cities[randStructure].getLocation() : batteries[randStructure].getLocation(), missleSpeed, 1, Color.BLACK));
							try {
								Thread.sleep(missleDelay);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							misslesFired++;
						}
						pause = true;
						misslesFired = 0;
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
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (round != 0) {
			if (pause && missles.isEmpty() && citiesLeft() > 0) {
				btnNextRound.setVisible(true);
			} else if (pause && missles.isEmpty() && citiesLeft() == 0 && !btnNextRound.isVisible()) {
				btnPlayAgain.setVisible(true);
				g.drawString("YOU LOSE", 200, 250);
			}
			g.drawString("Round " + round, 10, 20);
			g.setColor(new Color(21, 178, 0));
			g.fillRect(0, 420, 500, 100);
			
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
			for (Missle m : missles) {
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

	@Override
	public void mouseMoved(MouseEvent e) {
		mouse = e.getPoint();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int shortestDistance = 1000;
		Battery battery = null;
		for (Battery b : batteries) {
			if (Math.abs(b.getLocPoint().getX() - mouse.getX()) < shortestDistance && b.isActive() && b.getNumMissles() > 0) {
				shortestDistance = (int) Math.abs(b.getLocPoint().getX() - mouse.getX());
				battery = b;
			} else {
				continue;
			}
		}
		try {
			if (battery.getNumMissles() > 0) {
				missles.add(new Missle(battery.getLocation(), mouse, 10, 0, Color.RED));
				battery.removeMissle();
			}
		} catch (NullPointerException ex) {
			ex.printStackTrace();
		}
	}

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
				missles.add(new Missle(battery.getLocation(), mouse, 10, 0, Color.RED));
				battery.removeMissle();
			}
		} catch (NullPointerException ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void run() {
		while (true) {
			for (int i = 0; i < missles.size(); i++) {
				if (missles.get(i).isActive()) {
					missles.get(i).move();
					for (int j = 0; j < cities.length; j++) {
						if (cities[j].isHit(missles.get(i).getLocPoint())) {
						}
					}
					
					for (int j = 0; j < batteries.length; j++) {
						if (batteries[j].isHit(missles.get(i).getLocPoint())) {
						}
					}
				} else {
					explosions.add(new Explosion(missles.get(i).getLocPoint()));
					missles.remove(i);
				}
			}
			
			for (int i = 0; i < explosions.size(); i++) {
				if (explosions.get(i).isActive()) {
					explosions.get(i).move();
					for (int j = 0; j < missles.size(); j++) {
						if (explosions.get(i).contains(missles.get(j).getLocPoint()) && missles.get(j).getType() == 1) {
							explosions.add(new Explosion(missles.get(j).getLocPoint()));
							missles.remove(j);
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
	
	public void startNewRound() {
		if (round == 0) {
			// Create the City objects
			for (int i = 0; i < cities.length; i++) {
				cities[i] = new City(i*55 + 65 + (i > 2 ? 40 : 0), 400);
			}
		}
		// Create the Battery objects
		for (int i = 0; i < batteries.length; i++) {
			batteries[i] = new Battery(i * 200 + 20, 390);
		}
		pause = false;
		round++;
		missles = new ArrayList<Missle>();
	}
	
	public int citiesLeft() {
		int left = cities.length;
		for (City c: cities) {
			if (!c.isActive()) {
				left--;
			}
		}
		return left;
	}
	
	@Override
	public void mouseDragged(MouseEvent e) { }
	
	@Override
	public void mousePressed(MouseEvent e) { }

	@Override
	public void mouseReleased(MouseEvent e) { }

	@Override
	public void mouseEntered(MouseEvent e) { }

	@Override
	public void mouseExited(MouseEvent e) { }

	@Override
	public void keyTyped(KeyEvent e) { }
	
	@Override
	public void keyReleased(KeyEvent e) { }
}
