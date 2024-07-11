package edu.pk.algo.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Partition of a set into K subsets with equal sum. (Using backtracking algo.)
 * Given {2, 1, 4, 5, 6}
 * Subsets that sum to 6: [2, 4]   [1, 5]   [6]   true
 * Subsets that sum to 9: [2, 1, 6]   [4, 5]   true
 * Subsets that sum to 4: [] false
 */
public class KPartitionWithEqualSum {

  public static List<List<Integer>> findSubsetsThatSumToK(int[] nums, int K) {
    List<List<Integer>> result = new ArrayList<>();
    findSubsetsThatSumToK(nums, K, 0, new ArrayList<>(), result);
    return result;
  }

  /* Using Backtracking Algo */
  private static void findSubsetsThatSumToK(int[] nums, int target, int index, List<Integer> currentSubset, List<List<Integer>> result) {
    if (target == 0) {
      result.add(new ArrayList<>(currentSubset));
      return;
    }

    if (target < 0 || index >= nums.length) {
      return;
    }

    // Include the current element
    currentSubset.add(nums[index]);
    findSubsetsThatSumToK(nums, target - nums[index], index + 1, currentSubset, result);

    // Exclude the current element
    currentSubset.remove(currentSubset.size() - 1);
    findSubsetsThatSumToK(nums, target, index + 1, currentSubset, result);
  }

  static boolean isKPartitionWithEqualSumPossible(int[] nums, int partitionCount) {
    final int sum = Arrays.stream(nums).sum();
    if (partitionCount == 1) {
      return true;
    }
    if (sum % partitionCount != 0) {
      return false;
    }
    int K = sum / partitionCount;
    List<List<Integer>> subsets = findSubsetsThatSumToK(nums, K);
    System.out.print("Subsets that sum to " + K + ": ");
    for (List<Integer> subset : subsets) {
      System.out.print(subset + "   ");
    }
    final long count = subsets.stream().mapToLong(Collection::size).sum();
    return count == nums.length;
  }

  public static void main(String[] args) {
    int[] nums = {2, 1, 4, 5, 6};
    System.out.println(isKPartitionWithEqualSumPossible(nums, 3));
    System.out.println(isKPartitionWithEqualSumPossible(nums, 2));
    System.out.println(isKPartitionWithEqualSumPossible(nums, 4));
  }
}
