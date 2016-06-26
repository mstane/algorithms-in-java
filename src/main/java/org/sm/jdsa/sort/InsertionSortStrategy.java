package org.sm.jdsa.sort;

public class InsertionSortStrategy<E extends Comparable<E>> implements SortStrategy<E> {

	private final E[] array;

	public InsertionSortStrategy(E[] array) {
		this.array = array;
	}	
	
	@Override
	public E[] run() {
		if (array != null) {
			for (int i = 0; i < array.length; i++) {
				if (i > 0) {
					int j = i - 1;
					boolean foundSmaller = false;			
					while (!foundSmaller && j >= 0) {
						if (array[i].compareTo(array[j]) < 0) {
							E temp = array[i];
							array[i] = array[j];
							array[j] = temp;
						} else {
							foundSmaller = true;
						}
						j++;
					}
				}
			}
		}
		return array;

	}
}
