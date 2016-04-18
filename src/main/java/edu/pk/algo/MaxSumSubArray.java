package edu.pk.algo;

/* Kadane's algorithm */

public class MaxSumSubArray {
	static int maxSumSubArray(int a[]) {
		int max = a[0];
		int curr_max = a[0];
		for (int i = 1; i < a.length; i++) {
			curr_max += a[i];
			if (curr_max < a[i])//left sub array of the current element contains -ve value. 
				 				//So only this current element will be the max sub array 
				curr_max = a[i]; 
			if (curr_max > max)
				max = curr_max;
		}
		return max;
	}

	public static void main(String[] args) {
		System.out.println(maxSumSubArray(new int[] { 2, 3, -7, -1 }));
	}
}
