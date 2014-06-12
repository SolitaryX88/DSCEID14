package ceid.searching;

import java.util.Arrays;

public class BinarySearch {

	private int low = 0;
	private int high, mid;
	private int[] a;
	
	
    public BinarySearch() { }
    
    public BinarySearch(int[] arr) {
		inter(arr);
	}

	
	private void inter(int arr[]){
		high = arr.length - 1;
		a = Arrays.copyOf(arr, arr.length);
	}
	
	public int search (int[] arr, int key){
		inter(arr);
		return (search(key));
	}
	

    public int search(int key) {
		// Returns index of toFind in sortedArray, or -1 if not found
		if (a == null)
			throw new NullPointerException();

    	int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if      (key < a[mid]) hi = mid - 1;
            else if (key > a[mid]) lo = mid + 1;
            else return mid;
        }
        return -1;
    }
    
}
