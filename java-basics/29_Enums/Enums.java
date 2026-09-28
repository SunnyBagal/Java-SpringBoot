/*
 * ============================================================
 *  TOPIC 29: ENUMS (a fixed set of named constants)
 * ============================================================
 *
 *  WHAT: Use an enum when a value can only be one of a KNOWN, FIXED list:
 *        days of the week, order status, user roles, directions...
 *
 *        enum Status { PENDING, SHIPPED, DELIVERED }
 *
 *  WHY not just Strings? "SHIPED" (typo) compiles fine as a String but is
 *  a compile ERROR with an enum. Enums are type-safe.
 *
 *  KEY IDEAS:
 *   - Constants are written in UPPER_CASE by convention.
 *   - values()        -> array of all constants
 *   - name()          -> the constant's name as a String
 *   - ordinal()       -> its position (0, 1, 2...)
 *   - valueOf("X")    -> String -> enum constant
 *   - Enums can have fields, constructors and methods.
 *   - Work great with switch.
 *
 *  In Spring Boot, enums are used for things like Role.ADMIN / Role.USER
 *  and are saved to the database with @Enumerated.
 *
 *  RUN:  java Enums.java
 */
public class Enums {

    public static void main(String[] args) {

        // ---------- 1) Basic enum ----------
        Day today = Day.SATURDAY;
        System.out.println("Today: " + today);
        System.out.println("name(): " + today.name() + ", ordinal(): " + today.ordinal());

        // Compare enums with == (safe, since each constant exists only once)
        if (today == Day.SATURDAY || today == Day.SUNDAY) {
            System.out.println("Weekend!");
        }

        // ---------- 2) Loop over all values ----------
        System.out.print("All days: ");
        for (Day d : Day.values()) {
            System.out.print(d + " ");
        }
        System.out.println();

        // ---------- 3) String -> enum ----------
        Day parsed = Day.valueOf("MONDAY");       // must match exactly, else IllegalArgumentException
        System.out.println("Parsed: " + parsed);

        // ---------- 4) Enum with switch ----------
        OrderStatus status = OrderStatus.SHIPPED;
        String message = switch (status) {        // no need to write OrderStatus.SHIPPED here
            case PLACED -> "We got your order";
            case SHIPPED -> "On the way!";
            case DELIVERED -> "Enjoy!";
            case CANCELLED -> "Order cancelled";
        };
        System.out.println(status + ": " + message);

        // ---------- 5) Enum with fields and methods ----------
        for (Planet p : Planet.values()) {
            System.out.printf("%s: gravity %.1f m/s2, 70kg person weighs %.1f kg-force%n",
                    p, p.getGravity(), p.weightOf(70));
        }

        // Each constant can carry data
        System.out.println(OrderStatus.DELIVERED + " label: " + OrderStatus.DELIVERED.getLabel());
    }
}

enum Day {
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
}

enum OrderStatus {
    PLACED("Order placed"),               // calls the constructor below with this label
    SHIPPED("Shipped"),
    DELIVERED("Delivered to customer"),
    CANCELLED("Cancelled");

    private final String label;           // each constant has its own label

    OrderStatus(String label) {           // enum constructors are always private
        this.label = label;
    }

    String getLabel() {
        return label;
    }
}

enum Planet {
    MERCURY(3.7), EARTH(9.8), JUPITER(24.8);

    private final double gravity;

    Planet(double gravity) {
        this.gravity = gravity;
    }

    double getGravity() {
        return gravity;
    }

    double weightOf(double massKg) {
        return massKg * gravity / EARTH_GRAVITY;
    }

    private static final double EARTH_GRAVITY = 9.8;
}

/*
 * ------------------------- OUTPUT -------------------------
 * Today: SATURDAY
 * name(): SATURDAY, ordinal(): 5
 * Weekend!
 * All days: MONDAY TUESDAY WEDNESDAY THURSDAY FRIDAY SATURDAY SUNDAY
 * Parsed: MONDAY
 * SHIPPED: On the way!
 * MERCURY: gravity 3.7 m/s2, 70kg person weighs 26.4 kg-force
 * EARTH: gravity 9.8 m/s2, 70kg person weighs 70.0 kg-force
 * JUPITER: gravity 24.8 m/s2, 70kg person weighs 177.1 kg-force
 * DELIVERED label: Delivered to customer
 * ----------------------------------------------------------
 */
