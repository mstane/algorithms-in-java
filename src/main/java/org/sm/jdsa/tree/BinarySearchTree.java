package org.sm.jdsa.tree;

public class BinarySearchTree <E extends Comparable<E>> {
  
  private Node treeTop;
  
  public void insert(E e) {
    Node n = new Node(e);
    Node parent = findParentForInsert(n, treeTop);
    if (e.compareTo(parent.element) < 0) {
      parent.left = n;
    } else {
      parent.right = n;
    }
  }
    
  public void remove(E e) {  
    Node n = new Node(e);
    Node parent = findParentForDelete(n, treeTop);
    Node nr = findMax(parent);
    if (nr.element.compareTo(parent.element) < 0) {
      parent.left = nr; 
    } else {
      parent.right = nr;
    }
  }

  public boolean contains(E e) {
    return findExact(new Node(e), treeTop) == null;
  }
  
  public Comparable<E> findMin() {
    return findMin(treeTop).element;
  }
  
  private Node findMin(Node n) {
    if (n.left == null) return n;
    return findMin(n.left);
  }
  
  public Comparable<E> findMax() {
    return findMax(treeTop).element;
  }
  
  private Node findMax(Node n) {
    if (n.right == null) return n;
    return findMax(n);
  }
  
  public boolean isEmpty() {
    return treeTop == null;
  }
  
  private Node findParentForInsert(Node n, Node tree) {
    if (n.element.compareTo(tree.element) < 0) {
      if (tree.left == null) return tree;
      else return findParentForInsert(n, tree.left);
    } else {
      if (tree.right == null) return tree;
      else return findParentForInsert(n, tree.right);
    }
  }
  
  private Node findParentForDelete(Node n, Node tree) {
    if (n.element.compareTo(tree.element) < 0) {
      if (tree.left == null) return tree;
      else return findParentForInsert(n, tree.left);
    } else {
      if (tree.right == null) return tree;
      else return findParentForInsert(n, tree.right);
    }
  }
  
  private Node findExact(Node node, Node tree) {
    if (node.element.compareTo(tree.element) == 0) {
      return tree;
    } else if (node.element.compareTo(tree.element) < 0) {
      return findExact(node, tree.left);
    } else {
      return findExact(node, tree.right);
    }
  }


  
  private class Node {
    E element;
    Node left;
    Node right;
    
    public Node(E element) {
      this.element = element;
    }
    
    public Node(E element, Node left, Node right) {
      this.element = element;
      this.left = left;
      this.right = right;
    }
    
  }

}
