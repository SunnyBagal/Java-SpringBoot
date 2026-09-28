/*
 * ============================================================
 *  TOPIC 04: TYPE CASTING (converting one type to another)
 * ============================================================
 *
 *  TWO KINDS:
 *
 *  1) WIDENING (automatic / implicit) - small type -> bigger type. Safe, no data lost.
 *        byte -> short -> int -> long -> float -> double
 *
 *  2) NARROWING (manual / explicit) - big type -> smaller type. You must write
 *     (type) in front. Data CAN be lost (decimals cut off, overflow).
 *        double -> float -> long -> int -> short -> byte
 *
 *  STRING <-> NUMBER is NOT casting; use helper methods:
 *     Integer.parseInt("42")    String -> int
 *     Double.parseDouble("3.5") String -> double
 *     String.valueOf(42)        int    -> String
 *
 *  RUN:  java TypeCasting.java
 */
public class TypeCasting {

    public static void main(String[] args) {

        // ---------- 1) Widening: automatic ----------
        int myInt = 9;
        double myDouble = myInt;            // int fits inside double, Java does it for you
        System.out.println("int to double: " + myDouble);   // 9.0

        char ch = 'A';
        int ascii = ch;                     // char -> int gives its character code
        System.out.println("char to int: " + ascii);        // 65

        // ---------- 2) Narrowing: manual with (type) ----------
        double price = 9.78;
        int rounded = (int) price;          // (int) CUTS OFF the decimal part - does NOT round
        System.out.println("double to int: " + rounded);    // 9, not 10

        // If you want real rounding, use Math.round()
        System.out.println("Math.round(9.78): " + Math.round(price)); // 10

        int big = 130;
        byte small = (byte) big;            // 130 doesn't fit in byte (-128..127) -> wraps around
        System.out.println("int 130 to byte: " + small);    // -126 (data lost!)

        int code = 66;
        char letter = (char) code;          // int -> char gives the character for that code
        System.out.println("int 66 to char: " + letter);    // B

        // ---------- 3) Casting in division ----------
        int a = 7, b = 2;
        System.out.println("7 / 2 (int division): " + (a / b));              // 3  - decimals dropped
        System.out.println("7 / 2 (cast to double): " + ((double) a / b));   // 3.5

        // ---------- 4) String <-> number ----------
        String ageText = "25";
        int age = Integer.parseInt(ageText);    // turns text "25" into number 25
        System.out.println("Parsed age + 5 = " + (age + 5));    // 30

        String piText = "3.14";
        double pi = Double.parseDouble(piText);
        System.out.println("Parsed pi * 2 = " + (pi * 2));      // 6.28

        int number = 100;
        String numberText = String.valueOf(number); // number -> text
        System.out.println("As text + 1: " + numberText + 1);   // "100" + 1 = "1001" (text joining!)

        // Integer.parseInt("abc") would crash with NumberFormatException
        // (we'll learn to handle that in the Exception Handling topic)
    }
}

/*
 * ------------------------- OUTPUT -------------------------
 * int to double: 9.0
 * char to int: 65
 * double to int: 9
 * Math.round(9.78): 10
 * int 130 to byte: -126
 * int 66 to char: B
 * 7 / 2 (int division): 3
 * 7 / 2 (cast to double): 3.5
 * Parsed age + 5 = 30
 * Parsed pi * 2 = 6.28
 * As text + 1: 1001
 * ----------------------------------------------------------
 */
