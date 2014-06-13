package ceid.misc;

import ceid.sorting.*;
import java.util.*;

import ceid.searching.BinarySearch;
import ceid.searching.InterpolationSearch;

public class Testing {
	static Random r = new Random();
	static int mod ;
	private static int[] randArray(){
		return(randArray(10000));
	}
	
	private static int[] randArray(int size){

		int[] test = new int[size];
		mod = (int) (size / 1.2); // With much mod value the array has
									  // duplicate values

		Random r = new Random();
		for (int i = 0; i < test.length; i++) {
			test[i] = r.nextInt() % mod;
			// System.out.println(test[i]);
		}
		
		return(test);
	}

	public static void searching() {

		int[] test = randArray();

		Arrays.sort(test);

		InterpolationSearch IS = new InterpolationSearch(test);
		BinarySearch BS = new BinarySearch(test);

		int searchInt = r.nextInt() % mod ;
		int posIS = IS.search(searchInt);
		int posBS = BS.search(searchInt);

		if (posIS != -1)
			System.out.println("IS Found! Int: " + searchInt + ", in pos: "
					+ posIS + ", with value: " + test[posIS]);

		if (posBS != -1)
			System.out.println("BS Found! Int: " + searchInt + ", in pos: "
					+ posBS + ", with value: " + test[posBS]);

		if (posBS!=posIS)
			System.out.println("Different positions in search Algs");
		

		if (test[posBS]!=searchInt || test[posIS]!=searchInt )
			System.out.println("Verification error! The Search Algs failed to find the right key!");
		
	}
	
	
	// Sorting check functions
    

	public static void sorting() {
		int[] test = randArray();
		
		try {MergeSort.sort(test);
			}catch(Exception e){
			
			}
		
		if(!isSorted(test))
			System.out.println("MS: The Array is not sorted! :-( ");
		else
			System.out.println("MS: The Array is sorted! :-) ");
		
		int[] testb = randArray();
		
		QuickSort.sort(testb);
		
		if(!isSorted(testb))
			System.out.println("QS: The Array is not sorted! :-( ");
		else
			System.out.println("QS: The Array is sorted! :-) ");
		
	}
	
	

    private static boolean isSorted(int[] a) {
        for (int i = 1; i < a.length; i++)
            if (a[i]- a[i-1] < 0) return false;
        return true;
    }

}

