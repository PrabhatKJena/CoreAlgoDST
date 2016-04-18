package edu.pk.algo;

import java.util.HashSet;
/* Given an string and a dictionary of words, find out if the input string can be broken into
   a space-separated sequence of one or more dictionary words. */
public class WordBreakProblem {
	public static void main(String[] args) {
		String str = "thenumberare";
		breakWord(str, getDictionary());

	}

	private static void breakWord(String str, HashSet<String> dictionary) {
		if (!findWord(str, dictionary, ""))
			System.out.println("Cant break");
	}

	private static boolean findWord(String str, HashSet<String> dictionary, String result) {
		// If there is no more character in inout string then just print the result
		if (str == null || str.length() == 0) {
			System.out.println(result);
			return true;
		}

		int i = 0;
		String word = "";
		while (i < str.length()) {
			word = word + str.charAt(i);
			if (dictionary.contains(word)) {
				if (findWord(str.substring(i + 1), dictionary, result + word + " ")) {
					return true;
				} 
			} 
			i++;
		}
		return false;
	}

	private static HashSet<String> getDictionary() {
		HashSet<String> dictionary = new HashSet<>();
		dictionary.add("the");dictionary.add("then");dictionary.add("number");dictionary.add("is");dictionary.add("even");
		dictionary.add("an");dictionary.add("a");dictionary.add("not");dictionary.add("um");dictionary.add("are");
		return dictionary;
	}
}
