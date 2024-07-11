package edu.pk.algo.dynapro;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/* Longest Increasing Subsequence (LIS) */

/**
 * The longest Increasing Subsequence (LIS) problem is to find the length of the longest subsequence of a given
 * sequence such that all elements of the subsequence are sorted in increasing order.
 * For example, length of LIS for { 10, 22, 9, 33, 21, 50, 41, 60, 80 } is 6 and LIS is {10, 22, 33, 41, 60, 80}.
 */
class LongestIncreasingSubsequence {
	public static void main(String[] args) {
		print(findLongestIncreasingSubsequence(new int[]{10, 22, 9, 33, 21, 50, 41, 60, 80}));
		print(findLongestIncreasingSubsequence(new int[]{50, 3, 10, 7, 40, 80}));
		print(findLongestIncreasingSubsequence(new int[]{3, 10, 2, 1, 20}));
		print(findLongestIncreasingSubsequence(new int[]{10, 20, 35, 80}));
		print(findLongestIncreasingSubsequence(new int[]{30, 20, 10}));
		
		/* O/P
			[10, 22, 33, 41, 60, 80]
			[3, 7, 40, 80]
			[3, 10, 20]
			[10, 20, 35, 80]
			[10]
		* */
	}
	
	public static List<Integer> findLongestIncreasingSubsequence(int[] array) {
		int n = array.length;
		int[] dp = new int[n];
		dp[0] = 1;
		for (int i = 1; i < n; i++) {
			int currentItem = array[i];
			int subSequenceLengthSoFar = IntStream.range(0, i)
					                             .filter(operand -> array[operand] < currentItem)
					                             .map(operand -> dp[operand])
					                             .max().orElse(0);
			dp[i] = subSequenceLengthSoFar + 1;
		}
		final int maxLength = IntStream.of(dp).max().orElse(0);// return the max length of LIS
		
		// Collect all increasing order elements as list
		List<Integer> subsequences = new ArrayList<>();
		for (int i = n - 1; i >= 0; i--) {
			int lastIncrementIndex = i;
			if (dp[i] == maxLength) {
				subsequences.add(array[i]);
				// If increasing order length is 1, then return only the 1st element.
				if (maxLength == 1)
					break;
				for (int j = i - 1; j >= 0; j--) {
					if (dp[j] + 1 == dp[lastIncrementIndex]) {
						subsequences.add(array[j]);
						lastIncrementIndex = j;
					}
				}
			}
		}
		subsequences.sort(Integer::compare);
		return subsequences;
	}
	
	private static void print(List<Integer> array) {
		System.out.println(array);
	}
	
}
