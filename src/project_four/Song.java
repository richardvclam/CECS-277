package project_four;

/**
 * Song class contains the song title, artist, album, and rating.
 * @author rvclam
 *
 */
public class Song implements Comparable<Song> {
	
	/**
	 * Title of the song.
	 */
	private String title;
	/**
	 * Name of the artist.
	 */
	private String artist;
	/**
	 * Name of the album.
	 */
	private String album;
	/**
	 * Rating of the song.
	 */
	private int rating;
	
	/**
	 * Constructor
	 * @param title is the title of the song
	 * @param artist is the artist of the song
	 * @param album is the album of the song
	 * @param rating is the rating of the song
	 */
	public Song(String title, String artist, String album, int rating) {
		this.title = title;
		this.artist = artist;
		this.album = artist;
		this.rating = rating;
	}
	
	/**
	 * Returns the song title.
	 * @return the song title.
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * Returns the artist of the song.
	 * @return the artist of the song
	 */
	public String getArtist() {
		return artist;
	}
	
	/**
	 * Returns the album of the song.
	 * @return the album of the song
	 */
	public String getAlbum() {
		return album;
	}
	
	/**
	 * Returns the rating of the song.
	 * @return the rating of the song.
	 */
	public int getRating() {
		return rating;
	}

	/**
	 * Compares two Song objects by their ratings. If their ratings are equal, then 
	 * it'll break the tie using the song title.
	 */
	@Override
	public int compareTo(Song s) {
		if (rating == s.rating) {
			return title.compareToIgnoreCase(s.title);
		}
		return s.rating - rating;
	}
	
	/**
	 * Returns the artist and song title.
	 */
	@Override
	public String toString() {
		return artist + " - " + title + " Rating: " + rating;
	}

}
