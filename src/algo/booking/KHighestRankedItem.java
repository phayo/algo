package algo.booking;

import algo.Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <h2><a href="https://leetcode.com/problems/k-highest-ranked-items-within-a-price-range/solutions/3897006/most-understandable-java-solution-trading-off-time-performance/">LeetCode: K Highest ranked</a></h2>
 * <div class="xFUwe" data-track-load="description_content"><p>You are given a <strong>0-indexed</strong> 2D integer array <code>grid</code> of size <code>m x n</code> that represents a map of the items in a shop. The integers in the grid represent the following:</p>
 *
 * <ul>
 * 	<li><code>0</code> represents a wall that you cannot pass through.</li>
 * 	<li><code>1</code> represents an empty cell that you can freely move to and from.</li>
 * 	<li>All other positive integers represent the price of an item in that cell. You may also freely move to and from these item cells.</li>
 * </ul>
 *
 * <p>It takes <code>1</code> step to travel between adjacent grid cells.</p>
 *
 * <p>You are also given integer arrays <code>pricing</code> and <code>start</code> where <code>pricing = [low, high]</code> and <code>start = [row, col]</code> indicates that you start at the position <code>(row, col)</code> and are interested only in items with a price in the range of <code>[low, high]</code> (<strong>inclusive</strong>). You are further given an integer <code>k</code>.</p>
 *
 * <p>You are interested in the <strong>positions</strong> of the <code>k</code> <strong>highest-ranked</strong> items whose prices are <strong>within</strong> the given price range. The rank is determined by the <strong>first</strong> of these criteria that is different:</p>
 *
 * <ol>
 * 	<li>Distance, defined as the length of the shortest path from the <code>start</code> (<strong>shorter</strong> distance has a higher rank).</li>
 * 	<li>Price (<strong>lower</strong> price has a higher rank, but it must be <strong>in the price range</strong>).</li>
 * 	<li>The row number (<strong>smaller</strong> row number has a higher rank).</li>
 * 	<li>The column number (<strong>smaller</strong> column number has a higher rank).</li>
 * </ol>
 *
 * <p>Return <em>the </em><code>k</code><em> highest-ranked items within the price range <strong>sorted</strong> by their rank (highest to lowest)</em>. If there are fewer than <code>k</code> reachable items within the price range, return <em><strong>all</strong> of them</em>.</p>
 *
 * <p>&nbsp;</p>
 * <p><strong class="example">Example 1:</strong></p>
 * <img alt="" src="https://assets.leetcode.com/uploads/2021/12/16/example1drawio.png" style="width: 200px; height: 151px;">
 * <pre><strong>Input:</strong> grid = [[1,2,0,1],[1,3,0,1],[0,2,5,1]], pricing = [2,5], start = [0,0], k = 3
 * <strong>Output:</strong> [[0,1],[1,1],[2,1]]
 * <strong>Explanation:</strong> You start at (0,0).
 * With a price range of [2,5], we can take items from (0,1), (1,1), (2,1) and (2,2).
 * The ranks of these items are:
 * - (0,1) with distance 1
 * - (1,1) with distance 2
 * - (2,1) with distance 3
 * - (2,2) with distance 4
 * Thus, the 3 highest ranked items in the price range are (0,1), (1,1), and (2,1).
 * </pre>
 *
 * <p><strong class="example">Example 2:</strong></p>
 * <img alt="" src="https://assets.leetcode.com/uploads/2021/12/16/example2drawio1.png" style="width: 200px; height: 151px;">
 * <pre><strong>Input:</strong> grid = [[1,2,0,1],[1,3,3,1],[0,2,5,1]], pricing = [2,3], start = [2,3], k = 2
 * <strong>Output:</strong> [[2,1],[1,2]]
 * <strong>Explanation:</strong> You start at (2,3).
 * With a price range of [2,3], we can take items from (0,1), (1,1), (1,2) and (2,1).
 * The ranks of these items are:
 * - (2,1) with distance 2, price 2
 * - (1,2) with distance 2, price 3
 * - (1,1) with distance 3
 * - (0,1) with distance 4
 * Thus, the 2 highest ranked items in the price range are (2,1) and (1,2).
 * </pre>
 *
 * <p><strong class="example">Example 3:</strong></p>
 * <img alt="" src="https://assets.leetcode.com/uploads/2021/12/30/example3.png" style="width: 149px; height: 150px;">
 * <pre><strong>Input:</strong> grid = [[1,1,1],[0,0,1],[2,3,4]], pricing = [2,3], start = [0,0], k = 3
 * <strong>Output:</strong> [[2,1],[2,0]]
 * <strong>Explanation:</strong> You start at (0,0).
 * With a price range of [2,3], we can take items from (2,0) and (2,1).
 * The ranks of these items are:
 * - (2,1) with distance 5
 * - (2,0) with distance 6
 * Thus, the 2 highest ranked items in the price range are (2,1) and (2,0).
 * Note that k = 3 but there are only 2 reachable items within the price range.
 * </pre>
 *
 * <p>&nbsp;</p>
 * <p><strong>Constraints:</strong></p>
 *
 * <ul>
 * 	<li><code>m == grid.length</code></li>
 * 	<li><code>n == grid[i].length</code></li>
 * 	<li><code>1 &lt;= m, n &lt;= 10<sup>5</sup></code></li>
 * 	<li><code>1 &lt;= m * n &lt;= 10<sup>5</sup></code></li>
 * 	<li><code>0 &lt;= grid[i][j] &lt;= 10<sup>5</sup></code></li>
 * 	<li><code>pricing.length == 2</code></li>
 * 	<li><code>2 &lt;= low &lt;= high &lt;= 10<sup>5</sup></code></li>
 * 	<li><code>start.length == 2</code></li>
 * 	<li><code>0 &lt;= row &lt;= m - 1</code></li>
 * 	<li><code>0 &lt;= col &lt;= n - 1</code></li>
 * 	<li><code>grid[row][col] &gt; 0</code></li>
 * 	<li><code>1 &lt;= k &lt;= m * n</code></li>
 * </ul>
 * </div>
 */
