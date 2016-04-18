package edu.pk.algo;
/**
 *  NOT TESTED
 */
public class MaxPallindrom {
	public static void main(String[] args) {
		PalindromeLengthPuzzle();
	}

	public static int PalindromeLengthPuzzle() {
		char[] ch = { 'B', 'A', 'B', 'C', 'D', 'A', 'B' };
		System.out.println(palin(ch, 0, ch.length-1));
		return 0;
	}

	static int palin(char[] str, int i, int j) {
		if (i == j)
			return 1;
		if (str[i] == str[j] && i + 1 == j)
			return 2;
		if (str[i] == str[j])
			return palin(str, i + 1, j - 1) + 2;
		return Math.max(palin(str, i, j - 1), palin(str, i + 1, j));
	}
}