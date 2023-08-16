package algo.booking;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * In an infinite chessboard with coordinates from -infinity to +infinity, you have a knight at square [0, 0].
 *
 * A knight has 8 possible moves it can make, as illustrated below. Each move is two squares in a cardinal direction, then one square in an orthogonal direction.
 *
 * Return the minimum number of steps needed to move the knight to the square [x, y]. It is guaranteed the answer exists.
 */
public class MinimumKnightMoves {

    /*
    DEPTH FIRST SEARCH SOLUTION
    * Get a list of all possible knight move squares from 0.
    * moves = {{-2,-1},{-1,-2}, {-2, 1}, {-1, 2}, {2, 1}, {1, 2}, {2, -1}, {1, -2}}
    * Loop from {0,0} to each square by doing x + move[i], y + move[i] (no bounds, square is infinite)
    * if the current is the square we are looking for {m, n} return if not add the moves from current to the queue. stored all visited in a set
    * continue till we find {m. n}
    *
    * Time complexity: O(max(x²,y²)). This is due to the fact that every place the Knight covers a radius of 2x or 2y. The number of cells inside this circle is the max of x² or y².
    * */

    public static int optimalKnightMovesSolution(int x, int y){
        TreeMap<int[], Integer> knownPointMoves = new TreeMap<>((a, b) -> a[0] == b[0] ? Integer.compare(a[1], b[1]) : Integer.compare(a[0], b[0]));
        knownPointMoves.put(new int[]{0, 0}, 0);
        knownPointMoves.put(new int[]{0, 1}, 3);
        knownPointMoves.put(new int[]{1, 0}, 3);
        knownPointMoves.put(new int[]{1, 1}, 2);
        knownPointMoves.put(new int[]{0, 2}, 2);
        knownPointMoves.put(new int[]{2, 0}, 2);

        return dfs(x, y, knownPointMoves);
    }

    private static int dfs(int x, int y, TreeMap<int[], Integer> knownMoves){
        if (knownMoves.containsKey(new int[]{x, y})){
            return knownMoves.get(new int[]{x, y});
        }

        int response = Math.min(dfs(Math.abs(x - 1), Math.abs(y - 2), knownMoves), dfs(Math.abs(x - 2), Math.abs(y - 1), knownMoves));

        knownMoves.put(new int[]{x, y}, response);

        return response;
    }

    /**
     * Solution to minimum knight moves with little improvement that we are searching only on the positive x-y axis.
     * time complexity is O(2ed) e = exponential d = depth of the found tile(x,y)
     * @param x
     * @param y
     * @return
     */
    public static int minimumKnightMoves(int x, int y){
        int[][] moves = new int[][]{{-2,-1},{-1,-2}, {-2, 1}, {-1, 2}, {2, 1}, {1, 2}, {2, -1}, {1, -2}};

        Queue<int[]> queue =  new ArrayDeque<>();
        Set<int[]> visited = new TreeSet<>((a, b) -> a[0] == b[0] ? Integer.compare(a[1], b[1]) : Integer.compare(a[0], b[0]));

        queue.add(new int[]{0, 0, 0}); // index 0 is x coordinate, 1 is y coordinate, 2 is number of moves
        visited.add(new int[]{0, 0});

        while(!queue.isEmpty()){
            int[] curr = queue.poll();
            if(curr[0] == Math.abs(x) && curr[1] == Math.abs(y)){
                return curr[2];
            }

            for (int[] nextMove : moves){
                int nextX = curr[0] + nextMove[0];
                int nextY = curr[1] + nextMove[1];

                if(!visited.contains(new int[]{Math.abs(nextX), Math.abs(nextY)})){
                    visited.add(new int[]{Math.abs(nextX), Math.abs(nextY)});
                    queue.add(new int[]{Math.abs(nextX), Math.abs(nextY), curr[2] + 1});
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        System.out.println(minimumKnightMoves(-1, 2));
    }
}
