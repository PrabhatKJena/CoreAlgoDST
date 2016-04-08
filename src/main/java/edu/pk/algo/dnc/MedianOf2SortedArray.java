package edu.pk.algo.dnc;
/**
 * There are 2 sorted arrays A and B. Write an algorithm to find the median of the array
 * obtained after merging the above 2 arrays. The complexity should be O(log(n))
 */
public class MedianOf2SortedArray {
    public static void main(String[] args) {
        int ar1[] = {1, 12, 15, 26, 38};
        int ar2[] = {2, 13, 16};
        int median = findMedian(ar1, 0, ar1.length - 1, ar2, 0, ar2.length - 1);
        System.out.println(median);
    }

    private static int findMedian(int[] ar1, int i1, int n1, int[] ar2, int i2, int n2) {
        int len1 = n1 - i1 + 1;
        int len2 = n2 - i2 + 1;
        /* Base Conditions - START */
        if (len1 <= 0 && len2 <= 0)
            return -1;
        if (len1 == 1 && len2 <= 0)
            return ar1[0];
        if (len1 <= 0 && len2 == 1)
            return ar2[0];
        if (len1 == 1 && len2 == 1)
            return (ar1[0] + ar2[0]) / 2;
        /* Base Conditions - START */

        Median m1 = median(ar1, i1, n1);
        Median m2 = median(ar2, i2, n2);
        /*
         If both median same, then m1 or m2 will be the median after merge
         1 5 7  AND 2 5 9   => m1 = 5, m2 = 5
         1 2 5 5 7 9  => m=5
        */
        if (m1.val == m2.val) {
            return m1.val;
        }
        // if m1 < m2 then median must exist in ar1[m1....] and ar2[....m2]
        if (m1.val < m2.val) {
            return findMedian(ar1, m1.index, n1, ar2, i2, m2.index);
        } else { /* if m1 > m2 then median must exist in ar1[....m1] and ar2[m2...] */
            return findMedian(ar1, i1, m1.index, ar2, m2.index, n2);
        }
    }
    /**
     * Finding median of an array within the start and end range
     */
    private static Median median(int[] ar, int start, int end) {
        int diff = (end - start + 1);
        if (diff % 2 == 0) {
            int mi1 = diff / 2 + start - 1;
            int mi2 = diff / 2 + start;
            return new Median(mi1, (ar[mi1] + ar[mi2]) / 2);
        }
        return new Median(diff / 2 + start, ar[diff / 2 + start]);
    }

    static class Median {
        int val;
        int index;

        public Median(int index, int val) {
            this.val = val;
            this.index = index;
        }

        @Override
        public String toString() {
            return "Median{" +
                    "val=" + val +
                    ", index=" + index +
                    '}';
        }
    }

}
