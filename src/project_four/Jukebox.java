package project_four;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * Jukebox class main reads from a "songs.txt" file and adds them into a heap. The
 * user can then display the list of songs, display the current song, add a new song
 * to the list, play the next song, rate the current song, or quit the program.
 * @author rvclam
 *
 */
public class Jukebox {
	
	public static void main(String[] args) {
		Scanner in = null;
		Scanner input = new Scanner(System.in);
		try {
			File file = new File("src/project_four/songs.txt");
			in = new Scanner(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Heap<Song> heap = new Heap<Song>();
		
		// Reads song from the file
		while (in.hasNextLine()) {
			String[] taskString = in.nextLine().split(",");
			heap.addNode(new Song(taskString[0], taskString[1], taskString[2], Integer.parseInt(taskString[3])));
		}
		
		String[] menu = {"Display the list of song", "Display current song", "Add a new song to list",
				"Play next song", "Re-rate current song", "Quit"};
		
		int response = -1;
		while (response != 6) {
			response = Util.checkUserInput("==== Jukebox ====\nWhat would you like to do?", menu);
			
			switch (response) {
			case 1:
				if (!heap.isEmpty()) {
					heap.printHeap();
				} else {
					System.out.println("You currently have no songs.");
				}
				break;
			case 2:
				if (!heap.isEmpty()) {
					System.out.println("Now playing '" + heap.getNodeAt(0).toString() + "'.");
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
				System.out.println("Enter the rating: ");
				int rating = input.nextInt();
				heap.addNode(new Song(title, artist, album, rating));
				break;
			case 4:
				if (!heap.isEmpty()) {
					heap.removeMin();
				}
				if (!heap.isEmpty()) {
					System.out.println("Now playing '" + heap.getNodeAt(0).toString() + "'.");
				} else {
					System.out.println("You currently have no songs.");
				}
				break;
				
			case 5:
				if (!heap.isEmpty()) {
					System.out.println("Please enter a new rating: ");
					int newRating = input.nextInt();
					Song currentSong = (Song) heap.getNodeAt(0);
					Song tempSong = new Song(currentSong.getTitle(), currentSong.getArtist(), currentSong.getAlbum(), newRating);
					heap.removeMin();
					heap.addNode(tempSong);
				} else {
					System.out.println("You currently have no songs.");
				}
				break;
			}
		}
		
		in.close();
	}

}
