package org.sm.jdsa.tree;

public class BinarySearchTreeBasic <E extends Comparable<E>> implements BinarySearchTree<E> {
  
  private Node<E> tree;
  
  @Override
  public void insert(E e) {
    tree = insert(e, tree);
  }
  
  private Node<E> insert(E e, Node<E> n) {
    if (n == null) 
      return new Node<E>(e);
    
    int comparision = e.compareTo(n.element);
  
    if (comparision < 0)
      n.left = insert(e, n.left);
    else if (comparision > 0)
      n.right = insert(e, n.right);
    return n;
  }
    
  @Override
  public void remove(E e) {
    tree = remove(e, tree);
  }
  
  private Node<E> remove(E e, Node<E> n) {
    if (n == null)
      return null;
    
    int comparision = e.compareTo(n.element);
    
    if (comparision < 0)
      n.left = remove(e, n.left);
    else if (comparision > 0)
      n.right = remove(e, n.right);
    else if (n.left != null && n.right != null) {
      n.element = findMin(n.right).element;
      n.right = remove(n.element, n.right);
    } else {
      n = n.left != null ? n.left : n.right;
    }
    return n;
    
  }

  @Override
  public boolean contains(E e) {
    return contains(e, tree);
  }
  
  private boolean contains(E e, Node<E> n) {
    if (n == null)
      return false;
    int comparision = e.compareTo(n.element);
    if (comparision < 0)
      return contains(e, n.left);
    else if (comparision >0)
      return contains(e, n.right);
    else
      return true;
  }
  
  @Override
  public E findMin() {
    return findMin(tree).element;
  }
  
  private Node<E> findMin(Node<E> n) {
    if (n == null)
      return null;
    else if (n.left == null)
      return n;
    else
      return findMin(n.left);
  }
  
  @Override
  public E findMax() {
    return findMax(tree).element;
  }
  
  private Node<E> findMax(Node<E> n) {
    if (n == null)
      return null;
    else if (n.right == null)
      return n;
    else
      return findMax(n.right);
  }


  public boolean isEmpty() {
    return tree == null;
  }

}
