import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javafx.util.Pair;

/* Largest Sum Contiguous Subarray */
public class MaxSumSubArray {
	
	public static void main(String[] args) {
		int[] items = {10, -2, 2, -1, 13, 14,};
		final int[] indexes = findNonEmptySubArrayHavingLargestSum(items);
		System.out.println(indexes[0] + " " + indexes[1]);
		final Pair<Integer, Integer> range = findContiguousSubArrayHavingLargestSum(items);
		final String subArray = IntStream.range(range.getKey(), range.getValue() + 1)
				                        .mapToObj(operand -> String.valueOf(items[operand]))
				                        .collect(Collectors.joining(", ", "Sub array = [", "]"));
		System.out.println(subArray);
	}
	
	/**
	 * Using dynamic programming
	 *
	 * @param items given list
	 * @return pair of start and end index of the sub array
	 */
	static Pair<Integer, Integer> findContiguousSubArrayHavingLargestSum(int[] items) {
		int[] dp = new int[items.length];
		int maxSum = items[0];
		int startIndex = 0;
		int endIndex = 0;
		dp[0] = items[0];
		for (int i = 1; i < items.length; i++) {
			if (dp[i - 1] + items[i] < items[i]) {
				dp[i] = items[i];
				startIndex = i;
			} else {
				dp[i] = dp[i - 1] + items[i];
			}
			if (maxSum < dp[i]) {
				maxSum = dp[i];
				endIndex = i;
			}
		}
		return new Pair<>(startIndex, endIndex);
	}
	
	/* Kadane's algo */
	static int findLargestSumOfNonEmptySubArray(int[] array) {
		int maxSum = array[0];
		int curSum = 0;
		for (int n : array) {
			curSum = Math.max(curSum, 0) + n;
			maxSum = Math.max(maxSum, curSum);
		}
		return maxSum;
	}
	
	static int maxSumSubArray(int a[]) {
		int max = a[0];
		int curr_max = a[0];
		for (int i = 1; i < a.length; i++) {
			curr_max += a[i];
			if (curr_max < a[i])//left sub array of the current element(including current element) contains -ve value.
			//So only this current element will be the max sub array
			{
				curr_max = a[i];
			}
			if (curr_max > max) {
				max = curr_max;
			}
		}
		return max;
	}
	
	/* Sliding Window algo */
	static int[] findNonEmptySubArrayHavingLargestSum(int[] array) {
		int maxSum = array[0];
		int curSum = 0;
		int left = 0, right = 0;
		int maxLeft = 0, maxRight = 0;
		for (right = 0; right < array.length; right++) {
			int n = array[right];
			if (curSum < 0) {
				curSum = 0;
				left = right;
			}
			curSum += n;
			if (curSum > maxSum) {
				maxSum = curSum;
				maxLeft = left;
				maxRight = right;
			}
		}
		return new int[]{maxLeft, maxRight};
	}
}
