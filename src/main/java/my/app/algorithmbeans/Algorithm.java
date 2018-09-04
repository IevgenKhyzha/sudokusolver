package my.app.algorithmbeans;

import java.util.List;
import java.util.Stack;

public abstract class Algorithm {

    protected int tryCount = 0;
    protected int lapsCount = 0;
    protected int findingValuesCount = 0;

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

    protected boolean findUnambiguousForCell(int[][] sudokuPuzzle, int i, int j) {
        Stack<Integer> possibleValues = findPossibleValuesForCell(sudokuPuzzle, i, j);
        if (possibleValues.size() == 1) {
            findingValuesCount++;
            return true;
        }
        return false;
    }

    protected boolean findUnambiguousForCell(int[][] sudokuPuzzle, int i, int j, List<Integer> possibleValuesList) {
        List<Integer> possibleValues = findPossibleValuesForCell(sudokuPuzzle, i, j, possibleValuesList);
        if (possibleValues.size() == 1) {
            sudokuPuzzle[i][j] = possibleValues.get(0);
            possibleValues.clear();
            findingValuesCount++;
            return true;
        }
        return false;
    }

    protected List<Integer> findPossibleValuesForCell(int[][] sudokuPuzzle, int i, int j, List<Integer> possibleValuesList) {
        possibleValuesList.clear();
        for (int possibleValue = 1; possibleValue <= 9; possibleValue++) {
            tryCount++;
            if (!findInAllPossiblePlaces(possibleValue, sudokuPuzzle, i, j)) {
                possibleValuesList.add(possibleValue);
            }
        }
        return possibleValuesList;
    }

    protected Stack<Integer> findPossibleValuesForCell(int[][] sudokuPuzzle, int i, int j) {
        Stack<Integer> possibleValues = new Stack<Integer>();
        for (int possibleValue = 1; possibleValue <= 9; possibleValue++) {
            tryCount++;
            if (!findInAllPossiblePlaces(possibleValue, sudokuPuzzle, i, j)) {
                possibleValues.push(possibleValue);
            }
        }
        return possibleValues;
    }

    // methods for check puzzle right resolving
    public boolean isAlgorithmSolvedPuzzle(int[][] sudokuArray) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (sudokuArray[i][j] == 0 | checkInAllPossiblePlases(sudokuArray[i][j], sudokuArray, i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean checkInAllPossiblePlases(int findingValue, int[][] sudokuArray, int i, int j) {
        return checkInRow(findingValue, sudokuArray, i, j) | checkInColumn(findingValue, sudokuArray, i, j) | checkInSquare(findingValue, sudokuArray, i, j) ;
    }

    private boolean checkInRow(int searchingValue, int[][] sudokuPuzzle, int rowNumber, int columnNumber) {
        for (int j = 0; j < sudokuPuzzle[rowNumber].length; j++) {
            if (sudokuPuzzle[rowNumber][j] == searchingValue && j != columnNumber) {
                return true;
            }
        }
        return false;
    }

    private boolean checkInColumn(int searchingValue, int[][] sudokuPuzzle, int rowNumber, int columnNumber) {
        for (int i = 0; i < sudokuPuzzle.length; i++) {
            if (sudokuPuzzle[i][columnNumber] == searchingValue && i != rowNumber) {
                return true;
            }
        }
        return false;
    }

    private boolean checkInSquare(int searchingValue, int[][] sudokuPuzzle, int rowNumber, int columnNumber) {
        for (int i = 3 * (rowNumber / 3); i <= 3 * (rowNumber / 3) + 2; i++) {
            for (int j = 3 * (columnNumber / 3); j <= 3 * (columnNumber / 3) + 2; j++) {
                if (sudokuPuzzle[i][j] == searchingValue && i != rowNumber && j != columnNumber) {
                    return true;
                }
            }
        }
        return false;
    }

    protected boolean isTheSameValuePresentInPossiblePlace_checkInAllPuzzle(int[][] sudokuPuzzle) {
        for (int i = 0; i < sudokuPuzzle.length; i++) {
            for (int j = 0; j < sudokuPuzzle[i].length; j++) {
                if (!isPresentInCell(sudokuPuzzle, i, j)) {
                    checkInAllPossiblePlases(sudokuPuzzle[i][j], sudokuPuzzle, i, j);
                }
            }
        }
        return false;
    }

    protected boolean isPresentInCell(int[][] sudokuPuzzle, int i, int j) {
        return sudokuPuzzle[i][j] != 0;
    }
}
