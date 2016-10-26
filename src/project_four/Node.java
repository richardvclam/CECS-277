package project_four;

public class Node<Comparable> {
	private Comparable data;
	
	public Node(Comparable data) {
		setData(data);
	}
	
	public Comparable getData() {
		return data;
	}
	
	public void setData(Comparable data) {
		this.data = data;
	}
	
}
