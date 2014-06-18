package ceid.misc;
import java.util.HashMap;


public class Statistics<T extends Number> {
	private HashMap<String, Metrics<T>> hsh = new HashMap<String, Metrics<T>>();

	private Statistics() {
	}

	public void setStat(String test, T[] res) throws IllegalArgumentException {

		for (int i = 0; i < res.length; i++)
			if (Double.valueOf(res[i].toString()) <= 0.0)
				throw new IllegalArgumentException();

	}

	private void setStat() {
	}

	private class Metrics<T extends Number> {

		private T mean;
		private T err, hi, lo;

		public Metrics(T res[]) {

		}

		private T mean(T[] res) {
			T r;
			initZero(r);
			for (int i = 0; i < res.length; i++) {
				r = add(r, res[i]);

			}
			mean = div(r, res.length);
			return mean;
		}

		private T stddev() {
			if (nExpr == 1)
				return Double.NaN;
			else {
				double q = 0;
				for (int i = 0; i < results.length; i++) {
					q += (results[i] - mean) * (results[i] - mean);
				}
				return Math.sqrt(q / (nExpr - 1));
			}
		}

		private T confidenceHi() {
			return this.mean() + 1.96 * this.stddev() / Math.sqrt(this.t);
		}

		private T confidenceLo() {
			return this.mean() - 1.96 * this.stddev() / Math.sqrt(this.t);
		}

		@SuppressWarnings("unchecked")
		private T add(T one, T two) {

			if (one.getClass() == Integer.class) {
				return (T) (Integer) ((Integer) one + (Integer) two);
			}
			return (T) Double.valueOf(((Double) one).doubleValue()
					+ ((Double) two).doubleValue());
		}

		@SuppressWarnings("unchecked")
		private T mul(T one, T two) {

			if (one.getClass() == Integer.class) {
				return (T) (Integer) ((Integer) one * (Integer) two);
			}
			return (T) Double.valueOf(((Double) one).doubleValue()
					* ((Double) two).doubleValue());
		}

		@SuppressWarnings("unchecked")
		private T div(T one, T two) {

			if (one.getClass() == Integer.class) {
				return (T) (Integer) ((Integer) one / (Integer) two);
			}
			return (T) Double.valueOf(((Double) one).doubleValue()
					/ ((Double) two).doubleValue());
		}

		@SuppressWarnings("unchecked")
		private T div(T one, Integer two) {

			if (one.getClass() == Integer.class) {
				return (T) (Integer) ((Integer) one / two);
			}
			return (T) Double.valueOf(((Double) one).doubleValue()
					/ two.doubleValue());
		}

		@SuppressWarnings("unchecked")
		private T initZero(T one) {
			if (one.getClass() == Integer.class)
				return (T) Integer.valueOf(0);

			return (T) Double.valueOf(0);

		}
	}

}

/*
    public double mean() {
        double r = 0;
        for (int i = 0; i < results.length; i++) {
            r += results[i];
        }
        mean = r / nExpr;
        return mean;
    }

    public double stddev() {
        if (nExpr == 1)
            return Double.NaN;
        else {
            double q = 0;
            for (int i = 0; i < results.length; i++) {
                q += (results[i] - mean) * (results[i] - mean);
            }
            return Math.sqrt(q / (nExpr - 1));
        }
    }
    
    public double confidenceHi(){
     return this.mean() + 1.96 * this.stddev() / Math.sqrt(this.t);
    }
    public double confidenceLo(){
     return this.mean() - 1.96 * this.stddev() / Math.sqrt(this.t);
    }
    
*/