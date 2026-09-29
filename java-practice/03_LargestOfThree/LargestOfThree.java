/*
 * PRACTICE 03: Find the largest of three numbers
 * Concepts: if / else if, &&, Math.max
 * RUN: java LargestOfThree.java
 */
public class LargestOfThree {
    public static void main(String[] args) {
        int a = 12, b = 45, c = 30;

        // Way 1: compare with if-else
        int largest;
        if (a >= b && a >= c) {
            largest = a;
        } else if (b >= a && b >= c) {
            largest = b;
        } else {
            largest = c;
        }
        System.out.println("Largest (if-else): " + largest);

        // Way 2: Math.max takes two numbers, so nest it
        System.out.println("Largest (Math.max): " + Math.max(a, Math.max(b, c)));
    }
}

/*
 * ------------------------- OUTPUT -------------------------
 * Largest (if-else): 45
 * Largest (Math.max): 45
 * ----------------------------------------------------------
 */
