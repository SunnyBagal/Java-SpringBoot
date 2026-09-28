/*
 * ============================================================
 *  TOPIC 12: ARRAYS
 * ============================================================
 *
 *  WHAT: An array stores MANY values of the SAME type in one variable.
 *
 *     int[] marks = {90, 75, 60};
 *     index:          0   1   2       <- indexes start at 0
 *
 *  KEY IDEAS:
 *   - Size is FIXED once created (use ArrayList if you need to grow - topic 27).
 *   - array.length gives the size (no brackets - it's a property, not a method).
 *   - Last index is always length - 1.
 *   - Accessing index -1 or length crashes: ArrayIndexOutOfBoundsException.
 *   - New arrays get default values: 0 for numbers, false for boolean, null for objects.
 *   - The java.util.Arrays class has handy helpers: toString, sort, fill, copyOf...
 *
 *  RUN:  java Arrays1D.java
 */
import java.util.Arrays;

public class Arrays1D {

    public static void main(String[] args) {

        // ---------- 1) Creating arrays ----------
        int[] marks = {90, 75, 60, 85, 70};        // create + fill with values
        String[] names = new String[3];            // create EMPTY array of size 3 (all null)
        double[] prices = new double[2];           // all 0.0 by default

        // ---------- 2) Reading and writing elements ----------
        System.out.println("First mark: " + marks[0]);
        System.out.println("Last mark: " + marks[marks.length - 1]);
        marks[1] = 80;                             // change the 2nd element (index 1)
        System.out.println("After change, marks[1] = " + marks[1]);

        names[0] = "Asha";
        names[1] = "Ravi";                         // names[2] stays null
        System.out.println("names: " + Arrays.toString(names));
        System.out.println("prices default: " + Arrays.toString(prices));

        // Printing an array directly shows a memory reference, not the values!
        // System.out.println(marks);  -> something like [I@1b6d3586
        System.out.println("marks: " + Arrays.toString(marks));   // use Arrays.toString

        // ---------- 3) Looping through an array ----------
        int total = 0;
        for (int i = 0; i < marks.length; i++) {   // classic loop - you get the index
            total += marks[i];
        }
        System.out.println("Total: " + total + ", Average: " + (double) total / marks.length);

        int highest = marks[0];
        for (int m : marks) {                      // for-each - simpler when you don't need index
            if (m > highest) {
                highest = m;
            }
        }
        System.out.println("Highest: " + highest);

        // ---------- 4) Useful Arrays methods ----------
        int[] nums = {5, 2, 9, 1, 7};
        Arrays.sort(nums);                         // sorts in place (ascending)
        System.out.println("Sorted: " + Arrays.toString(nums));

        int pos = Arrays.binarySearch(nums, 7);    // fast search - array MUST be sorted
        System.out.println("Index of 7: " + pos);

        int[] copy = Arrays.copyOf(nums, 7);       // copy into a bigger array (extra = 0)
        System.out.println("Copy (size 7): " + Arrays.toString(copy));

        int[] filled = new int[4];
        Arrays.fill(filled, 3);                    // set every element to 3
        System.out.println("Filled: " + Arrays.toString(filled));

        // ---------- 5) Arrays are REFERENCE types ----------
        int[] original = {1, 2, 3};
        int[] sameArray = original;                // NOT a copy! both point to the same array
        sameArray[0] = 100;
        System.out.println("original[0] is now: " + original[0]);   // changed too!

        // ---------- 6) Reverse an array (classic interview question) ----------
        int[] arr = {1, 2, 3, 4, 5};
        for (int left = 0, right = arr.length - 1; left < right; left++, right--) {
            int temp = arr[left];                  // swap using a temporary variable
            arr[left] = arr[right];
            arr[right] = temp;
        }
        System.out.println("Reversed: " + Arrays.toString(arr));
    }
}

/*
 * ------------------------- OUTPUT -------------------------
 * First mark: 90
 * Last mark: 70
 * After change, marks[1] = 80
 * names: [Asha, Ravi, null]
 * prices default: [0.0, 0.0]
 * marks: [90, 80, 60, 85, 70]
 * Total: 385, Average: 77.0
 * Highest: 90
 * Sorted: [1, 2, 5, 7, 9]
 * Index of 7: 3
 * Copy (size 7): [1, 2, 5, 7, 9, 0, 0]
 * Filled: [3, 3, 3, 3]
 * original[0] is now: 100
 * Reversed: [5, 4, 3, 2, 1]
 * ----------------------------------------------------------
 */
