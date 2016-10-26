package project_four;

import java.util.ArrayList;

public class Heap {
	
	public class Node<T> {
		private T data;
		
		public Node(T data) {
			this.data = data;
		}
		
		public T getData() {
			return data;
		}
		
		public void setData(T data) {
			this.data = data;
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
	
	public Node<Comparable> getNodeAt(int i) {
		if (heap.get(i) == null) {
			return null;
		} else {
			return heap.get(i);
		}
	}
	
	public void addNode(Node<Comparable> n) {
		heap.add(null);
		int index = heap.size() - 1;
		
		//while (index > 0 && getNodeAt(getPLoc(index)).getData() > n.getData()) {
		while (index > 0 && getNodeAt(getPLoc(index)).getData().compareTo(n.getData()) > 0) {
			heap.set(index, getNodeAt(getPLoc(index)));
			index = getPLoc(index);
		}
		heap.set(index, n);
	}
	
	public Node<Comparable> removeMin() {
		Node<Comparable> min = heap.get(0);
		int index = heap.size() - 1;
		Node<Comparable> last = heap.remove(index);
		
		if (index > 0) {
			heap.set(0, last);
			Node<Comparable> root = heap.get(0);
			int end = heap.size() - 1;
			index = 0;
			boolean done = false;
			while (!done) {
				if (getLCLoc(index) <= end) {
					Node<Comparable> child = getNodeAt(getLCLoc(index));
					int childLoc = getLCLoc(index);
					if (getRCLoc(index) <= end) {
						if (getNodeAt(getRCLoc(index)).getData().compareTo(child.getData()) < 0) {
							child = getNodeAt(getRCLoc(index));
							childLoc = getRCLoc(index);
						}
					}
					if (child.getData().compareTo(root.getData()) < 0) {
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
