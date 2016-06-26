package org.sm.jdsa.combinatorial;

public class Combinations<E> {
	
	private final E[] elements;
	private final int k;
	private final E[] currentCombination;
	
	@SuppressWarnings("unchecked")
	public Combinations(E[] elements, int k) {
		this.elements = elements;
		this.k = k;
		this.currentCombination = (E[])new Object[k];
	}
	
	public void run() {
		combine(0);
		
	}
	
	private void combine(int pos) {
		if (pos == k) {
			consumeCurrentCombination();
		} else {
			for (int i = pos; i < elements.length; i++) {
				currentCombination[pos] = elements[i];
				combine(pos + i + 1);
			}
		}
	}
	
	private void consumeCurrentCombination() {
		//Do something with elements in currentCombination
	}

}
