package project_four;

import java.util.ArrayList;

public class Heap {
	
	private class Node implements Comparable {
		private Object data;
		
		public Node(Object data) {
			this.data = data;
		}
		
		public Object getData() {
			return data;
		}
		
		public void setData(int data) {
			this.data = data;
		}

		@Override
		public int compareTo(Object n) {
			return ((Node) data).compareTo(n);
		}
	}
	
	private ArrayList<Node> heap;
	
	public Heap() {
		heap = new ArrayList<Node>();
	}
	
	public int getSize() {
		return heap.size();
	}
	
	public boolean isEmpty() {
		return heap.isEmpty();
	}
	
	public int getPLoc(int i) {
		return (i-1)/2;
	}
	
	public int getLCLoc(int i) {
		return 2*i+1;
	}
	
	public int getRCLoc(int i) {
		return 2*i+2;
	}
	
	public Node getNodeAt(int i) {
		if (heap.get(i) == null) {
			return null;
		} else {
			return heap.get(i);
		}
	}
	
	public void addNode(Node n) {
		heap.add(null);
		int index = heap.size() - 1;
		
		//while (index > 0 && getNodeAt(getPLoc(index)).getData() > n.getData()) {
		while (index > 0 && getNodeAt(getPLoc(index)).getData() > n.getData()) {
			heap.set(index, getNodeAt(getPLoc(index)));
			index = getPLoc(index);
		}
		heap.set(index, n);
	}
	
	public Node removeMin() {
		Node min = heap.get(0);
		int index = heap.size() - 1;
		Node last = heap.remove(index);
		
		if (index > 0) {
			heap.set(0, last);
			Node root = heap.get(0);
			int end = heap.size() - 1;
			index = 0;
			boolean done = false;
			while (!done) {
				if (getLCLoc(index) <= end) {
					Node child = getNodeAt(getLCLoc(index));
					int childLoc = getLCLoc(index);
					if (getRCLoc(index) <= end) {
						if (getNodeAt(getRCLoc(index)).getData() < child.getData()) {
							child = getNodeAt(getRCLoc(index));
							childLoc = getRCLoc(index);
						}
					}
					if (child.getData() < root.getData()) {
						heap.set(index, child);
						index = childLoc;
					} else {
						done = true;
					}
				} else {
					done = true;
				}
			}
			heap.set(index, root);
		}
		return min;
	}
	
	public void printHeap() {
		for (int i = 0; i < heap.size(); i++) {
			System.out.print(heap.get(i).getData() + " ");
		}
		System.out.println();
	}

}
