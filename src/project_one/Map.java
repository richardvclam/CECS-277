package project_one;

import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.Scanner;

public class Map implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private Map prev;
	private Map next;
	private char[][] map;
	private boolean[][] revealed;
	
	public Map() {
		map = new char[5][5];
		revealed = new boolean[5][5];
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Map getPrevMap() {
		return prev;
	}
	
	public void setPrevMap(Map prev) {
		this.prev = prev;
	}
	
	public Map getNextMap() {
		return next;
	}
	
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
	
	public char getCharAtLoc(Point p) {
		try {
			return map[(int) p.getY()][(int) p.getX()];
		} catch (ArrayIndexOutOfBoundsException e) {
			return '0';
		}
	}
	
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
	
	public void reveal(Point p) {
		revealed[(int) p.getY()][(int) p.getX()] = true;
	}
	
	public void removeOppAtLoc(Point p) {
		map[(int) p.getY()][(int) p.getX()] = 'n';
	}

}
