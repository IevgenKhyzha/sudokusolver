package my.app.pojo;

import java.util.Stack;

public class PuzzleStateObject {

    private int[][] sudokuPuzzle;
    private int[] cellCoordinates;
    private Stack possibleValues;

    public PuzzleStateObject(int[][] sudokuPuzzle, int[] cellCoordinates, Stack possibleValuesList) {
        this.sudokuPuzzle = sudokuPuzzle;
        this.cellCoordinates = cellCoordinates;
        this.possibleValues = possibleValuesList;
    }

    public int[][] getSudokuPuzzle() {
        return sudokuPuzzle;
    }

    public int[] getCellCoordinates() {
        return cellCoordinates;
    }

    public Stack getPossibleValues() {
        return possibleValues;
    }
}
