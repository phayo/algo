package heap;

import java.util.*;

/**
 *  Mean sum after K operation
 *  k is number of times to perform the operation.
 *  Operation is taking any number from the array halving it and adding the ceiling of the result back into the array.
 *  Return the smallest possible sum after k operations
 */
public class MinSumAfterKOperations {

    public static void main(String[] args) {
        System.out.println(7/2);
        System.out.println(minimumSum(List.of(10, 20, 7), 4));
    }

    public static int minimumSum(List<Integer> arr, int k){
        PriorityQueue<Integer> pQ = new PriorityQueue<>(Comparator.reverseOrder());
        pQ.addAll(arr);

        while(k > 0 && !pQ.isEmpty()){
            Integer i = pQ.poll();
            pQ.add((int) Math.ceil(i.doubleValue()/2));
            --k;
        }

        return pQ.stream().mapToInt(a -> a).sum();
    }
}
