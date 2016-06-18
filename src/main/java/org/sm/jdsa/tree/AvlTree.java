package org.sm.jdsa.tree;

public class AvlTree<E extends Comparable<E>> implements BinarySearchTree<E> {

	private AvlNode<E> tree;
	
  @Override
  public void insert(E e) {
   tree = insert(e, tree); 
  }
  
  private AvlNode<E> insert(E e, AvlNode<E> n) {
	  if (e == null) {
		  return new AvlNode<E>(e);
	  }
	  int comparision = e.compareTo(n.element);
	  if (comparision < 0) {
		  n.left = insert(e, n.left);
		  if (ht(n.left) - ht(n.right) > 1) {
			  if (ht(n.left.left) > ht(n.left.right))
				  return singleRotationLeft(n);
			  else
				  return doubleRotationLeft(n);
		  }
	  } else if (comparision > 0) {
		  n.right = insert(e, n.right);
		  if (ht(n.right) - ht(n.left) > 1) {
			  if (ht(n.right.right) > ht(n.right.left))
				  return singleRotationRight(n);
			  else
				  return doubleRotationRight(n);
		  }
	  }

	  updateHeight(n);
	  return n;
  }
  
  private int ht(AvlNode<E> n) {
	  return n == null ? -1 : n.height;
  }

  private void updateHeight(AvlNode<E> n) {
	  if (n != null)
		  n.height = Math.max(ht(n.left), ht(n.right)) + 1;
  }
  
  private AvlNode<E> singleRotationLeft(AvlNode<E> n) {
	  AvlNode<E> nl = n.left;
	  n.left = nl.right;
	  nl.right = n;
	  updateHeight(n);
	  updateHeight(nl);
	  return nl;
  }
  
  private AvlNode<E> doubleRotationLeft(AvlNode<E> n) {
	  n.left = singleRotationRight(n.left);
	  return singleRotationLeft(n);
  }
  
  private AvlNode<E> singleRotationRight(AvlNode<E> n) {
	  AvlNode<E> nr = n.right;
	  n.right = nr.left;
	  nr.left = n;
	  updateHeight(n);
	  updateHeight(nr);
	  return nr;
  }
  
  private AvlNode<E> doubleRotationRight(AvlNode<E> n) {
	  n.right = singleRotationLeft(n.right);
	  return singleRotationRight(n);
  }
  
  @Override
  public void remove(E e) {
    
  }
  
  @Override
  public boolean contains(E e) {
    return contains(e, tree);
  }
  
  private boolean contains(E e, AvlNode<E> n) {
	  if (n == null)
		  return false;
	  
	  int comparision = e.compareTo(n.element);
	  
	  if (comparision < 0)
		  return contains(e, n.left);
	  else if (comparision > 0)
		  return contains(e, n.right);
	  else
		  return true;
  }
  
  @Override
  public E findMin() {
    return findMin(tree).element;
  }
  
  private AvlNode<E> findMin(AvlNode<E> n) {
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
  
  private AvlNode<E> findMax(AvlNode<E> n) {
	  if (n == null)
		  return null;
	  else if (n.right == null)
		  return n;
	  else
		  return n.right;
  }
  
}
