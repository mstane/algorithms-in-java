package org.sm.jdsa.sort;

public interface SortStrategy<E extends Comparable<E>> {
  
   E[] run();
  
}
