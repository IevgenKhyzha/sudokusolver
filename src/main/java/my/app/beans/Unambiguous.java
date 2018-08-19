package my.app.beans;

import java.util.ArrayList;
import java.util.List;

public class Unambiguous extends Algorithm {

    @Override
    public String getAlgorithmName() {
        return "Unambiguous";
    }

    @Override
//    public int[][] resolvePuzzle(int[][] sudokuPuzzle) {
//        boolean findUnambiguous = true; // if find, try one more time in every cell
//        List<Integer> possibleValuesList = new ArrayList<Integer>();
//        while(findUnambiguous) {
//            findUnambiguous = false;
//            // for variable to change some value in puzzle
//            for (int i = 0; i < sudokuPuzzle.length; i++) {
//                for (int j = 0; j < sudokuPuzzle[i].length; j++) {
//                    if (!isPresentInCell(sudokuPuzzle, i, j)) {
//                        possibleValuesList.clear();
//                        for (int possibleValue = 1; possibleValue <= 9; possibleValue++) {
//                            tryCount++;
//                            if (!findInAllPossiblePlaces(possibleValue, sudokuPuzzle, i, j)) {
//                                possibleValuesList.add(possibleValue);
//                            }
//                        }
//                        if (possibleValuesList.size() == 1) {
//                            sudokuPuzzle[i][j] = possibleValuesList.get(0);
//                            findUnambiguous = true;
//                        }
//                    }
//                }
//            }
//        }
//        System.out.println("Number of try: " + tryCount);
//        return sudokuPuzzle;
//    }

    public int[][] resolvePuzzle(int[][] sudokuPuzzle) {
        boolean findUnambiguousInPuzzle = true; // if find, try one more time in every cell
        List<Integer> possibleValuesList = new ArrayList<Integer>();
        while(findUnambiguousInPuzzle) {
            findUnambiguousInPuzzle = false;
            lapsCount++;
            for (int i = 0; i < sudokuPuzzle.length; i++) {
                for (int j = 0; j < sudokuPuzzle[i].length; j++) {
                    if (!isPresentInCell(sudokuPuzzle, i, j)) {
                        findUnambiguousInPuzzle |= findUnambiguousInCell(sudokuPuzzle, possibleValuesList, i, j);
                    }
                }
            }
        }
        System.out.println("Number of try: " + tryCount);
        System.out.println("Number of laps: " + lapsCount);
        System.out.println("Number of finding values: " + fidingValuesCount);
        return sudokuPuzzle;
    }
}
