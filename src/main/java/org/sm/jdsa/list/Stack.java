package org.sm.jdsa.list;

public class Stack<E> {
  
  private List<E> elements;
  
  public Stack(List<E> elements) {
    this.elements = elements;
  }
  
  public E push(E e) {
    elements.set(0, e);
    return e;
  }
  
  public E pop() {
    return elements.remove(0);
  }

  public E peek() {
    return elements.get(0);
  }

  public boolean isEmpty() {
    return elements.isEmpty();
  }

}
