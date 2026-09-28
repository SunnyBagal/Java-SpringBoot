/*
 * ============================================================
 *  TOPIC 18: CONSTRUCTORS
 * ============================================================
 *
 *  WHAT: A special method that runs AUTOMATICALLY when you create an object
 *        with "new". Used to give the object its starting values.
 *
 *  RULES:
 *   - Same name as the class.
 *   - NO return type (not even void).
 *   - If you write NO constructor, Java gives you a free empty "default constructor".
 *   - As soon as you write ANY constructor, that free one disappears.
 *   - You can have many constructors with different parameters (overloading).
 *   - this(...) calls another constructor of the same class (must be the first line).
 *
 *  In Spring Boot, constructors are how dependencies get "injected" into your
 *  classes (constructor injection) - you'll see this a lot!
 *
 *  RUN:  java Constructors.java
 */
public class Constructors {

    public static void main(String[] args) {

        Student s1 = new Student();                         // calls the no-arg constructor
        Student s2 = new Student("Sunny", 22);              // calls the 2-arg constructor
        Student s3 = new Student("Priya", 21, "Computer");  // calls the 3-arg constructor

        s1.display();
        s2.display();
        s3.display();

        // Copy constructor: make a new object with the same values as another
        Student s4 = new Student(s3);
        s4.name = "Priya (copy)";
        s3.display();                                       // original is unchanged
        s4.display();
    }
}

class Student {
    String name;
    int age;
    String branch;

    // 1) No-argument constructor - sets default values
    Student() {
        System.out.println("-> no-arg constructor called");
        this.name = "Unknown";
        this.age = 18;
        this.branch = "Not chosen";
    }

    // 2) Parameterized constructor
    //    "this.name" = the FIELD, "name" = the PARAMETER (see topic 19)
    Student(String name, int age) {
        this(name, age, "Not chosen");    // reuse the 3-arg constructor below (constructor chaining)
        System.out.println("-> 2-arg constructor called");
    }

    // 3) Another parameterized constructor (overloaded)
    Student(String name, int age, String branch) {
        System.out.println("-> 3-arg constructor called");
        this.name = name;
        this.age = age;
        this.branch = branch;
    }

    // 4) Copy constructor
    Student(Student other) {
        System.out.println("-> copy constructor called");
        this.name = other.name;
        this.age = other.age;
        this.branch = other.branch;
    }

    void display() {
        System.out.println(name + ", " + age + ", " + branch);
    }
}

/*
 * ------------------------- OUTPUT -------------------------
 * -> no-arg constructor called
 * -> 3-arg constructor called
 * -> 2-arg constructor called
 * -> 3-arg constructor called
 * Unknown, 18, Not chosen
 * Sunny, 22, Not chosen
 * Priya, 21, Computer
 * -> copy constructor called
 * Priya, 21, Computer
 * Priya (copy), 21, Computer
 * ----------------------------------------------------------
 *  Notice: for s2, the 3-arg constructor prints FIRST because this(...)
 *  runs it before the rest of the 2-arg constructor.
 */
