/*
 * ============================================================
 *  TOPIC 02: VARIABLES
 * ============================================================
 *
 *  WHAT: A variable is a named box in memory that stores a value.
 *
 *  SYNTAX:   type name = value;
 *            int  age  = 25;
 *
 *  KEY IDEAS:
 *   - Java is STATICALLY TYPED: you must say what type a variable holds,
 *     and it can never hold a different type later.
 *   - Declare  = create the box         ->  int age;
 *   - Initialize = put the first value  ->  age = 25;
 *   - You can do both at once           ->  int age = 25;
 *   - "final" makes a variable a CONSTANT (cannot be changed).
 *   - "var" (Java 10+) lets the compiler figure out the type for you.
 *
 *  NAMING RULES:
 *   - Can contain letters, digits, _ and $ but cannot START with a digit.
 *   - Cannot be a keyword (int, class, public ...).
 *   - Convention: camelCase for variables  -> firstName, totalMarks
 *   - Convention: UPPER_SNAKE_CASE for constants -> MAX_SPEED
 *
 *  RUN:  java Variables.java
 */
public class Variables {

    public static void main(String[] args) {

        // 1) Declare and initialize in one line
        int age = 25;                 // whole number
        String name = "Sunny";        // text (String starts with capital S - it's a class)
        double height = 5.9;          // decimal number
        boolean isStudent = true;     // true or false
        char grade = 'A';             // single character - uses SINGLE quotes

        // + joins (concatenates) text with values
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Height: " + height);
        System.out.println("Student? " + isStudent);
        System.out.println("Grade: " + grade);

        // 2) Declare first, initialize later
        int score;                    // declared (box created, but empty)
        score = 90;                   // initialized (value put in box)
        System.out.println("Score: " + score);

        // 3) Changing (re-assigning) a variable's value
        score = 95;                   // old value 90 is replaced
        System.out.println("New score: " + score);

        // score = "ninety";          // ERROR! score is an int, cannot hold text

        // 4) Declare multiple variables of the same type in one line
        int x = 10, y = 20, z = 30;
        System.out.println("x + y + z = " + (x + y + z)); // () so math happens first

        // 5) Constants with "final"
        final double PI = 3.14159;
        // PI = 3.0;                  // ERROR! cannot assign a value to final variable
        System.out.println("PI: " + PI);

        // 6) "var" - type inference. Compiler sees 100 and decides it's an int.
        var count = 100;              // count is an int
        var city = "Pune";            // city is a String
        System.out.println("Count: " + count + ", City: " + city);

        // 7) Copying a variable copies its VALUE (for primitive types)
        int a = 5;
        int b = a;                    // b gets a copy of 5
        a = 50;                       // changing a does NOT change b
        System.out.println("a = " + a + ", b = " + b);
    }
}

/*
 * ------------------------- OUTPUT -------------------------
 * Name: Sunny
 * Age: 25
 * Height: 5.9
 * Student? true
 * Grade: A
 * Score: 90
 * New score: 95
 * x + y + z = 60
 * PI: 3.14159
 * Count: 100, City: Pune
 * a = 50, b = 5
 * ----------------------------------------------------------
 */
