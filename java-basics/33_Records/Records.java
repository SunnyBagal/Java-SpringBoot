/*
 * ============================================================
 *  TOPIC 33: RECORDS (Java 16+) - short data classes
 * ============================================================
 *
 *  PROBLEM: A simple class that just holds data needs a LOT of code:
 *           private final fields, constructor, getters, equals(),
 *           hashCode(), toString()...
 *
 *  SOLUTION: A record writes all of that for you in ONE line:
 *
 *        record Point(int x, int y) { }
 *
 *  Java auto-generates:
 *   - private final fields x and y
 *   - a constructor Point(int x, int y)
 *   - accessor methods x() and y()      <- note: NOT getX()
 *   - equals() and hashCode() comparing all fields
 *   - a readable toString(): Point[x=1, y=2]
 *
 *  RULES:
 *   - Records are IMMUTABLE: no setters, fields can't change.
 *   - You can add methods, static fields, and a "compact constructor" for validation.
 *   - A record can implement interfaces but cannot extend another class.
 *
 *  In Spring Boot, records are perfect for DTOs (data sent to / received
 *  from an API), e.g.  record UserResponse(Long id, String name) { }
 *
 *  RUN:  java Records.java
 */
public class Records {

    public static void main(String[] args) {

        // ---------- 1) Create and read ----------
        Point p1 = new Point(3, 4);
        System.out.println("p1: " + p1);                        // auto toString
        System.out.println("x = " + p1.x() + ", y = " + p1.y()); // auto accessors

        // ---------- 2) equals compares DATA (unlike normal classes) ----------
        Point p2 = new Point(3, 4);
        System.out.println("p1.equals(p2): " + p1.equals(p2));  // true - same values
        System.out.println("p1 == p2: " + (p1 == p2));          // false - different objects

        // Compare with a normal class that doesn't override equals
        OldPoint o1 = new OldPoint(3, 4);
        OldPoint o2 = new OldPoint(3, 4);
        System.out.println("OldPoint equals: " + o1.equals(o2)); // false!

        // ---------- 3) Custom methods ----------
        System.out.println("distance from origin: " + p1.distanceFromOrigin());

        // ---------- 4) Immutability: "change" by creating a new record ----------
        Point moved = p1.withX(10);
        System.out.println("original: " + p1 + ", moved: " + moved);

        // ---------- 5) Validation in a compact constructor ----------
        User u = new User("  sunny  ", "sunny@example.com");
        System.out.println(u);                                  // name was trimmed
        try {
            new User("Ravi", "not-an-email");
        } catch (IllegalArgumentException e) {
            System.out.println("Rejected: " + e.getMessage());
        }
    }
}

record Point(int x, int y) {

    // You can add your own methods
    double distanceFromOrigin() {
        return Math.sqrt(x * x + y * y);
    }

    Point withX(int newX) {
        return new Point(newX, y);            // return a NEW record; the old one is unchanged
    }
}

record User(String name, String email) {

    // COMPACT CONSTRUCTOR: no parameter list; runs before fields are assigned
    User {
        if (!email.contains("@")) {
            throw new IllegalArgumentException("invalid email: " + email);
        }
        name = name.trim();                   // you may adjust the parameter values
    }
}

// The "old way" - and this still lacks equals/hashCode/toString!
class OldPoint {
    private final int x;
    private final int y;

    OldPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    int getX() { return x; }
    int getY() { return y; }
}

/*
 * ------------------------- OUTPUT -------------------------
 * p1: Point[x=3, y=4]
 * x = 3, y = 4
 * p1.equals(p2): true
 * p1 == p2: false
 * OldPoint equals: false
 * distance from origin: 5.0
 * original: Point[x=3, y=4], moved: Point[x=10, y=4]
 * User[name=sunny, email=sunny@example.com]
 * Rejected: invalid email: not-an-email
 * ----------------------------------------------------------
 */
