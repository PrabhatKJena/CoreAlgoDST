package edu.pk.algo.backtracking;

import java.util.ArrayList;

/**
 * Find all subsets of an int array whose sums equal a given target
 */
public class FindAllSubsetSum {
    public static void main(String[] args) {
        int weights[] = {10, 7, 5, 18, 12, 20, 15};
        target = 35;
        findAllSubset(weights, new ArrayList<Integer>(), 0);
    }

    static int sumOfSub = 0;
    static int target = 0;

    private static void findAllSubset(int[] arr, ArrayList<Integer> sub, int startIndex) {
        if (sumOfSub == target) { // if found the goal print the list
            System.out.println(sub);
            return;
        } else if (sumOfSub > target) { // if sum > target then don't proceed
            return;
        }
        for (int i = startIndex; i < arr.length; i++) {// for each element from startIndex onwards
            sub.add(arr[i]);  // add current element into solution list
            sumOfSub += arr[i];
            findAllSubset(arr, sub, i + 1);
            sumOfSub -= sub.remove(sub.size() - 1); // remove last;
        }
    }

}
