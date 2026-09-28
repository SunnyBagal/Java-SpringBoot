/*
 * ============================================================
 *  TOPIC 03: DATA TYPES
 * ============================================================
 *
 *  WHAT: The "type" tells Java what kind of value a variable holds
 *        and how much memory it needs.
 *
 *  TWO FAMILIES:
 *
 *  A) PRIMITIVE types (8 of them) - store the actual value, lowercase names.
 *     +---------+---------+----------------------------------+-----------+
 *     | Type    | Size    | Range / Use                      | Example   |
 *     +---------+---------+----------------------------------+-----------+
 *     | byte    | 1 byte  | -128 to 127                      | 100       |
 *     | short   | 2 bytes | -32,768 to 32,767                | 30000     |
 *     | int     | 4 bytes | about -2.1 billion to 2.1 billion| 25        |
 *     | long    | 8 bytes | very big whole numbers (add L)   | 9000000L  |
 *     | float   | 4 bytes | decimals, ~7 digits (add f)      | 3.14f     |
 *     | double  | 8 bytes | decimals, ~15 digits (default)   | 3.14159   |
 *     | char    | 2 bytes | one character (single quotes)    | 'A'       |
 *     | boolean | JVM-defined | true / false                 | true      |
 *     +---------+---------+----------------------------------+-----------+
 *
 *  B) NON-PRIMITIVE / REFERENCE types - store an ADDRESS pointing to an object.
 *     Names start with a Capital letter: String, arrays, Scanner, your own classes...
 *     They can be null (point to nothing) and have methods (e.g. name.length()).
 *
 *  TIP: In real code you'll mostly use: int, long, double, boolean, char, String.
 *
 *  RUN:  java DataTypes.java
 */
public class DataTypes {

    public static void main(String[] args) {

        // ---------- Integer types (whole numbers) ----------
        byte smallNumber = 100;            // good for saving memory in big arrays
        short mediumNumber = 30000;
        int normalNumber = 2_000_000;      // _ is allowed to make big numbers readable
        long bigNumber = 9_000_000_000L;   // L tells Java "this is a long" (too big for int)

        System.out.println("byte:  " + smallNumber);
        System.out.println("short: " + mediumNumber);
        System.out.println("int:   " + normalNumber);
        System.out.println("long:  " + bigNumber);

        // Min and max values are stored as constants in "wrapper" classes
        System.out.println("int max: " + Integer.MAX_VALUE);
        System.out.println("int min: " + Integer.MIN_VALUE);

        // ---------- Decimal types ----------
        float price = 99.99f;              // f is REQUIRED for float, else Java thinks double
        double pi = 3.141592653589793;     // double is the default for decimals

        System.out.println("float:  " + price);
        System.out.println("double: " + pi);

        // Decimals are not always exact! (they are stored in binary)
        System.out.println("0.1 + 0.2 = " + (0.1 + 0.2));  // not exactly 0.3

        // ---------- char ----------
        char letter = 'J';
        char digit = '7';                  // a character, NOT the number 7
        char unicode = 'A';           // Unicode for 'A'
        System.out.println("char: " + letter + ", " + digit + ", " + unicode);

        // chars are stored as numbers internally (ASCII/Unicode code)
        int code = letter;                 // 'J' is 74
        System.out.println("Code of 'J': " + code);

        // ---------- boolean ----------
        boolean isJavaFun = true;
        boolean isFishFlying = false;
        System.out.println("boolean: " + isJavaFun + ", " + isFishFlying);

        // ---------- String (reference type) ----------
        String greeting = "Hello";         // double quotes for Strings
        System.out.println("String: " + greeting + " (length " + greeting.length() + ")");

        // A reference type can be null (means "no object")
        String nothing = null;
        System.out.println("null String: " + nothing);

        // ---------- Integer overflow ----------
        // If you go past the max, the value "wraps around" to the negative side!
        int max = Integer.MAX_VALUE;
        int overflow = max + 1;
        System.out.println("MAX + 1 = " + overflow);   // surprise!
    }
}

/*
 * ------------------------- OUTPUT -------------------------
 * byte:  100
 * short: 30000
 * int:   2000000
 * long:  9000000000
 * int max: 2147483647
 * int min: -2147483648
 * float:  99.99
 * double: 3.141592653589793
 * 0.1 + 0.2 = 0.30000000000000004
 * char: J, 7, A
 * Code of 'J': 74
 * boolean: true, false
 * String: Hello (length 5)
 * null String: null
 * MAX + 1 = -2147483648
 * ----------------------------------------------------------
 */
