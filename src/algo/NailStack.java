package algo;

public class NailStack {
    public static void main(String[] args) {
        System.out.println("Something cooking");
        int[] A = new int[]{1, 4, 5, 8};
        int[] B = new int[]{4, 5, 9, 10};
        int[] C = new int[]{4, 6, 7, 10, 2};

        System.out.println(solution(A, B, C));
    }

    private static int solution(int[] A, int[] B, int[] C) {
        //Arrays.sort(C); // sort the nails C
        int[] planks = new int[2 * C.length + 1]; // create an array(planks) whose index, i is A[i] and planks(i) is B[i]

        //planks = reverseArray(planks);

        // fill in the line such that for plank(A[i]B[i]) if B[i] is smaller for same A[i]
        //then the plank with lesser B[i] takes precedence because it can replace the latter
        // maintain max and min boundary of plant
        for(int i = 0; i < A.length; i++){
            if(planks[ A[i] ] == 0 || B[i] < planks[A[i]]){
                planks[ A[i] ] = B[i];
            }
        }

        Queue stack = new Queue(2 * C.length);
        for (int i = 1; i < planks.length; i++) {
            if (planks[i] != 0) {
                while (!stack.isEmpty() && planks[i] <= planks[stack.last()]) {
                    stack.removeLast();
                }
                stack.addLast(i);
            }
        }

        int[] ends = new int[planks.length];
        while (!stack.isEmpty()) {
            int start = stack.removeLast();
            ends[start] = planks[start];
        }

        return -1;
    }

    private static class Queue {
        private int[] array;
        private int start = 0;
        private int end = -1;
        private int size = 0;

        Queue(int capacity) {
            this.array = new int[capacity];
        }
        void addLast(int element) {
            size++;
            end++;
            if (end == array.length) {
                end = 0;
            }
            array[end] = element;
        }
        int removeLast() {
            size--;
            int result = array[end--];
            if (end < 0) {
                end = array.length - 1;
            }
            return result;
        }
        int removeFirst() {
            size--;
            int result = array[start++];
            if (start == array.length) {
                start = 0;
            }
            return result;
        }
        int removeNextToLast() {
            size--;
            int index = nextToLastIndex();
            int result = array[index];
            array[index] = array[end];
            end = index;
            return result;
        }
        int last() {
            return array[end];
        }
        int first() {
            return array[start];
        }
        int nextToLast() {
            return array[nextToLastIndex()];
        }
        boolean isEmpty() {
            return size == 0;
        }
        int size() {
            return size;
        }
        int nextToLastIndex() {
            return end > 0 ? end - 1 : array.length - 1;
        }
    }
}
