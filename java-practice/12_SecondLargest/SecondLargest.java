/*
 * PRACTICE 12: Find the largest and second largest in ONE pass
 * Concepts: arrays, Integer.MIN_VALUE as a starting point
 * RUN: java SecondLargest.java
 */
public class SecondLargest {
    public static void main(String[] args) {
        int[] nums = {12, 35, 1, 10, 34, 35};
        int first = Integer.MIN_VALUE;    // smallest possible int, so any number beats it
        int second = Integer.MIN_VALUE;

        for (int n : nums) {
            if (n > first) {
                second = first;           // old largest becomes second
                first = n;
            } else if (n > second && n != first) {   // skip duplicates of the largest
                second = n;
            }
        }
        System.out.println("Largest: " + first);
        System.out.println("Second largest: " + second);
    }
}

/*
 * ------------------------- OUTPUT -------------------------
 * Largest: 35
 * Second largest: 34
 * ----------------------------------------------------------
 */
