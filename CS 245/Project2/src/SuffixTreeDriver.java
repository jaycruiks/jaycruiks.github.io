/** The driver class for SuffixTree */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class SuffixTreeDriver {
	public static void main(String[] args) {
		processStrings("inputStringsSimple");

	}
	
	private static BufferedReader reader; // BufferedReader Declared
	private static SuffixTree tree;
	/** Process strings from the given file. See comments inside the method */
	public static void processStrings(String filename) {
		// FILL IN CODE 
		// Read each string from the file, construct a suffix tree
		// Create a file that has the same name as the string + "Results.txt"
		// (like "bananaResults.txt")
		// First, write the suffix tree
		// Then empty line

		// Then read valid suffixes, call containsSuffix for each of them, and
		// write the results to the file

		// Read invalid suffixes, call containsSuffix for each of them, and
		// write the results to the file (should all be -1)

		// Read valid substrings, call getSubstringIndices and write results to
		// the file

		// Read invalid substrings, call getSubstringIndices and write results
		// to the file (should all be [])

		// read one empty line

		// repeat for the next string in the file
		
		
		//in the driver i just call the corrspoinding methods for each line to get the desired output. 
		// the the output is written to a file. 
//		try {
//			FileWriter fw = null;
//			BufferedWriter bw = null;
//			reader = new BufferedReader(new FileReader(filename));// Buffered Reader declared.
//			String line;// defining the string line
//			int count = 0;
//			String treeName  ="";
//			String outPut = "";
//			while((line = reader.readLine()) != null){
//				
//				if(count == 0) {
//					outPut ="";
//					
//					treeName = line;
//					tree = new SuffixTree(line);
////					System.out.println(line);
//					outPut += tree.toString() +"\n";
//					
//				
//				}
//				
//				
//				if (count == 1) {
//					String suffixText = "";
//					String[] lineSplit = line.split(", ");
////					outPut+="\n";
//					for(String suffix : lineSplit) {
//						suffixText += tree.containsSuffix(suffix) + " ";
////						System.out.println(suffixText);
////						System.out.print(tree.containsSuffix(suffix) + " ");
//					}
//					outPut+=suffixText + "\n";
////					System.out.println();
//					
//				}
//				if (count == 2) {
//					String suffixText = "";
//					String[] lineSplit = line.split(", ");
//					for(String suffix : lineSplit) {
//						suffixText += tree.containsSuffix(suffix) + " ";
////						System.out.print(tree.containsSuffix(suffix) + " ");
//					}
//					outPut+=suffixText+ "\n";
////					System.out.println();
//					
//				}
//				if (count == 3) {
//					String subStringText = "";
//					String[] lineSplit = line.split(", ");
//					for(String subString : lineSplit) {
//						subStringText += tree.getSubstringIndices(subString);
////						System.out.print(tree.getSubstringIndices(subString) );
//					}
//					outPut+= subStringText+ "\n";
////					System.out.println();
//				}
//				if (count == 4) {
//					String subStringText = "";
//					String[] lineSplit = line.split(", ");
//					for(String subString : lineSplit) {
//						subStringText += tree.getSubstringIndices(subString);
////						System.out.print(tree.getSubstringIndices(subString) );
//					}
//					outPut+= subStringText+ "\n";
////					System.out.println();
//				}
//				
//				if(line.equals("")) {
////					outPut+="\n";
////					System.out.println();
//					count =-1;
//				}
////				System.out.println(outPut);
//				count ++;
//				fw = new FileWriter(treeName + "Results.txt");
//				bw = new BufferedWriter(fw);
//				bw.write(outPut);
//				bw.close();
//				fw.close();
//			}
//			
//			//Below are the catch statements
//		} catch (FileNotFoundException e) {
//			System.out.println("Error file not found");
//		} catch (IOException e){
//			System.out.println("Error");
//		}finally {
//			System.out.println();
//		}
		
		
		System.out.println();
		tree = new SuffixTree("ahuihou");
//		
		System.out.println("IN driver");
//		
		System.out.println(tree);
		System.out.println(tree.containsSubstring("hui"));
		System.out.println(tree.containsSuffix("hou$"));
		System.out.println(tree.getSubstringIndices("h"));
//		System.out.println(tree.numOccurrences(""));
//		System.out.println(tree);
//		
		

	}

}
