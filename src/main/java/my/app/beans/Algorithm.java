package my.app.beans;

public abstract class Algorithm {

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
}
