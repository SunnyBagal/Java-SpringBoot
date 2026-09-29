/*
 * PRACTICE 15: Binary search - halve the search space each step
 * Array MUST be sorted. Time: O(log n) - 1 million items need ~20 checks.
 * Concepts: while loop, low/high/mid pointers
 * RUN: java BinarySearch.java
 */
public class BinarySearch {

    static int search(int[] arr, int target) {
        int low = 0, high = arr.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;   // same as (low+high)/2 but can't overflow
            System.out.println("  checking index " + mid + " (value " + arr[mid] + ")");
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] < target) {
                low = mid + 1;                  // target is in the right half
            } else {
                high = mid - 1;                 // target is in the left half
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] sorted = {2, 5, 8, 12, 16, 23, 38, 56, 72, 91};
        System.out.println("Searching 23:");
        System.out.println("Found at index " + search(sorted, 23));
    }
}

/*
 * ------------------------- OUTPUT -------------------------
 * Searching 23:
 *   checking index 4 (value 16)
 *   checking index 7 (value 56)
 *   checking index 5 (value 23)
 * Found at index 5
 * ----------------------------------------------------------
 */
