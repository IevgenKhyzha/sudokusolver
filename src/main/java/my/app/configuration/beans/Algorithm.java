package my.app.configuration.beans;

public abstract class Algorithm {

    public abstract int[][] resolvePuzzle(int[][] sudokuPuzzle);

    private boolean findIn3Square(int searchingValue, int[][] sudokuPuzzle, int lineNumber, int rowNumber) {
        for (int i = 3 * (lineNumber / 3); i <= 3 * (lineNumber / 3) + 2; i++) {
            for (int j = 3 * (rowNumber / 3); j <= 3 * (rowNumber / 3) + 2; j++) {
                if (sudokuPuzzle[i][j] == searchingValue) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean findInLine(int searchingValue, int[][] sudokuPuzzle, int lineNumber) {
        for (int j = 0; j < sudokuPuzzle[lineNumber].length; j++) {
            if (sudokuPuzzle[lineNumber][j] == searchingValue) {
                return true;
            }
        }
        return false;
    }

    private boolean findInColumn(int searchingValue, int[][] sudokuPuzzle, int rowNumber) {
        for (int i = 0; i < sudokuPuzzle.length; i++) {
            if (sudokuPuzzle[i][rowNumber] == searchingValue) {
                return true;
            }
        }
        return false;
    }

    protected boolean findEverywthere(int searchingValue, int[][] sudokuPuzzle, int lineNumber, int rowNumber) {
        return findInLine(searchingValue, sudokuPuzzle, lineNumber) | findInColumn(searchingValue, sudokuPuzzle, rowNumber) | findIn3Square(searchingValue, sudokuPuzzle, lineNumber, rowNumber);
    }

    protected boolean isPresentInCell(int[][] sudokuPuzzle, int i, int j) {
        return sudokuPuzzle[i][j] != 0;
    }
}
