/** Class SuffixTree. Has methods to build a suffix tree for a given string and use it to find suffixes, substrings etc.  
 *  You may add additional helper methods (in fact, it is recommended), but may not change the signatures of the given methods. */
import java.util.ArrayList;
import java.util.List;

public class SuffixTree {
	private Node root; // the root of the suffix tree
	private final static int ASCII = 97; // offset of letter 'a' in the ASCII table

	/** Constructor of SuffixTree. The root contains an empty string and index = -1 */
	public SuffixTree() {
		root = new Node("", -1);
	}

	/** Create a SuffixTree for a given string s.
	 *  Iterate backwards, and call insert method to insert each suffix into the tree. */
	public SuffixTree(String s) {
		String newS = s + "$"; // add $ to the end
		// Iterate backwards and insert into the suffix tree
		// FILL IN CODE
		root = new Node("", -1);
		char[] newScharArray = newS.toCharArray();
		for(int ind = newS.length()-1; ind >= 0; ind--){
			String temp = "";
			for(int count = ind; count < newS.length(); count++){
				temp += newScharArray[count];
			}
//			System.out.println(ind);
			insert(temp, ind);
			
		}
		rootSetter(root);// this is called because each node that does not have a termination symbol needs to be -1.
		//I also only made this method because my insert was done and used the indexes from the previous not to be passed to the correct location.
		// in the tree.
		
	}

	// ------------------ Inner class: A node of the SuffixTree ------------------------
	private class Node {
		private String string; // a string stored at the node
		private Node[] children; // an array of children
		private int index; // an index in the original string where this suffix starts

		/** Constructor for Node */
		public Node(String string, int index) {
			children = new Node[27]; // 26 letters + "$"
			this.string = string;
			this.index = index;
		}
	}  // ------------------------------------------------------------------------------

	

	// Used at the very end of insert because need to make all the roots zero.
	private void rootSetter(Node tree) {// Deceleration of the rootSetter function
		int index=0;
		if(tree == null) {
			return ;
		}
		while(index < tree.children.length) {// just for every tree in children if its children are not null meaning the tree has children
			if(tree.children[index] != null) {//its not null
				if(!tree.children[index].string.contains("$")) {// and does not contain a $ then the index is set to -1
					tree.children[index].index=-1;
//					System.out.println(tree.children[index].string+" String <> Index "+ tree.children[index].index);
				}
			}
			if(tree.children[index] != null) {// if the tree has children go into the subtree and check the same things.
				rootSetter(tree.children[index]);
			}
			index++;	
		}
	}
	
	
	/** Insert a new suffix (that starts at index ind in the string) into the suffix tree */
	public void insert(String word, int ind) {
		root = insert(word.toLowerCase(), ind, root);
	}
	/**
	 * Insert a new suffix that starts at index = ind, into the suffix tree with
	 * the given root.
	 */
	private Node insert(String word, int ind, Node tree) {
		
		if (tree == null) {
//			System.out.println("Created a new node with " + word);
			Node temp = new Node(word, ind);
			return temp;
		}
//		System.out.println("WORD I AM ON: " +word + " IND: " + ind + " TREE.string: " + tree.string);
		// FILL IN CODE - you are required to use the algorithm we discussed in class

		int index = getChildIndex(word);// get the index of the child so I didn't have to type that a bunch.
		
		if(tree.children[index] == null) {
//			System.out.println("null node; "+ word + " IND " + ind);
			tree.children[index] = new Node(word, ind);// creating a new node
			
		}else { // This is if the childern are null
//			System.out.println("index is: "+ ind +"---"+tree.children[index].index);

			char[] cA = tree.children[index].string.toCharArray();// character array declared. for the index of the word.
			char[] wA = word.toCharArray();// makes the word into a char array 
			//basically what this does is creates the common prefix. When 
			String temp= "";
			for(int i= 0; i <cA.length; i++) {
				if(cA[i] == wA[i]) {
					temp += cA[i];	
				}
				if(cA[i] == wA[i+1]) {// this is a case to check for repeated letter in the word char array
					break;
				}
			}
//			System.out.println("Tree: " + tree.children[index].string + " word: " + word + " Temp: "+ temp + " IND: " + ind);

			String strip = tree.children[index].string.replaceAll("^" + temp, "");// what replaceAll is doing here is removing the commonPrefix by using the
			// temp from above which is the common prefix that I didn't need to code above. 
//			System.out.println(strip);
			tree.children[index].string =""+ temp;
			
//			System.out.println("strip --> " + strip + "   -tree.child.index: "+ tree.children[index].index+ " IND: " + ind);
			if(!strip.equals("")) {
//				System.out.println(tree.children[index].index + " .equals");
				tree.children[index].children[getChildIndex(strip)]= new Node(strip, tree.children[index].index);// this is the reason I need a rootSetter
				// this creation of the new node needs the previous index. 
//				System.out.println(tree.children[index].children[getChildIndex(strip)].string);
			}else {
//				System.out.println(tree.children[index].index+" else");
				tree.children[index].children[26]= new Node("$", tree.children[index].index);
				// this just creates a new node for the dollar sign that was removed.
				//because the prev if statement checks to see if strip is not empty lines. 
				// which in course is check to see if the word needs to have more than just a dollar sign removed and moved the next line.
				
			}
			
			String wordStrip = word.replaceAll("^"+ tree.children[index].string, "");
			// this removes the trees starting sequence. and then drops the word to the next tree subtree.
			
			insert(wordStrip,ind ,tree.children[index]);//recursive call
			
		}

		return root; //
		
	}

