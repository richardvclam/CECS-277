package project_four;

public class Job implements Comparable<Job> {
	
	private String taskName;
	private String dueDate;

	public Job(String taskName, String dueDate) {
		this.taskName = taskName;
		this.dueDate = dueDate;
	}
	
	@Override
	public int compareTo(Job j) {
		if (dueDate.equals(j.dueDate)) {
			return taskName.compareTo(j.taskName);
		}
		return dueDate.compareTo(j.dueDate);
	}
	
	@Override
	public String toString() {
		return taskName;
	}
	
}
