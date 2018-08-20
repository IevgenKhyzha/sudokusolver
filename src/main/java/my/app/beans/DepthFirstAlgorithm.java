package my.app.beans;

import java.util.ArrayList;
import java.util.List;

public class DepthFirstAlgorithm extends Algorithm {

    @Override
    public String getAlgorithmName() {
        return "DFS";
    }
    // TODO: not working
    @Override
    public int[][] resolvePuzzle(int[][] sudokuPuzzle) {
        List<Integer> possibleValuesList = new ArrayList<Integer>();
        int[][] tempPuzzle;
        boolean findInLap = true;
        while(findInLap) {
            findInLap = false;

            for (int i = 0; i < sudokuPuzzle.length; i++) {
                a:for (int j = 0; j < sudokuPuzzle[i].length; j++) {

                    if (!isPresentInCell(sudokuPuzzle, i, j)) {

                        findInLap |= findUnambiguousInCell(sudokuPuzzle, possibleValuesList, i, j);

                        if (!findInLap && possibleValuesList.size() >= 2) {
                            nextPossibleValue: for (int possibleValue : possibleValuesList) {
                                tempPuzzle = sudokuPuzzle.clone();
                                tempPuzzle[i][j] = possibleValue;
                               //////////////
                                boolean find2InAllTempPuzzle = false;
                                boolean isFindTheSameValueInPuzzle = false;
                                // find in all temporary array
                                for (int ii = 0; ii < tempPuzzle.length; ii++) {
                                    for (int jj = 0; jj < tempPuzzle[ii].length; jj++) {
                                        if (!isPresentInCell(sudokuPuzzle, ii, jj)) {
                                            find2InAllTempPuzzle |= findUnambiguousInCell(sudokuPuzzle, new ArrayList<Integer>(), ii, jj);
                                            if (isTheSameValuePresentInPossiblePlace_checkInAllPuzzle(sudokuPuzzle)) {
                                                break nextPossibleValue;
                                                // TODO try to implement recursion to find more ambiguous values. make some recursion method
                                            }
                                        }
                                    }
                                }
                                if (find2InAllTempPuzzle) {
                                    sudokuPuzzle = tempPuzzle;
                                    continue a;
                                }
                            }
                        }
                    }
                }
            }
        }
        return sudokuPuzzle;
    }
}
