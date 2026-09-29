/*
 * PRACTICE 14: Linear search - check every element one by one
 * Works on ANY array (sorted or not). Time: O(n)
 * Concepts: method returning an index, -1 meaning "not found"
 * RUN: java LinearSearch.java
 */
public class LinearSearch {

    static int search(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return i;               // found -> return its position
            }
        }
        return -1;                      // checked everything, not there
    }

    public static void main(String[] args) {
        int[] data = {7, 3, 9, 1, 5};
        System.out.println("Index of 9: " + search(data, 9));
        System.out.println("Index of 4: " + search(data, 4));
    }
}

/*
 * ------------------------- OUTPUT -------------------------
 * Index of 9: 2
 * Index of 4: -1
 * ----------------------------------------------------------
 */
