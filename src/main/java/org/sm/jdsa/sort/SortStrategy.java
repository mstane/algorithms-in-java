package org.sm.jdsa.sort;

public interface SortStrategy<E extends Comparable<E>> {
  
   void run();
  
}
