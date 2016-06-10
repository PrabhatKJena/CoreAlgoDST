package edu.pk.algo.sort;

import edu.pk.PKUtil;

import java.util.Stack;

public class QuickSort {
    public static void main(String[] args) {
        int a[] = {1300, 1103, 114, 504, 78, -239, -11, 99, 760};
//        quickSortIterative(a, 0, a.length - 1);
        PKUtil.print(a);
    }

    /**
     * Iterative method
     * Put low and high in stack to work same as recursive
     */
    private  void quickSortIterative(int[] a, int l, int h) {
        if (l < h) {
            Stack<Integer> stack = new Stack<>();
            stack.push(l);
            stack.push(h);
            while (!stack.isEmpty()) {
                h = stack.pop();
                l = stack.pop();
                int p = findPivotIndex(a, l, h);
                if (p - 1 > l) { // If there are elements between pivot and low
                    stack.push(l);
                    stack.push(p - 1);
                }
                if (p + 1 < h) { // If there are elements after pivot and high
                    stack.push(p + 1);
                    stack.push(h);
                }
            }
        }
    }

    /**
     * Recursive method
     */
    public  void quickSortRecursive(int a[], int low, int high) {
        if (low < high) {
            int pivot = findPivotIndex(a, low, high);
            quickSortRecursive(a, low, pivot - 1);
            quickSortRecursive(a, pivot + 1, high);
        }
    }

    private  int findPivotIndex(int[] a, int low, int high) {
        int pivot = a[high];
        int i = low - 1; // elements smaller than pivot will be up to i index
        for (int j = low; j < high; j++) {
            if (a[j] <= pivot) {
                i++;
                if (i != j)
                    swap(a, i, j); // i is the end index of elements smaller than pivot
            }
        }
        swap(a, i + 1, high); // now pivot is the largest among the elements smaller than pivot,
        return i + 1;     // so i+1 is the pivot position
    }

    private static void swap(int[] a, int i, int j) {
        a[i] = (a[i] + a[j]) - (a[j] = a[i]);
    }

}
