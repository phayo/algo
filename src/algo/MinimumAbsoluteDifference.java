package algo;

import java.util.*;

public class MinimumAbsoluteDifference {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(printMinAbsDiff(new int[]{6,2,4,10})));
        System.out.println(Arrays.toString(printMinAbsDiff(new int[]{4,2,1,3})));
        System.out.println(Arrays.toString(printMinAbsDiff(new int[]{4,-2,-1,3})));
    }

    public static String[] printMinAbsDiff(int[] integers){
        int min = Integer.MAX_VALUE;
        TreeSet<String> pairs = new TreeSet<>();
        for(int i = 0; i < integers.length; i++){
            for(int j = i + 1; j < integers.length; j++){
                if(Math.abs(integers[i] - integers[j]) < min){
                    min = Math.abs(integers[i] - integers[j]);
                    pairs.clear();
                    pairs.add(Math.min(integers[i], integers[j]) + " " + Math.max(integers[i], integers[j]));
                }else if(Math.abs(integers[i] - integers[j]) == min){
                    pairs.add(Math.min(integers[i], integers[j]) + " " + Math.max(integers[i], integers[j]));
                }
            }
        }

        return  pairs.toArray(new String[0]);
    }
}
