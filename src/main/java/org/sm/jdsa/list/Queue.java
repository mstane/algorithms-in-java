package org.sm.jdsa.list;

public class Queue<E> {
  
  private List<E> elements;
  
  private Queue(List<E> elements) {
    this.elements = elements;
  }

  public E enqueue(E e) {
    elements.add(e);
    return e;
  }
  
  public E dequeue() {
    return elements.remove(0);
  }
  
  public E peek() {
    return elements.get(0);
  }
  
  public boolean isEmpty() {
    return elements.isEmpty();
  }

  
}
