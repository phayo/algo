package algo.algo_expert;

public class MonotonicArray {
    public static boolean isMonotonic(int[] array) {
        // Write your code here.
        boolean inc = true;
        boolean dec = true;
        for(int j = 1; j < array.length; j++){
            int i = j - 1;

            if(array[i] < array[j]){
                inc = false;
            }

            if(array[i] > array[j]){
                dec = false;
            }
        }
            return inc || dec;
    }
}
