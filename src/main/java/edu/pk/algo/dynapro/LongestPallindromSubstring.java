package edu.pk.algo.dynapro;

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
 */
public class LongestPallindromSubstring {
	public static void main(String[] args) {
		String str = "abcdmalayalamb";
		char[] chars = str.toCharArray();
		int len = chars.length;
		Boolean s[][] = new Boolean[len][len];
		for (int i = 0; i < len; i++) {
			s[i][i] = true;
			if (i < len - 1)
				if (chars[i] == chars[i + 1])
					s[i][i + 1] = true;
				else
					s[i][i + 1] = false;
		}

		int pallindromStartIndex = -1;
		int pallindromEndIndex = 0;
		for (int l = 2; l < len; l++) {
			for (int r = 0; r < len - l; r++) {
				int c = r + l;
				s[r][c] = (s[r + 1][c - 1] && chars[r] == chars[c]);
				if (s[r][c]) {
					pallindromStartIndex = r;
					pallindromEndIndex = c;
				}
			}
		}
		print(chars, s, pallindromStartIndex);
		System.out.println("\nLongest Pallindrom Substring : " + str.substring(pallindromStartIndex, pallindromEndIndex + 1));
	}

	private static void print(char[] chars, Boolean a[][], int start) {
		System.out.println("Dynamic Values : \n");
		for (char c : chars) {
			System.out.printf("%3s", c);
		}
		System.out.println();
		for (int i = 0; i < chars.length; i++) {
			System.out.printf("%s", "___");
		}
		System.out.println();
		for (int i = 0; i < a.length; i++) {
			System.out.print(chars[i] + "|");
			for (int j = 0; j < a[i].length; j++) {
				System.out.print((a[i][j] == null ? " " : (a[i][j] == true ? 1 : 0)) + "  ");
			}
			if (i == start) {
				System.out.print(" <--- LPS ");
			}
			System.out.println();
		}

	}
}
