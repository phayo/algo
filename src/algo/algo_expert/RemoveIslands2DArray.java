package algo.algo_expert;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;
import algo.Util;

/**
 * <div class="html">
 * <p>
 *   You're given a two-dimensional array (a matrix) of potentially unequal height
 *   and width containing only <span>0</span>s and <span>1</span>s. The matrix
 *   represents a two-toned image, where each <span>1</span> represents black and
 *   each <span>0</span> represents white. An island is defined as any number of
 *   <span>1</span>s that are horizontally or vertically adjacent (but not
 *   diagonally adjacent) and that don't touch the border of the image. In other
 *   words, a group of horizontally or vertically adjacent <span>1</span>s isn't an
 *   island if any of those <span>1</span>s are in the first row, last row, first
 *   column, or last column of the input matrix.
 * </p>
 * <p>
 *   Note that an island can twist. In other words, it doesn't have to be a
 *   straight vertical line or a straight horizontal line; it can be L-shaped, for
 *   example.
 * </p>
 * <p>
 *   You can think of islands as patches of black that don't touch the border of
 *   the two-toned image.
 * </p>
 * <p>
 *   Write a function that returns a modified version of the input matrix, where
 *   all of the islands are removed. You remove an island by replacing it with
 *   <span>0</span>s.
 * </p>
 * <p>Naturally, you're allowed to mutate the input matrix.</p>
 * <h3>Sample Input</h3>
 * <pre><span class="CodeEditor-promptParameter">matrix</span> =
 * [
 *   [1, 0, 0, 0, 0, 0],
 *   [0, 1, 0, 1, 1, 1],
 *   [0, 0, 1, 0, 1, 0],
 *   [1, 1, 0, 0, 1, 0],
 *   [1, 0, 1, 1, 0, 0],
 *   [1, 0, 0, 0, 0, 1],
 * ]
 * </pre>
 * <h3>Sample Output</h3>
 * <pre>[
 *   [1, 0, 0, 0, 0, 0],
 *   [0, 0, 0, 1, 1, 1],
 *   [0, 0, 0, 0, 1, 0],
 *   [1, 1, 0, 0, 1, 0],
 *   [1, 0, 0, 0, 0, 0],
 *   [1, 0, 0, 0, 0, 1],
 * ]
 * <span class="CodeEditor-promptComment">// The islands that were removed can be clearly seen here:</span>
 * <span class="CodeEditor-promptComment">// [</span>
 * <span class="CodeEditor-promptComment">//   [ ,  ,  ,  ,  , ],</span>
 * <span class="CodeEditor-promptComment">//   [ , 1,  ,  ,  , ],</span>
 * <span class="CodeEditor-promptComment">//   [ ,  , 1,  ,  , ],</span>
 * <span class="CodeEditor-promptComment">//   [ ,  ,  ,  ,  , ],</span>
 * <span class="CodeEditor-promptComment">//   [ ,  , 1, 1,  , ],</span>
 * <span class="CodeEditor-promptComment">//   [ ,  ,  ,  ,  , ], </span>
 * <span class="CodeEditor-promptComment">// ]</span>
 * </pre>
 * </div>
 */
public class RemoveIslands2DArray {

    public static int[][] removeIslands(int[][] matrix) {
        // Write your code here.

        visitNonIslands(matrix);

        for(int i = 1; i < matrix.length - 1; i++){
            for(int j = 1; j < matrix[0].length - 1; j++){
                if(matrix[i][j] >= 1){
                    matrix[i][j]--;
                }
            }
        }
        return matrix;
    }

    private record Pair(int i, int j){}

    private static void visitNonIslands(int[][] matrix){
        Set<Pair> visited = new HashSet<>();
        Stack<Pair> stack = new Stack<>();


        int iLen = matrix.length - 1;
        int jLen = matrix[0].length - 1;


        for(int j = 0; j <= jLen; j++){
            if(matrix[0][j] == 1)
                stack.push(new Pair(0, j));

            if(matrix[iLen][j] == 1)
                stack.push(new Pair(iLen, j));
        }

        for(int i = 0; i <= iLen; i++){
            if(matrix[i][0] == 1)
                stack.push(new Pair(i, 0));

            if(matrix[i][jLen] == 1)
                stack.push(new Pair(i, jLen));
        }

        while(!stack.isEmpty()){
            Pair p = stack.pop();
            if(!visited.contains(p) && matrix[p.i][p.j] >= 1){
                // bottom
                if(p.i + 1 <= iLen && matrix[p.i + 1][p.j] == 1){
                    Pair n = new Pair(p.i + 1, p.j);
                    if(!visited.contains(n) && !stack.contains(n)) {
                        matrix[p.i + 1][p.j] = 2;
                        stack.push(n);
                    }
                }

                // right
                if(p.j + 1 <= jLen && matrix[p.i][p.j + 1] == 1){
                    Pair n = new Pair(p.i, p.j + 1);
                    if(!visited.contains(n) && !stack.contains(n)){
                        matrix[p.i][p.j + 1] = 2;
                        stack.push(n);
                    }
                }

                // top
                if(p.i - 1 >= 0 && matrix[p.i - 1][p.j] == 1){
                    Pair n = new Pair(p.i - 1, p.j);
                    if(!visited.contains(n) && !stack.contains(n)) {
                        matrix[p.i - 1][p.j] = 2;
                        stack.push(n);
                    }
                }

                // left
                if(p.j - 1 >= 0 && matrix[p.i][p.j - 1] == 1){
                    Pair n = new Pair(p.i, p.j - 1);
                    if(!visited.contains(n) && !stack.contains(n)){
                        matrix[p.i][p.j - 1] = 2;
                        stack.push(n);
                    }
                }
            }
            visited.add(p);
        }
    }



    public static void main(String[] args) {
        int[] a = new int[]{1, 0, 0, 0, 0, 0};
        int[] b = new int[]{0, 1, 0, 1, 1, 1};
        int[] c = new int[]{0, 0, 1, 0, 1, 0};
        int[] d = new int[]{1, 1, 0, 0, 1, 0};
        int[] e = new int[]{1, 0, 1, 1, 0, 0};
        int[] f = new int[]{1, 0, 0, 0, 0, 1};

        int[][] matrix = new int[][]{a,b,c,d,e,f};

        Util.print2DArray(removeIslands(matrix));
    }


}
