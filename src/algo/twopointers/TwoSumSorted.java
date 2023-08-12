package algo.twopointers;

import java.util.Arrays;

public class TwoSumSorted {

    public static void main(String[] args) {
        int[] sortedArr = new int[]{2, 3, 4, 5, 8, 11, 18};
        System.out.println(Arrays.toString(twoSum(sortedArr, 22)));
    }

    public static int[] twoSum(int[] arr, int sum){
        int asc = 0;
        int desc = arr.length - 1;
        boolean found = false;

        while(asc < desc){
            if(arr[asc] + arr[desc] == sum){
                found = true;
                break;
            }
            if(arr[asc] + arr[desc] > sum) desc--;
            if(arr[asc] + arr[desc] < sum) asc++;
        }

        if(found)return new int[]{asc, desc};
        return null;
    }
}
