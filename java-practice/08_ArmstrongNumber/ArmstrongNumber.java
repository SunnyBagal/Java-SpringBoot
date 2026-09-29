/*
 * PRACTICE 08: Armstrong numbers
 * An Armstrong number equals the sum of its digits, each raised to the power
 * of the number of digits.  153 = 1^3 + 5^3 + 3^3 = 1 + 125 + 27
 * Concepts: String.valueOf to count digits, while loop, Math.pow
 * RUN: java ArmstrongNumber.java
 */
public class ArmstrongNumber {

    static boolean isArmstrong(int n) {
        int digits = String.valueOf(n).length();   // 153 -> "153" -> 3
        int sum = 0;
        int temp = n;
        while (temp > 0) {
            int d = temp % 10;
            sum += (int) Math.pow(d, digits);
            temp /= 10;
        }
        return sum == n;
    }

    public static void main(String[] args) {
        System.out.print("Armstrong numbers up to 1000: ");
        for (int i = 1; i <= 1000; i++) {
            if (isArmstrong(i)) {
                System.out.print(i + " ");
            }
        }
        System.out.println();
    }
}

/*
 * ------------------------- OUTPUT -------------------------
 * Armstrong numbers up to 1000: 1 2 3 4 5 6 7 8 9 153 370 371 407
 * ----------------------------------------------------------
 */
