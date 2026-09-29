/*
 * PRACTICE 13: Bubble sort
 * Repeatedly swap neighbours that are in the wrong order. After each pass the
 * biggest remaining number "bubbles up" to the end.
 * Concepts: nested loops, swapping, early exit with a boolean flag
 * RUN: java BubbleSort.java
 */
import java.util.Arrays;

public class BubbleSort {
    public static void main(String[] args) {
        int[] arr = {64, 25, 12, 22, 11};
        System.out.println("Before: " + Arrays.toString(arr));

        for (int pass = 0; pass < arr.length - 1; pass++) {
            boolean swapped = false;
            // the last "pass" items are already in place, so stop earlier each time
            for (int i = 0; i < arr.length - 1 - pass; i++) {
                if (arr[i] > arr[i + 1]) {
                    int temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                    swapped = true;
                }
            }
            System.out.println("After pass " + (pass + 1) + ": " + Arrays.toString(arr));
            if (!swapped) {
                break;                  // no swaps -> already sorted, stop early
            }
        }
    }
}

/*
 * ------------------------- OUTPUT -------------------------
 * Before: [64, 25, 12, 22, 11]
 * After pass 1: [25, 12, 22, 11, 64]
 * After pass 2: [12, 22, 11, 25, 64]
 * After pass 3: [12, 11, 22, 25, 64]
 * After pass 4: [11, 12, 22, 25, 64]
 * ----------------------------------------------------------
 */
