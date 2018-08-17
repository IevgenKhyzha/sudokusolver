package my.app.beans;

import java.util.ArrayList;
import java.util.List;

public class Unambiguous extends Algorithm {

    @Override
    public String getAlgorithmName() {
        return "Unambiguous";
    }

    public int[][] resolvePuzzle(int[][] sudokuPuzzle) {
        int tryCount = 0;
        boolean findUn = true; // if find, try one more time in every cell
        List<Integer> list = new ArrayList<Integer>();
        while(findUn) {
            findUn = false;
            // for variable to change some value in puzzle
            for (int i = 0; i < sudokuPuzzle.length; i++) {
                for (int j = 0; j < sudokuPuzzle[i].length; j++) {
                    if (!isPresentInCell(sudokuPuzzle, i, j)) {
                        list.clear();
                        for (int possibleValue = 1; possibleValue <= 9; possibleValue++) {
                            tryCount++;
                            if (!findInAllPossiblePlaces(possibleValue, sudokuPuzzle, i, j)) {
                                list.add(possibleValue);
                            }
                        }
                        if (list.size() == 1) {
                            sudokuPuzzle[i][j] = list.get(0);
                            findUn = true;
                        }
                    }
                }
            }
        }
        System.out.println("Number of try: " + tryCount);
        return sudokuPuzzle;
    }
}
