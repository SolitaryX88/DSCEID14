package ceid.searching;

import java.util.Arrays;

public class InterpolationSearch {
	private int low = 0;
	private int high, mid;
	private int[] a = null;
	
	public InterpolationSearch(int[] arr) {
		inter(arr);
	}
	
	public InterpolationSearch() {

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

		while (a[low] <= key && a[high] >= key) {
			mid = low + ((key - a[low]) * (high - low)) / (a[high] - a[low]); // out of range is
																
			if (a[mid] < key)low = mid + 1;
			else if (a[mid] > key) high = mid - 1;
			else return mid;
			
		}

		if (a[low] == key)
			return low;
		else
			return -1;
	}
}