package my.app.algorithmbeans;

import my.app.pojo.PuzzleStateObject;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BreadthFirstAlgorithm extends Algorithm {

    @Override
    public String getAlgorithmName() {
        return "BFS";
    }

    @Override
    public int[][] resolvePuzzle(int[][] sudokuPuzzle) {
        // first in first out implementation example
        Queue<PuzzleStateObject> queue = new LinkedList<>();
        queue.add(new PuzzleStateObject(sudokuPuzzle, new Stack<>(), 1, 0));
        queue.add(new PuzzleStateObject(sudokuPuzzle, new Stack<>(), 1, 0));
        System.out.println("actual queue " + queue);
        System.out.println("peek method return " + queue.peek().toString());
        System.out.println("actual queue " + queue);
        System.out.println("poll method return " + queue.poll().toString());
        System.out.println("actual queue " + queue);
        return sudokuPuzzle;
    }
}
