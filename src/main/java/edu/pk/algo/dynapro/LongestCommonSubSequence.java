package edu.pk.algo.dynaparo;

import java.util.Arrays;

public class LongestCommonSubSequence {
	public static void main(String[] args) {
		System.out.println(findLongestCommonSubsequence("ABC", "XBC")); // BC
		System.out.println(findLongestCommonSubsequence("AGGTAB", "GXTXAYB")); // GTAB
		System.out.println(findLongestCommonSubsequence("ACADB", "CBDA")); // CA
	}
	
	private static String findLongestCommonSubsequence(String a, String b) {
		int[][] db = new int[a.length() + 1][b.length() + 1];
		char[][] trace = new char[a.length() + 1][b.length() + 1];
		for (char[] chars : trace) {
			Arrays.fill(chars, '\0');
		}
		for (int i = 0; i <= a.length(); i++) {
			for (int j = 0; j <= b.length(); j++) {
				if (i == 0 || j == 0) {
					db[i][j] = 0;
				} else if (a.charAt(i - 1) == b.charAt(j - 1)) {
					db[i][j] = db[i - 1][j - 1] + 1;
					trace[i][j] = '\\';
				} else {
					if (db[i - 1][j] >= db[i][j - 1]) {
						db[i][j] = db[i - 1][j];
						trace[i][j] = '|';
					} else {
						db[i][j] = db[i][j - 1];
						trace[i][j] = '-';
					}
				}
			}
		}
		// Collect common characters from trace table
		StringBuilder sequence = new StringBuilder();
		int i = a.length(), j = b.length();
		while (i >= 0 && j >= 0) {
      // Only trace the diagonal arrow '\'
			if (trace[i][j] == '\\') {
				sequence.insert(0, a.charAt(i - 1));
				i--;
				j--;
			} else if (trace[i][j] == '|') {
				i--;
			} else {
				j--;
			}
		}
		return sequence.toString();
	}
}