public class KHighestRankedItem {

    /**
     * Find the shortest path between 2 positions in a 2d array
     * @param matrix 2d array
     * @param start start position
     * @param end end position
     * @return distance
     */
    public static int[][] shortestPath(int[][] matrix, int[] start) {
        int WALL = 0;
        return Util.pathMatrix(matrix, start, WALL);
    }

    public static List<List<Integer>> positionsWithinPriceRange(int[][] matrix, int[] pricing){
        int rows = matrix.length;
        int cols = matrix[0].length;
        List<List<Integer>> pricePositions = new ArrayList<>();

        for (int i = 0; i < rows; i++){
            for (int j = 0; j < cols; j++){
                if(matrix[i][j] >= pricing[0] && matrix[i][j] <= pricing[1]){
                    pricePositions.add(List.of(i, j));
                }
            }
        }
        return pricePositions;
    }

    public static void main(String[] args) {
//        int[][] matrix = { //
//                {0, 0, 1, 0},
//                {0, 0, 0, 0},
//                {0, 0, 1, 0},
//                {0, 1, 0, 0}
//        };
//        List<Integer> end = List.of(3, 3);
//        System.out.println("Shortest distance: " + shortestDistance);

        int[][] matrix = { // [[2,0,3],[2,0,3],[2,0,3]]
                {2,0,3},
                {2,0,3},
                {2,0,3}
        };

        int[] pricing = {2,3};
        int[] start = {0, 0};
        int k = 6;

        highestRankedKItems(matrix, pricing, start, k)
                .forEach(a -> System.out.printf("[%d,%d]%n", a.get(0), a.get(1)));
    }

