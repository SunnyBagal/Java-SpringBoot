/*
 * ============================================================
 *  TOPIC 31: StringBuilder (mutable strings)
 * ============================================================
 *
 *  PROBLEM: Strings are IMMUTABLE. Every  s = s + "x"  creates a brand-new
 *           String object. In a loop of 10,000 steps that's 10,000 objects - slow.
 *
 *  SOLUTION: StringBuilder is a MUTABLE sequence of characters. It changes
 *            in place, so building text piece by piece is fast.
 *
 *  COMMON METHODS (most return the same builder, so you can chain them):
 *     append(x)         add to the end
 *     insert(i, x)      insert at index i
 *     delete(start,end) remove characters [start, end)
 *     deleteCharAt(i)   remove one character
 *     replace(s, e, x)  replace a range
 *     reverse()         reverse in place
 *     setCharAt(i, c)   change one character
 *     length(), charAt(i)
 *     toString()        get the final String
 *
 *  (StringBuffer is the older, thread-safe but slower version. Use StringBuilder.)
 *
 *  RUN:  java StringBuilderBasics.java
 */
public class StringBuilderBasics {

    public static void main(String[] args) {

        // ---------- 1) Build and modify ----------
        StringBuilder sb = new StringBuilder("Hello");
        sb.append(" World");                  // Hello World
        System.out.println("append: " + sb);

        sb.insert(5, ",");                    // Hello, World
        System.out.println("insert: " + sb);

        sb.replace(7, 12, "Java");            // replace "World" with "Java"
        System.out.println("replace: " + sb);

        sb.delete(5, 6);                      // remove the comma
        System.out.println("delete: " + sb);

        sb.setCharAt(0, 'J');                 // Jello Java
        System.out.println("setCharAt: " + sb);

        sb.reverse();
        System.out.println("reverse: " + sb);
        System.out.println("length: " + sb.length());

        // ---------- 2) Method chaining ----------
        String result = new StringBuilder()
                .append("Name: ").append("Sunny")
                .append(", Age: ").append(22)       // append works with any type
                .toString();                        // convert back to a String
        System.out.println(result);

        // ---------- 3) Building in a loop (the main use case) ----------
        StringBuilder numbers = new StringBuilder();
        for (int i = 1; i <= 5; i++) {
            numbers.append(i);
            if (i < 5) {
                numbers.append(", ");
            }
        }
        System.out.println("numbers: " + numbers);

        // ---------- 4) Palindrome check using reverse ----------
        String word = "racecar";
        boolean isPalindrome = new StringBuilder(word).reverse().toString().equals(word);
        System.out.println(word + " is palindrome? " + isPalindrome);

        // ---------- 5) Speed comparison (times will differ on your machine) ----------
        long start = System.nanoTime();
        String slow = "";
        for (int i = 0; i < 20000; i++) {
            slow += "a";                      // creates a new String each time
        }
        long stringTime = System.nanoTime() - start;

        start = System.nanoTime();
        StringBuilder fast = new StringBuilder();
        for (int i = 0; i < 20000; i++) {
            fast.append("a");                 // modifies the same object
        }
        long builderTime = System.nanoTime() - start;

        System.out.println("Both have length " + slow.length() + " and " + fast.length());
        System.out.println("StringBuilder was faster? " + (builderTime < stringTime));
    }
}

/*
 * ------------------------- OUTPUT -------------------------
 * append: Hello World
 * insert: Hello, World
 * replace: Hello, Java
 * delete: Hello Java
 * setCharAt: Jello Java
 * reverse: avaJ olleJ
 * length: 10
 * Name: Sunny, Age: 22
 * numbers: 1, 2, 3, 4, 5
 * racecar is palindrome? true
 * Both have length 20000 and 20000
 * StringBuilder was faster? true
 * ----------------------------------------------------------
 */
