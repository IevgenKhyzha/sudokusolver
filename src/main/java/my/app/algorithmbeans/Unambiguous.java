package my.app.algorithmbeans;

import java.util.ArrayList;
import java.util.List;

public class Unambiguous extends Algorithm {

    @Override
    public String getAlgorithmName() {
        return "Unambiguous";
    }

    @Override
    public int[][] resolvePuzzle(int[][] sudokuPuzzle) {
        boolean findUnambiguousInPuzzle = true; // if true, try one more time in every cell
        List<Integer> possibleValuesList = new ArrayList<Integer>();
        while(findUnambiguousInPuzzle) {
            findUnambiguousInPuzzle = false;
            lapsCount++;
            for (int i = 0; i < sudokuPuzzle.length; i++) {
                for (int j = 0; j < sudokuPuzzle[i].length; j++) {
                    if (!isPresentInCell(sudokuPuzzle, i, j)) {
                        findUnambiguousInPuzzle |= findUnambiguousForCell(sudokuPuzzle, i, j, possibleValuesList);
                    }
                }
            }
        }
        System.out.println("Number of try: " + tryCount);
        System.out.println("Number of laps: " + lapsCount);
        System.out.println("Number of finding values: " + findingValuesCount);
        return sudokuPuzzle;
    }
}
