import java.awt.List;

public class SortingDriver {
	public static void main(String[] args) {
		Comparable[] comp =  {5,3,9,4,8,6,2,17,2,7,10};
		for(int pos1 = 0; pos1 < comp.length ; pos1++) {
			System.out.print(comp[pos1] + " ");
		}
		System.out.println("\n***********");
		SortingAlgorithms sortA = new SortingAlgorithms();
		int lowindex = 0;
		int highindex = 10;
		
//		sortA.shakerSort(comp, lowindex, highindex);
//		sortA.heapSort(comp, lowindex, highindex);
//		sortA.randomizedQuickSort(comp, lowindex, highindex);

		
//		for(int pos1 = 0; pos1 < comp.length ; pos1++) {
//			System.out.print(comp[pos1] + " ");
//		}
//		System.out.println();
		
		
//		String[] array = {"b","r","b","r","g","b","g","b"};
//		sortA.rgbSort(array);
//		for(int pos1 = 0; pos1 < array.length ; pos1++) {
//			System.out.print(array[pos1] + " ");
//		}
//		System.out.println();
		
		
		
		sortA.externalSort("list.txt", "sortedList", 11, 3);
		
	}
}
