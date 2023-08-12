package algo;

import java.util.*;

public class OverlappingIntervals {

    public static void main(String[] args) {
        List<int[]> collapsed = collapseOverlap(new ArrayList<>(List.of(new int[]{6,7}, new int[]{6,10}, new int[]{0,1}, new int[]{1,2}, new int[]{2,5}, new int[]{3,5})));
        for(int[] arr: collapsed) System.out.println(Arrays.toString(arr));
    }

    public static List<int[]> collapseOverlap(List<int[]> overlapped){
        LinkedList<int[]> result = new LinkedList<>();
        if(overlapped.isEmpty()) return result;

        Collections.sort(overlapped, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        result.add(overlapped.get(0));
        for(int i = 1; i < overlapped.size(); i++){
            if(overlapped.get(i)[0] <= result.getLast()[1]){
                int[] last = result.removeLast();
                result.add(new int[]{Math.min(overlapped.get(i)[0], last[0]), Math.max(overlapped.get(i)[1], last[1])});
            } else {
                result.add(overlapped.get(i));
            }
        }

        return result;
    }
}
