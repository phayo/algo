package dynamic;

import java.util.Arrays;

public class LongestCommonSubSeq {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(commonSubStringDynamicProgApproach("aaaaaaaa".toCharArray(), "aaaaaaab".toCharArray())));
    }

    public static String[] commonSubStringDynamicProgApproach(char[] firstString, char[] secondString){
        int[][] temp = new int[firstString.length + 1][secondString.length + 1];
        int max = 0;
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i < temp.length; i++){
            for(int j = 1; j < temp[i].length; j++){
                if(firstString[i-1] == secondString[j-1]){
                    temp[i][j] = 1 + temp[i - 1][j - 1];
                }else {
                    temp[i][j] = Math.max(temp[i][j-1], temp[i-1][j]);
                }
                max = Math.max(temp[i][j], max);
            }
        }
        int i = firstString.length;
        int j = secondString.length;
        while(i > 0 && j > 0){
            if(firstString[i-1] == secondString[j-1]){
                sb.append(firstString[i-1]);
                i--;
                j--;
            }else if(temp[i][j-1] >= temp[i-1][j]){
                j--;
            }else{
                i--;
            }
        }

        return new String[]{"" + max, sb.reverse().toString()};
    }
}
