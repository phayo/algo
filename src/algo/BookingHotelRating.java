package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.Map.Entry;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class BookingHotelRating {
    public static void main(String[] args) throws IOException {
//        String word = "a. boy is, famous, for committing fouls.";
//        System.out.println(Arrays.toString(word.split("[,|.| ]")));

        BufferedReader bufferedReader = new BufferedReader(new FileReader("/Users/chukwuebuka/dev/algo/src/input.txt"));

        int textCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<String> text = IntStream.range(0, textCount).mapToObj(i -> {
                    try {
                        return bufferedReader.readLine();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                })
                .toList();

        List<String> l = funWithAnagrams(text);
        //List<String> l = funWithAnagrams(List.of("poke","pkoe","okpe","ekop"));
        //List<String> l = funWithAnagrams(List.of("code","aaagmnrs","anagrams","doce"));
        String[] lString = l.toArray(String[]::new);
        System.out.println(Arrays.toString(lString));
    }


    public static List<Integer> awardTopKHotels(String positiveKeywords, String negativeKeywords,
                                                List<Integer> hotelIds,
                                                List<String> reviews, int k) {
        Set<String> positiveWords = new HashSet<>();
        Set<String> negativeWords = new HashSet<>();

        for (String word: positiveKeywords.split(" ")) {
            positiveWords.add(word.toLowerCase());
        }

        for (String word: negativeKeywords.split(" ")) {
            negativeWords.add(word.toLowerCase());
        }

        Map<Integer, Integer> hotelScore = new HashMap<>();
        for (int i = 0; i < reviews.size(); i++) {
            int hotel = hotelIds.get(i);
            int score = hotelScore.getOrDefault(hotel, 0);

            int pos=0, neg=0;
            for (String word: reviews.get(i).split("[,|.| ]")) {
                if (word.charAt(word.length()-1) == '.' || word.charAt(word.length()-1) == ',') {
                    word = word.substring(0, word.length() - 1);
                }
                if (positiveWords.contains(word.toLowerCase())) {
                    pos++;
                }
                if (negativeWords.contains(word.toLowerCase())) {
                    neg++;
                }
            }
            hotelScore.put(hotel, score + 3*pos - neg);
        }

        PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>(
                (a,b) -> Objects.equals(a.getValue(), b.getValue()) ? b.getKey().compareTo(a.getKey()) : a.getValue()-b.getValue()
        );


        for(Map.Entry<Integer, Integer> entry: hotelScore.entrySet())
        {
            pq.offer(entry);
        }

        List<Integer> result = new ArrayList<>();
        while(!pq.isEmpty() && k > 0){
            result.add(0, pq.poll().getKey());
            k--;
        }

        return result;
    }

    /**
     * You are given two string arrays positive_feedback and negative_feedback, containing the words denoting positive and negative feedback, respectively.
     * Note that no word is both positive and negative.
     * Initially every student has 0 points. Each positive word in a feedback report increases the points of a student by 3,
     * whereas each negative word decreases the points by 1.
     * You are given n feedback reports, represented by a 0-indexed string array report and a 0-indexed integer array student_id,
     * where student_id[i] represents the ID of the student who has received the feedback report report[i]. The ID of each student is unique.
     * Given an integer k, return the top k students after ranking them in non-increasing order by their points.
     * In case more than one student has the same points, the one with the lower ID ranks higher.
     *
     * @param positive_feedback List of words that denote positive feedback
     * @param negative_feedback List of words that denote negative feedback
     * @param report List of reports for each student i
     * @param student_id List of student id i
     * @param k The number of the top students we are interested in.
     * @return list of student ids according to the top performing
     */
    public List<Integer> topStudents(String[] positive_feedback, String[] negative_feedback, String[] report, int[] student_id, int k) {
        Set<String> positiveWords = new HashSet<>();
        Set<String> negativeWords = new HashSet<>();

        for (String word: positive_feedback) {
            positiveWords.add(word.toLowerCase());
        }

        for (String word: negative_feedback) {
            negativeWords.add(word.toLowerCase());
        }

        Map<Integer, Integer> studentScore = new HashMap<>();
        for (int i = 0; i < report.length; i++) {
            int hotel = student_id[i];
            int score = studentScore.getOrDefault(hotel, 0);

            int pos=0, neg=0;
            for (String word: report[i].split("[,|.| ]")) {
                if (word.charAt(word.length()-1) == '.' || word.charAt(word.length()-1) == ',') {
                    word = word.substring(0, word.length() - 1);
                }
                if (positiveWords.contains(word.toLowerCase())) {
                    pos++;
                }
                if (negativeWords.contains(word.toLowerCase())) {
                    neg++;
                }
            }
            studentScore.put(hotel, score + 3*pos - neg);
        }

        studentScore.forEach((key, val) -> System.out.println( key + " -> " + val));

        PriorityQueue<Entry<Integer, Integer>> pq = new PriorityQueue<>(
                (a,b) -> Integer.compare(b.getValue(), a.getValue())
        );

        for(Map.Entry<Integer, Integer> entry: studentScore.entrySet())
        {
            pq.offer(entry);
        }

        System.out.println("PQ:" + Arrays.toString(pq.toArray()));

        List<Integer> result = new ArrayList<>();
        while(!pq.isEmpty() && k > 0){
            result.add(pq.poll().getKey());
            k--;
        }

        return result;
    }

    public static List<String> funWithAnagrams(List<String> text) {
        TreeSet<String> unique = new TreeSet<>(
                (x,y) -> isAnagram(x,y) ? 0 : x.compareTo(y)
        );

        for(String s: text){
            if(unique.isEmpty())
                unique.add(s);

            boolean found = false;
            Iterator<String> iterator = unique.iterator();
            while (iterator.hasNext()) {
                String u = iterator.next();

                if (isAnagram(s, u)) {
                    found = true;
                    break;
                }
            }
            if(!found)
                unique.add(s);
        }

//        Collections.sort(new ArrayList<>(text));
//        unique.addAll(text);

        return new ArrayList<>(unique);
    }

    public static boolean isAnagram(String first, String second){
        first = first.replaceAll("\\s", "").toLowerCase();
        second = second.replaceAll("\\s", "").toLowerCase();

        if(first.length() != second.length())
            return false;

        char[] firstChar = first.toCharArray();
        char[] secondChar = second.toCharArray();

        Arrays.sort(firstChar);
        Arrays.sort(secondChar);

        return Arrays.equals(firstChar, secondChar);
    }
}
