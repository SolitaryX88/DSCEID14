package ceid.sorting;

public class MergeSort {

	private int[] a = null;
	public static long comp;

	public MergeSort() {
	}

	public MergeSort(int[] arr) {
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

		int[] aux = new int[a.length];
		sort(a, aux, 0, a.length - 1);
		// assert isSorted(a);
	}

	private static void merge(int[] a, int[] aux, int lo, int mid, int hi) {

		for (int k = lo; k <= hi; k++, comp++) {
			
			aux[k] = a[k];
		}

		int i = lo, j = mid + 1;
		for (int k = lo; k <= hi; k++, comp++) {
			
			if (i > mid) {
				a[k] = aux[j];
				j++;
				comp ++;
			} else if (j > hi) {
				a[k] = aux[i];
				i++;
				comp ++;
			} else if (aux[j] - aux[i] < 0) {
				a[k] = aux[j];
				j++;
				comp ++;
			} else {
				a[k] = aux[i];
				i++;
				comp ++;
			}
		}
	}

	private static void sort(int[] a, int[] aux, int lo, int hi) {
		if (hi <= lo){
			comp++;
			return;
		}
		int mid = lo + (hi - lo) / 2;
		sort(a, aux, lo, mid);
		sort(a, aux, mid + 1, hi);
		merge(a, aux, lo, mid, hi);
	}

}
