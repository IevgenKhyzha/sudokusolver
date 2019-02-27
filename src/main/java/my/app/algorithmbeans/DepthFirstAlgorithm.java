package my.app.algorithmbeans;

import my.app.pojo.PuzzleStateObject;

import java.util.Stack;

public class DepthFirstAlgorithm extends Algorithm {

    @Override
    public String getAlgorithmName() {
        return "DFS";
    }

    @Override
    public int[][] resolvePuzzle(int[][] sudokuPuzzle) {
        boolean puzzleSolved = false;
        int[][] clonedSudokuPuzzle = null;
        Stack<PuzzleStateObject> stack = new Stack<PuzzleStateObject>();
        // the first stack member, have always be present in
        int[][] clonedFirstSudokuPuzzle = cloneSudokuPuzzle(sudokuPuzzle);
        int[] emptyFirstCell = findFirstEmptyCellInPuzzleAndReturnCoordinates(clonedFirstSudokuPuzzle);
        Stack<Integer> possibleValuesForFirstEmptyCell = findPossibleValuesForCell(clonedFirstSudokuPuzzle, emptyFirstCell[0], emptyFirstCell[1]);
        stack.push(new PuzzleStateObject(clonedFirstSudokuPuzzle, possibleValuesForFirstEmptyCell, emptyFirstCell[0], emptyFirstCell[1]));
        while (!puzzleSolved) {
            tryCount++;
            // peek from stack
            PuzzleStateObject puzzleStateObject = stack.peek();
            // get objects from pojo to work with
            int[][] puzzleFromStack = puzzleStateObject.getSudokuPuzzle();
            Stack<Integer> possibleValuesForCell = puzzleStateObject.getPossibleValues();
            int i = puzzleStateObject.getIi();
            int j = puzzleStateObject.getJj();
            // clone puzzle to wokr with
            clonedSudokuPuzzle = cloneSudokuPuzzle(puzzleFromStack);
            // get first possible value and put it into cloned puzzle to coordinates
            if (!possibleValuesForCell.empty()) {
                int firstPossibleValueForCell = possibleValuesForCell.pop();
                clonedSudokuPuzzle[i][j] = firstPossibleValueForCell;
            } else {
                stack.pop();
                continue;
            }
            // find first empty cell in puzzle
            int[] emptyCell = findFirstEmptyCellInPuzzleAndReturnCoordinates(clonedSudokuPuzzle); // todo use method which returns next coordinates with which program worked before
            if (emptyCell != null) {
                Stack<Integer> possibleValuesForEmptyCell = findPossibleValuesForCell(clonedSudokuPuzzle, emptyCell[0], emptyCell[1]);
                if (!possibleValuesForEmptyCell.empty()) {
                    stack.push(new PuzzleStateObject(clonedSudokuPuzzle, possibleValuesForEmptyCell, emptyCell[0], emptyCell[1]));
                }
            } else {
                puzzleSolved = isAlgorithmSolvedPuzzle(clonedSudokuPuzzle);
                if (puzzleSolved) {
                    break;
                } else {
                    stack.pop();
                }
            }
            puzzleSolved = isAlgorithmSolvedPuzzle(clonedSudokuPuzzle);
        }
        System.out.println("Number of try: " + tryCount);
        return clonedSudokuPuzzle;
    }
}
