package project_four;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Tasklist {

	public static void main(String[] args) {
		Scanner in = null;
		try {
			File file = new File("src/project_four/taskList.txt");
			in = new Scanner(file);
		} catch (IOException e) {
			
		}
		
		while (in.hasNextLine()) {
			String[] taskString = in.nextLine().split(",");
			Job job = new Job(taskString[0], taskString[1]);
		}
	}

}
