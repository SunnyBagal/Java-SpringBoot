/*
 * ============================================================
 *  TOPIC 16: RECURSION (a method that calls itself)
 * ============================================================
 *
 *  WHAT: Solve a problem by solving a SMALLER version of the same problem.
 *
 *  EVERY recursive method needs:
 *   1) BASE CASE      -> when to STOP (otherwise it runs forever)
 *   2) RECURSIVE CASE -> call itself with a smaller input, moving toward the base case
 *
 *  Example: factorial(4) = 4 * factorial(3)
 *                        = 4 * 3 * factorial(2)
 *                        = 4 * 3 * 2 * factorial(1)
 *                        = 4 * 3 * 2 * 1          <- base case reached
 *                        = 24
 *
 *  DANGER: No base case (or never reaching it) -> StackOverflowError.
 *  NOTE:   Anything recursive can also be written with loops; recursion is
 *          often shorter for tree-like problems.
 *
 *  RUN:  java Recursion.java
 */
public class Recursion {

    // ---------- 1) Factorial: n! = n * (n-1)! ----------
    static long factorial(int n) {
        if (n <= 1) {                     // BASE CASE
            return 1;
        }
        return n * factorial(n - 1);      // RECURSIVE CASE (smaller problem)
    }

    // ---------- 2) Fibonacci: 0 1 1 2 3 5 8 ... ----------
    static int fibonacci(int n) {
        if (n <= 1) {                     // fib(0) = 0, fib(1) = 1
            return n;
        }
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    // ---------- 3) Sum of digits: 1234 -> 1+2+3+4 ----------
    static int sumOfDigits(int n) {
        if (n == 0) {
            return 0;
        }
        return (n % 10) + sumOfDigits(n / 10);   // last digit + sum of the rest
    }

    // ---------- 4) Reverse a string ----------
    static String reverse(String s) {
        if (s.isEmpty()) {
            return "";
        }
        // last char + reverse of everything before it
        return s.charAt(s.length() - 1) + reverse(s.substring(0, s.length() - 1));
    }

    // ---------- 5) Countdown - shows the order of calls ----------
    static void countdown(int n) {
        if (n == 0) {
            System.out.println("Liftoff!");
            return;
        }
        System.out.print(n + "... ");
        countdown(n - 1);
    }

    public static void main(String[] args) {
        System.out.println("factorial(5) = " + factorial(5));
        System.out.println("factorial(10) = " + factorial(10));

        System.out.print("First 10 Fibonacci: ");
        for (int i = 0; i < 10; i++) {
            System.out.print(fibonacci(i) + " ");
        }
        System.out.println();

        System.out.println("sumOfDigits(1234) = " + sumOfDigits(1234));
        System.out.println("reverse(\"Java\") = " + reverse("Java"));
        countdown(3);
    }
}

/*
 * ------------------------- OUTPUT -------------------------
 * factorial(5) = 120
 * factorial(10) = 3628800
 * First 10 Fibonacci: 0 1 1 2 3 5 8 13 21 34
 * sumOfDigits(1234) = 10
 * reverse("Java") = avaJ
 * 3... 2... 1... Liftoff!
 * ----------------------------------------------------------
 */
