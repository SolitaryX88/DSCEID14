package ceid.sorting;

public class QuickSort {

	private int[] a = null;
	public static long comp;
	
	public QuickSort() {
	}

	public QuickSort(int[] arr) {
		this.a = arr;
		this.sort();
	}

	private void sort() {
		comp = 0;
		sort(this.a);
	}

	public static void sort(int[] a) {
		if (a == null)
			throw new NullPointerException();
		
		//TODO Babis With or without Shuffle?
		
		sort(a, 0, a.length - 1);
	}

	private static void sort(int[] a, int lo, int hi) {
		if (hi <= lo){
			comp++;
			return;		
		}
		int j = partition(a, lo, hi);
		sort(a, lo, j - 1);
		sort(a, j + 1, hi);
	}

	private static int partition(int[] a, int low, int hi) {
		int i = low;
		int j = hi + 1;
		int v = a[low];
		while (true) {

			while ((a[++i] - v) < 0)
				if (i == hi){
					comp+=2;
					break;			
				}
			while ((v - a[--j]) < 0)
				if (j == low){
					comp+=2;
					break;
				}
			if (i >= j){
				comp++;
				break;
				}

			int swap = a[i];
			a[i] = a[j];
			a[j] = swap;
		}

		int swap = a[low];
		a[low] = a[j];
		a[j] = swap;

		return j;
	}

	public static int select(int[] a, int key) {
		if (key < 0 || key >= a.length) {
			throw new IndexOutOfBoundsException("Selected element out of bounds");
		}
		
		int low = 0, hi = a.length - 1;
		while (hi > low) {
			comp++;
			int i = partition(a, low, hi);
			if (i > key){
				hi = i - 1;
				comp++;
				}
			else if (i < key){
				low = i + 1;
				comp++;
				}
			else
				return a[i];
		}
		return a[low];
	}

}
