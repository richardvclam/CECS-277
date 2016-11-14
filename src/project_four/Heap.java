package project_four;

import java.util.ArrayList;

/**
 * Generic Heap class constrained by Comparable type. The user can add Comparable
 * objects, remove the minimum node, get the size of the heap, check if it's empty,
 * and find the index of a parent or child node.
 * @author rvclam
 *
 * @param <T>
 */
public class Heap<T extends Comparable<T>> {
	
	/**
	 * ArrayList of a generic type used for heap storage.
	 */
	private ArrayList<T> heap;
	
	/**
	 * Constructor
	 */
	public Heap() {
		heap = new ArrayList<T>();
	}
	
	/**
	 * Returns the size of the heap.
	 * @return
	 */
	public int getSize() {
		return heap.size();
	}
	
	/**
	 * Checks if the heap is empty.
	 * @return
	 */
	public boolean isEmpty() {
		return heap.isEmpty();
	}
	
	/**
	 * Return the parent location of an index.
	 * @param i is the index
	 * @return the parent location of the index
	 */
	public int getPLoc(int i) {
		return (i-1)/2;
	}
	
	/**
	 * Returns the left child location of an index.
	 * @param i is the index
	 * @return the left child location of the index
	 */
	public int getLCLoc(int i) {
		return 2*i+1;
	}
	
	/**
	 * Returns the right child location of an index.
	 * @param i is the index
	 * @return the right child location of the index
	 */
	public int getRCLoc(int i) {
		return 2*i+2;
	}
	
	/**
	 * Returns the generic object of an index.
	 * @param i is the index
	 * @return the generic object of the index
	 */
	public T getNodeAt(int i) {
		if (heap.get(i) == null) {
			return null;
		} else {
			return heap.get(i);
		}
	}
	
	/**
	 * Adds a generic object to the heap and moves it to its appropriate location.
	 * @param n
	 */
	public void addNode(T n) {
		heap.add(null);
		int index = heap.size() - 1;
		
		while (index > 0 && (getNodeAt(getPLoc(index))).compareTo(n) > 0) {
			heap.set(index, getNodeAt(getPLoc(index)));
			index = getPLoc(index);
		}
		heap.set(index, (T) n);
	}
	
	/**
	 * Removes the minimum node and reorganizes the ArrayList to match a heap structure.
	 * @return
	 */
	public T removeMin() {
		T min = heap.get(0);
		int index = heap.size() - 1;
		T last = heap.remove(index);
		
		if (index > 0) {
			heap.set(0, last);
			T root = heap.get(0);
			int end = heap.size() - 1;
			index = 0;
			boolean done = false;
			while (!done) {
				if (getLCLoc(index) <= end) {
					T child = getNodeAt(getLCLoc(index));
					int childLoc = getLCLoc(index);
					if (getRCLoc(index) <= end) {
						if (getNodeAt(getRCLoc(index)).compareTo(child) < 0) {
							child = getNodeAt(getRCLoc(index));
							childLoc = getRCLoc(index);
						}
					}
					if (child.compareTo(root) < 0) {
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
	
	/**
	 * Prints the heap.
	 */
	public void printHeap() {
		for (int i = 0; i < heap.size(); i++) {
			System.out.println((i+1) + ") " + heap.get(i) + " ");
		}
		System.out.println();
	}

}
