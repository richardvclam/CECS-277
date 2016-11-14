package project_four;

/**
 * Job class contains a task and due date.
 * @author rvclam
 *
 */
public class Job implements Comparable<Job> {
	
	/**
	 * Description of the task.
	 */
	private String taskName;
	/**
	 * Year the task is due.
	 */
	private int year;
	/**
	 * Month the task is due.
	 */
	private int month;
	/**
	 * Date the task is due.
	 */
	private int date;
	/**
	 * Hour the task is due.
	 */
	private int hour;
	/**
	 * Minute the task is due.
	 */
	private int minute;

	/**
	 * Constructor
	 * When passing in the dueDate, the constructor will split the string and properly
	 * assign the date values to their respective data members.
	 * @param taskName is the description of the task
	 * @param dueDate is the date and time the task is due
	 */
	public Job(String taskName, String dueDate) {
		this.taskName = taskName;
		
		String[] temp = dueDate.split(" ");
		String[] datee = temp[0].split("/");
		String[] time = temp[1].split(":");
		
		month = Integer.valueOf(datee[0]);
		date = Integer.valueOf(datee[1]);
		year = Integer.valueOf(datee[2]);
		hour = Integer.valueOf(time[0]);
		minute = Integer.valueOf(time[1]);
	}
	
	/**
	 * Returns the task name.
	 * @return the task name
	 */
	public String getTaskName() {
		return taskName;
	}
	
	/**
	 * Compares two Job objects by their due dates. Compares dates in order of year, 
	 * month, day, hour, then day If all of these are equal, then break the tie by 
	 * comparing song names.
	 */
	@Override
	public int compareTo(Job j) {
		if (year == j.year) {
			if (month == j.month) {
				if (date == j.date) {
					if (hour == j.hour) {
						if (minute == j.minute) {
							return taskName.compareTo(j.taskName);
						}
						return minute - j.minute;
					}
					return hour - j.hour;
				}
				return date - j.date;
			}
			return month - j.month;
		}
		return year - j.year;
	}
	
	/**
	 * Returns the task name along with the due date.
	 */
	@Override
	public String toString() {
		String minStr = "";
		if (minute < 10) {
			minStr += "0" + minute;
		} else {
			minStr += minute;
		}
		return taskName + " Due: " + month + "/" + date + "/" + year + " " + hour + ":" + minStr;
	}
	
}
