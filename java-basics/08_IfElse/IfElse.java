/*
 * ============================================================
 *  TOPIC 08: IF / ELSE IF / ELSE (decision making)
 * ============================================================
 *
 *  WHAT: Run a block of code only when a condition is true.
 *
 *  SYNTAX:
 *      if (condition) {
 *          // runs when condition is true
 *      } else if (anotherCondition) {
 *          // runs when first is false and this is true
 *      } else {
 *          // runs when all above are false
 *      }
 *
 *  KEY IDEAS:
 *   - The condition MUST be a boolean (true/false). if (5) is an error in Java.
 *   - Only ONE branch of an if / else-if / else chain runs - the first true one.
 *   - Always use { } even for one line; it avoids bugs.
 *
 *  RUN:  java IfElse.java
 */
public class IfElse {

    public static void main(String[] args) {

        // ---------- 1) Simple if ----------
        int temperature = 35;
        if (temperature > 30) {                  // 35 > 30 is true, so the block runs
            System.out.println("It's hot today!");
        }

        // ---------- 2) if - else ----------
        int number = 7;
        if (number % 2 == 0) {                   // remainder 1, so false
            System.out.println(number + " is even");
        } else {                                 // runs because the if was false
            System.out.println(number + " is odd");
        }

        // ---------- 3) if - else if - else (grading system) ----------
        int marks = 78;
        String grade;
        if (marks >= 90) {
            grade = "A+";
        } else if (marks >= 75) {                // 78 >= 75 -> true, stop checking further
            grade = "A";
        } else if (marks >= 60) {
            grade = "B";
        } else if (marks >= 40) {
            grade = "C";
        } else {
            grade = "Fail";
        }
        System.out.println("Marks " + marks + " -> Grade " + grade);

        // ---------- 4) Nested if (if inside if) ----------
        int age = 20;
        boolean hasLicense = false;
        if (age >= 18) {
            if (hasLicense) {
                System.out.println("You can drive.");
            } else {
                System.out.println("You are old enough, but get a license first.");
            }
        } else {
            System.out.println("Too young to drive.");
        }

        // ---------- 5) Combining conditions with && and || ----------
        int hour = 14;
        if (hour >= 9 && hour < 18) {            // between 9 and 17
            System.out.println("Office is open.");
        }

        String day = "Sunday";
        if (day.equals("Saturday") || day.equals("Sunday")) {   // .equals for Strings!
            System.out.println("It's the weekend!");
        }

        // ---------- 6) Ternary operator = short if-else that returns a value ----------
        int a = 10, b = 25;
        int max = (a > b) ? a : b;               // if a > b use a, else use b
        System.out.println("Max of " + a + " and " + b + " is " + max);

        // ---------- 7) Real example: leap year ----------
        int year = 2024;
        // Leap year: divisible by 4 AND (not divisible by 100 OR divisible by 400)
        if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
            System.out.println(year + " is a leap year");
        } else {
            System.out.println(year + " is not a leap year");
        }
    }
}

/*
 * ------------------------- OUTPUT -------------------------
 * It's hot today!
 * 7 is odd
 * Marks 78 -> Grade A
 * You are old enough, but get a license first.
 * Office is open.
 * It's the weekend!
 * Max of 10 and 25 is 25
 * 2024 is a leap year
 * ----------------------------------------------------------
 */
