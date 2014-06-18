package ceid.sorting;

//TODO Babis, should we make it NULL the a[] at the after sorting?

public class InsertionSort {

	private int[] a = null;
	public static long comp;

	public InsertionSort() {
	}

	public InsertionSort(int[] arr) {
		a = arr;
		this.sort();
	}

	private void sort() {
		comp = 0;
		sort(this.a);
	}

	public static void sort(int[] a) {
		if (a == null)
			throw new NullPointerException();

		int N = a.length;
		for (int i = 0; i < N; i++, comp++) {
			
			for (int j = i; j > 0 && ((a[j] - a[j - 1]) < 0); j--) {
				int swap = a[j];
				a[j] = a[j - 1];
				a[j - 1] = swap;
				 comp++;
			}
		}
	}

}
