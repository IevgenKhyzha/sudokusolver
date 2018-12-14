package my.app.algorithmbeans;

import my.app.pojo.PuzzleStateObject;

import java.util.EmptyStackException;
import java.util.Stack;

public class DepthFirstAlgorithm extends Algorithm {

    @Override
    public String getAlgorithmName() {
        return "DFS";
    }

    @Override
    public int[][] resolvePuzzle(int[][] sudokuPuzzle) {
        Stack<PuzzleStateObject> stack = new Stack<PuzzleStateObject>();
        for (int i = 0; i < sudokuPuzzle.length; i++) {
            for (int j = 0; j < sudokuPuzzle[i].length; j++) {
                int[][] clonePuzzle = sudokuPuzzle.clone();
                if (!isPresentInCell(clonePuzzle, i, j)) {
                    stack.push(new PuzzleStateObject(clonePuzzle, findPossibleValuesForCell(clonePuzzle, i, j), i, j));
                    getNextCoordinates(clonePuzzle, 0, 8);
//                    stack = findValueInActualStack(stack);
                    // todo method which return actual stack and do one find action + copy all variables in main stack
                }
            }
        }
        return sudokuPuzzle;
    }

    private void setPossibleValueToPuzzleAndContinueThaChain(Stack<PuzzleStateObject> stack) {
        PuzzleStateObject lastElementInStack = stack.peek();
        int[][] clonedPuzzle = lastElementInStack.getSudokuPuzzle().clone();
        Stack<Integer> possibleValues = lastElementInStack.getPossibleValues();
        int ii = lastElementInStack.getIi();
        int jj = lastElementInStack.getJj();
        // todo check puzzle for resolving

        while (!possibleValues.empty()) {
            for (int i = ii ; i < clonedPuzzle.length; i++) { // need to continue element i and j
                for (int j = jj; j < clonedPuzzle[i].length; j++) { // need to continue element i and j
                    Stack<Integer> newPossibleValues = findPossibleValuesForCell(clonedPuzzle, i, j);
                }
            }
        }
    }

    private void getNextCoordinates(int[][] sudokuPuzzle, int ii, int jj) {
//        if (jj == sudokuPuzzle[ii].length - 1 && ii != sudokuPuzzle.length - 1) {
//            ii++; // todo
//            jj = 0;
//        }
        for (int i = ii; i < sudokuPuzzle.length; i++) { // need to continue element i and j
            for (int j = jj; j < sudokuPuzzle[i].length; j++) { // need to continue element i and j
                Stack<Integer> newPossibleValues = findPossibleValuesForCell(sudokuPuzzle, i, j);
//                sudokuPuzzle
                return;
            }
        }
    }

    private Stack<PuzzleStateObject> findValueInActualStack(Stack<PuzzleStateObject> sudokuStateStack) { // one find action (one floor)
        PuzzleStateObject puzzleStateObject;
        try {
            puzzleStateObject = sudokuStateStack.peek();
        } catch (EmptyStackException ese) {
            return sudokuStateStack;
        }
        int[][] sudokuPuzzle = puzzleStateObject.getSudokuPuzzle();
        Stack<Integer> possibleValues = puzzleStateObject.getPossibleValues();
        int[][] clonedSudokuPuzzle = sudokuPuzzle.clone();
        int i_coord_work_with = puzzleStateObject.getIi();
        int j_coord_work_with = puzzleStateObject.getJj();
        try {
            clonedSudokuPuzzle[i_coord_work_with][j_coord_work_with] = possibleValues.pop(); // todo empty stack exception
        } catch (EmptyStackException ese) {
            sudokuStateStack.pop(); // todo
            findValueInActualStack(sudokuStateStack);
        }
        Stack<Integer> possibleValuesForNextUnknownCellInClonedPuzzle;
        for (int ii = 0; ii < sudokuPuzzle.length; ii++) {
            for (int jj = 0; jj < sudokuPuzzle[ii].length; jj++) {
                if (!isPresentInCell(sudokuPuzzle, ii, jj)) {
                    possibleValuesForNextUnknownCellInClonedPuzzle = findPossibleValuesForCell(clonedSudokuPuzzle, ii, jj);
                    if (possibleValuesForNextUnknownCellInClonedPuzzle.capacity() > 0) {
                        sudokuStateStack.push(new PuzzleStateObject(sudokuPuzzle, possibleValuesForNextUnknownCellInClonedPuzzle, ii, jj));
                        findValueInActualStack(sudokuStateStack);
                    } else {
                        sudokuStateStack.pop();
                    }
                    findValueInActualStack(sudokuStateStack);
                    // if not valid then pop and go to previous stack value
                    // if valid go further
                }
            }
        }

        // stack for new step in depth
        Stack<Integer> possibleValuesStack = findPossibleValuesForCell(sudokuPuzzle, puzzleStateObject.getIi(), puzzleStateObject.getJj());
        // if stack next
        return sudokuStateStack;
    }
}
