package network_fwg;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class FWGServer extends Thread {
	
	private File saveFile = new File("src/network_fwg/fwg.dat");
	private ServerSocket server;
	private Socket sock;
	private BufferedReader read;
	private PrintStream write;
	private Computer cpu;
	
	public FWGServer() {
		System.out.println("FWG Server");
		
		if (saveFile.exists()) {
			System.out.print("Existing patterns file found. Loading file...");
			try {
				ObjectInputStream load = new ObjectInputStream(new FileInputStream(saveFile));
				cpu = (Computer) load.readObject();
				cpu.resetScores();
				load.close();
				System.out.println("complete!");
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("There was an error loading the file.");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				System.out.println("There was an error loading the file.");
			}
		} else {
			cpu = new Computer();
		}
		try {
			server = new ServerSocket(8888);
			System.out.print("Waiting for connection...");
			sock = server.accept();
			read = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			write = new PrintStream(sock.getOutputStream());
			System.out.println("Connected!");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		while (true) {
			try {
				String input = read.readLine();
				System.out.println(input);
				int cpuMove = cpu.makePrediction();
				int playerMove = Integer.parseInt(input);
				cpu.storePattern(playerMove);
				write.println(cpuMove);
			} catch (SocketException e) {
				System.out.print("Connection closed. ");
				
				// Save patterns to file
				try {
					ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(saveFile));
					out.writeObject(cpu);
					out.close();
					System.out.println("Patterns successfully saved!");
				} catch (IOException ex) {
					System.err.println("There was an error saving patterns.");
				}
				
				// Listening for new client connection
				System.out.print("Waiting for connection...");
				try {
					sock = server.accept();
					read = new BufferedReader(new InputStreamReader(sock.getInputStream()));
					write = new PrintStream(sock.getOutputStream());
					System.out.println("Connected!");
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public static void main(String[] args) {
		FWGServer fwgServer = new FWGServer();
		fwgServer.start();
	}

}
