package edu.pk.algo;

public class NextLexicographicalString {
	
	public static void main(String[] args) {
		System.out.println(nextLexicographicalString("abz"));
		System.out.println(nextLexicographicalString("azz"));
		System.out.println(nextLexicographicalString("xzz"));
		System.out.println(nextLexicographicalString("yzz"));
		System.out.println(nextLexicographicalString("yzz"));
		System.out.println(nextLexicographicalString("zzy"));
		System.out.println(nextLexicographicalString("zzz"));
	}
	
	static String nextLexicographicalString(String input) {
		if (input == null || input.isEmpty()) {
			return "a";
		}
		final char[] array = input.toCharArray();
		int i = 0;
		for (; i < array.length; i++) {
			if (array[i] != 'z') {
				break;
			}
		}
		if (i == array.length) {
			return input + "a";
		}
		
		final int carry = 1;
		for (i = array.length - 1; i >= 0; i--) {
			if (array[i] + carry <= 'z') {
				array[i] = (char) (array[i] + carry);
				break;
			} else {
				array[i] = (char) ((array[i] + carry) % 122 + 96);
			}
		}
		return String.valueOf(array);
	}
	
}