	private String lenCheck(String word1, String word2) {// this is a helper method for the helper method of common prefix.
		if(word1.length()< word2.length()) {// all this does is return the shortest word
			return word1;
		}else {
			return word2;
		}
	}
	/** A helper method: returns the longest common prefix of word1 and word2.
	 *  Example: if word1 = "banana" and word2 = "band", the  longest common prefix is "ban". */
	private String commonPrefix(String word1, String word2) {// sort of wish I didn't miss this until late but in any case. 
		// FILL IN CODE
		String temp = "";
		char[] w1 = word1.toCharArray();
		char[] w2 = word2.toCharArray();
		for(int i = 0; i < lenCheck(word1, word2).length(); i++) {
			if(w1[i] == w2[i]) {
				temp+= ""+w1[i];
			}else {
				break;
			}
		}
		
		return temp; // don't forget to change it
	}

	
	/** Return a suffix tree as a string in human readable form (using preorder traversal and indentations). For the root, 
	 * print "Root" instead of an empty string. See project description for details. */
	public String toString() {
		return toString(root, 0);	
	}

	/**
	 * A  private recursive method that returns the string representation of the suffix tree with the given root
	 * with i indentations. If i = 0, the value at the root should not be indented. 
	 * If i = 1, there should be one space printed before the value at the root.
	 * If i = 2, there should be two spaces etc..
	 * Hint: Use StringBuilder or StringBuffer (using "+" for concatenation is very slow).
	 */
	
	//I created this to addSpaces to the string builder for the number of I; 
	private StringBuilder addSpaces(StringBuilder blr, int i) {
		for(int count = 0; count < i; count ++) {
			blr.append(" ");
		}
		return blr;
	}
	
	private String toString(Node tree, int i) {
		StringBuilder blr = new StringBuilder();
		
		// FILL IN CODE
		int index  = 0;
		
		if(tree == null) {
			return blr.toString();// base case
			
		}
		if(tree.string.equals("")) {
			blr.append("Root\n");
		}// this is the check to see if we need to add the word root to the builder
//		if(blr.toString()==nullz&& !blr.toString().startsWith("root")) {
//			blr.append("root\n");
//		}
		blr.append(" ");
			while(index < tree.children.length) {
				
				
				if(tree.children[index] != null) {
					
//					System.out.println("Here");
//					blr.append(tree.children[index].string);
					if(tree.children[index].string.contains("$")) {
						if(i>0) {
							addSpaces(blr, i);
							
						}
						
						blr.append(tree.children[index].string);
						blr.append(":");
						blr.append(tree.children[index].index);
						blr.append("\n");
//						System.out.println(tree.children[index].string  + " " +  tree.children[index].index);
					}else{
						
						
						if(i>0) {
							addSpaces(blr, i);
							
							
						}

						blr.append(tree.children[index].string);
						blr.append("\n");
//						System.out.println(tree.children[index].string);//+ " NOT HERE NORMALLY " +tree.children[index].index);
						
						
					}
			
					
				}
				
//				System.out.println(index);
				
				if(tree.children[index] != null) {
//					System.out.println(tree.children[index].string);
					i++;//This part right here took me longer than it should have, I just laughed when i found this goes here. 
//					System.out.println(i);
					blr.append(toString(tree.children[index], i));//recursive call if the children are not null because we want to go through the whole tree.
					i--;// same with this to subtract i.
				}
//				toString(tree.children[index++], i);
				index++;
				
			}
//		}
		
		
		return blr.toString();

	}
	
