package my.app.algorithmbeans;

import my.app.pojo.PuzzleStateObject;

import java.util.ArrayList;
import java.util.List;
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
                if (!isPresentInCell(sudokuPuzzle, i, j)) {
                    stack.push(new PuzzleStateObject(sudokuPuzzle, new int[]{i,j}, findPossibleValuesForCell(sudokuPuzzle, i, j)));
                }
            }
        }
        return sudokuPuzzle;
    }

    private List<Integer> graph = new ArrayList<Integer>();
    private boolean[] used = new boolean[graph.size()];

    private void dfs(int pos) {
        used[pos] = true;
        System.out.println(pos);
        for (int i = 0; i < graph.size(); i++){
            int next = graph.get(i);
            if (!used[next]){
                dfs(next);
            }
        }
    }
}
