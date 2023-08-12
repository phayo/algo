package algo;

import java.util.Arrays;
import java.util.LinkedList;

public class NumberToEnglishWords {
    static String[] words = new String[]{"", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten",
            "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"};
    static String[] tens = new String[]{"twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"};
    static String[] hundreds = new String[]{"", "thousand", "million", "billion", "trillion"};

    public static void main(String[] args) {
        System.out.println(toWords(370196854221L));
    }

    public static String toWords(long number){
        String numberValue = String.format("%,d", number);
        String[] commaGroupedDigit = numberValue.split(",");
        StringBuilder result = new StringBuilder();
        for(int i = 0; i < commaGroupedDigit.length; i++){
            result.append(groupName(commaGroupedDigit[i]))
                    .append(" ")
                    .append(hundreds[commaGroupedDigit.length - i - 1]);
            if(i == commaGroupedDigit.length - 1){
                result.deleteCharAt(result.length() - 1);
                result.append(".");
            }else{
                result.append(", ");
            }
        }
        return Character.toUpperCase(result.charAt(0)) + result.substring(1);
    }

    public static String groupName(String group){
        StringBuilder sb = new StringBuilder();
        if(group.length() == 1){
            sb.append(words[Integer.parseInt(group)]);
            return sb.toString();
        }
        if(group.length() >= 2){
            if(group.length() == 3){
                sb.append(words[Character.getNumericValue(group.charAt(0))]);
                sb.append(" hundred ");
            }

            int tensValue = Character.getNumericValue(group.charAt(group.length() - 2));
            if(tensValue <= 1){
                int t = Integer.parseInt(group.substring(group.length() - 2));
                if(t != 0) sb.append("and ").append(words[t]);
            } else {
                if(group.length() == 3)  sb.append("and ");
                sb.append(tens[tensValue - 2]);
                sb.append(" ");
                sb.append(words[Character.getNumericValue(group.charAt(group.length() - 1))]);
            }
        }
        return sb.toString().trim();
    }
}
