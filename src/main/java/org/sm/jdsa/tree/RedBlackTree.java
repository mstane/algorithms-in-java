package org.sm.jdsa.tree;

public class RedBlackTree<E extends Comparable<E>> implements BinarySearchTree<E> {

    private RbNode<E> tree;
    
    private final RbNode<E> NULL_NODE;

    public RedBlackTree() {
        NULL_NODE = new RbNode<>(null, null, null, null);
        NULL_NODE.parent = NULL_NODE.left = NULL_NODE.right = NULL_NODE;
        NULL_NODE.setBlack();
        tree = NULL_NODE;
    }
    
    @Override
    public void insert(E e) {
        RbNode<E> n = createRedNode(e);
        RbNode<E> p = NULL_NODE;
        RbNode<E> n1 = tree;
        
        while (n1 == NULL_NODE) {
            p = n1;
            if (n.element.compareTo(n1.element) < 0)
                n1 = n1.left;
            else
                n1 = n1.right;
        }
        
        n.parent = p;
        
        if (p == NULL_NODE) 
            tree = n;
        else if (n.element.compareTo(p.element) < 0)
            p.left = n;
        else
            p.right = n;
        
        
        insertColorAdjust(n);
    }

    private void insertColorAdjust(RbNode<E> n) {
        while (n.parent.isRed()) {
            if (n.parent == n.parent.parent.left) {
                RbNode<E> uncle = n.parent.parent.right;
                if (uncle.isRed()) {
                    n.parent.setBlack();
                    uncle.setBlack();
                    n.parent.parent.setRed();
                    n = n.parent.parent;
                } else {
                    if (n == n.parent.right) {
                        n = n.parent;
                        rotateLeft(n);
                    }
                    n.parent.setBlack();
                    n.parent.parent.setRed();
                    rotateRight(n.parent.parent);
                }
            } else {
                RbNode<E> uncle = n.parent.parent.left;
                if (uncle.isRed()) {
                    n.parent.setBlack();
                    uncle.setBlack();
                    n.parent.parent.setRed();
                    n = n.parent.parent;
                } else {
                    if (n == n.parent.left) {
                        n = n.parent;
                        rotateRight(n);
                    }
                    n.parent.setBlack();
                    n.parent.parent.setRed();
                    rotateLeft(n.parent.parent);
                }
            }
        }
        tree.setBlack();
    }
    
    private void rotateLeft(RbNode<E> n) {
        RbNode<E> n1 = n.right;
        n.right = n1.left;
        
        if (n1.left != NULL_NODE)
            n1.left.parent = n;
        n1.parent = n.parent;
        
        if (n.parent == NULL_NODE)
            tree = n1;
        else if (n == n.parent.left)
            n.parent.left = n1;
        else
            n.parent.right = n1;
        
        n1.left = n;
        n.parent = n1;
    }

    private void rotateRight(RbNode<E> n) {
        RbNode<E> n1 = n.left;
        n.left = n1.right;
        
        if (n1.right != NULL_NODE)
            n1.right.parent = n;
        n1.parent = n.parent;
        
        if (n.parent == NULL_NODE)
            tree = n1;
        else if (n == n.parent.right)
            n.parent.right = n1;
        else
            n.parent.left = n1;
        
        n1.right = n;
        n.parent = n1;
    }
    
    private void switchParent(RbNode<E> nFrom, RbNode<E> nTo) {
        if (nFrom.parent == NULL_NODE)
            tree = nTo;
        else if (nFrom == nFrom.parent.left)
            nFrom.parent.left = nTo;
        else 
            nFrom.parent.right = nTo;
        nTo.parent = nFrom.parent;
    }

    @Override
    public void remove(E e) {
        RbNode<E> orphan = NULL_NODE;
        RbNode<E> n = find(e);
        RbNode<E> replacement = n;
        boolean wasReplacementBlack = replacement.isBlack();
        
        if (n.left == NULL_NODE) {
            orphan = n.right;
            switchParent(n, n.right);
        } else if (n.right == NULL_NODE) {
            orphan = n.left;
            switchParent(n, n.left);
        } else {
            replacement = findMin(n.right);
            wasReplacementBlack = replacement.isBlack();
            orphan = replacement.right;
            if (replacement.parent == n) {
                orphan.parent = replacement;
            } else {
              switchParent(replacement, replacement.right);
              replacement.right = n.right;
              replacement.right.parent = replacement;
            }
            switchParent(n, replacement);
            replacement.left = n.left;
            replacement.left.parent = replacement;
            replacement.setColor(n);
        }
        if (wasReplacementBlack) //black
            deleteColorAdjust(orphan); 
    }

    private void deleteColorAdjust(RbNode<E> n) {
        RbNode<E> sibling = NULL_NODE;
        while (n != tree && n.isBlack()) {
            if (n == n.parent.left) {
                sibling = n.parent.right;
                if (sibling.isRed()) { //case 1
                    sibling.setBlack();
                    n.parent.setRed();
                    rotateLeft(n.parent);
                    sibling = n.parent.right;
                }
                if (sibling.left.isBlack() && sibling.right.isBlack()) { //case 2
                    sibling.setRed();
                    n = n.parent;
                } else {
                    if (sibling.right.isBlack()) { // case 3
                        sibling.left.setBlack();
                        sibling.setRed();
                        rotateRight(sibling);
                        sibling = n.parent.right;
                    }
                    // case 4
                    sibling.setColor(n.parent);
                    n.parent.setBlack();
                    sibling.right.setBlack();
                    rotateLeft(n.parent);
                    n = tree;
                }
            } else {
                sibling = n.parent.left;
                if (sibling.isRed()) { //case 1
                    sibling.setBlack();
                    n.parent.setRed();
                    rotateRight(n.parent);
                    sibling = n.parent.left;
                }
                if (sibling.left.isBlack() && sibling.right.isBlack()) { //case 2
                    sibling.setRed();
                    n = n.parent;
                } else {
                    if (sibling.left.isBlack()) { // case 3
                        sibling.right.setBlack();
                        sibling.setRed();
                        rotateLeft(sibling);
                        sibling = n.parent.left;
                    }
                    // case 4
                    sibling.setColor(n.parent);
                    n.parent.setBlack();
                    sibling.left.setBlack();
                    rotateRight(n.parent);
                    n = tree;
                }               
            }
        }
        n.setBlack();
    }
    
    private RbNode<E> find(E e) {
        return find(e, tree);
    }
    
    @Override
    public boolean contains(E e) {
        return find(e, tree) != NULL_NODE;
    }

    private RbNode<E> find(E e, RbNode<E> n) {
        if (n == NULL_NODE)
            return NULL_NODE;

        int comparision = e.compareTo(n.element);

        if (comparision < 0)
            return find(e, n.left);
        else if (comparision > 0)
            return find(e, n.right);
        else
            return n;
    }

    @Override
    public E findMin() {
        return findMin(tree).element;
    }

    private RbNode<E> findMin(RbNode<E> n) {
        if (n == NULL_NODE)
            return NULL_NODE;
        else if (n.left == NULL_NODE)
            return n;
        else
            return findMin(n.left);
    }

    @Override
    public E findMax() {
        return findMax(tree).element;
    }

    private RbNode<E> findMax(RbNode<E> n) {
        if (n == NULL_NODE)
            return NULL_NODE;
        else if (n.right == NULL_NODE)
            return n;
        else
            return findMax(n.right);
    }

    private RbNode<E> createRedNode(E e) {
        RbNode<E> n = new RbNode<>(e, NULL_NODE, NULL_NODE, NULL_NODE);
        n.setRed();
        return n;
    } 

    
}
