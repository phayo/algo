package algo.booking;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

/**
 * <h2><a href="https://leetcode.com/problems/all-paths-from-source-to-target/">LeetCode: All Paths From Source to Target</a></h2>
 *
 * <div class="flex h-full w-full flex-1 flex-col"><div class="w-full px-5 pt-5"><div class="w-full"><div class="flex space-x-4"><div class="flex-1"><div class="flex h-full items-center"><a class="mr-2 text-label-1 dark:text-dark-label-1 hover:text-label-1 dark:hover:text-dark-label-1 text-lg font-medium" href="/problems/all-paths-from-source-to-target/">797. All Paths From Source to Target</a><div class="inline-flex min-h-[20px] items-center space-x-2 align-top mt-1"><div class="inline-flex items-center space-x-2"></div></div></div></div><div class="flex items-center"><div class="inline-flex gap-2 text-lg"><div class="popover-wrapper inline-block" data-headlessui-state=""><div><div id="headlessui-popover-button-:rr:" aria-expanded="false" data-headlessui-state=""><div class="cursor-pointer rounded p-[3px] text-gray-6 transition-colors dark:text-dark-gray-6 hover:bg-fill-3 dark:hover:bg-dark-fill-3"><svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="1em" height="1em" fill="currentColor"><path fill-rule="evenodd" d="M12 2c5.523 0 10 4.477 10 10s-4.477 10-10 10S2 17.523 2 12 6.477 2 12 2zm0 2a8 8 0 100 16 8 8 0 000-16zm-4.998 9.27a1.25 1.25 0 100-2.5 1.25 1.25 0 000 2.5zm6.25-1.25a1.25 1.25 0 11-2.5 0 1.25 1.25 0 012.5 0zm3.75 1.25a1.25 1.25 0 100-2.5 1.25 1.25 0 000 2.5z" clip-rule="evenodd"></path></svg></div></div></div></div></div></div></div><div class="mt-3 flex items-center space-x-4"><div class="text-yellow dark:text-dark-yellow inline-block text-sm font-medium capitalize leading-[22px]">Medium</div><div class="flex items-center space-x-4"><div class="text-xstransition-colors flex cursor-pointer items-center space-x-1 rounded py-[3px] px-1 hover:bg-fill-3 dark:hover:bg-dark-fill-3 text-gray-6 dark:text-dark-gray-6"><div class="text-lg text-gray-6 dark:text-dark-gray-6"><svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="1em" height="1em" fill="currentColor"><path fill-rule="evenodd" d="M7.04 9.11l3.297-7.419a1 1 0 01.914-.594 3.67 3.67 0 013.67 3.671V7.33h4.028a2.78 2.78 0 012.78 3.2l-1.228 8.01a2.778 2.778 0 01-2.769 2.363H5.019a2.78 2.78 0 01-2.78-2.78V11.89a2.78 2.78 0 012.78-2.78H7.04zm-2.02 2a.78.78 0 00-.781.78v6.232c0 .431.35.78.78.78H6.69V11.11H5.02zm12.723 7.793a.781.781 0 00.781-.666l1.228-8.01a.78.78 0 00-.791-.898h-5.04a1 1 0 01-1-1V4.77c0-.712-.444-1.32-1.07-1.56L8.69 10.322v8.58h9.053z" clip-rule="evenodd"></path></svg></div><div class="text-xs">6.7K</div></div><div class="text-xstransition-colors flex cursor-pointer items-center space-x-1 rounded py-[3px] px-1 hover:bg-fill-3 dark:hover:bg-dark-fill-3 text-gray-6 dark:text-dark-gray-6"><div class="text-lg text-gray-6 dark:text-dark-gray-6"><svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="1em" height="1em" fill="currentColor"><path fill-rule="evenodd" d="M13.663 22.309a1 1 0 01-.914.594 3.67 3.67 0 01-3.67-3.671V16.67H5.05a2.78 2.78 0 01-2.78-3.2l1.228-8.01a2.778 2.778 0 012.769-2.364H18.98a2.78 2.78 0 012.78 2.781v6.232a2.78 2.78 0 01-2.78 2.78H16.96l-3.297 7.419zm5.318-9.419a.78.78 0 00.78-.78V5.878a.78.78 0 00-.78-.78H17.31v7.792h1.67zM6.257 5.097a.781.781 0 00-.781.666l-1.229 8.01a.78.78 0 00.792.898h5.04a1 1 0 011 1v3.56c0 .712.443 1.32 1.07 1.56l3.16-7.113v-8.58H6.258z" clip-rule="evenodd"></path></svg></div><div class="text-xs">137</div></div></div><div class="popover-wrapper inline-block" data-headlessui-state=""><div><div id="headlessui-popover-button-:r10:" aria-expanded="false" data-headlessui-state=""><div><div class="flex h-full cursor-pointer items-center rounded p-[3px] text-lg transition-colors hover:bg-fill-3 dark:hover:bg-dark-fill-3 text-gray-6 dark:text-dark-gray-6"><svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="1em" height="1em" fill="currentColor"><path fill-rule="evenodd" d="M11.394 2.074a2.5 2.5 0 011.212 0c.723.181 1.185.735 1.526 1.262.342.528.703 1.259 1.131 2.127l.392.795c.302.61.348.667.386.7a.502.502 0 00.086.063c.043.025.11.052.786.15l.877.128c.958.139 1.764.256 2.372.418.606.162 1.276.43 1.671 1.062a2.5 2.5 0 01.375 1.152c.052.744-.333 1.354-.728 1.841-.397.489-.98 1.058-1.674 1.733l-.634.619c-.489.476-.527.537-.548.583a.506.506 0 00-.033.101c-.01.05-.015.122.1.794l.15.873c.164.954.302 1.758.335 2.386.034.627-.014 1.346-.493 1.918a2.5 2.5 0 01-.98.712c-.692.279-1.39.102-1.976-.124-.588-.226-1.309-.605-2.165-1.056l-.785-.412c-.603-.317-.674-.335-.724-.34a.496.496 0 00-.106 0c-.05.005-.12.023-.724.34l-.785.412c-.856.45-1.577.83-2.165 1.056-.585.226-1.284.403-1.976.124a2.501 2.501 0 01-.98-.712c-.48-.572-.527-1.291-.493-1.918.033-.628.171-1.431.335-2.386l.15-.873c.115-.672.11-.745.1-.794a.5.5 0 00-.033-.101c-.02-.046-.06-.107-.548-.583l-.634-.619c-.694-.675-1.277-1.244-1.674-1.733-.395-.487-.78-1.097-.728-1.841a2.5 2.5 0 01.375-1.152c.395-.633 1.065-.9 1.67-1.062.61-.162 1.415-.28 2.373-.418l.877-.128c.675-.098.743-.125.786-.15a.5.5 0 00.086-.062c.038-.034.084-.09.386-.701l.392-.795c.428-.868.789-1.599 1.131-2.127.341-.527.803-1.08 1.526-1.262zm.493 1.939c-.023.013-.132.089-.34.41-.271.418-.58 1.042-1.045 1.982l-.364.738-.05.103c-.213.434-.428.872-.788 1.197a2.5 2.5 0 01-.43.312c-.42.241-.903.31-1.381.379a52.6 52.6 0 00-.114.016l-.815.119c-1.037.15-1.725.252-2.207.38-.37.099-.476.18-.495.197a.5.5 0 00-.07.216c.005.025.044.153.285.45.314.386.811.874 1.562 1.605l.59.575.082.08c.346.336.697.676.895 1.118.072.162.127.332.164.506.1.474.016.955-.067 1.431l-.02.113-.138.811c-.178 1.033-.294 1.72-.32 2.217-.02.382.023.508.034.532.05.058.113.103.183.133.026.003.16.006.516-.132.465-.18 1.082-.502 2.01-.99l.728-.382.102-.054c.427-.226.859-.454 1.34-.505.177-.02.355-.02.532 0 .481.051.913.28 1.34.505l.102.054.728.383c.928.487 1.545.81 2.01.99.357.137.49.134.516.13a.499.499 0 00.183-.132c.01-.024.055-.15.034-.532-.026-.497-.142-1.184-.32-2.217l-.139-.81-.02-.114c-.082-.476-.166-.957-.066-1.431.037-.174.092-.344.164-.506.198-.442.549-.782.895-1.118a20.8 20.8 0 00.083-.08l.59-.575c.75-.731 1.247-1.219 1.561-1.606.241-.296.28-.424.285-.45a.5.5 0 00-.07-.215c-.02-.017-.126-.098-.495-.196-.482-.129-1.17-.23-2.207-.381l-.815-.119-.113-.016c-.479-.068-.963-.138-1.382-.379a2.5 2.5 0 01-.43-.312c-.36-.325-.575-.763-.788-1.197a31.757 31.757 0 00-.05-.103l-.364-.738c-.464-.94-.774-1.564-1.045-1.982-.208-.321-.317-.397-.34-.41a.5.5 0 00-.226 0zm8.326 6.044v.002-.002zm-3.246 9.575h-.002.002zm-9.934 0h.002-.002zm-3.246-9.575v.002-.002z" clip-rule="evenodd"></path></svg></div></div></div></div></div><div class="popover-wrapper inline-block" data-headlessui-state=""><div><div id="headlessui-popover-button-:r13:" aria-expanded="false" data-headlessui-state=""><div class="flex h-full cursor-pointer items-center rounded p-[3px] text-lg text-dark-gray-6 transition-colors dark:text-dark-gray-6 hover:bg-fill-3 dark:hover:bg-dark-fill-3"><svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="1em" height="1em" fill="currentColor"><path fill-rule="evenodd" d="M11.5 5.5a7 7 0 107 7 1 1 0 112 0 9 9 0 11-9-9 1 1 0 110 2z" clip-rule="evenodd"></path><path fill-rule="evenodd" d="M20.207 3.793a1 1 0 010 1.414l-7 7a1 1 0 01-1.414-1.414l7-7a1 1 0 011.414 0z" clip-rule="evenodd"></path><path fill-rule="evenodd" d="M14.5 4.5a1 1 0 011-1h4a1 1 0 011 1v4a1 1 0 11-2 0v-3h-3a1 1 0 01-1-1z" clip-rule="evenodd"></path></svg></div></div></div></div></div></div></div><div class="px-5 pt-3"><div class="relative"><div class="inline-block"><div class="transition-colors flex cursor-pointer items-center space-x-1.5 rounded-[21px] px-2 py-1 text-xs hover:bg-opacity-20 bg-fill-3 dark:bg-dark-fill-3 text-label-2 dark:text-dark-label-2 hover:bg-fill-2 dark:hover:bg-dark-fill-2"><svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="1em" height="1em" fill="currentColor" class="text-brand-orange dark:text-dark-brand-orange"><path fill-rule="evenodd" d="M7 8v2H6a3 3 0 00-3 3v6a3 3 0 003 3h12a3 3 0 003-3v-6a3 3 0 00-3-3h-1V8A5 5 0 007 8zm8 0v2H9V8a3 3 0 116 0zm-3 6a2 2 0 100 4 2 2 0 000-4z" clip-rule="evenodd"></path></svg><span>Companies</span></div></div></div></div><div class="px-5 pt-4"><div class="flex"></div><div class="xFUwe" data-track-load="description_content"><p>Given a directed acyclic graph (<strong>DAG</strong>) of <code>n</code> nodes labeled from <code>0</code> to <code>n - 1</code>, find all possible paths from node <code>0</code> to node <code>n - 1</code> and return them in <strong>any order</strong>.</p>
 *
 * <p>The graph is given as follows: <code>graph[i]</code> is a list of all nodes you can visit from node <code>i</code> (i.e., there is a directed edge from node <code>i</code> to node <code>graph[i][j]</code>).</p>
 *
 * <p>&nbsp;</p>
 * <p><strong class="example">Example 1:</strong></p>
 * <img alt="" src="https://assets.leetcode.com/uploads/2020/09/28/all_1.jpg" style="width: 242px; height: 242px;">
 * <pre><strong>Input:</strong> graph = [[1,2],[3],[3],[]]
 * <strong>Output:</strong> [[0,1,3],[0,2,3]]
 * <strong>Explanation:</strong> There are two paths: 0 -&gt; 1 -&gt; 3 and 0 -&gt; 2 -&gt; 3.
 * </pre>
 *
 * <p><strong class="example">Example 2:</strong></p>
 * <img alt="" src="https://assets.leetcode.com/uploads/2020/09/28/all_2.jpg" style="width: 423px; height: 301px;">
 * <pre><strong>Input:</strong> graph = [[4,3,1],[3,2,4],[3],[4],[]]
 * <strong>Output:</strong> [[0,4],[0,3,4],[0,1,3,4],[0,1,2,3,4],[0,1,4]]
 * </pre>
 *
 * <p>&nbsp;</p>
 * <p><strong>Constraints:</strong></p>
 *
 * <ul>
 * 	<li><code>n == graph.length</code></li>
 * 	<li><code>2 &lt;= n &lt;= 15</code></li>
 * 	<li><code>0 &lt;= graph[i][j] &lt; n</code></li>
 * 	<li><code>graph[i][j] != i</code> (i.e., there will be no self-loops).</li>
 * 	<li>All the elements of <code>graph[i]</code> are <strong>unique</strong>.</li>
 * 	<li>The input graph is <strong>guaranteed</strong> to be a <strong>DAG</strong>.</li>
 * </ul>
 */
