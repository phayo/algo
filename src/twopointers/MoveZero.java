package twopointers;

import java.util.Arrays;

public class MoveZero {
    public static void main(String[] args) {
        int[] arr = new int[]{1, 0, 2, 0, 0, 7};
        System.out.println(Arrays.toString(moveZero(arr)));
    }

    static int[] moveZero(int[] zeroArr){
        int zeroIdx = -1;
        for(int i = 0; i < zeroArr.length; i++){
            if(zeroArr[i] != 0){
                if(zeroIdx >= 0){
                    zeroArr[zeroIdx] = zeroArr[i];
                    zeroArr[i] = 0;
                    zeroIdx = i;
                }
            }else {
                if(zeroIdx < 0) zeroIdx = i;
            }
        }
        return zeroArr;
    }
}
