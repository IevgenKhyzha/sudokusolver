package my.app;

import my.app.algorithmbeans.Algorithm;
import my.app.utils.SudokuFileUtils;
import my.app.utils.SudokuUtils;

import java.time.LocalTime;

class SudokuSolver {

    SudokuSolver(String inFilePath, String outFilePath, Algorithm algorithm) {
        SudokuFileUtils sudokuFileUtils = SudokuFileUtils.getInstance(inFilePath, outFilePath);

        int[][] unresolvedPuzzle = sudokuFileUtils.transformASCIIFileToArray();

        SudokuUtils.printPuzzle(unresolvedPuzzle);

        LocalTime startTime = LocalTime.now();
        int[][] resolvedPuzzle = algorithm.resolvePuzzle(unresolvedPuzzle);
        LocalTime finishTime = LocalTime.now();

        SudokuUtils.printResult(algorithm.getAlgorithmName(), algorithm.isAlgorithmSolvedPuzzle(resolvedPuzzle),
                startTime, finishTime, resolvedPuzzle);

        sudokuFileUtils.createOutPutFile(SudokuUtils.transformNumberArrayToCellArray(unresolvedPuzzle, resolvedPuzzle));
    }
}
