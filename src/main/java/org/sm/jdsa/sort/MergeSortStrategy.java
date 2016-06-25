package org.sm.jdsa.sort;

public class MergeSortStrategy<E extends Comparable<E>> implements SortStrategy<E> {

  E[] elements;
  E[] sortedElements;
  
  @SuppressWarnings("unchecked")
  public MergeSortStrategy(E[] elements) {
    this.elements = elements;
    this.sortedElements = (E[]) new Comparable[elements.length];
  }
  
  	@Override
	public E[] run() {
		sort(0, elements.length - 1);
		return sortedElements;

	}

	private void sort(int theLeast, int theGratest) {
		if (theLeast < theGratest) {
			int mid = (theGratest - theLeast) / 2;
			sort(theLeast, mid);
			sort(mid + 1, theGratest);

			merge(theLeast, theGratest, mid);	
		}		
	}

	private void merge(int leftPos, int rightEnd, int mid) {
		int leftEnd = mid;
		int rightPos = mid + 1;
		int currentPos = leftPos;

		while (currentPos < rightEnd + 1) {
			if (rightPos > rightEnd) {
				sortedElements[currentPos++] = elements[leftPos++];
			} else if (leftPos > leftEnd) {
				sortedElements[currentPos++] = elements[rightPos++];
			} else {
				if (elements[leftPos].compareTo(elements[rightPos]) <= 0) {
					sortedElements[currentPos++] = elements[leftPos++]; 
				} else {
					sortedElements[currentPos++] = elements[rightPos++];				
				}
			}			
		}
		

	}



}
