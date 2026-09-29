/*
 * PRACTICE 01: Check if numbers are even or odd
 * Concepts: % operator, if-else, for-each loop (java-basics 05, 08, 10)
 * RUN: java EvenOdd.java
 */
public class EvenOdd {
    public static void main(String[] args) {
        int[] numbers = {4, 7, 0, -3};
        for (int n : numbers) {
            // n % 2 is the remainder after dividing by 2: 0 means even
            if (n % 2 == 0) {
                System.out.println(n + " is even");
            } else {
                System.out.println(n + " is odd");
            }
        }
    }
}

/*
 * ------------------------- OUTPUT -------------------------
 * 4 is even
 * 7 is odd
 * 0 is even
 * -3 is odd
 * ----------------------------------------------------------
 */