	/** Return an index in the array of children that corresponds to the first letter of the given word. If 
	 * the first letter is 'a', the method returns 0; if the first letter is 'b', the method returns 1 etc.
	 * '$' is handled separately: the index of the '$' child is 26 (the last one in the array).
	 * @param word
	 * @return
	 */
	public int getChildIndex(String word) {// I was so happy to find this because I was just about to code something that did the same thing
		int childIndex = word.charAt(0) - ASCII;
		if (word.charAt(0) == '$')
			childIndex = 26;
		return childIndex;
	}

	
	/**
	 * Return true if a string represented by a given suffix tree contains a given
	 * word. Return false otherwise.
	 */
	public boolean containsSubstring(String word) {

		return containsSubstring(word, root);
	}
	
	/**
	 * Return true if a string represented by the suffix tree with the given root,
	 * contains a given word. Return false otherwise. 
	 * Should be recursive and make use of the suffix tree.
	 */
	private boolean containsSubstring(String word, Node tree) {
		// FILL IN CODE
//		System.out.println("CONTAINS SUBSTRING");
//		System.out.println(word);
		try {// there is a try here because if any error occurs that means that it does not exist in the tree so i return false
		if(tree==null){
			return false ;
		}
		if(tree.children[getChildIndex(word)]!=null&& tree.index==-1 ) {
			containsSubstring(word, tree.children[getChildIndex(word)]);
		}//this just moves past the -1 index at the start. 
		
		if(tree!=null) {//check to see if the tree is not null
			if(tree.string.replaceAll("[$]$", "").contains(word)||// this replace all removes the dollar sign from then end of the word
					// and this fromat was the only one that I found that actually removes the dollar sign
					word.contains(tree.string.replaceAll("[$]$", ""))) {// just a basic check to see if the word contain a dollar sign. 
//				System.out.println(tree.string.replaceAll("[$]$", "")+"  <<>> "+word);
				if(tree.string.replaceAll("[$]$", "").equals(word) ) {
				
					
					return true;// if at any point it the tee.string == the word return true 
					
				}else {// else
//					System.out.println("came to else");
					char[] cArray = tree.string.toCharArray() ;
			
					char[] wCArray = word.toCharArray();
//					System.out.println(word + "  -----  " + tree.string);
					if(wCArray.length ==0 && cArray.length == 0) {
						return true;
					}
		
					if(wCArray.length < cArray.length) {
//						System.out.println("came to length");
						boolean torf = false;
						// a check to see if the remain of the word is still true for every character 
						for(int i = 0; i < wCArray.length; i ++ ) {
//							System.out.println(cArray[i] + " " + wCArray[i]);
							if(cArray[i] == wCArray[i]) {
								torf = true;
							}else {
								torf= false;
							}
							
						}
						return torf;
					}	
					if(tree!=null) {
						
//						System.out.println("Tree != null in the else: "+tree.string);
//						System.out.println("Tree != null in the else: "+ tree.children[getChildIndex(word.replaceAll("^"+tree.string, ""))].string);
						word = word.replaceAll("^"+ tree.string.replaceAll("[$]$", ""), "");// this removes the tree from the start of the word 
						// and allows the word to be checked agian because sometimes the word is in goes though muiltple. for example hui would start at the h and 
						// then the h is removed and called on the new word.
						
//						System.out.println(tree.string+ " <<Word print----->>>"+word + "{{}}" + tree.children[getChildIndex(word)].string);
						if(word.equals("")) {// basically means the whole word was removed and that it can return true becasue it does exist.
							return true;
						}else if (tree.children[getChildIndex(word.replaceAll("[$]$", ""))].string.equals(word)) {
//							System.out.println("this condition");
							return true;
						}
						return containsSubstring(word, tree.children[getChildIndex(word)]);
					}
				}
			}
		}
		
		
		}catch(StringIndexOutOfBoundsException e) {
//			System.out.println("caught");
			return false;
		}catch(NullPointerException e) {
//			System.out.println("null");
			return false;
		}
		return false; // change as needed
		
	}
	
