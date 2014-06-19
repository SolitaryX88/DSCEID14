package ceid.misc;

import java.util.HashMap;

public class Statistics<T extends Number> {
	
	private HashMap<String, Metrics<T>> hash = new HashMap<String, Metrics<T>>();

	public Statistics() { }
	
	public void setNewStat(String testCase, T[] res)
			throws IllegalArgumentException {

		if (res == null)
			throw new NullPointerException();

		for (int i = 0; i < res.length; i++)
			if (Double.valueOf(res[i].toString()) <= 0.0)
				throw new IllegalArgumentException();

		setStat(testCase, res);
	}
	
	public double getMean(String key){
		
		Metrics<T> m = hash.get(key);
		
		return m.mean;
	}

	public double getStd(String key){
		
		Metrics<T> m = hash.get(key);
		
		return m.stdd;
	}
	
	public double getLow(String key){
		
		Metrics<T> m = hash.get(key);
		
		return m.low;
	}
	
	public double getHigh(String key) {

		Metrics<T> m = hash.get(key);

		return m.high;
	}
	
	public void printStat(String key)
	{
		Metrics<T> m = hash.get(key);

		System.out.println(key + " Mean: "+ String.format("%.3f", m.mean) + "	Std: "+ String.format("%.3f", m.stdd) 
				+ "	High: "+ String.format("%.3f", m.high) + "	Low: " + String.format("%.3f", m.low) );
	}	
	
	public void printStatCSV(String key)
	{
		Metrics<T> m = hash.get(key);

		System.out.println(key + ";"+ String.format("%.3f", m.mean) + ";"+ String.format("%.3f", m.stdd) 
				+ ";"+ String.format("%.3f", m.high) + ";" + String.format("%.3f", m.low));
	}
	
	public static void printStatCSVHeader() {
		System.out.println("TestCase;" + "Mean;" + "Std;" + "High;" + "Low;");
	}	
	
	private void setStat(String Key, T[] res) {
		hash.put(Key, new Metrics<T>(res));
	}

	private class Metrics<T extends Number> {

		public double mean, stdd = 0.0;
		public double high, low;
		private int n;

		public Metrics(T res[]) {

			this.n = res.length;

			this.mean = mean(res);
			this.stdd = stddev(res);

			confidenceHi();
			confidenceLo();
		}

		private double mean(T[] res) {
			double r = 0.0;
			double mean;
			for (int i = 0; i < res.length; i++) {
				r += res[i].doubleValue();

			}
			mean = r / res.length;
			return mean;
		}

		private double stddev(T[] res) {
			if (res.length == 1)
				return Double.NaN;
			else {
				double q = 0.0;

				for (int i = 0; i < res.length; i++) {
					q += (res[i].doubleValue() - this.mean)
							* (res[i].doubleValue() - this.mean);
				}
				return Math.sqrt(q / (res.length - 1));
			}
		}

		private void confidenceHi() {
			this.high = this.mean + 1.96 * this.stdd / Math.sqrt(this.n);
		}

		private void confidenceLo() {
			this.low = this.mean - 1.96 * this.stdd / Math.sqrt(this.n);
		}
	}
}
