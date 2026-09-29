/*
 * PRACTICE 17: FizzBuzz - the classic interview warm-up
 * Multiples of 3 -> Fizz, of 5 -> Buzz, of both -> FizzBuzz, else the number.
 * Concepts: order of conditions matters (check 15 first!)
 * RUN: java FizzBuzz.java
 */
public class FizzBuzz {
    public static void main(String[] args) {
        for (int i = 1; i <= 15; i++) {
            if (i % 15 == 0) {              // divisible by both 3 and 5
                System.out.println("FizzBuzz");
            } else if (i % 3 == 0) {
                System.out.println("Fizz");
            } else if (i % 5 == 0) {
                System.out.println("Buzz");
            } else {
                System.out.println(i);
            }
        }
    }
}

/*
 * ------------------------- OUTPUT -------------------------
 * 1
 * 2
 * Fizz
 * 4
 * Buzz
 * Fizz
 * 7
 * 8
 * Fizz
 * Buzz
 * 11
 * Fizz
 * 13
 * 14
 * FizzBuzz
 * ----------------------------------------------------------
 */
