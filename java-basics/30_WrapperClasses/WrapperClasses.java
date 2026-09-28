/*
 * ============================================================
 *  TOPIC 30: WRAPPER CLASSES, AUTOBOXING & UNBOXING
 * ============================================================
 *
 *  WHAT: Every primitive type has an OBJECT version ("wrapper") in java.lang:
 *
 *      byte -> Byte      short -> Short     int  -> Integer    long    -> Long
 *      float -> Float    double -> Double   char -> Character  boolean -> Boolean
 *
 *  WHY:
 *   - Collections (ArrayList, HashMap...) can only hold OBJECTS.
 *   - Wrappers can be null (useful for "no value yet", e.g. an optional DB column).
 *   - They have helpful methods: Integer.parseInt, Character.isDigit, ...
 *
 *  AUTOBOXING = Java automatically converts primitive -> wrapper.  Integer x = 5;
 *  UNBOXING   = Java automatically converts wrapper -> primitive.  int y = x;
 *
 *  TRAPS:
 *   - Unboxing a null wrapper -> NullPointerException.
 *   - Compare wrappers with .equals(), NOT == (== compares object references).
 *
 *  RUN:  java WrapperClasses.java
 */
import java.util.ArrayList;
import java.util.List;

public class WrapperClasses {

    public static void main(String[] args) {

        // ---------- 1) Autoboxing and unboxing ----------
        int primitive = 10;
        Integer boxed = primitive;            // autoboxing: int -> Integer
        int unboxed = boxed;                  // unboxing:   Integer -> int
        System.out.println("boxed: " + boxed + ", unboxed: " + unboxed);

        List<Integer> list = new ArrayList<>();
        list.add(5);                          // 5 is autoboxed to Integer
        int first = list.get(0);              // unboxed back to int
        System.out.println("first from list: " + first);

        // ---------- 2) Useful static methods ----------
        int n = Integer.parseInt("123");                // String -> int
        double d = Double.parseDouble("4.5");           // String -> double
        boolean flag = Boolean.parseBoolean("TRUE");    // case-insensitive
        System.out.println("parsed: " + n + ", " + d + ", " + flag);

        System.out.println("Integer.toBinaryString(10): " + Integer.toBinaryString(10));
        System.out.println("Integer.max(3, 8): " + Integer.max(3, 8));
        System.out.println("Integer.compare(3, 8): " + Integer.compare(3, 8));  // -1, 0 or 1

        // ---------- 3) Character helpers ----------
        char c = 'a';
        System.out.println("isLetter: " + Character.isLetter(c));
        System.out.println("isDigit('7'): " + Character.isDigit('7'));
        System.out.println("toUpperCase: " + Character.toUpperCase(c));
        System.out.println("isWhitespace(' '): " + Character.isWhitespace(' '));

        // Count letters, digits and spaces in a string
        String text = "Java 21 rocks";
        int letters = 0, digits = 0, spaces = 0;
        for (char ch : text.toCharArray()) {
            if (Character.isLetter(ch)) letters++;
            else if (Character.isDigit(ch)) digits++;
            else if (Character.isWhitespace(ch)) spaces++;
        }
        System.out.println("letters=" + letters + ", digits=" + digits + ", spaces=" + spaces);

        // ---------- 4) The == trap ----------
        Integer a = 127, b = 127;
        Integer x = 1000, y = 1000;
        System.out.println("127 == 127: " + (a == b));          // true  (small values are cached)
        System.out.println("1000 == 1000: " + (x == y));        // false (different objects!)
        System.out.println("1000 equals 1000: " + x.equals(y)); // true  <- always use equals

        // ---------- 5) null wrappers ----------
        Integer maybeAge = null;              // "we don't know the age yet" - int can't do this
        System.out.println("maybeAge: " + maybeAge);
        try {
            int age = maybeAge;               // unboxing null -> crash
        } catch (NullPointerException e) {
            System.out.println("Unboxing null throws NullPointerException");
        }
    }
}

/*
 * ------------------------- OUTPUT -------------------------
 * boxed: 10, unboxed: 10
 * first from list: 5
 * parsed: 123, 4.5, true
 * Integer.toBinaryString(10): 1010
 * Integer.max(3, 8): 8
 * Integer.compare(3, 8): -1
 * isLetter: true
 * isDigit('7'): true
 * toUpperCase: A
 * isWhitespace(' '): true
 * letters=9, digits=2, spaces=2
 * 127 == 127: true
 * 1000 == 1000: false
 * 1000 equals 1000: true
 * maybeAge: null
 * Unboxing null throws NullPointerException
 * ----------------------------------------------------------
 */
