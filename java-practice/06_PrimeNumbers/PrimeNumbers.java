/*
 * PRACTICE 06: Check prime numbers and list primes up to 50
 * Concepts: methods returning boolean, loops, early return
 * RUN: java PrimeNumbers.java
 */
public class PrimeNumbers {

    // A prime has exactly two divisors: 1 and itself
    static boolean isPrime(int n) {
        if (n < 2) {
            return false;               // 0 and 1 are not prime
        }
        // Only need to test divisors up to the square root of n
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                return false;           // found a divisor -> not prime
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println("Is 29 prime? " + isPrime(29));
        System.out.println("Is 91 prime? " + isPrime(91));   // 7 * 13

        System.out.print("Primes up to 50: ");
        for (int i = 2; i <= 50; i++) {
            if (isPrime(i)) {
                System.out.print(i + " ");
            }
        }
        System.out.println();
    }
}

/*
 * ------------------------- OUTPUT -------------------------
 * Is 29 prime? true
 * Is 91 prime? false
 * Primes up to 50: 2 3 5 7 11 13 17 19 23 29 31 37 41 43 47
 * ----------------------------------------------------------
 */
