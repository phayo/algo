package algo.twopointers;

import java.util.HashSet;

public class MinDeletionToUniqueNumberOfOccurrenceInString {
    public static void main(String[] args) {
        System.out.println(minDeletions("aaabbbcc"));
    }

    public static int minDeletions(String s){
        int deletions = 0;
        int[] count = new int[26];

        for(char c: s.toCharArray()){
            count[c - 'a']++;
        }

        HashSet<Integer> frequencySet = new HashSet<>();
        for(int i : count){
            while(i != 0 && frequencySet.contains(i)){
                deletions++;
                i--;
            }
            frequencySet.add(i);
        }

        return deletions;
    }
}
