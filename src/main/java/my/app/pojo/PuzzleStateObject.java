package my.app.pojo;

import java.util.Stack;

public class PuzzleStateObject {

    private int[][] sudokuPuzzle;
    private Stack<Integer> possibleValues;
    private int ii;
    private int jj;

    public PuzzleStateObject(int[][] sudokuPuzzle, Stack<Integer> possibleValues, int ii, int jj) {
        this.sudokuPuzzle = sudokuPuzzle;
        this.possibleValues = possibleValues;
        this.ii = ii;
        this.jj = jj;
    }

    public int[][] getSudokuPuzzle() {
        return sudokuPuzzle;
    }

    public Stack<Integer> getPossibleValues() {
        return possibleValues;
    }

    public int getIi() {
        return ii;
    }

    public int getJj() {
        return jj;
    }
}
