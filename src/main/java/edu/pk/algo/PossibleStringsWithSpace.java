package edu.pk.algo;

/*
 * Given a string you need to print all possible strings that can be made by placing spaces (zero or one) in between them.

 Input:  str[] = "ABC"
 Output: ABC
 AB C
 A BC
 A B C
 */
public class PossibleStringsWithSpace {
	public static void main(String[] args) {
		String str = "Alpha";
		printAllPossible(str);
	}

	private static void printAllPossible(String str) {
		char ch[] = str.toCharArray();
		char buf[] = new char[(2 * ch.length) - 1];
		buf[0] = ch[0];
		printPatterns(ch, buf, 1, 1, ch.length - 1);
	}

	private static void printPatterns(char[] ch, char[] buf, int i, int j, int length) {
		if (i == length) {
			System.out.println(String.valueOf(buf));
		}

		buf[j] = ch[i];
		printPatterns(ch, buf, i + 1, j + 1, length);

		buf[j] = ' ';
		buf[j + 1] = ch[i];
		printPatterns(ch, buf, i + 1, j + 2, length);
	}
}
