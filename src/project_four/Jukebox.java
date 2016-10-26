package project_four;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Jukebox {
	
	public static void main(String[] args) {
		Scanner in = null;
		Scanner input = new Scanner(System.in);
		try {
			File file = new File("src/project_four/songs.txt");
			in = new Scanner(file);
		} catch (IOException e) {
			
		}
		
		Heap heap = new Heap();
		
		while (in.hasNextLine()) {
			String[] taskString = in.nextLine().split(",");
			heap.addNode(new Node<Comparable>(new Song(taskString[0], taskString[1], taskString[2], Integer.parseInt(taskString[3]))));
		}
		
		String[] menu = {"Display the list of song", "Display current song", "Add a new song to list",
				"Play next song", "Re-rate current song", "Quit"};
		
		int response = -1;
		while (response != 6) {
			response = Util.checkUserInput("==== Jukebox ====\nWhat would you like to do?", menu);
			
			switch (response) {
			case 1:
				if (heap.getSize() > 0) {
					heap.printHeap();
				} else {
					System.out.println("You currently have no songs.");
				}
				break;
			case 2:
				if (heap.getSize() > 0) {
					System.out.println("Now playing '" + heap.getNodeAt(0).getData().toString() + "'.");
				} else {
					System.out.println("You currently have no songs.");
				}
				break;
			case 3:
				System.out.println("Enter in a song title: ");
				String title = input.nextLine();
				System.out.println("Enter the artist: ");
				String artist = input.nextLine();
				System.out.println("Enter the album: ");
				String album = input.nextLine();
				System.out.println("Enter the artist: ");
				int rating = input.nextInt();
				heap.addNode(new Node<Comparable>(new Song(title, artist, album, rating)));
				break;
			case 4:
				if (heap.getSize() > 0) {
					heap.removeMin();
				}
				if (heap.getSize() > 0) {
					System.out.println("Now playing '" + heap.getNodeAt(0).getData().toString() + "'.");
				} else {
					System.out.println("You currently have no songs.");
				}
				break;
				
			case 5:
				if (heap.getSize() > 0) {
					System.out.println("Please enter a new rating: ");
					int newRating = input.nextInt();
					Song currentSong = (Song) heap.getNodeAt(0).getData();
					Song tempSong = new Song(currentSong.getTitle(), currentSong.getArtist(), currentSong.getAlbum(), newRating);
					heap.removeMin();
					heap.addNode(new Node<Comparable>(tempSong));
				} else {
					System.out.println("You currently have no songs.");
				}
				break;
			}
		}
		
		in.close();
	}

}