public class HotelItineraryPlanning {

    public static List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        //Pseudo code
        // start from graph[0] get all locations for each location continue until we find the end return the ist of path
        
        List<List<Integer>> result = new ArrayList<>();

        dfs(graph, 0, graph.length - 1, new ArrayList<>(), result);

        return result;
    }

    public static List<Integer> allPathsSourceTargetAdapted(int[][] graph) {
        //Pseudo code
        // start from graph[0] get all locations for each location continue until we find the end return the ist of path

        PriorityQueue<List<Integer>> result = new PriorityQueue<>(
                (x,y) -> Integer.compare(y.size(), x.size())
        );

        dfsAdapted(graph, 0, graph.length - 1, new ArrayList<>(), result);

        return result.isEmpty() ? List.of() : result.poll();
    }

    private static void dfsAdapted(int[][] graph, int current, int end, List<Integer> path, PriorityQueue<List<Integer>> result){

        path.add(current);
        if(current == end){
            return;
        }

        for (int i : graph[current]){
            List<Integer> newPath = new ArrayList<>(path);
            dfsAdapted(graph, i, end, newPath, result);

            // In the other solution we may make start to be the return destination (end) also. In order to simulate a round trip
            // We might also have to calculate for HERE flight/hotel when we reach the end in other to determine if it is within budget.
            if(!newPath.isEmpty() && newPath.get(newPath.size() - 1) == end){
                result.add(newPath);
            }
        }
    }

    private static void dfs(int[][] graph, int current, int end, List<Integer> path, List<List<Integer>> result){

        path.add(current);
        if(current == end){
            return;
        }

        for (int i : graph[current]){
            List<Integer> newPath = new ArrayList<>(path);
            dfs(graph, i, end, newPath, result);

            if(!newPath.isEmpty() && newPath.get(newPath.size() - 1) == end){
                result.add(newPath);
            }
        }
    }

    public static void main(String[] args) {
        int[][] graph = { // [[2,0,3],[2,0,3],[2,0,3]]
                {1,2},
                {3},
                {3},
                {}
        };

        int[][] matrix = { // [[2,0,3],[2,0,3],[2,0,3]]
                {4,3,1},
                {3,2,4},
                {3},
                {4},
                {}
        };

        allPathsSourceTarget(matrix)
                .forEach(path -> System.out.println("Found path: " + path.stream().map(Objects::toString).collect(Collectors.joining("->"))));

        System.out.println();

        System.out.println("Longest Itinerary " + allPathsSourceTargetAdapted(matrix).stream().map(Objects::toString).collect(Collectors.joining("->")));

    }
}
