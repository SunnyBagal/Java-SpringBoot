/*
 * PRACTICE 07: Reverse a number and check if it's a palindrome number
 * Concepts: while loop, % 10 (last digit), / 10 (drop last digit)
 * RUN: java ReverseNumber.java
 */
public class ReverseNumber {

    static int reverse(int n) {
        int reversed = 0;
        while (n > 0) {
            int lastDigit = n % 10;              // 1234 % 10 = 4
            reversed = reversed * 10 + lastDigit; // shift left and add digit
            n = n / 10;                          // 1234 / 10 = 123
        }
        return reversed;
    }

    public static void main(String[] args) {
        System.out.println("Reverse of 1234 = " + reverse(1234));
        int[] tests = {121, 123, 1331};
        for (int t : tests) {
            // A palindrome number reads the same forwards and backwards
            System.out.println(t + " palindrome? " + (t == reverse(t)));
        }
    }
}

/*
 * ------------------------- OUTPUT -------------------------
 * Reverse of 1234 = 4321
 * 121 palindrome? true
 * 123 palindrome? false
 * 1331 palindrome? true
 * ----------------------------------------------------------
 */
