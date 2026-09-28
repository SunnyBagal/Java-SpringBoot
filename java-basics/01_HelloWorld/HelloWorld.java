/*
 * ============================================================
 *  TOPIC 01: HELLO WORLD - your first Java program
 * ============================================================
 *
 *  WHAT: The smallest program that prints something to the screen.
 *
 *  KEY IDEAS:
 *   - Every Java program lives inside a CLASS.
 *   - Java starts running your program from the main() method.
 *   - The file name MUST match the public class name
 *     (class HelloWorld  ->  file HelloWorld.java).
 *   - Every statement ends with a semicolon ;
 *   - Java is case-sensitive: "System" is not the same as "system".
 *
 *  HOW JAVA RUNS YOUR CODE:
 *   HelloWorld.java --(javac compiles)--> HelloWorld.class (bytecode)
 *   HelloWorld.class --(java runs it on the JVM)--> output
 *   The JVM (Java Virtual Machine) is why Java runs on Windows, Mac
 *   and Linux without changes: "write once, run anywhere".
 *
 *  RUN:  java HelloWorld.java
 */

// "public"  -> this class can be used from anywhere.
// "class"   -> we are defining a class (a container for code).
// "HelloWorld" -> the class name; must match the file name.
public class HelloWorld {

    // This is the ENTRY POINT. The JVM looks for exactly this method.
    // public  -> JVM (outside the class) must be able to call it.
    // static  -> can be called without creating an object of HelloWorld.
    // void    -> the method returns nothing.
    // main    -> the special name the JVM looks for.
    // String[] args -> command-line arguments passed to the program (an array of text).
    public static void main(String[] args) {

        // System      -> a built-in class that gives access to the system.
        // System.out  -> the "standard output" stream (your terminal).
        // println()   -> prints the text and then moves to a NEW line.
        System.out.println("Hello, World!");

        // print() (without "ln") does NOT move to a new line afterwards.
        System.out.print("Java ");
        System.out.print("is ");
        System.out.println("fun!"); // this println ends the line

        // \n inside a string is a "new line" character.
        // \t inside a string is a "tab" character.
        System.out.println("Line 1\nLine 2");
        System.out.println("Name:\tSunny");

        // This is a single-line comment. The compiler ignores it.

        /* This is a multi-line comment.
           It can span many lines.
           Also ignored by the compiler. */

        /** This is a Javadoc comment - used to generate documentation. */
    }
}

/*
 * ------------------------- OUTPUT -------------------------
 * Hello, World!
 * Java is fun!
 * Line 1
 * Line 2
 * Name:	Sunny
 * ----------------------------------------------------------
 */
