package my.app.beans;

import java.util.List;

public abstract class Algorithm {

    protected int tryCount = 0;
    protected int lapsCount = 0;
    protected int fidingValuesCount = 0;

    public abstract String getAlgorithmName();

    public abstract int[][] resolvePuzzle(int[][] sudokuPuzzle);

    private boolean findInSquare(int searchingValue, int[][] sudokuPuzzle, int rowNumber, int columnNumber) {
        for (int i = 3 * (rowNumber / 3); i <= 3 * (rowNumber / 3) + 2; i++) {
            for (int j = 3 * (columnNumber / 3); j <= 3 * (columnNumber / 3) + 2; j++) {
                if (sudokuPuzzle[i][j] == searchingValue) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean findInRow(int searchingValue, int[][] sudokuPuzzle, int rowNumber) {
        for (int j = 0; j < sudokuPuzzle[rowNumber].length; j++) {
            if (sudokuPuzzle[rowNumber][j] == searchingValue) {
                return true;
            }
        }
        return false;
    }

    private boolean findInColumn(int searchingValue, int[][] sudokuPuzzle, int columnNumber) {
        for (int i = 0; i < sudokuPuzzle.length; i++) {
            if (sudokuPuzzle[i][columnNumber] == searchingValue) {
                return true;
            }
        }
        return false;
    }

    protected boolean findInAllPossiblePlaces(int searchingValue, int[][] sudokuPuzzle, int rowNumber, int columnNumber) {
        return findInRow(searchingValue, sudokuPuzzle, rowNumber) | findInColumn(searchingValue, sudokuPuzzle, columnNumber) | findInSquare(searchingValue, sudokuPuzzle, rowNumber, columnNumber);
    }

    protected boolean isPresentInCell(int[][] sudokuPuzzle, int i, int j) {
        return sudokuPuzzle[i][j] != 0;
    }

    protected boolean findUnambiguousInCell(int[][] sudokuPuzzle, List<Integer> possibleValuesList, int i, int j) {
        boolean findUnambiguousInCell = false;
        possibleValuesList.clear();
        for (int possibleValue = 1; possibleValue <= 9; possibleValue++) {
            tryCount++;
            if (!findInAllPossiblePlaces(possibleValue, sudokuPuzzle, i, j)) {
                possibleValuesList.add(possibleValue);
            }
        }
        if (possibleValuesList.size() == 1) {
            sudokuPuzzle[i][j] = possibleValuesList.get(0);
            findUnambiguousInCell = true;
            possibleValuesList.clear();
            fidingValuesCount++;
        }
        return findUnambiguousInCell;
    }

    public boolean isAlgorithmSolvedPuzzle(int[][] sudokuArray) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (sudokuArray[i][j] == 0 | checkValueInOtherPossiblePlases(sudokuArray[i][j], sudokuArray, i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean checkValueInOtherPossiblePlases(int findingValue, int[][] sudokuArray, int i, int j) {
        // TODO
        return false;
    }

    protected boolean isTheSameValuePresentInPossiblePlace_checkInAllPuzzle(int[][] sudokuPuzzle) {
        for (int i = 0; i < sudokuPuzzle.length; i++) {
            for (int j = 0; j < sudokuPuzzle[i].length; j++) {
                if (!isPresentInCell(sudokuPuzzle, i, j)) {
                    checkValueInOtherPossiblePlases(sudokuPuzzle[i][j], sudokuPuzzle, i, j);
                }
            }
        }

        return true;
    }
}
