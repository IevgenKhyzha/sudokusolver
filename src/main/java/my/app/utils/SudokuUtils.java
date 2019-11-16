package my.app.utils;

import my.app.pojo.Cell;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class SudokuUtils {

    public static void printPuzzle(int[][] sudokuArray) {
        String line = "+-------+-------+-------+";
        for (int i = 0; i < 9; i++) {
            if (i % 3 == 0) {
                System.out.println(line);
            }
            for (int j = 0; j < 9; j++) {
                if (j % 3 == 0) {
                    System.out.print("| ");
                }
                if (sudokuArray[i][j] != 0) {
                    System.out.print(sudokuArray[i][j] + " ");
                } else {
                    System.out.print("  ");
                }
            }
            System.out.print("|");
            System.out.println();
        }
        System.out.println(line);
    }

    public static void printResult(String algorithmName, boolean isResolvedPuzzle,
                                   LocalTime dateBegin, LocalTime dateEnd, int[][] sudokuArray) {
        System.out.println("Puzzle was " +  (isResolvedPuzzle ? "resolved" : "not resolved") + " by " + algorithmName +
                " algorithm");
        printDurationOfCalculation(dateBegin, dateEnd);
        printPuzzle(sudokuArray);
    }

    public static void printDurationOfCalculation(LocalTime dateBegin, LocalTime dateEnd) {
        System.out.println("Resolving time in seconds: " + dateBegin.until(dateEnd, ChronoUnit.SECONDS));
        System.out.println("Resolving time in milliseconds: " + dateBegin.until(dateEnd, ChronoUnit.MILLIS));
    }

    public static Cell[][] transformNumberArrayToCellArray(int[][] unresolvedPuzzle, int[][] resolvedPuzzle) {
        Cell[][] cells = new Cell[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                cells[i][j] = new Cell(resolvedPuzzle[i][j], unresolvedPuzzle[i][j] != 0);
            }
        }
        return cells;
    }
}
