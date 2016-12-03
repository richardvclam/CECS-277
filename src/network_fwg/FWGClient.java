package network_fwg;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

import javax.swing.JFrame;

public class FWGClient extends JFrame implements Runnable {
	
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
	
	private Socket sock;
	private BufferedReader read;
	private PrintStream write;
	private int cpuMove;
	private int playerMove;
	private int cpuWins;
	private int playerWins;
	private int ties;
	
	public FWGClient() {
		System.out.println("FWG Client");
		try {
			System.out.print("Requesting connection...");
			sock = new Socket("localhost", 8888);
			read = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			write = new PrintStream(sock.getOutputStream());
			System.out.println("Connected!");
		} catch (IOException e) {
			e.printStackTrace();
		}
		setBounds(frameX, frameY, frameWidth, frameHeight);
		panel = new Panel(this);
		getContentPane().add(panel);
	}

	@Override
	public void run() {
		while (true) {
			try {
				cpuMove = Integer.parseInt(read.readLine());
				System.out.println(cpuMove);
				play(playerMove, cpuMove);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void write(String command) {
		playerMove = Integer.parseInt(command);
		write.println(command);
		write.flush();
	}
	
	public static void main(String[] args) {
		FWGClient client = new FWGClient();
		client.setTitle("Fire Water Grass");
		client.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		client.setVisible(true);
		client.setResizable(false);
		
		Thread thread = new Thread(client);
		thread.start();
	}
	
	public void play(int playerMove, int cpuMove) {
		String[] choices = {"Fire", "Water", "Grass"};
		
		System.out.println("You played " + choices[playerMove - 1] + ".");
		System.out.println("The computer played " + choices[cpuMove - 1] + ".");
		
		if ((playerMove == 1 && cpuMove == 1) || (playerMove == 2 && cpuMove == 2) || (playerMove == 3 && cpuMove == 3)) {
			ties++;
			System.out.println("You tied with the computer.");
		} else if ((playerMove == 1 && cpuMove == 2) || (playerMove == 2 && cpuMove == 3) || (playerMove == 3 && cpuMove == 1)) {
			cpuWins++;
			System.out.println("You lost to the computer.");
		} else if ((playerMove == 1 && cpuMove == 3) || (playerMove == 2 && cpuMove == 1) || (playerMove == 3 && cpuMove == 2)) {
			playerWins++;
			System.out.println("You beat the computer!");
		}
		panel.repaint();
	}
	
	public int getPlayerMove() {
		return playerMove;
	}
	
	public int getCpuMove() {
		return cpuMove;
	}
	
	public int getTies() {
		return ties;
	}
	
	public int getCpuWins() {
		return cpuWins;
	}
	
	public int getPlayerWins() {
		return playerWins;
	}

}
