package ceid.searching;

import java.util.Arrays;

public class BinarySearch {

	private int low = 0;
	private int high, mid;
	private int[] a = null;
	
	
    public BinarySearch() { }
    
    public BinarySearch(int[] arr) {
		inter(arr);
	}

	
	private void inter(int arr[]){
		a = Arrays.copyOf(arr, arr.length);
		high = arr.length - 1;
	}
	
	public int search (int[] arr, int key){
		inter(arr);
		return (search(key));
	}
	

    public int search(int key) {
		// Returns index of toFind in sortedArray, or -1 if not found
		if (a == null)
			throw new NullPointerException();

    		while (low <= high) {
            mid = low + (high - low) / 2;
            if      (key < a[mid]) high = mid - 1;
            else if (key > a[mid]) low = mid + 1;
            else return mid;
        }
        return -1;
    }
    
}
