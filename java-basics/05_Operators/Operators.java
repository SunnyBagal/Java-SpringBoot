/*
 * ============================================================
 *  TOPIC 05: OPERATORS
 * ============================================================
 *
 *  WHAT: Symbols that perform operations on values.
 *
 *  1) Arithmetic : +  -  *  /  %(remainder)
 *  2) Assignment : =  +=  -=  *=  /=  %=
 *  3) Increment / Decrement : ++  --
 *  4) Comparison (result is boolean) : ==  !=  >  <  >=  <=
 *  5) Logical (combine booleans) : && (AND)  || (OR)  ! (NOT)
 *  6) Ternary (short if-else) : condition ? valueIfTrue : valueIfFalse
 *
 *  RUN:  java Operators.java
 */
public class Operators {

    public static void main(String[] args) {

        int a = 17, b = 5;

        // ---------- 1) Arithmetic ----------
        System.out.println("a + b = " + (a + b));   // 22
        System.out.println("a - b = " + (a - b));   // 12
        System.out.println("a * b = " + (a * b));   // 85
        System.out.println("a / b = " + (a / b));   // 3   (int / int -> decimals dropped)
        System.out.println("a % b = " + (a % b));   // 2   (remainder of 17 / 5)

        // % is very useful: number % 2 == 0 means the number is EVEN
        System.out.println("Is 10 even? " + (10 % 2 == 0));

        // ---------- 2) Assignment shortcuts ----------
        int x = 10;
        x += 5;     // same as x = x + 5  -> 15
        System.out.println("x += 5  -> " + x);
        x -= 3;     // x = x - 3 -> 12
        System.out.println("x -= 3  -> " + x);
        x *= 2;     // x = x * 2 -> 24
        System.out.println("x *= 2  -> " + x);
        x /= 4;     // x = x / 4 -> 6
        System.out.println("x /= 4  -> " + x);
        x %= 4;     // x = x % 4 -> 2
        System.out.println("x %= 4  -> " + x);

        // ---------- 3) Increment / Decrement ----------
        int count = 5;
        count++;    // add 1 -> 6
        count--;    // subtract 1 -> 5
        System.out.println("count: " + count);

        // PREFIX vs POSTFIX - matters when used inside an expression
        int i = 5;
        int post = i++;   // POSTFIX: first give old value (5) to post, THEN i becomes 6
        System.out.println("post = " + post + ", i = " + i);

        int j = 5;
        int pre = ++j;    // PREFIX: first j becomes 6, THEN give 6 to pre
        System.out.println("pre = " + pre + ", j = " + j);

        // ---------- 4) Comparison ----------
        System.out.println("a == b: " + (a == b));  // equal to?
        System.out.println("a != b: " + (a != b));  // not equal to?
        System.out.println("a > b:  " + (a > b));
        System.out.println("a <= b: " + (a <= b));

        // ---------- 5) Logical ----------
        int age = 20;
        boolean hasId = true;
        // && -> true only if BOTH sides are true
        System.out.println("Can enter? " + (age >= 18 && hasId));
        // || -> true if AT LEAST ONE side is true
        System.out.println("Weekend or holiday? " + (false || true));
        // !  -> flips true to false and false to true
        System.out.println("!hasId: " + !hasId);

        // Short-circuit: in A && B, if A is false Java doesn't even check B
        // (useful to avoid errors, e.g. name != null && name.length() > 0)

        // ---------- 6) Ternary ----------
        int marks = 72;
        String result = (marks >= 40) ? "Pass" : "Fail";
        System.out.println("Result: " + result);

        // ---------- Operator precedence ----------
        // * / % happen before + -   (like BODMAS in maths). Use () to be clear.
        System.out.println("2 + 3 * 4 = " + (2 + 3 * 4));     // 14
        System.out.println("(2 + 3) * 4 = " + ((2 + 3) * 4)); // 20

        // Careful with + and Strings: evaluated left to right
        System.out.println(1 + 2 + " apples");   // 3 apples  (numbers added first)
        System.out.println("apples " + 1 + 2);   // apples 12 (becomes text first)
    }
}

/*
 * ------------------------- OUTPUT -------------------------
 * a + b = 22
 * a - b = 12
 * a * b = 85
 * a / b = 3
 * a % b = 2
 * Is 10 even? true
 * x += 5  -> 15
 * x -= 3  -> 12
 * x *= 2  -> 24
 * x /= 4  -> 6
 * x %= 4  -> 2
 * count: 5
 * post = 5, i = 6
 * pre = 6, j = 6
 * a == b: false
 * a != b: true
 * a > b:  true
 * a <= b: false
 * Can enter? true
 * Weekend or holiday? true
 * !hasId: false
 * Result: Pass
 * 2 + 3 * 4 = 14
 * (2 + 3) * 4 = 20
 * 3 apples
 * apples 12
 * ----------------------------------------------------------
 */
