import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/*
 * MorseTree.java
 * CSSSKL 143 Binary Search Tree Lab
 * Author: Rob Nash
 *
 * This class reads in data from a text file ("data.txt") and populates a binary tree with an
 * ordering constraint.  See the lab instructions for more information, but in general, dots go right
 * and dashes go left when constructing or traversing a Morse code tree.  Search for TODO
 * in the code to see what code you have to implement.
 *
 * Start with the constructor. In your constructor read each line in from the text file first,
 * calling add() for each {letter, morseCodeStr} pair.
 */

public class MorseTree {
    // Inner class to create the linked structure
    private class TreeNode {
        Character data; // holds a given node’s data
        TreeNode right;
        TreeNode left;

        public TreeNode() {
            this.data = null;
            this.right = null;
            this.left = null;
        }

        public void setRight(TreeNode rightNode) {
            this.right = rightNode;
        }

        public void setLeft(TreeNode leftNode) {
            this.left = leftNode;
        }
    }

    // instance variables

    // Data member called "root" goes here
    private TreeNode root;

    // Constructor
    public MorseTree() {
        // first, open data.txt and add each line to the tree
        File file = new File("data.txt");
        Scanner fin;

        try {
            fin = new Scanner(file);
            String line;
            char letter;
            String morseStr;

            // for each line in the file,
            while (fin.hasNextLine()) {
                line = fin.nextLine().trim();
                //   get the letter(char) and the Morse string
                letter = line.charAt(0);
                morseStr = line.substring(2);

                //   call add() with this data
                add(morseStr, letter);

                //   print out the letter and Morse string here for debugging
                System.out.println(letter + " " + morseStr);
            }
            fin.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void add(String morseStr, char letter) {
        root = insertInSubtree(morseStr, letter, root);
    }

    // Recursively completed this function.
    private TreeNode insertInSubtree(String morseStr, char letter, TreeNode subtree) {

        // base case 1 : subtree is null
        if (subtree == null) {
            // create new treeNode
            subtree = new TreeNode();
        }
        // base case 2 : morseStr is of length 0
        if (morseStr.length() == 0) {
            // set letter to current node
            subtree.data = letter;
        } else {
            // recursive case 1: the first char in morseStr is a '.', so recursively traverse tree
            if (morseStr.charAt(0) == '.') {
                // call again minus the first character in string
                subtree.right = insertInSubtree(morseStr.substring(1), letter, subtree.right); // go right
            }
            // recursive case 2: the first char in the morseStr is a '-', so recurse accordingly
            else if (morseStr.charAt(0) == '-') {
                // call again minus the first character in string
                subtree.left = insertInSubtree(morseStr.substring(1), letter, subtree.left); // go left
            }
        }

        return subtree; // always the last line, always return the node you are working on
    }

    public Character translate(String morseStr) {
        return findInSubtree(morseStr, root);
    }

    // Recursively completed this function.
    private Character findInSubtree(String morseStr, TreeNode subtree) {
        // base case 1 : subtree is null
        if (subtree == null) {
            return null;
        }
        // base case 2 : morseStr is of length 0
        if (morseStr.length() == 0) {
            // return found character in subtree
            return subtree.data;

        } else {
            // recursive case 1: the first char in morseStr is a '.', so recursively traverse tree
            if (morseStr.charAt(0) == '.') {
                // call again minus the first character in string
                return findInSubtree(morseStr.substring(1), subtree.right); // go right
            }
            // recursive case 2: the first char in the morseStr is a '-', so re-curse accordingly
            else if (morseStr.charAt(0) == '-') {
                // call again minus the first character in string
                return findInSubtree(morseStr.substring(1), subtree.left); // go left
            }
        }

        return null; // shouldn't reach?
    }

    // Non-recursive function that calls other (recursive) functions
    public String translateString(String tokens) {
        String retVal = "";
        // build a scanner here using tokens as input
        // iterate over the tokens calling translate on each token (substring separated by a space)
        // concat these characters and return them

        return retVal;
    }

    public String toMorseCode(Character c) {
        // walk the tree looking for the TreeNode with the char c in it
        System.out.println();
        // preorder walk?
        preorderTraversal(root, c);
        System.out.println();
        // inorder walk?
        inorderTraversal(root, c);
        System.out.println();
        // postorder walk?
        postorderTraversal(root, c);

        // when you've found the char c, report the path from the root to the node
        // and build the morse code by adding a "." when you go right, "-" when you go left
        return new String("You wish.");
    }

    private void preorderTraversal(TreeNode node, Character c) {
        if (node != null && node.data != c) {
            System.out.print(node.data + " ");
            preorderTraversal(node.left, c);
            preorderTraversal(node.right, c);
        }
    }

    private void inorderTraversal(TreeNode node, Character c) {
        if (node != null && node.data != c) {
            inorderTraversal(node.left, c);
            System.out.print(node.data + " ");
            inorderTraversal(node.right, c);
        }
    }

    private void postorderTraversal(TreeNode node, Character c) {
        if (node != null && node.data != c) {
            postorderTraversal(node.left, c);
            postorderTraversal(node.right, c);
            System.out.print(node.data + " ");
        }
    }

    public String toString() {
        return inorderWalk();
    }
    private String inorderWalk() {
        return new String("Another wish.");
    }

    public static void main(String[] args) {
        MorseTree mt = new MorseTree();  // builds our tree using data from a file

        //System.out.println(mt.translate("..."));  // prints out S
        //System.out.println(mt.translate("---"));  // prints out O
        //System.out.println(mt.translate(".......-"));  // prints out null

        //System.out.println(mt.translateString("... --- ..."));  // SOS
        System.out.println(mt.toMorseCode('S'));  // find where we are in the tree, remember path to root
    }
}
