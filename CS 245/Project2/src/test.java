import java.util.ArrayList;
import java.util.List;

public class test {
	public static void main(String[] args ){
		List<Integer> sorted = new ArrayList<Integer>();
		sorted.add(3);
		sorted.add(1);
		sorted.add(6);
		sorted.add(9);
		sorted.add(4);
		
		
		System.out.println(sorted);
		for(int j=0; j< sorted.size();j++) {// this is the sorting part of the function. 
			
			for(int k=j; k< sorted.size();k++) {
				System.out.println(sorted);
				if(sorted.get(j) > sorted.get(k)) {
					int temp = sorted.get(j);
					sorted.set(j, sorted.get(k));
					sorted.set(k, temp);
				}
			}
		}
		
		
		
	}
}
