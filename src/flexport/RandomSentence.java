package flexport;

import java.util.*;

public class RandomSentence {
    public String generateRandomString(String paragraph, int n) {
        if(paragraph == null || paragraph.length() == 0) return null;

        StringBuilder res = new StringBuilder();

        String[] words = paragraph.split(" ");
        int len = words.length;

        Map<String, Set<String>> map = new HashMap<>();
        for(int i = 0; i < len; i++) {
            String curWord = words[i];
            String nextWord = "";
            if(i == len-1) { //means the last one word, so the next word is the first one
                nextWord = words[0];
            } else {
                nextWord = words[i+1];
            }

            if(map.containsKey(curWord)) {
                map.get(curWord).add(nextWord);
            } else {
                Set<String> nextWordSet = new HashSet<>();
                nextWordSet.add(nextWord);
                map.put(curWord, nextWordSet);
            }
        }

        Random randomObj = new Random();
        int randomIndex = randomObj.nextInt(len);

        System.out.println(" len = " + len +  "===== randomIndex == " + randomIndex);

        String randomWord = words[randomIndex];

        while(n > 0) {
            res.append(randomWord).append(" ");

            Set<String> nextWordSet = map.get(randomWord);
            String[] nextWords = convertSetToStringArray(nextWordSet);

            randomIndex = randomObj.nextInt(nextWords.length);
            randomWord = nextWords[randomIndex];

            n--;
        }
        return res.toString();
    }

    private String[] convertSetToStringArray(Set<String> set) {
        if(set == null || set.size() == 0) return null;

        int len = set.size();
        String[] stringArray = new String[len];
        int i = 0;
        for(String e: set) {
            stringArray[i++] = e;
        }
        return stringArray;
    }
}
