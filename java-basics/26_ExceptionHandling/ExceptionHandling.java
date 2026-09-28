/*
 * ============================================================
 *  TOPIC 26: EXCEPTION HANDLING
 * ============================================================
 *
 *  WHAT: An EXCEPTION is an error that happens while the program runs
 *        (divide by zero, bad array index, file not found...).
 *        If not handled, the program CRASHES and prints a stack trace.
 *
 *  SYNTAX:
 *      try {
 *          // code that might fail
 *      } catch (SomeException e) {
 *          // what to do if it fails
 *      } finally {
 *          // ALWAYS runs (success or failure) - used for cleanup
 *      }
 *
 *  KEYWORDS:
 *      throw   -> create/raise an exception yourself:  throw new IllegalArgumentException("...")
 *      throws  -> in a method signature: "this method may throw X, caller must deal with it"
 *
 *  TWO KINDS:
 *   - CHECKED   (e.g. IOException): compiler FORCES you to catch or declare them.
 *   - UNCHECKED (RuntimeException and children, e.g. NullPointerException,
 *                ArithmeticException, ArrayIndexOutOfBoundsException):
 *                not forced; usually caused by bugs.
 *
 *  Hierarchy:  Throwable
 *                ├── Error          (serious JVM problems - don't catch, e.g. OutOfMemoryError)
 *                └── Exception      (checked)
 *                      └── RuntimeException   (unchecked)
 *
 *  In Spring Boot you'll create custom exceptions (e.g. UserNotFoundException)
 *  and turn them into HTTP error responses.
 *
 *  RUN:  java ExceptionHandling.java
 */
public class ExceptionHandling {

    public static void main(String[] args) {

        // ---------- 1) Basic try-catch ----------
        try {
            int result = 10 / 0;                       // throws ArithmeticException
            System.out.println("This line never runs: " + result);
        } catch (ArithmeticException e) {
            System.out.println("Caught: " + e.getMessage());
        }

        // ---------- 2) Multiple catch blocks (most specific first) ----------
        int[] numbers = {1, 2, 3};
        try {
            System.out.println(numbers[5]);            // index 5 doesn't exist
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Bad index: " + e.getMessage());
        } catch (Exception e) {                        // catches anything else
            System.out.println("Something else went wrong");
        }

        // ---------- 3) Catching several types in one block ----------
        String text = null;
        try {
            System.out.println(text.length());         // NullPointerException
        } catch (NullPointerException | IllegalStateException e) {
            System.out.println("Caught " + e.getClass().getSimpleName());
        }

        // ---------- 4) finally always runs ----------
        try {
            int value = Integer.parseInt("abc");       // NumberFormatException
        } catch (NumberFormatException e) {
            System.out.println("Not a number: " + e.getMessage());
        } finally {
            System.out.println("finally block runs no matter what");
        }

        // ---------- 5) throw: raising your own exception ----------
        try {
            setAge(-5);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid input: " + e.getMessage());
        }

        // ---------- 6) Custom exception + throws ----------
        try {
            withdraw(1000, 5000);
        } catch (InsufficientFundsException e) {
            System.out.println("Custom exception: " + e.getMessage());
        }

        // ---------- 7) try-with-resources: auto-closes things like files/scanners ----------
        try (MyResource r = new MyResource()) {
            r.use();
        }                                              // close() is called automatically here

        System.out.println("Program continues normally - no crash!");
    }

    static void setAge(int age) {
        if (age < 0) {
            throw new IllegalArgumentException("Age cannot be negative: " + age);
        }
        System.out.println("Age set to " + age);
    }

    // "throws" tells callers they must handle this checked exception
    static void withdraw(double balance, double amount) throws InsufficientFundsException {
        if (amount > balance) {
            throw new InsufficientFundsException("Need " + (amount - balance) + " more");
        }
        System.out.println("Withdrawn " + amount);
    }
}

// Custom CHECKED exception: extend Exception (extend RuntimeException for unchecked)
class InsufficientFundsException extends Exception {
    InsufficientFundsException(String message) {
        super(message);                                // pass message to the parent
    }
}

// Anything that implements AutoCloseable works with try-with-resources
class MyResource implements AutoCloseable {
    void use() {
        System.out.println("Using resource");
    }

    @Override
    public void close() {
        System.out.println("Resource closed automatically");
    }
}

/*
 * ------------------------- OUTPUT -------------------------
 * Caught: / by zero
 * Bad index: Index 5 out of bounds for length 3
 * Caught NullPointerException
 * Not a number: For input string: "abc"
 * finally block runs no matter what
 * Invalid input: Age cannot be negative: -5
 * Custom exception: Need 4000.0 more
 * Using resource
 * Resource closed automatically
 * Program continues normally - no crash!
 * ----------------------------------------------------------
 */
