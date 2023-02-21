package LAB9.ZAD2;

import java.io.*;
import java.util.Random;

class BNode<E extends Comparable<E>> {

    public E info;
    public BNode<E> left;
    public BNode<E> right;

    public BNode(E info) {
        this.info = info;
        left = null;
        right = null;
    }

    public BNode(E info, BNode<E> left, BNode<E> right) {
        this.info = info;
        this.left = left;
        this.right = right;
    }

}


//
// CONSTRUCTION: with no initializer
//
// ******************PUBLIC OPERATIONS*********************
// void insert( x )       --> Insert x
// void remove( x )       --> Remove x
// Comparable find( x )   --> Return item that matches x
// Comparable findMin( )  --> Return smallest item
// Comparable findMax( )  --> Return largest item
// boolean isEmpty( )     --> Return true if empty; else false
// void makeEmpty( )      --> Remove all items
// void printTree( )      --> Print tree in sorted order
/**
 * Implements an unbalanced binary search tree.
 * Note that all "matching" is based on the compareTo method.
 * @author Mark Allen Weiss
 */
class BinarySearchTree<E extends Comparable<E>> {

    /** The tree root. */
    private BNode<E> root;

    /**
     * Construct the tree.
     */
    public BinarySearchTree() {
        root = null;
    }

    /**
     * Insert into the tree; duplicates are ignored.
     * @param x the item to insert.
     */
    public void insert(E x) {
        root = insert(x, root);
    }

    /**
     * Remove from the tree. Nothing is done if x is not found.
     * @param x the item to remove.
     */
    public void remove(E x) {
        root = remove(x, root);
    }

    /**
     * Find the smallest item in the tree.
     * @return smallest item or null if empty.
     */
    public E findMin() {
        return elementAt(findMin(root));
    }

    /**
     * Find the largest item in the tree.
     * @return the largest item of null if empty.
     */
    public E findMax() {
        return elementAt(findMax(root));
    }

    /**
     * Find an item in the tree.
     * @param x the item to search for.
     * @return the matching item or null if not found.
     */
    public BNode<E> find(E x) {
        return find(x, root);
    }

    /**
     * Make the tree logically empty.
     */
    public void makeEmpty() {
        root = null;
    }

    /**
     * Test if the tree is logically empty.
     * @return true if empty, false otherwise.
     */
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * Print the tree contents in sorted order.
     */
    public void printTree() {
        if (isEmpty()) {
            System.out.println("Empty tree");
        } else {
            printTree(root);
        }
    }

    /**
     * Internal method to get element field.
     * @param t the node.
     * @return the element field or null if t is null.
     */
    private E elementAt(BNode<E> t) {
        if (t == null)
            return null;
        return t.info;
    }

    /*
     * Internal method to insert into a subtree.
     * @param x the item to insert.
     * @param t the node that roots the tree.
     * @return the new root.
     */

    public BNode<E> getRoot()
    {
        return root;
    }
    private BNode<E> insert(E x, BNode<E> t) {
        if (t == null) {
            t = new BNode<E>(x, null, null);
        } else if (x.compareTo(t.info) < 0) {
            t.left = insert(x, t.left);
        } else if (x.compareTo(t.info) > 0) {
            t.right = insert(x, t.right);
        } else;  // Duplicate; do nothing
        return t;
    }

    /**
     * Internal method to remove from a subtree.
     * @param x the item to remove.
     * @param t the node that roots the tree.
     * @return the new root.
     */
    @SuppressWarnings({"raw", "unchecked"})
    private BNode<E> remove(Comparable x, BNode<E> t) {
        if (t == null)
            return t;   // Item not found; do nothing

        if (x.compareTo(t.info) < 0) {
            t.left = remove(x, t.left);
        } else if (x.compareTo(t.info) > 0) {
            t.right = remove(x, t.right);
        } else if (t.left != null && t.right != null) { // Two children
            t.info = findMin(t.right).info;
            t.right = remove(t.info, t.right);
        } else {
            if (t.left != null)
                return t.left;
            else
                return t.right;
        }
        return t;
    }

    /**
     * Internal method to find the smallest item in a subtree.
     * @param t the node that roots the tree.
     * @return node containing the smallest item.
     */
    private BNode<E> findMin(BNode<E> t) {
        if (t == null) {
            return null;
        } else if (t.left == null) {
            return t;
        }
        return findMin(t.left);
    }

    /**
     * Internal method to find the largest item in a subtree.
     * @param t the node that roots the tree.
     * @return node containing the largest item.
     */
    private BNode<E> findMax(BNode<E> t) {
        if (t == null) {
            return null;
        } else if (t.right == null) {
            return t;
        }
        return findMax(t.right);
    }

    /**
     * Internal method to find an item in a subtree.
     * @param x is item to search for.
     * @param t the node that roots the tree.
     * @return node containing the matched item.
     */
    private BNode<E> find(E x, BNode<E> t) {
        if (t == null)
            return null;

        if (x.compareTo(t.info) < 0) {
            return find(x, t.left);
        } else if (x.compareTo(t.info) > 0) {
            return find(x, t.right);
        } else {
            return t;    // Match
        }
    }

    /**
     * Internal method to print a subtree in sorted order.
     * @param t the node that roots the tree.
     */
    private void printTree(BNode<E> t) {
        if (t != null) {
            printTree(t.left);
            System.out.println(t.info);
            printTree(t.right);
        }
    }



    public int findHeight(BNode<Integer> node)
    {
       if(node==null || node.left==null &&node.right==null)
           return 1;
       return Math.max(findHeight(node.left),findHeight(node.right))+1;
    }
//    public  int countChildrenAtDepth(BNode<Integer> root, int depth, int currentDepth) {
//        // base case: empty tree or target depth reached
//        if (root == null || currentDepth == depth) {
//            return 0;
//        }
//        // count children in left and right subtrees
//        int count = countChildrenAtDepth(root.left, depth, currentDepth + 1)
//                + countChildrenAtDepth(root.right, depth, currentDepth + 1);
//        // add 1 if current node is at target depth
//        if (currentDepth == depth - 1) {
//            count++;
//        }
//        return count;
//    }
public static int countChildrenAtDepth(BNode<Integer> root, int depth, int currentDepth) {
    // base case: empty tree or target depth reached
    if (root == null || currentDepth > depth) {
        return 0;
    }
    // return 1 if current node is at target depth
    if (currentDepth == depth) {
        return 1;
    }
    // count children in left and right subtrees
    return countChildrenAtDepth(root.left, depth, currentDepth + 1)
            + countChildrenAtDepth(root.right, depth, currentDepth + 1);
}

}
public class HeightOfElement {


    public static void main(String[] args) throws IOException {
        int n;
        BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(System.in));
        n=Integer.parseInt(bufferedReader.readLine());
        System.out.println(n);
        BinarySearchTree<Integer> tree=new BinarySearchTree<>();
        for(int i=0;i<n;i++)
            tree.insert(Integer.parseInt(bufferedReader.readLine()));
        //tree.printTree();
        int broj=Integer.parseInt(bufferedReader.readLine());
        //System.out.println("posle broj");
        System.out.println("broj"+broj+"\n");
        BNode<Integer> najdeno=tree.find(broj);

        System.out.println(tree.findHeight(najdeno));
        //System.out.println(tree.findDepth());
        System.out.println(tree.countChildrenAtDepth(tree.getRoot(),tree.findHeight(najdeno),0));
    }
}
