import org.apache.commons.lang3.*;
import ceid.misc.*;

public class PerformanceExaminer {
	public static void main(String[] args) {

		boolean test = false;
		
		if (test) {
			Testing.searching();
			Testing.sorting();
			System.exit(0);
		}
		
		long startTime, endTime; 
		
		
		for(int i = 0 ; i < 10 ; i++){
		
		int[] one =  new int[1000];
		
		ArrayUtils.reverse(one);
		
		}

	}

}
