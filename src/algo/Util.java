package algo;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Util {

    public static void print2DArray(int[][] matrix){
        Arrays.stream(matrix)
                .forEach(arr -> System.out.println(Arrays.toString(arr)));
    }

    public static int shortestPath(int[][] matrix, int[] start, int[] end, int WALL) {
        int[][] distances = pathMatrix(matrix, start, WALL);
        Util.print2DArray(distances);
        return distances[end[0]][end[1]];
    }

    public static int[][] pathMatrix(int[][] matrix, int[] start,int WALL){
        int rows = matrix.length;
        int cols = matrix[0].length;

        // Fill the distance array
        int[][] distances = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                distances[i][j] = Integer.MAX_VALUE;
            }
        }

        // Set the start position to a distance of zero
        distances[start[0]][start[1]] = 0;

        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(start);

        // top, down, left right directions to go.
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        // BFS transversal
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            for (int[] dir : directions) {
                int newRow = current[0] + dir[0];
                int newCol = current[1] + dir[1];
                if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols && matrix[newRow][newCol] != WALL) {
                    int newDistance = distances[current[0]][current[1]] + 1;
                    if (newDistance < distances[newRow][newCol]) {
                        distances[newRow][newCol] = newDistance;
                        queue.offer(new int[]{newRow, newCol});
                    }
                }
            }
        }

        return distances;
    }
}
