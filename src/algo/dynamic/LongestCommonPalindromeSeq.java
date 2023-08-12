package algo.dynamic;

public class LongestCommonPalindromeSeq {
    public static void main(String[] args) {
        System.out.println();
    }

    public static String longestPalindromicSeq(String words){
        StringBuilder sb = new StringBuilder(words);
        String[] res = LongestCommonSubSeq.commonSubStringDynamicProgApproach(words.toCharArray(), sb.reverse().toString().toCharArray());

        return res[1];
    }
}
