package edu.pk.algo;

import java.util.ArrayList;
import java.util.Arrays;

public class MaxOverllapingInterval {
	public static void main(String[] args) {
		Integer intervals[][] = { { 0, 2 }, { 3, 7 }, { 4, 6 }, { 7, 8 }, { 1, 5 } };
		int mx = findMaxOverlaps(intervals);
		System.out.println(mx);
	}

	private static int findMaxOverlaps(Integer[][] intervals) {
		Integer start[] = new Integer[intervals.length];
		Integer end[] = new Integer[intervals.length];
		int i = 0;
		for (Integer[] interval : intervals) {
			start[i] = interval[0];
			end[i] = interval[1];
			i++;
		}
		System.out.println(Arrays.asList(start));
		System.out.println(Arrays.asList(end));
		Arrays.sort(start);
		Arrays.sort(end);
		i = 0;
		int j = 0;
		int cur_overlap = 0;
		int max_overlap = 0;
		while (i < start.length && j < end.length) {
			if (start[i] < end[j]) {
				cur_overlap++;
				max_overlap = Math.max(cur_overlap, max_overlap);
				i++;
			} else {
				cur_overlap--;
				j++;
			}
		}
		return max_overlap;
	}
}