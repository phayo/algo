package algo.twopointers;

import java.util.HashSet;
import java.util.Set;

public class NumberOfLongestUniqueSubString {

    public static void main(String[] args) {
        System.out.println(numberOfLongestUniqueSubString("abccabcabcc"));
        System.out.println(numberOfLongestUniqueSubString("aaaabaaa"));
        System.out.println(numberOfLongestUniqueSubString("ashahegshdjsbejeksbdgesbehheeeeehvshdqkcyugqikvqd"));
    }


    public static int numberOfLongestUniqueSubString(String word){

        Set<Character> found = new HashSet<>();
        int max = Integer.MIN_VALUE;
        for(int i = 0; i < word.length(); i++){
            if(found.contains(word.charAt(i))){
                found.clear();
            }else{
                found.add(word.charAt(i));
                max = Math.max(max, found.size());
            }
        }
        return  max;
    }
}