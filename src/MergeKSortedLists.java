import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class MergeKSortedLists {
    public static void main(String[] args) {
        List<Integer> a = Arrays.asList(0,1,2,5,6,7,9);
        List<Integer> b = Arrays.asList(-2,-1,0,2,2,3,5,6,6,8,19);

        //System.out.println(Arrays.toString(mergeTwoSortedLists(a,b).toArray(new Integer[0])));

        System.out.println(Arrays.toString(mergeKSortedLists(Arrays.asList(a, b, Arrays.asList(-20,20))).toArray(new Integer[0])));
    }

    // O(n)
    private static List<Integer> mergeTwoSortedLists(List<Integer> list1, List<Integer> list2){
        List<Integer> result = new ArrayList<>();
        int i = 0;
        int j = 0;
        while(i < list1.size() && j < list2.size()){
            if(list1.get(i) < list2.get(j)){
                result.add(list1.get(i));
                ++i;
            }else{
                result.add(list2.get(j));
                ++j;
            }
        }
        if(i < list1.size()){
            for(;i < list1.size(); i++) result.add(list1.get(i));
        }else{
            for(;j < list2.size(); j++) result.add(list2.get(j));
        }
        return result;
    }
    // O(n)
    private static LinkedNode mergeTwoSortedLinkedLists(LinkedNode node1, LinkedNode node2){
        LinkedNode head = new LinkedNode(-1);
        LinkedNode curr = head;

        while (node1 != null && node2 != null){
            if(node1.val < node2.val){
                curr.next = new LinkedNode(node1.val);
                node1 = node1.next;
            }else{
                curr.next = new LinkedNode(node2.val);
                node2 = node2.next;
            }
            curr = curr.next;
        }

        curr.next = node1 != null ? node1 : node2;

        return head.next;
    }

    static class LinkedNode {
        int val;
        LinkedNode next;

        public LinkedNode(int val){this.val = val;}
        public LinkedNode(int val, LinkedNode node){
            this.val = val;
            this.next = node;
        }
    }

    // O(n * log(k))
    private static List<Integer> mergeKSortedLists(List<List<Integer>> lists){
        if(lists == null || lists.size() == 0) return null;

        int interval = 1;
        while(interval < lists.size()){
            for(int i = 0; i + interval < lists.size(); i = i + interval * 2){
                lists.set(i, mergeTwoSortedLists(lists.get(i), lists.get(i + interval)));
            }

            interval = interval * 2;
        }

        return lists.get(0);
    }
    // O(n * log(k))
    private static LinkedNode mergeKSortedLinkedLists(LinkedNode[] lists){
        if(lists == null || lists.length == 0) return null;

        int interval = 1;
        while(interval < lists.length){
            for(int i = 0; i < lists.length; i = i + interval * 2){
                lists[0] = mergeTwoSortedLinkedLists(lists[i], lists[i + interval]);
            }
            interval = interval * 2;
        }

        return lists[0];
    }
}
