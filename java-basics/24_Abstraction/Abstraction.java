/*
 * ============================================================
 *  TOPIC 24: ABSTRACTION with ABSTRACT CLASSES  (OOP pillar #4)
 * ============================================================
 *
 *  ABSTRACTION = show WHAT something does, hide HOW it does it.
 *  (You drive a car with a steering wheel; you don't need to know the engine.)
 *
 *  ABSTRACT CLASS:
 *   - Declared with "abstract". You CANNOT create objects of it (no "new").
 *   - Can have ABSTRACT methods: no body, just a signature ending in ;
 *       abstract double calculatePay();
 *     Every (non-abstract) child MUST implement them.
 *   - Can ALSO have normal methods, fields and constructors (shared code).
 *
 *  WHEN TO USE: Children share some code AND must each fill in some parts.
 *  (If there's no shared code at all, an interface is often better - topic 25.)
 *
 *  RUN:  java Abstraction.java
 */
public class Abstraction {

    public static void main(String[] args) {

        // Worker w = new Worker("X");     // ERROR: Worker is abstract; cannot be instantiated

        Worker[] team = {
                new FullTimeWorker("Asha", 50000),
                new PartTimeWorker("Ravi", 40, 300),
                new Intern("Meera")
        };

        for (Worker w : team) {
            w.printPaySlip();               // shared method, but uses each child's calculatePay()
        }
    }
}

abstract class Worker {
    protected String name;

    Worker(String name) {                   // abstract classes CAN have constructors
        this.name = name;
    }

    // ABSTRACT method: no body. "Every worker gets paid, but HOW is up to the child."
    abstract double calculatePay();

    abstract String role();

    // CONCRETE method: shared by all children
    void printPaySlip() {
        System.out.println(name + " (" + role() + ") -> Rs. " + calculatePay());
    }
}

class FullTimeWorker extends Worker {
    private double monthlySalary;

    FullTimeWorker(String name, double monthlySalary) {
        super(name);
        this.monthlySalary = monthlySalary;
    }

    @Override
    double calculatePay() {
        return monthlySalary;
    }

    @Override
    String role() {
        return "Full-time";
    }
}

class PartTimeWorker extends Worker {
    private int hours;
    private double ratePerHour;

    PartTimeWorker(String name, int hours, double ratePerHour) {
        super(name);
        this.hours = hours;
        this.ratePerHour = ratePerHour;
    }

    @Override
    double calculatePay() {
        return hours * ratePerHour;
    }

    @Override
    String role() {
        return "Part-time";
    }
}

class Intern extends Worker {
    Intern(String name) {
        super(name);
    }

    @Override
    double calculatePay() {
        return 10000;                       // fixed stipend
    }

    @Override
    String role() {
        return "Intern";
    }
}

/*
 * ------------------------- OUTPUT -------------------------
 * Asha (Full-time) -> Rs. 50000.0
 * Ravi (Part-time) -> Rs. 12000.0
 * Meera (Intern) -> Rs. 10000.0
 * ----------------------------------------------------------
 */
