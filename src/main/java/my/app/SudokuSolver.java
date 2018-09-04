package my.app;

import my.app.algorithmbeans.Algorithm;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class SudokuSolver {

    SudokuSolver(String inFilePath, String outFilePath, Algorithm algorithm) {
        FileUtils fileUtils = new FileUtils(inFilePath, outFilePath);
        int[][] sudokuInArray = fileUtils.fromASCIIFileToArray();
        printArray(sudokuInArray);
        Date dateBegin = new Date();
        int[][] resolvedPuzzle = algorithm.resolvePuzzle(sudokuInArray);
        Date dateEnd = new Date();
        System.out.println("Puzzle was " +  printIsResolved(algorithm.isAlgorithmSolvedPuzzle(resolvedPuzzle)) + " by " + algorithm.getAlgorithmName() + " algorithm");
        printTime(dateBegin, dateEnd);
        printArray(resolvedPuzzle);
        fileUtils.createOutPutFile(resolvedPuzzle);
    }

    private void printArray(int[][] sudokuArray) {
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

    private void printTime(Date dateBegin, Date dateEnd) {
        long milissecondsTime = dateEnd.getTime() - dateBegin.getTime();
        System.out.println("Resolving time in seconds: " + TimeUnit.MILLISECONDS.toSeconds(milissecondsTime));
        System.out.println("Resolving time in milliseconds: " + milissecondsTime);
    }

    private String printIsResolved(boolean isResolved) {
        return isResolved ? "resolved" : "not resolved";
    }
}
