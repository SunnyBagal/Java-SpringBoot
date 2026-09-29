/*
 * PRACTICE 02: Swap two numbers (with and without a temp variable)
 * Concepts: variables, assignment, arithmetic
 * RUN: java SwapNumbers.java
 */
public class SwapNumbers {
    public static void main(String[] args) {
        // Way 1: using a temporary variable (clearest)
        int a = 5, b = 9;
        int temp = a;   // save a
        a = b;          // a gets b's value
        b = temp;       // b gets the saved old a
        System.out.println("With temp:    a = " + a + ", b = " + b);

        // Way 2: using + and - (no extra variable)
        int x = 5, y = 9;
        x = x + y;      // x = 14
        y = x - y;      // y = 14 - 9 = 5
        x = x - y;      // x = 14 - 5 = 9
        System.out.println("Without temp: x = " + x + ", y = " + y);
    }
}

/*
 * ------------------------- OUTPUT -------------------------
 * With temp:    a = 9, b = 5
 * Without temp: x = 9, y = 5
 * ----------------------------------------------------------
 */
