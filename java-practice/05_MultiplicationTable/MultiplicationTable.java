/*
 * PRACTICE 05: Print a multiplication table
 * Concepts: for loop, printf formatting
 * RUN: java MultiplicationTable.java
 */
public class MultiplicationTable {
    public static void main(String[] args) {
        int n = 7;
        for (int i = 1; i <= 10; i++) {
            // %2d -> integer padded to width 2 so the columns line up
            System.out.printf("%d x %2d = %d%n", n, i, n * i);
        }
    }
}

/*
 * ------------------------- OUTPUT -------------------------
 * 7 x  1 = 7
 * 7 x  2 = 14
 * 7 x  3 = 21
 * 7 x  4 = 28
 * 7 x  5 = 35
 * 7 x  6 = 42
 * 7 x  7 = 49
 * 7 x  8 = 56
 * 7 x  9 = 63
 * 7 x 10 = 70
 * ----------------------------------------------------------
 */
