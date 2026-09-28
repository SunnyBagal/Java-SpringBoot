/*
 * ============================================================
 *  TOPIC 21: ACCESS MODIFIERS & ENCAPSULATION  (OOP pillar #1)
 * ============================================================
 *
 *  ACCESS MODIFIERS - who can see a class member:
 *
 *   Modifier     | Same class | Same package | Subclass | Everywhere
 *   -------------+------------+--------------+----------+-----------
 *   private      |    yes     |      no      |    no    |    no
 *   (default)    |    yes     |     yes      |    no*   |    no
 *   protected    |    yes     |     yes      |   yes    |    no
 *   public       |    yes     |     yes      |   yes    |   yes
 *   (* unless the subclass is in the same package)
 *
 *  ENCAPSULATION = hide the data, expose safe methods.
 *   1) Make fields PRIVATE so nobody can change them directly.
 *   2) Provide PUBLIC getters (read) and setters (write) that can VALIDATE.
 *
 *  WHY: Protects the object from invalid states (e.g. negative balance),
 *       and lets you change internals later without breaking other code.
 *
 *  Naming convention: getBalance(), setName(), and isActive() for booleans.
 *  (Spring Boot / JSON libraries rely on these names!)
 *
 *  RUN:  java Encapsulation.java
 */
public class Encapsulation {

    public static void main(String[] args) {

        BankAccount acc = new BankAccount("Sunny", 1000);

        // acc.balance = -5000;      // ERROR: balance has private access

        System.out.println("Owner: " + acc.getOwner());
        System.out.println("Balance: " + acc.getBalance());

        acc.deposit(500);
        acc.withdraw(200);
        acc.withdraw(5000);          // rejected by validation
        acc.deposit(-50);            // rejected by validation

        System.out.println("Final balance: " + acc.getBalance());

        acc.setOwner("");            // rejected
        acc.setOwner("Sunny Bagal");
        System.out.println("Owner: " + acc.getOwner());
        System.out.println("Active? " + acc.isActive());
    }
}

class BankAccount {

    // private -> only code INSIDE this class can touch these
    private String owner;
    private double balance;
    private boolean active = true;

    public BankAccount(String owner, double initialBalance) {
        this.owner = owner;
        this.balance = initialBalance;
    }

    // ---------- Getters: read-only access ----------
    public String getOwner() {
        return owner;
    }

    public double getBalance() {
        return balance;
    }

    public boolean isActive() {     // boolean getters usually start with "is"
        return active;
    }

    // ---------- Setter with validation ----------
    public void setOwner(String owner) {
        if (owner == null || owner.isBlank()) {
            System.out.println("Owner name can't be empty");
            return;
        }
        this.owner = owner;
    }

    // No setBalance()! Balance only changes through deposit/withdraw rules.
    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Deposit must be positive");
            return;
        }
        balance += amount;
        System.out.println("Deposited " + amount + " -> balance " + balance);
    }

    public void withdraw(double amount) {
        if (amount > balance) {
            System.out.println("Insufficient funds to withdraw " + amount);
            return;
        }
        balance -= amount;
        System.out.println("Withdrew " + amount + " -> balance " + balance);
    }
}

/*
 * ------------------------- OUTPUT -------------------------
 * Owner: Sunny
 * Balance: 1000.0
 * Deposited 500.0 -> balance 1500.0
 * Withdrew 200.0 -> balance 1300.0
 * Insufficient funds to withdraw 5000.0
 * Deposit must be positive
 * Final balance: 1300.0
 * Owner name can't be empty
 * Owner: Sunny Bagal
 * Active? true
 * ----------------------------------------------------------
 */
