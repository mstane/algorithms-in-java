package org.sm.jdsa.tree;

public interface BinarySearchTree <E extends Comparable<E>> {
  
  void insert(E e);
  
  void remove(E e);
  
  E findMin();
  
  E findMax();
  
  boolean contains(E e);
  

}
