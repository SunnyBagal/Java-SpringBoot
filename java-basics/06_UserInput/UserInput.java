/*
 * ============================================================
 *  TOPIC 06: USER INPUT with Scanner
 * ============================================================
 *
 *  WHAT: Reading what the user types in the terminal.
 *
 *  KEY IDEAS:
 *   - Scanner is a built-in class in the java.util package, so we IMPORT it.
 *   - new Scanner(System.in) -> reads from the keyboard (standard input).
 *   - Common methods:
 *       nextInt()     -> reads an int
 *       nextDouble()  -> reads a double
 *       next()        -> reads ONE word (stops at a space)
 *       nextLine()    -> reads the WHOLE line (including spaces)
 *       nextBoolean() -> reads true/false
 *   - Close the scanner at the end with scanner.close().
 *
 *  COMMON BEGINNER BUG:
 *   nextInt() reads the number but leaves the "Enter" (\n) behind.
 *   A nextLine() right after it will read that empty leftover and seem to "skip".
 *   Fix: call an extra scanner.nextLine() to eat the leftover newline.
 *
 *  RUN:  java UserInput.java      (then type your answers and press Enter)
 */

// import -> tells Java where to find the Scanner class.
import java.util.Scanner;

public class UserInput {

    public static void main(String[] args) {

        // Create ONE Scanner object that reads from the keyboard.
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your full name: ");
        String name = scanner.nextLine();      // reads the whole line, e.g. "Sunny Bagal"

        System.out.print("Enter your age: ");
        int age = scanner.nextInt();           // reads a whole number

        System.out.print("Enter your height in feet: ");
        double height = scanner.nextDouble();  // reads a decimal number

        scanner.nextLine();                    // eat the leftover "Enter" after nextDouble()

        System.out.print("Enter your city: ");
        String city = scanner.nextLine();      // works correctly because we ate the leftover

        // Use the values
        System.out.println();                  // empty line
        System.out.println("Hi " + name + "!");
        System.out.println("You are " + age + " years old and " + height + " ft tall.");
        System.out.println("Next year you will be " + (age + 1) + ".");
        System.out.println("Greetings to " + city + "!");

        scanner.close();                       // free the resource when done
    }
}

/*
 * ------------------- SAMPLE RUN (your input will differ) -------------------
 * Enter your full name: Sunny Bagal
 * Enter your age: 22
 * Enter your height in feet: 5.8
 * Enter your city: Pune
 *
 * Hi Sunny Bagal!
 * You are 22 years old and 5.8 ft tall.
 * Next year you will be 23.
 * Greetings to Pune!
 * ---------------------------------------------------------------------------
 */
