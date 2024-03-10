import com.sun.source.tree.Tree;

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

        // Declare private data type char
        private char data;
        // Declare 2 links, rightLink & leftLink of type TreeNode
        private TreeNode leftLink;
        private TreeNode rightLink;



        // Parametrized constructor to build a node
        public TreeNode(char newData, TreeNode newLeftLink, TreeNode newRightLink) {
        // completed the constructor
            this.data = newData;
            this.leftLink = newLeftLink;
            this.rightLink = newRightLink;

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

    // to do Done
    private static boolean isInSubtree(char item, TreeNode subTreeRoot) {
    // base case: is subTreeRoot null?    then return false
        if (subTreeRoot == null) {
            return false;
        }
    // else if subTreeRoot.data == item   what would you return?
        else if (subTreeRoot.data == item) {
            return true;
        }
    // else item < subTreeRoot.data
        else if (item < subTreeRoot.data) {
            // recursive call
            return isInSubtree(item, subTreeRoot.leftLink);
        }
    //else
        else {  // item >= subTreeRoot.data
            // recursive call
            return isInSubtree(item, subTreeRoot.rightLink);
        }
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


        // Remove a node that is not in the tree
        tree.remove('e');
        System.out.println();
        tree.showElements();  // a b c s t v

        // Remove a node with one child
        tree.remove('a');
        System.out.println();
        tree.showElements();  // b c s t v
        // Remove a leaf node
        tree.remove('b');
        System.out.println();
        tree.showElements();  // c s t v
        // Remove a node with two children
        tree.remove('t');
        System.out.println();
        tree.showElements();  // c s v
        // Remove root node
        tree.remove('c');
        System.out.println();
        tree.showElements();  // sv

    }

    // This next part is for extra credit. Comment this section out and use the
    // commented out lines in the main method to test your remove() functionality.

    public void remove(char item) {
        root = removeFromSubtree(item, root);
    }

    private TreeNode removeFromSubtree(char item, TreeNode subtree) {
    // Step 1: find node to remove
        // check if null
        if (subtree == null) {
            return root;
        }

        if (item < subtree.data) {  // check left
            // check if item is in next node
            if (subtree.leftLink != null && item == subtree.leftLink.data) {
                removeNode(item, subtree.leftLink, subtree);
            } else {
                // recursive call if it isn't
                return removeFromSubtree(item, subtree.leftLink);
            }
        }
        //else if char comes after subtree.data
        else if (item > subtree.data) {  // check right
            if (subtree.rightLink != null && item == subtree.rightLink.data) {
                removeNode(item, subtree.rightLink, subtree);
            } else {
                // recursive call
                return removeFromSubtree(item, subtree.rightLink);
            }
        }
        // else root node item == subtree.data
        else {
            return removeNode(item, subtree, null);
        }

        return root;
    }

    private TreeNode removeNode(char item, TreeNode subtree, TreeNode parent) {
    // Step 2: Remove node

    // Case 1: leaf node
        // check if leaf node
        if (subtree.leftLink == null && subtree.rightLink == null) {
            // remove node
            if (parent != null) {
                if (parent.leftLink.data == item)
                    parent.leftLink = null;
                else parent.rightLink = null;
            } else {
                return null;
            }
        }

    // Case 2: one child
        // find child branch
        else if (subtree.leftLink == null && subtree.rightLink != null) {  // child branch right link
            // remove link from parent
            if (parent != null) {
                if (parent.rightLink == subtree) {
                    parent.rightLink = subtree.rightLink;
                } else {
                    parent.leftLink = subtree.rightLink;
                }
            } else {
                root = subtree.rightLink;
            }
        } else if (subtree.rightLink == null && subtree.leftLink != null) {  // child branch left link
            // remove link from parent
            if (parent != null) {
                if (parent.rightLink == subtree) {
                    parent.rightLink = subtree.leftLink;
                } else {
                    parent.leftLink = subtree.leftLink;
                }
            } else {
                root = subtree.leftLink;
            }
        }

    // Case 3: two children
        else {
            TreeNode nextHighest = findHighest(subtree);

            // swap the values
            subtree.data = nextHighest.data;

            // delete the node
            //subtree.rightLink = removeFromSubtree(nextHighest.data, subtree.rightLink);
            subtree.rightLink = removeNode(item, subtree.rightLink, null);
        }
        return root;
    }

    private TreeNode findHighest(TreeNode node) {
        // Find the next highest node
        while (node.rightLink != null) {
            node = node.rightLink;
        }
        return node;
    }
}