	/**
	 * Check if a string represented by a given suffix tree contains a given
	 * word, and if yes, return the list of indices where each occurrence of word starts. 
	 * Should be sorted in ascending order.
	 * Example: if the suffix tree is built for the word "banana" and we call this method on "ana",
	 * the method should return [1, 3]. 
	 */
	public List<Integer> getSubstringIndices(String word) {

		List<Integer> indicesOccurrences = getSubstringIndices(word, root);
		return indicesOccurrences;
	}

	private List<Integer> removDupAndSort(List<Integer> indices){// this is need to sort and remove the duplicates that occur
		List<Integer> sorted = new ArrayList<Integer>();
//		System.out.println(indices);
		for(int i: indices) {
			if(!sorted.contains(i)) {
				
				sorted.add(i);
				
			}
		}
		
		for(int j=0; j< sorted.size();j++) {// this is the sorting part of the function. 
			
			for(int k=j; k< sorted.size();k++) {
				
				if(sorted.get(j) > sorted.get(k)) {
					int temp = sorted.get(j);
					sorted.set(j, sorted.get(k));
					sorted.set(k, temp);
				}
			}
		}
		
//		System.out.println(sorted);
		return sorted;
	}
	
	/**
	 * Check if a string represented by the suffix tree with the given root,
	 * contains a given word. Return the List of indices where the
	 * substring occurrences start. Should be recursive and make use of the suffix tree.
	 */
	private List<Integer> diveSolo(String word, Node tree, List<Integer> indices){// this is only used for when the word that is being search is a solo character
		if(tree == null) {
			return indices;
		}
		if(tree.index!=-1) {
			indices.add(tree.index);
		}
		int index = 0; 
		while(index < tree.children.length) {// for every solo character it needs every index in that corrspinding tree.
			if(tree.children[index]!=null) {
				if(tree.children[index].index==-1) {
					diveSolo(word, tree.children[index], indices);
				}else {
				
					indices.add(tree.children[index].index);
					System.out.println(indices);
				}
			}
			index++;
		}

		return indices;
	}
	private List<Integer> getSubstringIndices(String word, Node tree) {
		List<Integer> indices = new ArrayList<Integer>();
		// FILL IN CODE=
		
		if(tree==null) {
			
			return removDupAndSort(indices);
		}
		System.out.println("Word: "  + word + " Tree: "+ tree.string);
//		if(word.length()==1) {
//			setTempWord(word);
//		}
		
		if(word.length()== 1) {
//			System.out.println(tree.string+" ^^^^^ "+tree.index);
			
			if(tree!=null  ) {
				
				if(tree.index==-1) {
//	
				indices.addAll(diveSolo(word, tree.children[getChildIndex(word)], indices));// the case in which dive solo is called. 
				
				}

					

			}
			
		}
	
		if(tree.children[getChildIndex(word)]!=null) {
			//goes below containsSubstring(word)
			if(containsSubstring(word)) {//just a check to make sure the word is in the tree at all. 
//				System.out.println("ContainsSubtring is True" );
//				System.out.println(tree.children[getChildIndex(word)].string.replaceAll("[$]$", "") + " <><> " + word);
				if(tree.children[getChildIndex(word)].string.replaceAll("[$]$", "").equals(word) && word.length()!=1) {
					System.out.println(".equals is true");
					if(tree.children[getChildIndex(word)].index != -1&&
							word.length()!= 1) {
						indices.add(tree.children[getChildIndex(word)].index);// this adds the indices of the word if it has its own tree. 
					}else if(tree.children[getChildIndex(word)].index == -1 &&// this is a case in which i need to get to the dollar sign index 
							tree.children[getChildIndex(word)].children[getChildIndex("$")]!=null&&
							word.length()!=1) {
						indices.add(tree.children[getChildIndex(word)].children[getChildIndex("$")].index);
					}
					System.out.println(indices);
//					getSubstringIndices(word, tree.children[getChildIndex(word)]);
					
					indices.addAll(getSubstringIndices(word, tree.children[getChildIndex(word)]));
					
				}else if(word.length()!=1) {
					System.out.println("came to else");
					String treePrior=tree.children[getChildIndex(word)].string;// not really tree prior just word prior to strip.
					String strip = word.replaceAll("^"+tree.children[getChildIndex(word)].string.replaceAll("[$]$", ""), "");// strips the tree from the word  
//					System.out.println("STRIP: "+strip);
					
					try {
						if(!tree.children[getChildIndex(word)].children[getChildIndex(strip)].string.replaceAll("[$]$", "").startsWith(strip)) {
//							System.out.println("this: "+tree.children[getChildIndex(word)].string.replaceAll("[$]$", ""));
							word = strip + tree.children[getChildIndex(word)].string.replaceAll("[$]$", "");
						}else {
							word = tree.children[getChildIndex(word)].children[getChildIndex(strip)].string.replaceAll("[$]$", "");
						}
					}catch(NullPointerException e) {
						word = tree.children[getChildIndex(word)].string.replaceAll("[$]$", "");// this is when the word is the start of the word. 
						indices.addAll(getSubstringIndices(word, tree));
					}catch(StringIndexOutOfBoundsException e) {
						return null;
					}
					
//					indices.addAll(getSubstringIndices(word, tree));
//					System.out.println(word + "+++"+treePrior);
//					indices.addAll(getSubstringIndices(strip, tree.children[getChildIndex(treePrior)]));
					indices.addAll(getSubstringIndices(word, tree.children[getChildIndex(treePrior)]));// i needed to be in the the this word not the stripped word 
//					
					
				}
				
			}
		}
		System.out.println(indices);
		return removDupAndSort(indices); 
	}
	
