package edu.pk;

public class EggDropProblem {
    public static void main(String[] args) {
        int c = minEggDrop(2, 6);
        System.out.println(c);
    }

    private static int minEggDrop(int eggs, int floors) {
        int a[][] = new int[eggs + 1][floors + 1];
        for (int i = 0; i <= eggs; i++) { // for each egg
            for (int j = 0; j <= floors; j++) { // for each building
                if (i == 0 || j == 0) // if no of eggs is 0 OR no of floors is 0 then 0 drops
                    a[i][j] = 0;
                else if (i == 1) {// if no of eggs is 1 then maximum j drops required in worst case
                    a[i][j] = j;
                } else {
                    if (i > j) // if eggs are more than floors then answer will be same as when no of eggs = i-1
                        a[i][j] = a[i - 1][j];
                    else {
                        int min = Integer.MAX_VALUE;
                        // find minimum of drops of all floors
                        for (int k = 2; k <= j; k++) { // find max of drops for each floor from 2nd floor when egg breaks
                                                            // and doesn't breaks
                            int drops = 1 + Math.max(a[i - 1][k - 1]/* when breaks then no of drops for 1 less egg and 1 less kth floor */
                                    ,a[i][j - k]);  /* if doesnt break then same no of eggs but floors-k no of floors */
                            if (min > drops)
                                min = drops;
                        }
                        a[i][j] = min;
                    }
                }
            }
        }
        return a[eggs][floors];
    }
}
