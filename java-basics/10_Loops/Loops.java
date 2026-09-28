/*
 * ============================================================
 *  TOPIC 10: LOOPS (repeat code)
 * ============================================================
 *
 *  1) for loop       -> when you KNOW how many times to repeat.
 *       for (start; condition; update) { ... }
 *
 *  2) while loop     -> repeat WHILE a condition is true (checked BEFORE each run).
 *       while (condition) { ... }
 *
 *  3) do-while loop  -> like while, but checked AFTER each run,
 *                       so the body always runs AT LEAST ONCE.
 *       do { ... } while (condition);
 *
 *  4) for-each loop  -> go through every item of an array/collection.
 *       for (Type item : collection) { ... }
 *
 *  DANGER: If the condition never becomes false you get an INFINITE loop.
 *          (Press Ctrl + C in the terminal to stop it.)
 *
 *  RUN:  java Loops.java
 */
public class Loops {

    public static void main(String[] args) {

        // ---------- 1) for loop ----------
        // int i = 1   -> runs ONCE at the start
        // i <= 5      -> checked BEFORE every round; loop stops when false
        // i++         -> runs AFTER every round
        System.out.print("for 1 to 5: ");
        for (int i = 1; i <= 5; i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        // Counting backwards, in steps of 2
        System.out.print("Countdown by 2: ");
        for (int i = 10; i > 0; i -= 2) {
            System.out.print(i + " ");
        }
        System.out.println();

        // Sum of 1 to 100
        int sum = 0;
        for (int i = 1; i <= 100; i++) {
            sum += i;                         // add each number to sum
        }
        System.out.println("Sum 1..100 = " + sum);

        // ---------- 2) while loop ----------
        int n = 1234;
        int digits = 0;
        while (n > 0) {                       // keep going while there are digits left
            n = n / 10;                       // remove the last digit: 1234 -> 123 -> 12 -> 1 -> 0
            digits++;
        }
        System.out.println("1234 has " + digits + " digits");

        // ---------- 3) do-while loop ----------
        int x = 100;
        do {
            System.out.println("do-while runs once even though x < 10 is false (x = " + x + ")");
        } while (x < 10);                     // checked AFTER the first run

        // ---------- 4) for-each loop ----------
        String[] fruits = {"Apple", "Banana", "Mango"};
        for (String fruit : fruits) {         // read as: "for each fruit in fruits"
            System.out.println("I like " + fruit);
        }

        // ---------- 5) Nested loops (loop inside a loop) ----------
        // Outer loop = rows, inner loop = columns
        System.out.println("Multiplication table (1-3):");
        for (int row = 1; row <= 3; row++) {
            for (int col = 1; col <= 3; col++) {
                System.out.print(row * col + "\t");
            }
            System.out.println();             // new line after each row
        }

        // Star pattern
        System.out.println("Triangle:");
        for (int i = 1; i <= 4; i++) {
            for (int j = 1; j <= i; j++) {    // row i prints i stars
                System.out.print("* ");
            }
            System.out.println();
        }
    }
}

/*
 * ------------------------- OUTPUT -------------------------
 * for 1 to 5: 1 2 3 4 5
 * Countdown by 2: 10 8 6 4 2
 * Sum 1..100 = 5050
 * 1234 has 4 digits
 * do-while runs once even though x < 10 is false (x = 100)
 * I like Apple
 * I like Banana
 * I like Mango
 * Multiplication table (1-3):
 * 1	2	3
 * 2	4	6
 * 3	6	9
 * Triangle:
 * *
 * * *
 * * * *
 * * * * *
 * ----------------------------------------------------------
 *  (lines really end with a trailing space/tab; trimmed here for readability)
 */
