/*
 * ============================================================
 *  TOPIC 07: STRINGS
 * ============================================================
 *
 *  WHAT: A String is a sequence of characters (text), written in "double quotes".
 *
 *  KEY IDEAS:
 *   - String is a CLASS (reference type), so it has many useful methods.
 *   - Strings are IMMUTABLE: once created they never change. Methods like
 *     toUpperCase() return a NEW string; the original stays the same.
 *   - Positions (indexes) start at 0:   "J a v a"
 *                                         0 1 2 3
 *   - ALWAYS compare strings with .equals(), NOT ==
 *       ==      checks if both variables point to the SAME object in memory
 *       equals  checks if the TEXT inside is the same   <- what you almost always want
 *
 *  RUN:  java Strings.java
 */
public class Strings {

    public static void main(String[] args) {

        String text = "Hello Java";

        // ---------- Basic info ----------
        System.out.println("Length: " + text.length());          // number of characters (space counts)
        System.out.println("charAt(0): " + text.charAt(0));      // character at index 0
        System.out.println("charAt(6): " + text.charAt(6));

        // ---------- Changing case (returns NEW strings) ----------
        System.out.println("Upper: " + text.toUpperCase());
        System.out.println("Lower: " + text.toLowerCase());
        System.out.println("Original is unchanged: " + text);    // immutability!

        // ---------- Searching ----------
        System.out.println("indexOf(\"Java\"): " + text.indexOf("Java"));  // where it starts
        System.out.println("indexOf(\"xyz\"): " + text.indexOf("xyz"));    // -1 = not found
        System.out.println("contains(\"llo\"): " + text.contains("llo"));
        System.out.println("startsWith(\"Hell\"): " + text.startsWith("Hell"));
        System.out.println("endsWith(\"va\"): " + text.endsWith("va"));

        // ---------- Extracting parts ----------
        // substring(start, end) -> from start up to (but NOT including) end
        System.out.println("substring(0, 5): " + text.substring(0, 5));   // "Hello"
        System.out.println("substring(6): " + text.substring(6));         // from 6 to the end

        // ---------- Modifying (again: returns new strings) ----------
        System.out.println("replace: " + text.replace("Java", "World"));
        String messy = "   spaces around   ";
        System.out.println("trim: [" + messy.trim() + "]");    // removes spaces at both ends

        // ---------- Splitting and joining ----------
        String csv = "apple,banana,mango";
        String[] fruits = csv.split(",");                      // cut into an array at each comma
        System.out.println("split -> first fruit: " + fruits[0] + ", count: " + fruits.length);
        System.out.println("join: " + String.join(" | ", fruits));

        // ---------- Comparing ----------
        String a = "java";
        String b = "java";
        String c = new String("java");                          // forces a brand-new object
        String d = "JAVA";

        System.out.println("a.equals(c): " + a.equals(c));      // true  - same text
        System.out.println("a == c: " + (a == c));              // false - different objects!
        System.out.println("a == b: " + (a == b));              // true  - Java reuses literals ("string pool")
        System.out.println("a.equalsIgnoreCase(d): " + a.equalsIgnoreCase(d));
        System.out.println("isEmpty(\"\"): " + "".isEmpty());

        // compareTo -> 0 if equal, negative if first comes before (alphabetically), positive if after
        System.out.println("\"apple\".compareTo(\"banana\"): " + "apple".compareTo("banana"));

        // ---------- Concatenation & formatting ----------
        String first = "Sunny", last = "Bagal";
        String full = first + " " + last;                       // + joins strings
        System.out.println("Full name: " + full);

        // String.format -> %s = string, %d = integer, %.2f = decimal with 2 places
        String info = String.format("%s is %d years old and scored %.2f%%", first, 22, 87.456);
        System.out.println(info);

        // ---------- Looping over characters ----------
        String word = "Java";
        for (int i = 0; i < word.length(); i++) {
            System.out.print(word.charAt(i) + "-");
        }
        System.out.println();

        // ---------- Text blocks (Java 15+) for multi-line strings ----------
        String poem = """
                Roses are red,
                Java is great.""";
        System.out.println(poem);
    }
}

/*
 * ------------------------- OUTPUT -------------------------
 * Length: 10
 * charAt(0): H
 * charAt(6): J
 * Upper: HELLO JAVA
 * Lower: hello java
 * Original is unchanged: Hello Java
 * indexOf("Java"): 6
 * indexOf("xyz"): -1
 * contains("llo"): true
 * startsWith("Hell"): true
 * endsWith("va"): true
 * substring(0, 5): Hello
 * substring(6): Java
 * replace: Hello World
 * trim: [spaces around]
 * split -> first fruit: apple, count: 3
 * join: apple | banana | mango
 * a.equals(c): true
 * a == c: false
 * a == b: true
 * a.equalsIgnoreCase(d): true
 * isEmpty(""): true
 * "apple".compareTo("banana"): -1
 * Full name: Sunny Bagal
 * Sunny is 22 years old and scored 87.46%
 * J-a-v-a-
 * Roses are red,
 * Java is great.
 * ----------------------------------------------------------
 */
