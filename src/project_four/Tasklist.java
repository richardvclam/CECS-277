package project_four;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * Tasklist class main reads from a "taskList.txt" file and adds them into a heap. The
 * user can then display the list of tasks, display the current task, add a new task to
 * the list, mark the current task completed, postpone the current task to a later due
 * date, or quit the program.
 * @author rvclam
 *
 */
public class Tasklist {

	public static void main(String[] args) {
		Scanner in = null;
		Scanner input = new Scanner(System.in);
		try {
			File file = new File("src/project_four/taskList.txt");
			in = new Scanner(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Heap<Job> heap = new Heap<Job>();
		
		// Reads tasks from the file
		while (in.hasNextLine()) {
			String[] taskString = in.nextLine().split(",");
			heap.addNode(new Job(taskString[0], taskString[1]));
		}
		
		String[] menu = {"Display the list of tasks", "Display current task", "Add a new item to task list",
				"Mark Complete", "Postpone current task", "Quit"};
		
		int response = -1;
		while (response != 6) {
			response = Util.checkUserInput("==== Tasklist ====\nWhat would you like to do?", menu);
			
			switch (response) {
			case 1:
				if (!heap.isEmpty()) {
					heap.printHeap();
				} else {
					System.out.println("You currently have no tasks.");
				}
				break;
			case 2:
				if (!heap.isEmpty()) {
					System.out.println("Your current task is '" + heap.getNodeAt(0).toString() + "'.");
				} else {
					System.out.println("You currently have no tasks.");
				}
				break;
			case 3:
				System.out.println("Enter in a task: ");
				String task = input.nextLine();
				System.out.println("Enter a due date and time: (Mm/Dd/Yyyy Hh:Mm)");
				String due = input.nextLine();
				heap.addNode(new Job(task, due));
				break;
			case 4:
				if (!heap.isEmpty()) {
					System.out.println("You've completed '" + heap.getNodeAt(0).toString() + "'.");
					heap.removeMin();
				}
				if (!heap.isEmpty()) {
					System.out.println("Your current task is '" + heap.getNodeAt(0).toString() + "'.");
				} else {
					System.out.println("You currently have no tasks.");
				}
				break;
				
			case 5:
				if (!heap.isEmpty()) {
					System.out.println("Please enter a new due date and time: ");
					String dueDate = input.nextLine();
					Job tempJob = new Job(heap.getNodeAt(0).getTaskName(), dueDate);
					heap.removeMin();
					heap.addNode(tempJob);
				} else {
					System.out.println("You currently have no tasks.");
				}
				break;
			}
		}
		
		in.close();
	}
	
	

	
}
