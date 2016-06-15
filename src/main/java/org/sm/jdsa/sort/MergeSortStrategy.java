package org.sm.jdsa.sort;

public class MergeSortStrategy<E extends Comparable<E>> implements SortStrategy<E> {

  E[] elements;
  E[] tempArray;
  
  @SuppressWarnings("unchecked")
  public MergeSortStrategy(E[] elements) {
    this.elements = elements;
    this.tempArray = (E[]) new Comparable[elements.length];
  }
  
  @Override
  public void run() {
    sort(0, elements.length - 1);
  }

  private void sort(int left, int right) {
    if (left < right) {
      int center = (left + right) / 2;
      sort(left, center);
      sort(center + 1, right);
      merge(left, center + 1, right);
    }
  }

  private void merge(int left, int right, int rightEnd) {
    int leftEnd = right - 1;
    int counter = left;
    int length = rightEnd - left + 1;

    while( left <= leftEnd && right <= rightEnd ) {
        if(elements[left].compareTo(elements[right]) <= 0 ) {
          tempArray[counter++] = elements[left++];
        } else {
          tempArray[counter++] = elements[right++];
        }
    }

    while(left <= leftEnd) {
      tempArray[counter++] = elements[left++];
    }

    while(right <= rightEnd) {
      tempArray[counter++] = elements[right++];
    }

    for(int i = 0; i < length; i++, rightEnd--) {
      elements[rightEnd] = tempArray[rightEnd];
    }
  }


}
