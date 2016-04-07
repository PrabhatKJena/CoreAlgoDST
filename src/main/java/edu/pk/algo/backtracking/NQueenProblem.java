package edu.pk.algo.backtracking;
/**
 *  N Queen Problem using Backtracking
 */
public class NQueenProblem {
    public static void main(String[] args) {
        int n = 4;
        solveNQ(n);
    }

    private static void solveNQ(int n) {
        int q[][] = new int[n][n];
        if (placeQueen(q, 0))
            print(q);
        else
            System.out.println("Can not be Solved.");
    }

    private static void print(int[][] q) {
        for (int[] r : q) {
            for (int c : r) {
                if (c == 0)
                    System.out.print("[ ]");
                else
                    System.out.print("[X]");
            }
            System.out.println();
        }
    }

    private static boolean placeQueen(int[][] q, int col) {
        /* If all column processed */
        if (col >= q.length) {
            System.out.println("All Queens Placed");
            return true;
        }
        /* Checking which row is safe for the current column to place the queen */
        for (int r = 0; r < q.length; r++) {
            /* Place a queen in safe row for the current column, then check for the next queen for next column */
            if (isSafe(q, r, col)) {
                q[r][col] = 1;
                /* Consider the current row as part of the solution and proceed with next queen for next column
                   and check for the solution */
                boolean result = placeQueen(q, col + 1);

                /* If Not reached at the goal(solution), then revert back the previous considered solution
                   and proceed with the next row for the current column */
                if (!result) {
                    q[r][col] = 0;
                } else {
                    return true;
                }
            }
        }
        return false;
    }

    /* Since we are filling column wise, so no need to check for the column as
       the queens are places only up to col-1 columns.
     */
    private static boolean isSafe(int[][] q, int r, int col) {
        // check only left of same row
        for (int j = 0; j < col; j++) {
            if (q[r][j] == 1)
                return false;
        }
        // check upper diagonal in left side ony
        for (int i = r - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (q[i][j] == 1)
                return false;
        }
        // check lower diagonal in left side ony
        for (int i = r + 1, j = col - 1; i < q.length && j >= 0; i++, j--) {
            if (q[i][j] == 1)
                return false;
        }
        return true;
    }
}
