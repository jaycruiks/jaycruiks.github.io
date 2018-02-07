
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class SortingAlgorithms implements SortInterface{
	
	public SortingAlgorithms() {
		
	}

	@Override
	public void shakerSort(Comparable[] array, int lowindex, int highindex) {
		Comparable temp;//declare a temp variable 
		if(lowindex < highindex){// just a check 
			for(int pos = lowindex; pos < highindex; pos++){// I make sure i start at lowIndex
				for (int j = pos; j < highindex ; j++) {// at the lowPos up to the highindex
					
					if (array[j].compareTo(array[j+1])>0 && j < highindex) {//if its greater than swap to the right
						temp = array[j];
						array[j] = array[j+1];
						array[j+1] = temp;
					}
				}
				for (int j = highindex - 1; j > pos; j--) {// Here it goes back the other direction
					if (array[j].compareTo(array[j-1])<=0) {//if its less then it swaps to the left
						temp = array[j];
						array[j] = array[j-1];
						array[j-1] = temp;
					}
				}
			}
			for(int pos1 = 0; pos1 < array.length ; pos1++) {
				System.out.print(array[pos1] + " ");
			}
			System.out.println();
		}else{// just to say something here
			System.out.println("LowIndex is higher than the HighIndex");
		}
		
	}

	private Comparable[] swap(Comparable[] array, int pos1, int pos2) {
		Comparable tmp;
		tmp = array[pos1];
		array[pos1] = array[pos2];
		array[pos2] = tmp;
		return array;
	}
	private Comparable[] swapbuild(Comparable[] array, int pos1, int pos2, int highindex, int lowindex) {
		swap(array, pos1, pos2);
		
		
		int count = pos2;
		while(count <= highindex){
//			System.out.println(count + " ------COUNT IND");
			
			int left2 =  count*2+1-lowindex;
			int right2 = count*2+2-lowindex;
//			System.out.println((left2) + " Left<inside>Right " + (right2));
			
			if(left2 <= highindex && array[left2].compareTo(array[right2])>0){
				if(left2 < highindex && array[left2].compareTo(array[count]) >= 0){
					
	//				System.out.println("leftinswap "+array[left2] +  "<>"  + array[count]);
					swap(array, left2, count);
				}
			}
			if(right2 <= highindex && array[right2].compareTo(array[left2]) >= 0){
				if(right2<=highindex && array[right2].compareTo(array[count]) >= 0){
	//				System.out.println("rightinswap "+array[right2] +  "<>"  + array[count]);
					swap(array, right2, count);
				}
			}
			count++;
		}
		
		return array;
	}

	public Comparable[] build(Comparable[] array, int lowindex, int highindex) {	
		for(int i = highindex/2+(lowindex/2); i>=lowindex; i--){
//			System.out.println(i);
			int left = (i)*2+1 - lowindex;
			int right = (i)*2+2 - lowindex;
//			System.out.println((left) + " Left<>Right " + (right));
			if(left < highindex && array[left].compareTo(array[right])>0){
				if(array[left].compareTo(array[i]) >= 0){
	//				System.out.println("left "+array[left] +  "<>"  + array[i]);
					swapbuild(array, i, left, highindex,lowindex);
				}
			}
			if(right <= highindex && array[right].compareTo(array[left]) >= 0){
				if(array[right].compareTo(array[i]) >= 0){
	//				System.out.println("right " + array[right] +  "<>"  + array[i]);
	//				System.out.println(array[right] +  "<>"  + array[i]);
					swapbuild(array,i,right,highindex,lowindex);
				}
			}
//			for(int pos1 = 0; pos1 < array.length ; pos1++) {
//				System.out.print(array[pos1] + " ");
//			}
//			System.out.println();
			
		
		}
		
//		for(int pos1 = 0; pos1 < array.length ; pos1++) {
//			System.out.print(array[pos1] + " ");
//		}
//		System.out.println();
		
		return array;
	}

	@Override
	public void heapSort(Comparable[] array, int lowindex, int highindex) {
		build(array, lowindex, highindex);
//		System.out.println("AFTER BUILD");
//		for(int pos1 = 0; pos1 < array.length ; pos1++) {
//			System.out.print(array[pos1] + " ");
//		}
//		System.out.println();
//		
		
		
		for(int i = highindex; i >= lowindex; i--){
//			System.out.println(lowindex + " " + (i));
			swap(array, lowindex,i);
			build(array, lowindex, i-1);

//			for(int pos1 = 0; pos1 < array.length ; pos1++) {
//				System.out.print(array[pos1] + " ");
//			}
//			System.out.println();
		}
		
		
	}

	@Override
	public void randomizedQuickSort(Comparable[] array, int lowindex, int highindex) {
		if (lowindex < highindex) {
			int pivot = partition(array, lowindex, highindex);
			randomizedQuickSort(array, lowindex, pivot - 1);
			randomizedQuickSort(array, pivot + 1, highindex);
		}
	}
	
	private int partition(Comparable[] array, int low, int high) {
		Comparable pivot;
		Comparable tmp;
		int max = high;
		Random r = new Random();
		int mid = r.nextInt(((high-low)+1)+low);
//		System.out.println(mid);
		
		tmp = array[mid];
		array[mid] = array[high];
		array[high] = tmp;
		pivot = array[high];
		low--;
		do {
			while (array[++low].compareTo(pivot)<0){
				
			}
			while ((low < high) && (array[--high].compareTo(pivot)>0)){
				
			}			
			tmp = array[low];
			array[low] = array[high];
			array[high] = tmp;
		} while (low < high);
		
		tmp = array[low];
		array[low] = array[max];
		array[max] = tmp;
		
		return low;
	}

	@Override
	public void externalSort(String inputFile, String outputFile, int n, int m) {
		BufferedReader reader; 
		try {
			FileWriter fw = null;
			BufferedWriter bw = null;
			reader = new BufferedReader(new FileReader(inputFile));// Buffered Reader declared.
			String line;
			
			String outPut = "";
			int count = 0;
			int total =1;
			int fileCount = 1;
			Comparable[] array = new Comparable[m];
			while(((line = reader.readLine()) != null) && count <= m-1){
				fw = new FileWriter("temp" + fileCount + ".txt");
				bw = new BufferedWriter(fw);
//				System.out.println(line + " <> " + count + " <> " + fileCount);
				array[count] =  Integer.parseInt(line);
				
				if(total == n && (n&m) !=0){
					for(int i =count+1; i < m; i++){
//						array[i] = "";
					}
				}
				if(count == m-1 || total == n){
					
					randomizedQuickSort(array, 0, m-1);
					for(int i =0; i < m; i++){
						outPut+= array[i]+"\n";
					}
					System.out.println(n%m-1);
					
					for(int i =0; i < m; i++){
						System.out.print(array[i] +" ");
					}System.out.println();
					
					
					bw.write(outPut);
					bw.close();
					fw.close();
					fileCount++;
					count = -1;
					outPut="";
				}
				
				total++;
				count++;
			}
			
			//Below are the catch statements
		} catch (FileNotFoundException e) {
			System.out.println("Error file not found");
		} catch (IOException e){
			System.out.println("Error");
		}
		
	}

	private String[] swapString(String[] array, int pos1, int pos2) {
		String tmp;
		tmp = array[pos1];
		array[pos1] = array[pos2];
		array[pos2] = tmp;
		return array;
	}
	
	@Override
	public void rgbSort(String[] array) {
		int z=0;
		for(int i = 0; i < array.length; i++){
			if(array[i].compareTo("r") == 0){
				swapString(array, i, z);
				z++;
			}
		}
		for(int i = 0; i < array.length; i++){
			if(array[i].compareTo("g") == 0){
				swapString(array, i, z);
				z++;
			}
		}
		for(int i = 0; i < array.length; i++){
			if(array[i].compareTo("b") == 0){
				swapString(array, i, z);
				z++;
			}
		}
	}
}
