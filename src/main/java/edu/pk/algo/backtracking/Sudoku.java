package edu.pk.algo.backtracking;

public class Sudoku {
    public static void main(String[] args) {
        int n = 9;
           /* var puzzleArray =  [ "  1| 7 |  3",  // Unsolved
             "   | 6 |   ",
             "   |9  |   ",

             "   |   |7  ",
             "92 | 5 | 81",
             "  3|   |   ",

             "   |  1|   ",
             "   | 4 |   ",
             "7  | 3 |  9"
             ];*/
        int grid[][] = {{0, 0, 1, 0, 7, 0, 0, 0, 3},
                {0, 0, 0, 0, 6, 0, 0, 0, 0},
                {0, 0, 0, 9, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 7, 0, 0},
                {9, 2, 0, 0, 5, 0, 0, 8, 1},
                {0, 0, 3, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 1, 0, 0, 0},
                {0, 0, 0, 0, 4, 0, 0, 0, 0},
                {7, 0, 0, 0, 3, 0, 0, 0, 9}};
        if (solveSudoku(grid) == true)
            printGrid(grid);
        else
            System.out.println("No solution exists");
    }

    private static boolean solveSudoku(int[][] grid) {
        Loc loc = findEmptyLoc(grid);
        if (loc == null) {
            return true;
        }
        int r = loc.r;
        int c = loc.c;
        for (int num = 1; num <= 9; num++) {
            if (isSafe(grid, r, c, num)) {
                grid[r][c] = num;
                if (solveSudoku(grid))
                    return true;
                grid[r][c] = 0;
            }
        }
        return false;
    }

    private static boolean usedInRow(int grid[][], int row, int num) {
        for (int col = 0; col < grid.length; col++)
            if (grid[row][col] == num)
                return true;
        return false;
    }

    private static boolean usedInCol(int grid[][], int col, int num) {
        for (int row = 0; row < grid.length; row++)
            if (grid[row][col] == num)
                return true;
        return false;
    }

    private static boolean usedInBox(int grid[][], int boxStartRow, int boxStartCol, int num) {
        for (int row = 0; row < 3; row++)
            for (int col = 0; col < 3; col++)
                if (grid[row + boxStartRow][col + boxStartCol] == num)
                    return true;
        return false;
    }

    private static boolean isSafe(int[][] grid, int row, int col, int num) {
        return !usedInRow(grid, row, num) &&
                !usedInCol(grid, col, num) &&
                !usedInBox(grid, row - row % 3, col - col % 3, num);
    }

    private static Loc findEmptyLoc(int[][] grid) {
        for (int row = 0; row < grid.length; row++)
            for (int col = 0; col < grid.length; col++)
                if (grid[row][col] == 0)
                    return new Loc(row, col);
        return null;
    }

    private static void printGrid(int grid[][]) {
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid.length; col++)
                System.out.printf("%2d", grid[row][col]);
            System.out.println();
        }
    }

    private static class Loc {
        int r;
        int c;

        public Loc(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

}
