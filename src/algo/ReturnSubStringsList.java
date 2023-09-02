package algo;

import java.util.ArrayList;
import java.util.List;

public class ReturnSubStringsList {

    static List<String> returnSubStringslist(List<String> words){
        String joined = String.join(" ", words);

        List<String> result =  new ArrayList<>();
        for (String word: words){
            if(joined.indexOf(word) != joined.lastIndexOf(word)){
                result.add(word);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        List<String> stringList = List.of("test", "es", "Sutest", "eat", "great");

        returnSubStringslist(stringList)
                .forEach(System.out::println);
    }
}
