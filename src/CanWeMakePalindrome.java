/**
 * Question: can we make a palindrome from a given substring of a string after number of operations
 */
public class CanWeMakePalindrome {
    public static void main(String[] args) {
        System.out.println(canWeMakePalindrome("cdecd", new int[]{0,0,0,0}, new int[]{0,1,2,3}, new int[]{0,1,1,0}));
        System.out.println(canWeMakePalindrome("xxdnssuqevu", new int[]{0}, new int[]{10}, new int[]{3}));
    }

    public static String canWeMakePalindrome(String word, int[] startIndex, int[] endIndex, int[] substitution){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < startIndex.length; i++){
            int res = 1;
            char[] toCheck = word.substring(startIndex[i], endIndex[i] + 1).toCharArray();
            int sub = toCheck.length % 2 == 1 ? ++substitution[i] : substitution[i];

            int[] occ = new int[26];
            for(char c: toCheck){
                occ[c - 'a']++;
            }
            int odd = 0;
            for (int k : occ) {
                if (k % 2 != 0) {
                    odd++;
                }
            }
            if(odd > sub && Math.ceil(odd/2) > sub){
                res = 0;
            }
            sb.append(res);
        }
        return sb.toString();
    }
}