    public static List<List<Integer>> highestRankedKItems(int[][] grid, int[] pricing, int[] start, int k) {
        int[][] pathGrid = Util.pathMatrix(grid, start, 0);

        List<List<Integer>> prices = positionsWithinPriceRange(grid, pricing)
                .stream().filter(p -> pathGrid[p.get(0)][p.get(1)] != Integer.MAX_VALUE)
                .collect(Collectors.toList());

        PriorityQueue<List<Integer>> pq =  new PriorityQueue<>( // Adding the comparator based on specified rank: path > price > row > col
                (x, y) -> {
                    // checking shorter path
                    int xPath = pathGrid[x.get(0)][x.get(1)];
                    int yPath = pathGrid[y.get(0)][y.get(1)];
                    if (xPath != yPath){
                        return xPath - yPath;
                    }

                    // checking lower price
                    int xPrice = grid[x.get(0)][x.get(1)];
                    int yPrice = grid[y.get(0)][y.get(1)];
                    if(xPrice != yPrice){
                        return xPrice - yPrice;
                    }

                    // Checking lower row - the row is at index 0
                    if(!Objects.equals(x.get(0), y.get(0))){
                        return x.get(0) - y.get(0);
                    }

                    // finally comparing by column. The column is at index 1
                    return Integer.compare(x.get(1), y.get(1));
                }
        );

        pq.addAll(prices);

        List<List<Integer>> result = new ArrayList<>();
        while(!pq.isEmpty() && k-- != 0){
            result.add(pq.poll());
        }

        return result;
    }


    /**
     * NOT Optimal just condensed
     */
    public List<List<Integer>> highestRankedKItemsSimplified(int[][] grid, int[] pricing, int[] start, int k) {
        // create an array to hold directions to advance during transversal
        // i.e right (0,1) that is d[0],d[1]
        // i.e down (1,0) that is d[1],d[2]
        // i.e left (0,-1) that is d[2],d[3]
        // i.e right (-1,0) that is d[3],d[4]
        final int[] d = {0, 1, 0, -1, 0};

        // get the num of rows and col
        int R = grid.length, C = grid[0].length;

        // initialize x, y -> the start position
        // initialize low,high -> the price ranges
        int x = start[0], y = start[1], low = pricing[0], high = pricing[1];

        // set to prune duplicates
        Set<String> seen = new HashSet<>(); // Set used to prune duplicates.
        // insert the start position as seen
        seen.add(x + "," + y);

        // result list
        List<List<Integer>> ans = new ArrayList<>();

        // Construct a pq that has integers with index representing (0->distance, 1->price, 2->row, 3->col)
        // PriorityQueue comparator sorts by (distance, price, row, col).
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] == b[0] ? a[1] == b[1] ? a[2] == b[2] ? a[3] - b[3] : a[2] - b[2] : a[1] - b[1] : a[0] - b[0]);

        // to begin BFS, we start from the given 'start' which will have a distance of 0,
        //  we also get the price from the grid, including the row and col (x, y) respectively
        pq.offer(new int[]{0, grid[x][y], x, y}); // BFS starting point.

        while (!pq.isEmpty() && ans.size() < k) { // continue while k and queue is not empty
            int[] cur = pq.poll();
            int distance = cur[0], price = cur[1], r = cur[2], c = cur[3]; // distance, price, row & column.

            if (low <= price && price <= high) { // price in range?
                ans.add(Arrays.asList(r, c));
            }

            for (int m = 0; m < 4; ++m) { // traverse 4 neighbors. using d
                int i = r + d[m], j = c + d[m + 1];
                // in boundary, not wall, and not visited yet?
                if (0 <= i && i < R && 0 <= j && j < C && grid[i][j] > 0 && seen.add(i + "," + j)) {
                    pq.offer(new int[]{distance + 1, grid[i][j], i, j});
                }
            }
        }
        return ans;
    }
}
