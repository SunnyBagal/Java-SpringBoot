/*
 * ============================================================
 *  TOPIC 20: THE "static" KEYWORD
 * ============================================================
 *
 *  static = belongs to the CLASS itself, not to any single object.
 *
 *  - static FIELD  -> ONE shared copy for all objects (e.g. a counter).
 *  - static METHOD -> called with ClassName.method(), no object needed
 *                     (e.g. Math.sqrt(), Integer.parseInt(), main()).
 *  - static BLOCK  -> runs ONCE when the class is first loaded.
 *
 *  RULE: A static method can't use instance fields/methods directly
 *        (or "this"), because there is no object to take them from.
 *
 *     Instance (non-static)          Static
 *     ---------------------          ------
 *     one copy PER OBJECT            one copy PER CLASS
 *     obj.field / obj.method()       ClassName.field / ClassName.method()
 *
 *  RUN:  java StaticKeyword.java
 */
public class StaticKeyword {

    public static void main(String[] args) {

        System.out.println("Objects created so far: " + Employee.count);  // no object needed

        Employee e1 = new Employee("Asha");
        Employee e2 = new Employee("Ravi");
        Employee e3 = new Employee("Meera");

        // Each object has its own name and id, but they SHARE count and company
        e1.show();
        e2.show();
        e3.show();
        System.out.println("Objects created so far: " + Employee.count);

        // Changing a static field affects EVERY object
        Employee.company = "NewCorp";
        e1.show();

        // Static (utility) methods - called on the class
        System.out.println("MathUtils.square(7) = " + MathUtils.square(7));
        System.out.println("MathUtils.isPrime(13) = " + MathUtils.isPrime(13));

        // Java's own static methods you'll use all the time
        System.out.println("Math.max(3, 9) = " + Math.max(3, 9));
        System.out.println("Math.sqrt(16) = " + Math.sqrt(16));
        System.out.println("Math.pow(2, 10) = " + Math.pow(2, 10));
        System.out.println("Math.abs(-5) = " + Math.abs(-5));
    }
}

class Employee {
    static int count = 0;               // shared by all Employee objects
    static String company;              // shared as well

    // static block: runs once, when Employee class is loaded (before first use)
    static {
        company = "TechCorp";
        System.out.println("[static block] Employee class loaded");
    }

    String name;                        // instance field - each object has its own
    int id;

    Employee(String name) {
        this.name = name;
        count++;                        // increase the SHARED counter
        this.id = count;                // use it to give each employee a unique id
    }

    void show() {
        // instance methods CAN use static fields
        System.out.println("#" + id + " " + name + " @ " + company);
    }
}

class MathUtils {
    // static methods: pure helpers that don't need object data
    static int square(int n) {
        return n * n;
    }

    static boolean isPrime(int n) {
        if (n < 2) return false;
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) return false;
        }
        return true;
    }
}

/*
 * ------------------------- OUTPUT -------------------------
 * [static block] Employee class loaded
 * Objects created so far: 0
 * #1 Asha @ TechCorp
 * #2 Ravi @ TechCorp
 * #3 Meera @ TechCorp
 * Objects created so far: 3
 * #1 Asha @ NewCorp
 * MathUtils.square(7) = 49
 * MathUtils.isPrime(13) = true
 * Math.max(3, 9) = 9
 * Math.sqrt(16) = 4.0
 * Math.pow(2, 10) = 1024.0
 * Math.abs(-5) = 5
 * ----------------------------------------------------------
 */
