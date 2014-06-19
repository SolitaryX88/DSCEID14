
import java.io.*;
import java.math.BigInteger;
import java.util.*;

import javax.swing.JOptionPane;

import org.apache.commons.lang3.*;

import ceid.searching.BinarySearch;
import ceid.searching.InterpolationSearch;
import ceid.sorting.*;

public class PerformanceExaminer {
	private static boolean search = false;
	private static boolean binarySearch = false;
	
	public static void main(String[] args) {

		boolean randArray = true;
		boolean readFile = true;
		
		
		if (randArray) {
			System.out.println("==== Random Array Results ===");
			randomArray();
		}
		
		if(readFile){
			System.out.println("==== Read from File Results ===");
			readFromFile();
		}

	}

	
	private static void searchKey(int[] one) {

		String key = JOptionPane.showInputDialog("Please give me a key to search: ");
		int pos = -1;

		if (binarySearch) {
			BinarySearch bs = new BinarySearch();
			pos = bs.search(one, Integer.parseInt(key));

		} else {
			InterpolationSearch is = new InterpolationSearch();
			pos = is.search(one, Integer.parseInt(key));
		}

		if (pos != -1)
			JOptionPane.showMessageDialog(null, "Key (" + key
					+ ") found on position: " + pos);
		else
			JOptionPane.showMessageDialog(null, "Key (" + key + ") not found!");

	}
	
	private static void readFromFile() {

		Scanner[] scanner = new Scanner[5];

		for (int k = 0; k < 5; k++) {
			try {
				scanner[k] = new Scanner(new File("src/files/f" + (k + 1) + ".txt"));
			} catch (FileNotFoundException e) {
				System.out.println("File not found! f" + (k + 1));
				e.printStackTrace();
				System.exit(0);
			}
		}

		int[][] listInt = new int[5][];
		int[] scale = { 1, 5, 10, 50, 100 };

		for (int k = 0; k < scale.length; k++) {
			listInt[k] = new int[(scale[k]) * 1000];

			int i = 0;
			while (scanner[k].hasNextInt() && !(scanner == null)) {
				listInt[k][i] = scanner[k].nextInt();
			}
		}
		int repl = 15;
		long startTime;

		for (int i = 0; i < scale.length; i++) {

			System.out.println("Scale: " + scale[i] + "K");
			

			double timeReplQS = 0.0;
			double timeReplIS = 0.0;
			double timeReplMS = 0.0;

			long compReplQS = 0;
			BigInteger compReplIS = new BigInteger("0");
			long compReplMS = 0;

			for (int j = 0; j < repl; j++) {

				int[] one = Arrays.copyOf(listInt[i], listInt[i].length);
						
				startTime = System.nanoTime();
				QuickSort.sort(one);
				timeReplQS += Double.valueOf((System.nanoTime() - startTime) / 1000);
				compReplQS += QuickSort.comp;
				QuickSort.comp = 0;

				one = Arrays.copyOf(listInt[i], listInt[i].length);

				startTime = System.nanoTime();
				MergeSort.sort(one);
				timeReplMS += Double.valueOf((System.nanoTime() - startTime) / 1000);
				compReplMS += MergeSort.comp;
				MergeSort.comp = 0;
				
				one = Arrays.copyOf(listInt[i], listInt[i].length);

				startTime = System.nanoTime();
				InsertionSort.sort(one);
				timeReplIS += Double.valueOf((System.nanoTime() - startTime) / 1000);
				compReplIS = compReplIS.add(BigInteger.valueOf(InsertionSort.comp));	
				InsertionSort.comp = 0;
				
				if (search) {
					searchKey(one);
				}
				
			}

			System.out.println("Comp QS_" + String.valueOf(scale[i] * 1000)+" : "+compReplQS/repl);
			System.out.println("Comp MS_" + String.valueOf(scale[i] * 1000)+" : "+compReplMS/repl);
			System.out.println("Comp IS_" + String.valueOf(scale[i] * 1000)+" : "+compReplIS.longValue()/repl);
			
			System.out.println("Time QS_" + String.valueOf(scale[i] * 1000)+" : "+timeReplQS/repl);
			System.out.println("Time MS_" + String.valueOf(scale[i] * 1000)+" : "+timeReplMS/repl);
			System.out.println("Time IS_" + String.valueOf(scale[i] * 1000)+" : "+timeReplIS/repl);
			System.out.println();
		}

		
		}

	
	private static void randomArray() {
		int repl = 10;

		long startTime;
		
		for (int i = 0; i < 10; i++) {

			System.out.println("Scale: " + (i + 1) + "K");

			double timeReplQS = 0.0;
			double timeReplIS = 0.0;
			double timeReplMS = 0.0;

			long compReplQS = 0;
			BigInteger compReplIS = new BigInteger("0");
			long compReplMS = 0;

			for (int j = 0; j < repl; j++) {

				int[] one = randArray((i + 1) * 1000);

				MergeSort.sort(one);
				MergeSort.comp = 0;
				ArrayUtils.reverse(one);

				startTime = System.nanoTime();
				QuickSort.sort(one);
				timeReplQS += Double.valueOf((System.nanoTime() - startTime) / 1000);
				compReplQS += QuickSort.comp;
				QuickSort.comp = 0;

				ArrayUtils.reverse(one);

				startTime = System.nanoTime();
				MergeSort.sort(one);
				timeReplMS += Double.valueOf((System.nanoTime() - startTime) / 1000);
				compReplMS += MergeSort.comp;
				MergeSort.comp = 0;
				
				ArrayUtils.reverse(one);

				startTime = System.nanoTime();
				InsertionSort.sort(one);
				timeReplIS += Double.valueOf((System.nanoTime() - startTime) / 1000);				
				compReplIS = compReplIS.add(BigInteger.valueOf(InsertionSort.comp));	
				InsertionSort.comp = 0;
				
				if (search) {
					searchKey(one);
				}
			}

			String s = String.valueOf((i + 1) * 1000);
			
			System.out.println("Comp QS_" + s + " : " + compReplQS / repl);
			System.out.println("Comp MS_" + s + " : " + compReplMS / repl);
			System.out.println("Comp IS_" + s + " : " + compReplIS.longValue()/repl);

			System.out.println("Time QS_" + s + " : " + timeReplQS / repl);
			System.out.println("Time MS_" + s + " : " + timeReplMS / repl);
			System.out.println("Time IS_" + s + " : " + timeReplIS / repl);
			System.out.println();

		}
	}

	public static int[] randArray(int size) {

		int[] test = new int[size];
		int mod = (int) (size / 1.2); 

		Random r = new Random();
		for (int i = 0; i < test.length; i++) {
			test[i] = r.nextInt() % mod;
		}

		return (test);
	}
}
