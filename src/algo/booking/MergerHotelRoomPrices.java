package algo.booking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Stack;
import java.util.TreeMap;

/**
 * Merge two hotel objects based on there high and low price range. Hotel objects were not in order.
 *
 *   Merge HotelRoom object when highPrice + 1 = lowPrice of other object
 *   Ex.1  [1,2], [3,4] ==> [1,4]
 *   Ex.2  [1,5],[6,9] ==> [1,9]
 *   Ex.3 [1,5], [14, 17], [6,9], [10,13] ==> [1,17]
 *   Ex.3 [1,5], [14, 17], [6,9], [10,13], [4,7], [8,12] ==> [1,17], [4,12]
 */
public class MergerHotelRoomPrices {

    public static List<int[]> mergeHotelRooms(int[][] hotels){

        Arrays.sort(hotels, Comparator.comparingInt((int[] a) -> a[0]));

        HashMap<Integer, Stack<int[]>> hm = new HashMap<>();

        for(int[] hotel: hotels){
            int lowPrice = hotel[0];
            int highPrice = hotel[1];

            if(hm.containsKey(lowPrice-1)){
                // pop first element
                int[] interval = hm.get(lowPrice-1).pop();

                if(hm.get(lowPrice-1).empty()) hm.remove(lowPrice-1);

                hm.computeIfAbsent(highPrice, k-> new Stack<>()).push(new int[]{interval[0], highPrice});
            }else{
                hm.computeIfAbsent(highPrice, k-> new Stack<>()).push(hotel);
            }
        }

        List<int[]> al = new ArrayList<>();
        for(int key: hm.keySet()){
            Stack<int[]> st = hm.get(key);
            while(!st.empty()) al.add(st.pop());
        }
        return al;
    }

    public static List<int[]> mergeHotelRoomsTreemap(int[][] hotels){

        Arrays.sort(hotels, Comparator.comparingInt((int[] a) -> a[1]));

        TreeMap<Integer, int[]> tm = new TreeMap<>();

        for (int[] hotel: hotels){
            // check if treemap contains the curr hotel end price -1
            Entry<Integer, int[]> found = tm.floorEntry(hotel[0]);
            if (found != null && found.getKey() + 1 == hotel[0]){
                tm.put(hotel[1], new int[]{found.getValue()[0], hotel[1]});
                tm.remove(found.getKey());
                continue;
            }

            tm.put(hotel[1], hotel);
        }

        return tm.values().stream().toList();
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {1,5},
                {14, 17},
                {6,9},
                {10,13},
                {8,12},
                {4,7}
        };

        mergeHotelRoomsTreemap(matrix)
                .forEach( i -> System.out.println(Arrays.toString(i)));
    }
}
