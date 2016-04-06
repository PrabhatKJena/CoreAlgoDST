package edu.pk.algo;

/**
 * Given a string ‘str’ of digits, find length of the longest substring of
 * ‘str’, such that the length of the substring is 2k digits and sum of left k
 * digits is equal to the sum of right k digits.
 * 
 * T(n) = O(n^2) S(n) = O(1)
 * 
 */
public class LongestSubstringHaving2EqualSum {
	public static void main(String[] args) {
		String s = "125344";
		int res[] = findLength(s);
		if (res[0] > 0) {
			System.out.println("Max Length :" + res[0]);
			System.out.println("String :" + s.substring(res[1], res[1] + res[0]));
		}else{
			System.out.println("No Such Strig");
		}
	}

	private static int[] findLength(String s) {
		int n = s.length();
		int maxLen = 0;
		int start = -1;
		/*
		 * Consider i and i+1 as midpoint, expanding into both side and check
		 * leftSum==rightSum then update the result (maxLength, start and end)
		 */
		for (int i = 0; i < n - 1; i++) {
			int left = i;
			int right = i + 1;
			int lsum = 0;
			int rsum = 0;
			while (left >= 0 && right < n) {
				lsum += s.charAt(left) - '0';
				rsum += s.charAt(right) - '0';
				if (lsum == rsum) {
					if (right - left + 1 > maxLen) {
						maxLen = right - left + 1;
						start = left;
					}
				}
				left--;
				right++;
			}
		}
		return new int[] { maxLen, start };
	}
}
