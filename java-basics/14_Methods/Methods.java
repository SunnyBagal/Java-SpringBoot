/*
 * ============================================================
 *  TOPIC 14: METHODS (reusable blocks of code)
 * ============================================================
 *
 *  WHAT: A method is a named block of code you can call again and again.
 *        (Other languages call them "functions".)
 *
 *  SYNTAX:
 *      modifiers  returnType  name ( parameters ) {
 *          // body
 *          return value;   // only if returnType is not void
 *      }
 *
 *      public static int add(int a, int b) { return a + b; }
 *
 *  KEY IDEAS:
 *   - PARAMETERS = variables in the method definition (int a, int b).
 *   - ARGUMENTS  = actual values you pass when calling  add(3, 4).
 *   - void = returns nothing. Any other type = MUST return that type.
 *   - "return" ends the method immediately.
 *   - Java is PASS-BY-VALUE: the method gets a COPY of each argument.
 *       - For primitives: changing it inside the method doesn't affect the caller.
 *       - For objects/arrays: the copy is of the REFERENCE, so the method CAN
 *         change the object's contents.
 *   - "static" here means we can call it from main without creating an object
 *     (more in topic 20).
 *
 *  RUN:  java Methods.java
 */
public class Methods {

    // ---------- 1) No parameters, no return value ----------
    static void greet() {
        System.out.println("Hello from greet()!");
    }

    // ---------- 2) Parameters, no return value ----------
    static void greetPerson(String name, int age) {
        System.out.println("Hi " + name + ", you are " + age);
    }

    // ---------- 3) Parameters AND a return value ----------
    static int add(int a, int b) {
        return a + b;                     // sends the result back to whoever called
    }

    static double average(int[] numbers) {
        int sum = 0;
        for (int n : numbers) {
            sum += n;
        }
        return (double) sum / numbers.length;
    }

    // ---------- 4) Returning boolean - great for checks ----------
    static boolean isEven(int n) {
        return n % 2 == 0;                // the comparison itself is true/false
    }

    // ---------- 5) Early return ----------
    static String checkAge(int age) {
        if (age < 0) {
            return "Invalid age";         // leaves the method right here
        }
        return age >= 18 ? "Adult" : "Minor";
    }

    // ---------- 6) Pass-by-value demo ----------
    static void tryToChange(int number) {
        number = 999;                     // changes only the local COPY
    }

    static void changeArray(int[] arr) {
        arr[0] = 999;                     // changes the SAME array the caller has
    }

    // ---------- 7) Varargs: accept any number of arguments ----------
    static int sumAll(int... nums) {      // nums is treated as an int[]
        int total = 0;
        for (int n : nums) {
            total += n;
        }
        return total;
    }

    public static void main(String[] args) {
        greet();                                        // calling a method
        greetPerson("Sunny", 22);                       // passing arguments

        int result = add(5, 7);                         // store the returned value
        System.out.println("5 + 7 = " + result);
        System.out.println("10 + 20 = " + add(10, 20)); // or use it directly

        System.out.println("Average: " + average(new int[]{10, 20, 30, 40}));
        System.out.println("Is 8 even? " + isEven(8));
        System.out.println("checkAge(-5): " + checkAge(-5));
        System.out.println("checkAge(20): " + checkAge(20));

        int x = 10;
        tryToChange(x);
        System.out.println("x after tryToChange: " + x);          // still 10

        int[] data = {1, 2, 3};
        changeArray(data);
        System.out.println("data[0] after changeArray: " + data[0]); // 999

        System.out.println("sumAll(): " + sumAll());
        System.out.println("sumAll(1, 2, 3, 4): " + sumAll(1, 2, 3, 4));
    }
}

/*
 * ------------------------- OUTPUT -------------------------
 * Hello from greet()!
 * Hi Sunny, you are 22
 * 5 + 7 = 12
 * 10 + 20 = 30
 * Average: 25.0
 * Is 8 even? true
 * checkAge(-5): Invalid age
 * checkAge(20): Adult
 * x after tryToChange: 10
 * data[0] after changeArray: 999
 * sumAll(): 0
 * sumAll(1, 2, 3, 4): 10
 * ----------------------------------------------------------
 */
