import java.util.*;
import java.util.stream.Collectors;

public class NailHammer {

    public static void main(String[] args) {
        System.out.println("Something cooking");
        int[] A = new int[]{1, 4, 5, 8};
        int[] B = new int[]{4, 5, 9, 10};
        int[] C = new int[]{4, 6, 7, 10, 2};

        System.out.println(NailingPlanks.solution(A, B, C));
    }

    static int hammerNails(int[] A, int[] B, int[] C){
        //Arrays.sort(C); // sort the nails C
        int[] nailLine = new int[2 * C.length + 1]; // create an array(nailLine) whose index, i is A[i] and nailLine(i) is B[i]

        //nailLine = reverseArray(nailLine);

        // fill in the line such that for plank(A[i]B[i]) if B[i] is smaller for same A[i]
        //then the plank with lesser B[i] takes precedence because it can replace the latter
        // maintain max and min boundary of plant
        int plankMaxBoundary = Integer.MIN_VALUE;
        int plankMinBoundary = Integer.MAX_VALUE;
        for(int i = 0; i < A.length; i++){
            if(nailLine[ A[i] ] == 0 || B[i] < nailLine[A[i]]){
                nailLine[ A[i] ] = B[i];
            }
            plankMaxBoundary = Math.max(plankMaxBoundary, B[i]);
            plankMinBoundary = Math.min(plankMinBoundary, A[i]);
        }
        Set<Integer> nailsUsed = new HashSet<>();
        List<Integer> cList = Arrays.stream(C).boxed().sorted().collect(Collectors.toList());
        for(int j = nailLine.length - 1; j > 0; j--){
            if(nailLine[j] != 0){
                int nail = checkNail(j, nailLine[j], cList);
                if(nail == -1)return -1;
                nailsUsed.add(nail);
            }
        }


        return nailsUsed.size();
    }

    private static int checkNail(int lower, int upper, List<Integer> cList) {
        for (int k = cList.size() - 1; k >= 0; k--){
            if(cList.get(k) <= upper && cList.get(k) >= lower){
                return cList.get(k);
            }else {
                cList.remove(cList.size() - 1);
            }
        }

        return -1;
    }

    static int[] reverseArray(int[] arr){
        int len = arr.length;
        int[] reversedArray = new int[arr.length];

        for(int i = len - 1; i >= 0; i--){
            reversedArray[len - 1 - i] = arr[i];
        }

        return reversedArray;
    }

    static int hammerNails2(int[] A, int[] B, final int[] C){
        Queue<String> qPlank = new LinkedList<>();
        for (int i = 0; i < A.length; i++){
            String s = A[i] + "-" + B[i];
            qPlank.add(s);
        }


        Queue<Integer> qNails = Arrays.stream(C).boxed().collect(Collectors.toCollection(LinkedList::new));
        Integer nail = qNails.peek();
        while(!qPlank.isEmpty()){
            String cur = qPlank.poll();
            int lower = Integer.parseInt(cur.split("-")[0]);
            int upper = Integer.parseInt(cur.split("-")[1]);

//            if(nail != null && checkNail2(lower, upper, nail)){
//                qNails.last()
//            }
        }
        return -1;
    }


    // Online Solution
    public static int solution(int[] A, int[] B, final int[] C) {

        Integer[] nailIndex = new Integer[C.length];
        for (int i = 0; i < C.length; i++) {
            nailIndex[i] = i;
        }
        Comparator<Integer> nailCompare = new Comparator<Integer>() {

            public int compare(Integer arg0, Integer arg1) {
                if (C[arg0] == C[arg1]) {
                    return arg0 - arg1;
                }
                return C[arg0] - C[arg1];
            }

        };
        Arrays.sort(nailIndex, nailCompare);
        int[] bstArray = buildTree(nailIndex);
        int j = 0;
        for (int i = 0; i < A.length; i++) {
            int leftMost = findNail(A[i], B[i], C, nailIndex, true);
            if (leftMost == -1) {
                return -1;
            }
            int rightMost = findNail(A[i], B[i], C, nailIndex,  false);

            if (leftMost == rightMost) {
                j = Math.max(j, nailIndex[leftMost]);
            }
            int index = query(bstArray, nailIndex, leftMost, rightMost);
            j = Math.max(j, nailIndex[index]);
        }
        return j + 1;
    }

    static int findNail(int lower, int upper, int[] nails, Integer[] nailIndex, boolean leftMost) {
        int start = 0;
        int end = nails.length - 1;
        int index = -1;
        while (start <= end) {
            int mid = (end - start) / 2 + start;
            if (lower <= nails[nailIndex[mid]] && nails[nailIndex[mid]] <= upper) {
                index = mid;
                if (leftMost) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            } else if (nails[nailIndex[mid]] > upper) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return index;
    }

    public static int[] buildTree(Integer[] nailIndex) {
        int x = (int) Math.ceil(Math.log(nailIndex.length) / Math.log(2));
        int[] bstArray = new int[2 * (1 << x) - 1];

        buildTree(nailIndex, bstArray, 0, 0, nailIndex.length - 1);

        return bstArray;
    }

    // stores the index of the minimum element in a given range
    private static int buildTree(Integer[] nailIndex, int[] bstArray, int index, int start, int end) {

        if (start == end) {
            bstArray[index] = start;
            return start;
        }
        int left = buildTree(nailIndex, bstArray, 2 * index + 1, start, (end - start) / 2 + start);
        int right = buildTree(nailIndex, bstArray, 2 * index + 2, (end - start) / 2 + start + 1, end);

        bstArray[index] = (nailIndex[left] <= nailIndex[right]) ? left : right;
        return bstArray[index];

    }

    public static int query(int[] bstArray, Integer[] nailIndex, int leftMost, int rightMost) {

        if (leftMost < 0 || rightMost >= bstArray.length) {
            return -1;
        }
        return query(bstArray, nailIndex, 0, 0, nailIndex.length - 1, leftMost, rightMost);
    }

    private static int query(int[] nodes, Integer[] A, int index, int start, int end, int a, int b) {

        if (a > end || b < start) {
            return -1;
        }

        if (start >= a && end <= b) {
            return nodes[index];
        }
        int left = query(nodes, A, 2 * index + 1, start, (end - start) / 2 + start, a, b);
        int right = query(nodes, A, 2 * index + 2, (end - start) / 2 + start + 1, end, a, b);
        if (left == -1) {
            return right;
        }
        if (right == -1) {
            return left;
        }
        return (A[left] <= A[right]) ? left : right;
    }
}
