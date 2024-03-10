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

    //TODO: Data member called "root" goes here
    
    //TODO: Complete the constructor
    public MorseTree() {
		// first, open data.txt and add each line to the tree
		Scanner fin;
		try {
			// for each line in the file,
			//   get the letter(char) and the Morse string
			//   call add() with this data
			//   print out the letter and Morse string here for debugging
		}
        catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

    public void add(String morseStr, char letter) {
        root = insertInSubtree(morseStr, letter, root);
    }
    
    // TODO: Recursively complete this function. It's only a few characters different from findInSubtree()
	private TreeNode insertInSubtree(String morseStr, char letter, TreeNode subtree) {
		// base case 1 : subtree is null
		// base case 2 : morseStr is of length 0
		// recursive case 1: the first char in morseStr is a '.', so recursively traverse tree
		// recursive case 2: the first char in the morseStr is a '-', so recurse accordingly
		
		return subtree; // always the last line, always return the node you are working on
	}
    
    public Character translate(String morseStr) {
        return findInSubtree(morseStr, root);
    }
    
    // TODO: Recursively complete this function. Very similar to insertInSubtree()
	private Character findInSubtree(String morseStr, TreeNode subtree) {
		// base case 1 : subtree is null
		// base case 2 : morseStr is of length 0
		// recursive case 1: the first char in morseStr is a '.', so recursively traverse tree
		// recursive case 2: the first char in the morseStr is a '-', so re-curse accordingly
		return null; // remove this later
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
            // preorder walk?
            // inorder walk?
            // postorder walk?
        
        // when you've found the char c, report the path from the root to the node
        // and build the morse code by adding a "." when you go right, "-" when you go left
        return new String("You wish.");
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
        //System.out.println(mt.toMorseCode('S'));  // find where we are in the tree, remember path to root
    }
}
