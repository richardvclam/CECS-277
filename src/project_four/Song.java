package project_four;

public class Song implements Comparable<Song> {
	
	private String title;
	private String artist;
	private String album;
	private int rating;
	
	public Song(String title, String artist, String album, int rating) {
		this.title = title;
		this.artist = artist;
		this.album = artist;
		this.rating = rating;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getArtist() {
		return artist;
	}
	
	public String getAlbum() {
		return album;
	}
	
	public int getRating() {
		return rating;
	}

	@Override
	public int compareTo(Song s) {
		if (rating == s.rating) {
			return title.compareTo(s.title);
		}
		return rating - s.rating;
	}
	
	@Override
	public String toString() {
		return artist + " - " + title;
	}

}
