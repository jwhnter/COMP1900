/**
 * Joshua Hunter
 * Lab 7 HW
 * Section 103
 */
public class Sudoku {
  public static void main(String[] args) {
    int[][] solvedPuzzle = {
        {5,3,4,6,7,8,9,1,2},
        {6,7,2,1,9,5,3,4,8},
        {1,9,8,3,4,2,5,6,7},
        {8,5,9,7,6,1,4,2,3},
        {4,2,6,8,5,3,7,9,1},
        {7,1,3,9,2,4,8,5,6},
        {9,6,1,5,3,7,2,8,4},
        {2,8,7,4,1,9,6,3,5},
        {3,4,5,2,8,6,1,7,9}
    };

    if (isValidSolution(solvedPuzzle)) {
      System.out.println("Your solution is valid!");
    } else {
      System.out.println("Your solution is wrong!");
    }
  }

  /**
   * The prompt said to write a function with this exact name and parameter,
   * so I did even though it's a bit redundant...
   */
  public static boolean isValidSolution(int[][] solution) {
    return (checkRows(solution) && checkCols(solution) && checkCells(solution));
  }

  public static boolean checkRows(int[][] solution) {
    for (int i = 0; i < solution.length; i++) {
      // get a row from the solution
      int[] row = new int[solution[i].length];
      for (int j = 0; j < row.length; j++) {
        row[j] = solution[i][j];
      }
      // check whether this row uses each number once
      for (int k = 1; k <= 9; k++) {
        if (linearSearch(row, k) != 1) {
          return false;
        }
      }
    }
    return true;
  }

  public static boolean checkCols(int[][] solution) {
    for (int i = 0; i < solution[0].length; i++) {
      // get a column from the solution
      int[] col = new int[solution.length];
      for (int j = 0; j < col.length; j++) {
        col[j] = solution[j][i];
      }
      // check whether this column uses each number once
      for (int k = 1; k <= 9; k++) {
        if (linearSearch(col, k) != 1) {
          return false;
        }
      }
    }
    return true;
    }

  public static boolean checkCells(int[][] solution) {
    // start at each new cell
    for (int i = 0; i <= 6; i = i + 3) {
      for (int j = 0; j <= 6; j = j + 3) {
        // create a list from inside each cell
        int index = 0;
        int[] cell = new int[solution.length];
        for (int cellRow = i; cellRow < i + 3; cellRow++) {
          for (int cellCol = j; cellCol < j + 3; cellCol++) {
            cell[index] = solution[cellRow][cellCol];
            index++;
          }
        }
        for (int k = 1; k <= 9; k++) {
          if (linearSearch(cell, k) != 1) {
            return false;
          }
        }
      }
    }
    return true;
  }

  /**
   * Counts the occurrences of an integer in a list
   */
  public static int linearSearch(int[] list, int key) {
    int count = 0;
    for (int i = 0; i < list.length; i++) {
      if (list[i] == key) {
        count++;
      }
    }
    return count;
  }
}
