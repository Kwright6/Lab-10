import java.io.*;
import java.util.*;

/**
 * Class invariant: This code for a binary tree satisfies the
 * binary search tree storage rule.
 * CSSSKL 143
 * @author (your name)
 * @version (a version number or a date)
/**
 */

public class CharTree {

    /*Inner class Node, 2 references(pointers), one data element
    * The only reason this inner class is static is that it is used in
    * the static methods insertInSubtree , isInSubtree , and
    * showElementsInSubtree. This class should have more methods.
    * This is just a sample of possible methods.
    */
    private static class TreeNode {

        // TODO: Declare private data type char
        // TODO: Declare 2 links, rightLink & leftLink of type TreeNode


        // Parametrized constructor to build a node
        public TreeNode(char newData, TreeNode newLeftLink, TreeNode newRightLink) {
        // TODO: complete the constructor

        }
    }           //End of IntTreeNode inner class

    // The first node of the tree, called root
    private TreeNode root;

    // Default constructor to build the CharTree
    public CharTree( ) {
        root = null;
    }

    // Utility methods for CharTree:
    public void add(char item) {
        root = insertInSubtree(item, root);
    }


    public boolean contains(char item) {
        return isInSubtree(item, root);
    }

    public void showElements( ) {
        showElementsInSubtree(root);
    }

    /**
     * Returns the root node of a tree that is the tree with root node
     * subTreeRoot, but with a new node added that contains item.
    */
    private static TreeNode insertInSubtree(char item, TreeNode subTreeRoot) {
        if (subTreeRoot == null) {
            return new TreeNode(item, null, null);
        } else if (item < subTreeRoot.data) {
            subTreeRoot.leftLink = insertInSubtree(item, subTreeRoot.leftLink);
            return subTreeRoot;
        } else {         //item >= subTreeRoot.data
            subTreeRoot.rightLink = insertInSubtree(item, subTreeRoot.rightLink);
                return subTreeRoot;
        }
    }

    // TODO
    private static boolean isInSubtree(char item, TreeNode subTreeRoot) {
    // base case: is subTreeRoot null?    then return false

    // else if subTreeRoot.data == item   what would you return?

    // else item < subTreeRoot.data
        // recursive call

        //else         // item >= link.data
        // recursive call
    }

    private static void showElementsInSubtree(TreeNode subTreeRoot) { //Uses inorder traversal.
        if (subTreeRoot != null) {
            showElementsInSubtree(subTreeRoot.leftLink);
            System.out.print(subTreeRoot.data + " ");
            showElementsInSubtree(subTreeRoot.rightLink);
        }                    //else do nothing. Empty tree has nothing to display.
    }

    public static void main(String[] args) {
        CharTree tree = new CharTree();
        tree.add('c');
        tree.add('a');
        tree.add('t');
        tree.add('b');
        tree.add('s');
        tree.add('v');
        tree.showElements();
        System.out.println();
        System.out.println(tree.contains('t'));
        System.out.println(tree.contains('z'));

        /*
        // Remove a node that is not in the tree
        tree.remove('e');
        System.out.println();
        tree.showElements();
        // Remove a node with one child
        tree.remove('a');
        System.out.println();
        tree.showElements();
        // Remove a leaf node
        tree.remove('b');
        System.out.println();
        tree.showElements();
        // Remove a node with two children
        tree.remove('t');
        System.out.println();
        tree.showElements();
        // Remove root node
        tree.remove('c');
        System.out.println();
        tree.showElements();
        */
    }

    // This next part is for extra credit. Comment this section out and use the
    // commented out lines in the main method to test your remove() functionality.
    /*
    public void remove(char item) {
        root = removeFromSubtree(item, root);
    }

    private TreeNode removeFromSubtree(char item, TreeNode subtree) {

    }

    */
}
