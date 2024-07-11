package edu.pk.algo.dynapro;

import edu.pk.PKUtil;
import edu.pk.Util;

/**
 * Find Longest Pallindrom Substring using Dynamic Programming
 * 
 * P[i][j]  --> Si......Sj
 * Base Case :
 * 		P[i][i] = true
 * 		P[i][i+1] = true iff S(i) == S(i+1)
 *		
 * Therefore : 
 *		P[i][j] = true iff ( P[i+1][j-1] == true && S(i) == S(j) )
 *		// i.e   if abcba is the result iff bcb is the previous result and a==a
 */
public class LongestPallindromSubstring {
	public static void main(String[] args) {
		String s = "XAABADAABAAY";
		System.out.println(longestPalindromLength(s.toCharArray(), 0, s.length() - 1));
		final Pair<String, Integer> pair = longestPalindromByDP(s);
		System.out.println("Max pallindrom length = " + pair.getValue());
		System.out.println("Longest pallindrom string = " + pair.getKey());
	}
	
	/* Dynamic programming approach */
	private static Pair<String, Integer> longestPalindromByDP(String str) {
		int pallindromLength = 1;
		int pallindromStartIndex = 0;
		final char[] array = str.toCharArray();
		boolean[][] dp = new boolean[array.length][array.length];
		// fill diagonal
		for (int i = 0; i < array.length; i++) {
			dp[i][i] = true;
		}
		// fill (0,1) (1,2) (2,3) adjacent entries for substring length = 2
		for (int i = 1; i < array.length - 1; i++) {
			if (array[i] == array[i - 1]) {
				dp[i - 1][i] = true;
				pallindromLength = 2;
			}
		}
		// fill for length 3 to string length
		for (int len = 2; len < array.length; len++) {
			for (int i = 0; i < array.length - len; i++) {
				if (array[i] == array[i + len] && dp[i + 1][i + len - 1]) {
					dp[i][i + len] = true;
					pallindromLength = len + 1;
					pallindromStartIndex = i;
				}
			}
		}
		return new Pair<>(str.substring(pallindromStartIndex, pallindromStartIndex + pallindromLength), pallindromLength);
	}
	
	/* Recursion method */
	public static int longestPalindromLength(char[] str, int i, int j) {
		if (i == j) {
			return 1;
		}
		if (str[i] == str[j] && i + 1 == j) {
			return 2;
		}
		int l = 0;
		if (str[i] == str[j] && (l = isPallindrom(str, i + 1, j - 1)) > 0) {
			return l + 2;
		}
		return Math.max(longestPalindromLength(str, i, j - 1), longestPalindromLength(str, i + 1, j));
	}
	
	private static int isPallindrom(char[] str, int i, int j) {
		int l = j - i + 1;
		while (i <= j) {
			if (str[i] != str[j]) {
				return 0;
			}
			i++;
			j--;
		}
		return l;
	}
	
}
