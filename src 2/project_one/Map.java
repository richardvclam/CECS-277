package project_one;

import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.Scanner;

public class Map implements Serializable {

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
	public Map() {
		map = new char[5][5];
		revealed = new boolean[5][5];
	}
	
	/**
	 * Returns the map's name.
	 * @return the map's name
	 */
	public String getName() {
		return name;
	}
	
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
	public void setPrevMap(Map prev) {
		this.prev = prev;
	}
	
	/**
	 * Returns the next linked map.
	 * @return the next linked map
	 */
	public Map getNextMap() {
		return next;
	}
	
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
	
	/**
	 * Returns the char at a specific location on the map.
	 * @param p the location of the char to return
	 * @return the char of the location
	 */
	public char getCharAtLoc(Point p) {
		try {
			return map[(int) p.getY()][(int) p.getX()];
		} catch (ArrayIndexOutOfBoundsException e) {
			return '0';
		}
	}
	
	/**
	 * Prints the map.
	 * @param p is the point where the player is currently at
	 */
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
	
	/**
	 * Finds the start location of the map.
	 * @return the start location of the map
	 */
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
	
	/**
	 * Finds the end location of the map.
	 * @return the end location of the map
	 */
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
	
	/**
	 * Sets a specific point to true on the map.
	 * @param p the point to set to true on the map
	 */
	public void reveal(Point p) {
		revealed[(int) p.getY()][(int) p.getX()] = true;
	}
	
	/**
	 * Replaces the char at a specific point to 'n'.
	 * @param p the point to set to 'n' on the map
	 */
	public void removeOppAtLoc(Point p) {
		map[(int) p.getY()][(int) p.getX()] = 'n';
	}

}
