package edu.pk.algo;

public class FindSubArrayWithMaxSum {
	public static void main(String[] args) {
		int[] arr = new int[] { 2, 3, -6, 10, -1, 2 };
		System.out.println(maxSumSubArray(arr, 0, arr.length - 1));
	}

	public static SubArray maxSumSubArray(int[] arr, int start, int end) {
		if (start == end) {
			return SubArray.create(arr, start, end, arr[start]);
		}
		int mid = (start + end) / 2;
		// Find Left Sub Array with Max Sum
		SubArray leftSubArray = maxSumSubArray(arr, start, mid); 
		// Find right Sub array with Max Sum
		SubArray rightSubArray = maxSumSubArray(arr, mid + 1, end); 
		// Find Sub array having element from left and right sub array crossing the mid with max sum
		// i.e Contiguous sub array having some portion from each side
		SubArray crossingSubArray = maxSumCrossingArray(arr, start, end, mid); 
		
		if (leftSubArray.sum >= rightSubArray.sum && leftSubArray.sum >= crossingSubArray.sum) {
			return leftSubArray;
		} else if (rightSubArray.sum >= leftSubArray.sum && rightSubArray.sum >= crossingSubArray.sum) {
			return rightSubArray;
		} else
			return crossingSubArray;
	}

	/* Find the crossing sub srray with max sum */
	private static SubArray maxSumCrossingArray(int[] arr, int start, int end, int mid) {
		int leftSum = Integer.MIN_VALUE;
		int low = mid;
		int sum = 0;
		for (int i = mid; i >= start; i--) {
			sum += arr[i];
			if (sum > leftSum) {
				leftSum = sum;
				low = i;
			}
		}
		int rightSum = Integer.MIN_VALUE;
		int high = mid + 1;
		sum = 0;
		for (int i = mid + 1; i <= end; i++) {
			sum += arr[i];
			if (sum > rightSum) {
				rightSum = sum;
				high = i;
			}
		}
		return SubArray.create(arr, low, high, leftSum + rightSum);
	}

	private static class SubArray {
		private int start;
		private int end;
		private int sum;
		private int[] arr;

		static SubArray create(int[] arr, int s, int e, int sum) {
			return new SubArray(arr, s, e, sum);
		}

		SubArray(int[] arr, int start, int end, int sum) {
			this.arr = arr;
			this.start = start;
			this.end = end;
			this.sum = sum;
		}

		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append("start:" + start + ", end:" + end + ", sum:" + sum + ", Sub Array :[");
			for (int i = start; i <= end; i++) {
				sb.append(arr[i] + ",");
			}
			int li = sb.lastIndexOf(",");
			if (li > -1)
				sb.deleteCharAt(li);
			sb.append("]");
			return sb.toString();
		}
	}
}
