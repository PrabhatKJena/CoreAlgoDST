package edu.pk.algo;

import java.util.HashSet;
import java.util.Set;

public class LongestSubStringWithUniqueChars {
	public static void main(String[] args) {
		System.out.println(longestSubStringWithUniqueChars("abca"));
		System.out.println(longestSubStringWithUniqueChars("abbcab"));
		System.out.println(longestSubStringWithUniqueChars("abcabcd"));
		System.out.println(longestSubStringWithUniqueChars("abcbadab"));
		System.out.println(longestSubStringWithUniqueChars("abcdddc"));
		System.out.println(longestSubStringWithUniqueChars("ab:-Cd-:dlcf"));
	}
	
	static String longestSubStringWithUniqueChars(String s) {
		Set<Character> uniqueChars = new HashSet<>();
		int j = 0;
		int maxLength = 0;
		int start = 0, end = 0;
		for (int i = 0; i < s.length(); ) {
			final char charAt = s.charAt(i);
			if (!uniqueChars.contains(charAt)) {
				uniqueChars.add(charAt);
				i++;
				if (uniqueChars.size() > maxLength) {
					maxLength = uniqueChars.size();
					start = j;
					end = i;
				}
			} else {
				// remove from head until remove the duplicate char
				uniqueChars.remove(s.charAt(j++));
			}
		}
		return s.substring(start, end);
	}
}
