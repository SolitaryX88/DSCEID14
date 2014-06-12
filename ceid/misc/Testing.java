package ceid.misc;

import java.util.Arrays;
import java.util.Random;

import ceid.searching.BinarySearch;
import ceid.searching.InterpolationSearch;

public class Testing {


	public static void searching() {

		int size = 10000;

		int[] test = new int[size];
		int mod = (int) (size / 1.2); // With much mod value the array has
										// duplicate values
										// This results in duplicate values,
										// and the algorithm result in different
										// positions.

		Random r = new Random();
		for (int i = 0; i < test.length; i++) {
			test[i] = r.nextInt() % mod;
			// System.out.println(test[i]);
		}

		Arrays.sort(test);

		InterpolationSearch IS = new InterpolationSearch(test);
		BinarySearch BS = new BinarySearch(test);

		int searchInt = r.nextInt() % mod;
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
		isSorted(a);
	}
	
	
	private static boolean less(int v, int w) {
        return v-w < 0;
    }
        

    private static boolean isSorted(int[] a) {
        for (int i = 1; i < a.length; i++)
            if (less(a[i], a[i-1])) return false;
        return true;
    }

}









