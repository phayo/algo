package algo;

import java.util.*;
import java.util.stream.Collectors;

public class RaboBankTest {

    public static void main(String[] args) {
        String[] inputs = input.split("\n");
        List<Integer> stockData = new ArrayList<>();
        List<Integer> queries = new ArrayList<>();
        for(int i = 1; i <= Integer.parseInt(inputs[0]); i++){
            stockData.add(Integer.parseInt(inputs[i]));
        }

        for(int i = Integer.parseInt(inputs[0]) + 2;
            i < Integer.parseInt(inputs[0]) + 2 + (Integer.parseInt(inputs[Integer.parseInt(inputs[0]) + 1])); i++){
            queries.add(Integer.parseInt(inputs[i]));
        }

        long start = System.currentTimeMillis();
//        System.out.println(Arrays.toString(stockData.toArray()));
        System.out.println(Arrays.toString(predictAnswerStack(stockData,
                queries).toArray()));
        long end = System.currentTimeMillis() - start;
        System.out.println(start);
        System.out.println(end);
        System.out.println("Time taken = " + end / 1000 / 60 + "min:"+ end / 1000 % 60 +"seconds");
    }

    public static List<Integer> predictAnswer(List<Integer> stockData, List<Integer> queries) {
        // Write your code here
        List<Integer> values = new ArrayList<>();

        for(int query: queries){
            int index = query - 1;
            int currentDayValue = stockData.get(index);
            int i = index - 1;
            int j = index + 1;
            boolean found = false;

            while((i >= 0 || j < stockData.size()) && !found){
                if(i < 0) i = 0;
                if(j > stockData.size() - 1) j = stockData.size() - 1;

                if(stockData.get(i) < currentDayValue || stockData.get(j) < currentDayValue){
                    if(stockData.get(i) < currentDayValue && stockData.get(j) < currentDayValue){
                        values.add(i + 1);
                    } else {
                        if(stockData.get(i) < stockData.get(j)){
                            values.add(i + 1);
                        }else{
                            values.add(j + 1);
                        }
                    }
                    found = true;
                }

                i--;
                j++;

                if(i <= 0 && j >= stockData.size() - 1){
                    values.add(-1);
                    found = true;
                }
            }
        }

        return values;
    }
    public static List<Integer> predictAnswerTree(List<Integer> stockData, List<Integer> queries) {
        // Write your code here
        List<Integer> values = new ArrayList<>();
        Set<Integer> stockDataSet = new HashSet<>(stockData);
        if(stockDataSet.size() == 1){
            int[] returnQuery = new int[queries.size()];
            Arrays.fill(returnQuery, -1);
            return Arrays.stream(returnQuery)
                    .boxed().collect(Collectors.toList());
        }

        for(int query: queries){
            int m = values.size();

            for(int i = 1; i <= Math.max(query - 1, stockData.size() - query - 1); i++){
                if(stockData.get(Math.max(query - 1 - i, 0)) < stockData.get(query - 1) || stockData.get(Math.min(query - 1 + i, stockData.size() - 1)) < stockData.get(query - 1)){
                    if(stockData.get(Math.max(query - 1 - i, 0)) < stockData.get(query - 1) && stockData.get(Math.min(query - 1 + i, stockData.size() - 1)) < stockData.get(query - 1)){
                        values.add(query - i);
                    }else{
                        if(stockData.get(Math.max(query - 1 - i, 0)) < stockData.get(Math.min(query - 1 + i, stockData.size() - 1))){
                            values.add(query - i);
                        }else{
                            values.add(query + i);
                        }
                    }
                    break;
                }
            }

            if(values.size() == m) values.add(-1);
        }

        return values;
    }
    static final class Entry<K, V> implements Map.Entry<K, V> {
        private final K key;
        private V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V value) {
            V old = this.value;
            this.value = value;
            return old;
        }
    }
    public static List<Integer> predictAnswerStack(List<Integer> stockData, List<Integer> queries) {
        // Write your code here
        Stack<Entry<Integer, Integer>> countStack = new Stack<>();

        List<Integer> leftMinimum = new ArrayList<>();
        List<Integer> rightMinimum = new ArrayList<>();
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < stockData.size(); i++) {
            while (!countStack.empty() && countStack.peek().getValue() >= stockData.get(i)) {
                countStack.pop();
            }

            if (countStack.empty()) {
                leftMinimum.add(-1);
            } else {
                leftMinimum.add(countStack.peek().getKey());
            }

            countStack.push(new Entry<>(i + 1, stockData.get(i)));
        }
        System.out.println("stockData=" + Arrays.toString(stockData.toArray()));
        System.out.println("left min array=" + Arrays.toString(leftMinimum.toArray()));

        List<Integer> reversedStockData = new ArrayList<>(stockData);
        Collections.reverse(reversedStockData);

        countStack.clear();
        for (int i = 0; i < reversedStockData.size(); i++) {
            while (!countStack.empty() && countStack.peek().getValue() >= reversedStockData.get(i)) {
                countStack.pop();
            }

            if (countStack.empty()) {
                rightMinimum.add(-1);
            } else {
                rightMinimum.add(countStack.peek().getKey());
            }

            countStack.push(new Entry<>(reversedStockData.size() - i, reversedStockData.get(i)));
        }
        System.out.println("reversedStockData=" + Arrays.toString(reversedStockData.toArray()));
        System.out.println("right min array=" + Arrays.toString(rightMinimum.toArray()));

        for(int query: queries){
            int indexLeft = query - 1;
            int indexRight = stockData.size() - query;

            if(leftMinimum.get(indexLeft) != -1 && rightMinimum.get(indexRight) != -1){
                if(query - leftMinimum.get(indexLeft) <= rightMinimum.get(indexRight) - query){
                    result.add(leftMinimum.get(indexLeft));
                }else {
                    result.add(rightMinimum.get(indexRight));
                }
            } else {
                result.add(Math.max(leftMinimum.get(indexLeft), rightMinimum.get(indexRight)));
            }
        }

        return result;
    }

    private static String smallInput = "10\n" +
            "5\n" +
            "6\n" +
            "8\n" +
            "4\n" +
            "9\n" +
            "10\n" +
            "8\n" +
            "3\n" +
            "6\n" +
            "4\n" +
            "3\n" +
            "3\n" +
            "1\n" +
            "8";

    private static String input = "100\n" +
            "89214\n" +
            "26671\n" +
            "75144\n" +
            "32445\n" +
            "13656\n" +
            "66289\n" +
            "21951\n" +
            "10265\n" +
            "59857\n" +
            "59133\n" +
            "63227\n" +
            "86121\n" +
            "37411\n" +
            "54628\n" +
            "25859\n" +
            "43510\n" +
            "63756\n" +
            "54763\n" +
            "30852\n" +
            "53243\n" +
            "76238\n" +
            "96885\n" +
            "33074\n" +
            "17745\n" +
            "81814\n" +
            "43436\n" +
            "79172\n" +
            "92819\n" +
            "30001\n" +
            "68442\n" +
            "54021\n" +
            "35566\n" +
            "95113\n" +
            "29164\n" +
            "84362\n" +
            "25120\n" +
            "11804\n" +
            "6313\n" +
            "51736\n" +
            "71661\n" +
            "81797\n" +
            "14962\n" +
            "57781\n" +
            "35560\n" +
            "85941\n" +
            "99991\n" +
            "95421\n" +
            "66048\n" +
            "54754\n" +
            "26272\n" +
            "35642\n" +
            "47343\n" +
            "39508\n" +
            "85068\n" +
            "65087\n" +
            "21321\n" +
            "28503\n" +
            "60611\n" +
            "30491\n" +
            "58503\n" +
            "29052\n" +
            "84512\n" +
            "94069\n" +
            "40516\n" +
            "13675\n" +
            "78430\n" +
            "65635\n" +
            "25479\n" +
            "1094\n" +
            "17370\n" +
            "13491\n" +
            "99243\n" +
            "48683\n" +
            "71271\n" +
            "34802\n" +
            "34624\n" +
            "87613\n" +
            "46574\n" +
            "671\n" +
            "42366\n" +
            "89197\n" +
            "36313\n" +
            "89708\n" +
            "28704\n" +
            "21380\n" +
            "54795\n" +
            "66376\n" +
            "49882\n" +
            "15405\n" +
            "96867\n" +
            "24737\n" +
            "60808\n" +
            "81378\n" +
            "35157\n" +
            "1324\n" +
            "11404\n" +
            "29938\n" +
            "66958\n" +
            "53234\n" +
            "47384\n" +
            "50\n" +
            "80\n" +
            "24\n" +
            "26\n" +
            "62\n" +
            "46\n" +
            "79\n" +
            "85\n" +
            "59\n" +
            "52\n" +
            "8\n" +
            "76\n" +
            "48\n" +
            "72\n" +
            "84\n" +
            "3\n" +
            "3\n" +
            "30\n" +
            "30\n" +
            "36\n" +
            "86\n" +
            "96\n" +
            "72\n" +
            "93\n" +
            "25\n" +
            "28\n" +
            "68\n" +
            "81\n" +
            "18\n" +
            "78\n" +
            "14\n" +
            "1\n" +
            "57\n" +
            "90\n" +
            "26\n" +
            "18\n" +
            "87\n" +
            "56\n" +
            "55\n" +
            "97\n" +
            "59\n" +
            "62\n" +
            "73\n" +
            "58\n" +
            "85\n" +
            "8\n" +
            "60\n" +
            "87\n" +
            "89\n" +
            "89\n" +
            "22";
}
