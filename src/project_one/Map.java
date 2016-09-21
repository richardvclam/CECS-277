package project_one;

import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.Scanner;

public class Map implements Serializable {
<<<<<<< HEAD

	/**
	 * Serial ID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Map name.
	 */
	private String name;
	/**
	 * Previous map link.
	 */
	private Map prev;
	/**
	 * Next map link.
	 */
	private Map next;
	/**
	 * 5x5 array of map characters/objects
	 */
	private char[][] map;
	/**
	 * 5x5 array of boolean values depicting whether the player has discovered this region of the map.
	 */
	private boolean[][] revealed;
	
	/**
	 * Constructor
	 * Creates a 5x5 map and revealed array.
	 */
=======
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private Map prev;
	private Map next;
	private char[][] map;
	private boolean[][] revealed;
	
>>>>>>> origin/master
	public Map() {
		map = new char[5][5];
		revealed = new boolean[5][5];
	}
	
<<<<<<< HEAD
	/**
	 * Returns the map's name.
	 * @return the map's name
	 */
=======
>>>>>>> origin/master
	public String getName() {
		return name;
	}
	
<<<<<<< HEAD
	/**
	 * Sets the map's name.
	 * @param name to set the map to
	 */

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Returns the previous linked map.
	 * @return the previous linked map
	 */
	public Map getPrevMap() {
		return prev;
	}

	/**
	 * Sets the previous linked map.
	 * @param prev the previous map to link this current map to
	 */
=======
	public void setName(String name) {
		this.name = name;
	}
	
	public Map getPrevMap() {
		return prev;
	}
	
>>>>>>> origin/master
	public void setPrevMap(Map prev) {
		this.prev = prev;
	}
	
<<<<<<< HEAD
	/**
	 * Returns the next linked map.
	 * @return the next linked map
	 */
=======
>>>>>>> origin/master
	public Map getNextMap() {
		return next;
	}
	
<<<<<<< HEAD
	/**
	 * Sets the next linked map.
	 * @param next the next map to link this current map to
	 */
	public void setNextMap(Map next) {
		this.next = next;
	}

	/**
	 * Reads a text file to generate the map area objects/events.
	 * @param areaNum is the area number of the map
	 */
=======
	public void setNextMap(Map next) {
		this.next = next;
	}
	
	public void printMap() {
		System.out.println(" ___________ ");
		for (int i = 0; i < map.length; i++) {
			System.out.print("| ");
			for (int j = 0; j < map.length; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println("|");
		}
		System.out.println(" ___________ ");
	}
	
>>>>>>> origin/master
	public void generateArea(int areaNum) {
		File file = new File("src/project_one/Area" + areaNum + ".txt");
		Scanner in = null;
		
		try {
			in = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		for (int i = 0; i < map.length; i++) {
			String[] str = in.nextLine().split(" ");
			for (int j = 0; j < str.length; j++) {
				map[i][j] = str[j].charAt(0);
			}
		}
	}
	
<<<<<<< HEAD
	/**
	 * Returns the char at a specific location on the map.
	 * @param p the location of the char to return
	 * @return the char of the location
	 */
=======
>>>>>>> origin/master
	public char getCharAtLoc(Point p) {
		try {
			return map[(int) p.getY()][(int) p.getX()];
		} catch (ArrayIndexOutOfBoundsException e) {
			return '0';
		}
	}
	
<<<<<<< HEAD
	/**
	 * Prints the map.
	 * @param p is the point where the player is currently at
	 */
=======
>>>>>>> origin/master
	public void displayMap(Point p) {
		System.out.println(" ----------- ");
		for (int i = 0; i < map.length; i++) {
			System.out.print("| ");
			for (int j = 0; j < map.length; j++) {
				if (i == (int) p.getY() && j == (int) p.getX()) {
					System.out.print("*");
				}else {
					System.out.print((revealed[i][j] == true || map[i][j] == 'c' || map[i][j] == 's') ? map[i][j] : "x");
				}
				System.out.print(" ");
			}
			System.out.println("|");
		}
		System.out.println(" ----------- ");
	}
	
<<<<<<< HEAD
	/**
	 * Finds the start location of the map.
	 * @return the start location of the map
	 */
=======
>>>>>>> origin/master
	public Point findStartLocation() {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map.length; j++) {
				if (map[i][j] == 's') {
					return new Point(j, i);
				}
			}
		}
		return null;
	}
	
<<<<<<< HEAD
	/**
	 * Finds the end location of the map.
	 * @return the end location of the map
	 */
=======
>>>>>>> origin/master
	public Point findEndLocation() {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map.length; j++) {
				if (map[i][j] == 'f') {
					return new Point(j, i);
				}
			}
		}
		return null;
	}
	
<<<<<<< HEAD
	/**
	 * Sets a specific point to true on the map.
	 * @param p the point to set to true on the map
	 */
=======
>>>>>>> origin/master
	public void reveal(Point p) {
		revealed[(int) p.getY()][(int) p.getX()] = true;
	}
	
<<<<<<< HEAD
	/**
	 * Replaces the char at a specific point to 'n'.
	 * @param p the point to set to 'n' on the map
	 */
=======
>>>>>>> origin/master
	public void removeOppAtLoc(Point p) {
		map[(int) p.getY()][(int) p.getX()] = 'n';
	}

}
