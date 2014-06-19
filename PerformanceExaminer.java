
import java.io.*;
import java.util.*;

import org.apache.commons.lang3.*;

import ceid.misc.*;
import ceid.sorting.*;

public class PerformanceExaminer {
	public static void main(String[] args) {

		boolean test = false;
		boolean randArray = true;
		boolean readFile = false;
		if (test) {
			Testing.searching();
			Testing.sorting();
			System.exit(0);
		}

		if (randArray) {
			randomArray();
		}
		
		if(readFile){
			readFromFile();
		}

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

		Statistics<Long> comp = new Statistics<Long>();
		Statistics<Double> time = new Statistics<Double>();

		String[] sortAlgs = { "QS", "MS", "IS" };

		
		for (int i = 0; i < scale.length; i++) {

			System.out.println("Scale: " + scale[i] + "K");
			

			Double[] timeReplQS = new Double[repl];
			Double[] timeReplIS = new Double[repl];
			Double[] timeReplMS = new Double[repl];

			Long[] compReplQS = new Long[repl];
			Long[] compReplIS = new Long[repl];
			Long[] compReplMS = new Long[repl];

			for (int j = 0; j < repl; j++) {

				int[] one = Arrays.copyOf(listInt[i], listInt[i].length);
						
				startTime = System.nanoTime();
				QuickSort.sort(one);
				timeReplQS[j] = Double.valueOf((System.nanoTime() - startTime) / 1000);
				compReplQS[j] = QuickSort.comp;
				QuickSort.comp = 0;

				one = Arrays.copyOf(listInt[i], listInt[i].length);

				startTime = System.nanoTime();
				MergeSort.sort(one);
				timeReplMS[j] = Double.valueOf((System.nanoTime() - startTime) / 1000);
				compReplMS[j] = MergeSort.comp;
				MergeSort.comp = 0;
				
				one = Arrays.copyOf(listInt[i], listInt[i].length);

				startTime = System.nanoTime();
				InsertionSort.sort(one);
				timeReplIS[j] = Double.valueOf((System.nanoTime() - startTime) / 1000);
				compReplIS[j] = InsertionSort.comp;
				InsertionSort.comp = 0;

			}

			comp.setNewStat("QS_" + String.valueOf(scale[i] * 1000), compReplQS);
			time.setNewStat("QS_" + String.valueOf(scale[i] * 1000), timeReplQS);

			comp.setNewStat("IS_" + String.valueOf(scale[i] * 1000), compReplIS);
			time.setNewStat("IS_" + String.valueOf(scale[i] * 1000), timeReplIS);

			comp.setNewStat("MS_" + String.valueOf(scale[i] * 1000), compReplMS);
			time.setNewStat("MS_" + String.valueOf(scale[i] * 1000), timeReplMS);

		}

		System.out.println();
		Statistics.printStatCSVHeader();

		for (int k = 0; k < sortAlgs.length; k++) {
			for (int i = 0; i < scale.length; i++) {

				System.out.print("Time_");
				time.printStatCSV(sortAlgs[k]+"_"+ String.valueOf(scale[i] * 1000));
			}

			for (int i = 0; i < scale.length; i++) {

				System.out.print("Compares_");
				comp.printStatCSV(sortAlgs[k]+"_"+ String.valueOf(scale[i] * 1000));
			}
		}
		
	}

	
	private static void randomArray() {
		int repl = 10;

		long startTime;

		Statistics<Long> comp = new Statistics<Long>();
		Statistics<Double> time = new Statistics<Double>();

		String[] sortAlgs = { "QS", "MS", "IS" };

		for (int i = 0; i < 10; i++) {

			System.out.println("Scale: " + (i + 1) + "K");

			Double[] timeReplQS = new Double[repl];
			Double[] timeReplIS = new Double[repl];
			Double[] timeReplMS = new Double[repl];

			Long[] compReplQS = new Long[repl];
			Long[] compReplIS = new Long[repl];
			Long[] compReplMS = new Long[repl];

			for (int j = 0; j < repl; j++) {

				int[] one = Testing.randArray((i + 1) * 1000);

				MergeSort.sort(one);
				MergeSort.comp = 0;
				ArrayUtils.reverse(one);

				startTime = System.nanoTime();
				QuickSort.sort(one);
				timeReplQS[j] = Double.valueOf((System.nanoTime() - startTime) / 1000);
				compReplQS[j] = QuickSort.comp;
				QuickSort.comp = 0;

				ArrayUtils.reverse(one);

				startTime = System.nanoTime();
				MergeSort.sort(one);
				timeReplMS[j] = Double.valueOf((System.nanoTime() - startTime) / 1000);
				compReplMS[j] = MergeSort.comp;
				MergeSort.comp = 0;
				
				ArrayUtils.reverse(one);

				startTime = System.nanoTime();
				InsertionSort.sort(one);
				timeReplIS[j] = Double.valueOf((System.nanoTime() - startTime) / 1000);
				compReplIS[j] = InsertionSort.comp;
				InsertionSort.comp = 0;

			}

			comp.setNewStat("QS_" + String.valueOf((i + 1) * 1000), compReplQS);
			time.setNewStat("QS_" + String.valueOf((i + 1) * 1000), timeReplQS);

			comp.setNewStat("IS_" + String.valueOf((i + 1) * 1000), compReplIS);
			time.setNewStat("IS_" + String.valueOf((i + 1) * 1000), timeReplIS);

			comp.setNewStat("MS_" + String.valueOf((i + 1) * 1000), compReplMS);
			time.setNewStat("MS_" + String.valueOf((i + 1) * 1000), timeReplMS);

		}

		System.out.println();
		Statistics.printStatCSVHeader();

		for (int k = 0; k < sortAlgs.length; k++) {
			for (int i = 0; i < repl; i++) {

				System.out.print("Time_");
				time.printStatCSV(sortAlgs[k]+"_"+ String.valueOf((i + 1) * 1000));
			}

			for (int i = 0; i < repl; i++) {

				System.out.print("Compares_");
				comp.printStatCSV(sortAlgs[k]+"_"+ String.valueOf((i + 1) * 1000));
			}
		}

	}
}