	/**
	 * Return the number of occurrences of a given word in the string, represented by the suffix tree
	 */
	public int numOccurrences(String word) {// just calls getSubstring and counts how many numbers are there. nothing more. 
		// FILL IN CODE
		// Hint: you can call getSubstringIndices method you wrote above
//		System.out.println("NUM OCCURRENCES");
		int count= 0; 
		try {
			for(int num: getSubstringIndices(word)) {
				count ++;
			}
		}catch(StringIndexOutOfBoundsException e) {
			count =-1;
		}
		
		return count; // // don't forget to change
	}
	
	
	/** If the suffix tree contains a given suffix, return the index where it starts in the original string, 
	 * otherwise return -1.  */
	public int containsSuffix(String suffix) {
		return containsSuffix(suffix, root);
		
	}

	/** If a given suffix tree contains a given suffix, return its' index, otherwise return -1. 
	 * Should be recursive and make use of the suffix tree. */
	private int containsSuffix(String suffix, Node tree) {//this method basically check to see if the suffix is in the tree
		// FILL IN CODE
//		System.out.println("CONTAINS SUFFIX");
//		try {
//		System.out.println(suffix);
		if(tree.children[getChildIndex(suffix)] == null || !suffix.endsWith("$")) {
			return -1;
		}
		if( tree.index==-1 && tree.children[getChildIndex(suffix)] != null) {
			containsSuffix(suffix, tree.children[getChildIndex(suffix)]);
		}
		if(tree != null ) {
//			System.out.println("Tree != null");
			if(tree.children[getChildIndex(suffix)]!=null) {
//				System.out.println("Came to contains \n" + tree.children[getChildIndex(suffix)].string + " <> " + suffix);
				if(tree.children[getChildIndex(suffix)].string.equals(suffix)) {
					return tree.children[getChildIndex(suffix)].index;//case where the suffix just is there
				}else {
//					System.out.println("came to else");
		
//					System.out.println(suffix + "  -----  " + tree.string);		
					if(tree.children[getChildIndex(suffix)]!=null) {
//						System.out.println("Tree != null in the else: "+tree.string);
//						System.out.println("Tree != null in the else: "+tree.children[getChildIndex(suffix)].string);
						String suffixPrior=suffix;
						suffix = suffix.replaceAll("^"+ tree.children[getChildIndex(suffix)].string, "");// replaces the start of the tree.
						if(suffix.equals("")) {
//							System.out.println("Came to equals nullish");
							return tree.children[getChildIndex(suffix)].index;
							
						}
						return containsSuffix(suffix, tree.children[getChildIndex(suffixPrior)]);
					}
				}
			}
		}
		
//	}catch(StringIndexOutOfBoundsException e) {
//		System.out.println("exception ob");
//		return -1;
//	}catch(NullPointerException e) {
//		System.out.println("exception");
//		return -1;
//	}

		return -1; // don't forget to change
		
	}
}