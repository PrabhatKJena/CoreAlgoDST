package edu.pk.algo.sort;

public class HeapSort {
	public static void main(String[] args) {
		int a[] = { 8, -118, 2, -3, 0, 11, 3, 0, -56, 4, -90 };
		print(a);
		heapSort(a, true);
		print(a);
	}

	/**
	 * Modified version of heap sort
	 * @param a is an array of integers
	 * @param isAscending is the order whether ascending or descending
	 */
	public static void heapSort(int[] a, boolean isAscending) {
		heapify(a, isAscending);
		int heapSize = a.length;
		while (heapSize >= 1) {
			swap(a, heapSize - 1, 0);
			heapSize--;
			shiftDown(a, 0, heapSize, isAscending);
		}
	}

	/**
	 * @param a
	 * @param isMax represents whether to build max-heap OR min-heap
	 */
	private static void heapify(int[] a, boolean isMax) {
		int l = a.length;
		/* Start from parent of last leaf node */
		int i = l / 2 - 1;
		while (i >= 0) {
			shiftDown(a, i, a.length, isMax);
			i--;
		}
	}

	private static void shiftDown(int[] a, int i, int heapSize, boolean isMax) {
		int l = 2 * i + 1;
		int r = 2 * i + 2;
		int max = i;
		// If Max-heap, compare child > root
		if (isMax) {
			if (l < heapSize && a[l] > a[i])
				max = l;
			if (r < heapSize && a[r] > a[max])
				max = r;
		}
		// Else if Min-heap, compare child < root
		else {
			if (l < heapSize && a[l] < a[i])
				max = l;
			if (r < heapSize && a[r] < a[max])
				max = r;
		}
		if (i != max) {
			swap(a, i, max);
			shiftDown(a, max, heapSize, isMax);
		}
	}

	private static void swap(int[] a, int i, int max) {
		a[max] = (a[i] + a[max]) - (a[i] = a[max]);
	}

	public static void print(int a[]) {
		for (int i : a) {
			System.out.print(i + "  ");
		}
		System.out.println();
	}
}
