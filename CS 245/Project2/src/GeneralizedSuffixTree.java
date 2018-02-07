/** A class that stores a generalizes suffix tree for a given list of strings and a given  list of terminal markers. */

import java.util.ArrayList;
public class GeneralizedSuffixTree {
	private final static int ASCII = 97;
	private Node root; // the root of the generalized suffix tree
	private ArrayList<String> markers = null; // a list of markers to use after each string
	private ArrayList<String> strings = null; // a list of strings for this generalized suffix tree  - you do not have to use it
	private String concatString = null; // The string that is "all strings concatenated into one"
	
	/** Create a GeneralizedSuffixTree for a given list of strings, with the given terminal markers.
	 *  Size of the markers array should be the same as the strings array */
	public GeneralizedSuffixTree(ArrayList<String> strings, ArrayList<String> markers) {
		// FILL IN CODE
		// Concatenate all strings into one, separated by markers given in the array
		// Iterate backwards over the suffixes of the concatString and call insert on each suffix
		

	}

	// ------------------ A node of the GeneralizedSuffixTree ------------------------
	private class Node {
		private String string;
		private Node[] children;
		private int index;
		private int numMarkers;
		// private int depth = 0; // you are not required to use it, but might find useful
		
		public Node(String string, int index, int numMarkers) {
			children = new Node[numMarkers + 26]; // 26 letters + however many markers we have
			this.string = string;
			this.index = index;
		}

	} // -----------------------------------------------------------------------

	/** Insert a given suffix into the tree (the suffix starts at index = ind in the concatString */
	public void insert(String word, int ind) {
		root = insert(word.toLowerCase(), ind, root);
	}
	
	
	/**
	 * Insert a new suffix that starts at index = ind, into the suffix tree with the given root
	 */
	private Node insert(String word, int ind, Node tree) {
		System.out.println("Inserting suffix " + word);
		if (tree == null) {
			System.out.println("Created a new node with " + word);
			Node temp = new Node(word, ind, markers.size());
			return temp;
		}

		// FILL IN CODE
		
		return null; // don't forget to change
	}

	/** A helper method: returns the longest common prefix of word1 and word2.
	 *  Example: if word1 = "banana" and word2 = "band", the  longest common prefix is "ban". */
	private String commonPrefix(String word1, String word2) {
		// FILL IN CODE
		
		
		return null; // don't forget to change it
	}

	
	/** Return a suffix tree as a string in human readable form (using preorder traversal and indentations) */
	public String toString() {
		return toString(root, 0);
	}

	/**
	 * A private recursive method that returns the string representation of the suffix tree with the given root
	 * with i indentations.
	 */
	private String toString(Node tree, int i) {
		StringBuilder blr = new StringBuilder();
		// FILL IN CODE
		
		return blr.toString();
	}
	
	
	/** Return the index in the children array that corresponds to the first letter of the given word. If 
	 * the first letter is 'a', the method returns 0; if the first letter is 'b', the method returns 1 etc.
	 * Markers are handled separately: the index of the first marker child is 26 (the last one in the array),
	 * the index of the second marker child is 27, etc..
	 * @param word
	 * @return
	 */
	public int getChildIndex(String word) {
		int childIndex = word.charAt(0) - ASCII;
		String s = Character.toString(word.charAt(0));
		int i = markers.indexOf(s);
		if (i != -1 ) // one of the markers
			childIndex = 26 + i;
		return childIndex;
	}


	
	/** Return an ArrayList of common longest substrings of the strings in the generalized suffix tree */
	public ArrayList<String> findLCS() {
		ArrayList<String> lcsStrings = new ArrayList<String>();
		// FILL IN CODE
		
		
		// Note: you will likely write a couple of helper methods for this method
		return lcsStrings;
	}
}