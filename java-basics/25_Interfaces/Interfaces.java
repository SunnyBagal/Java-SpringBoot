/*
 * ============================================================
 *  TOPIC 25: INTERFACES
 * ============================================================
 *
 *  WHAT: An interface is a CONTRACT: a list of methods a class promises to have.
 *        A class "implements" an interface and must provide those methods.
 *
 *        interface Payment { void pay(double amount); }
 *        class UpiPayment implements Payment { public void pay(double a) { ... } }
 *
 *  KEY IDEAS:
 *   - Interface methods are public and abstract by default.
 *   - Fields in an interface are automatically public static final (constants).
 *   - A class can implement MANY interfaces (Java's answer to multiple inheritance).
 *   - "default" methods (Java 8+) have a body - an optional shared behaviour.
 *   - "static" methods in interfaces are helpers called with InterfaceName.method().
 *   - You can't create an object of an interface, but you CAN use it as a type:
 *        Payment p = new UpiPayment();      <- very common!
 *
 *  ABSTRACT CLASS vs INTERFACE (quick guide):
 *   - abstract class: "IS-A" + shared state/code, single inheritance.
 *   - interface:      "CAN-DO" capability, no instance state, multiple allowed.
 *
 *  WHY IT MATTERS FOR SPRING BOOT:
 *   Spring code is written against interfaces (e.g. JpaRepository, your own
 *   UserService). This "loose coupling" lets Spring swap implementations easily.
 *
 *  RUN:  java Interfaces.java
 */
public class Interfaces {

    public static void main(String[] args) {

        // Program to the INTERFACE type, not the concrete class
        Payment payment1 = new UpiPayment("sunny@upi");
        Payment payment2 = new CardPayment("1234567812345678");

        Checkout checkout = new Checkout(payment1);    // pass ANY Payment implementation
        checkout.placeOrder(499);

        checkout = new Checkout(payment2);             // swap it - Checkout code is unchanged
        checkout.placeOrder(1299);

        // default method (inherited from the interface)
        payment1.printReceipt(499);

        // static method on the interface
        System.out.println("Valid amount 0? " + Payment.isValidAmount(0));

        // constant from the interface
        System.out.println("Currency: " + Payment.CURRENCY);

        // A class implementing TWO interfaces
        SmartPhone phone = new SmartPhone();
        phone.call("9876543210");
        phone.takePhoto();

        Camera cam = phone;                            // phone can be used as a Camera...
        cam.takePhoto();
        Phone ph = phone;                              // ...or as a Phone
        ph.call("1122334455");
    }
}

// ---------------- Interface with abstract, default and static methods ----------------
interface Payment {
    String CURRENCY = "INR";                   // public static final automatically

    void pay(double amount);                   // public abstract automatically

    default void printReceipt(double amount) { // has a body; classes may override it
        System.out.println("Receipt: paid " + amount + " " + CURRENCY);
    }

    static boolean isValidAmount(double amount) {
        return amount > 0;
    }
}

class UpiPayment implements Payment {
    private String upiId;

    UpiPayment(String upiId) {
        this.upiId = upiId;
    }

    @Override
    public void pay(double amount) {           // must be public (interface methods are public)
        System.out.println("Paid " + amount + " via UPI (" + upiId + ")");
    }
}

class CardPayment implements Payment {
    private String cardNumber;

    CardPayment(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Override
    public void pay(double amount) {
        String last4 = cardNumber.substring(cardNumber.length() - 4);
        System.out.println("Paid " + amount + " via Card ending " + last4);
    }
}

// Checkout depends only on the Payment INTERFACE -> "loose coupling"
class Checkout {
    private final Payment payment;

    Checkout(Payment payment) {                // the dependency is given via constructor
        this.payment = payment;                // (this is exactly how Spring does it!)
    }

    void placeOrder(double amount) {
        if (!Payment.isValidAmount(amount)) {
            System.out.println("Invalid amount");
            return;
        }
        payment.pay(amount);
    }
}

// ---------------- Multiple interfaces ----------------
interface Phone {
    void call(String number);
}

interface Camera {
    void takePhoto();
}

class SmartPhone implements Phone, Camera {
    @Override
    public void call(String number) {
        System.out.println("Calling " + number);
    }

    @Override
    public void takePhoto() {
        System.out.println("Click! Photo taken");
    }
}

/*
 * ------------------------- OUTPUT -------------------------
 * Paid 499.0 via UPI (sunny@upi)
 * Paid 1299.0 via Card ending 5678
 * Receipt: paid 499.0 INR
 * Valid amount 0? false
 * Currency: INR
 * Calling 9876543210
 * Click! Photo taken
 * Click! Photo taken
 * Calling 1122334455
 * ----------------------------------------------------------
 */
