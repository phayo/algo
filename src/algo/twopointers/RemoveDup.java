package algo.twopointers;

public class RemoveDup {
    public static int removeDup(int[] sortedArr){
        if(sortedArr.length == 0) return 0;
        int i = 1;
        int j = 1;
        while(i < sortedArr.length){
            if(sortedArr[i-1] != sortedArr[i]) {
                sortedArr[j] = sortedArr[i];
                j++;
            }
            i++;
        }

        return j;
    }

    public static void main(String[] args) {
        int[] a = new int[]{0, 0, 0, 0, 0, 0, 0};
        System.out.println(removeDup(a));
    }
}
