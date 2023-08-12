package algo.algo_expert;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class MoveIntegers {
    public static List<Integer> moveElementToEnd(
            List<Integer> array, int toMove
    ) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(array);

        LinkedList<Integer> sortedLinkedlist = new LinkedList<>();

        while(!pq.isEmpty()){
            sortedLinkedlist.add(pq.poll());
        }

        System.out.println(Arrays.toString(sortedLinkedlist.toArray(Integer[]::new)));

        while(!sortedLinkedlist.isEmpty() && !sortedLinkedlist.peekLast().equals(toMove)){
            System.out.println("PeekLast item = "+ sortedLinkedlist.peekLast());
            sortedLinkedlist.offerFirst(sortedLinkedlist.pollLast());
        }
        return sortedLinkedlist;
    }

    public static List<Integer> moveElementToEnd2pointer(
            List<Integer> array, int toMove
    ) {

        int i = 0;
        int j = array.size() - 1;

        ArrayList<Integer> arr = new ArrayList<>(array);
        while(i < j){

            if(arr.get(i) == toMove && arr.get(j) != toMove){
                int x = arr.get(i);
                arr.set(i, arr.get(j));
                arr.set(j, x);
            }

            if(arr.get(i) != toMove){
                i++;
            }

            if(arr.get(j) == toMove){
                j--;
            }
        }

        return arr;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(moveElementToEnd2pointer(
                Arrays.asList(2, 4, 2, 5, 6, 2, 2), 2
        ).toArray(Integer[]::new)));
    }
}
