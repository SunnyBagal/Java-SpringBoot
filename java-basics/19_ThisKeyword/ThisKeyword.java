/*
 * ============================================================
 *  TOPIC 19: THE "this" KEYWORD
 * ============================================================
 *
 *  "this" means "the CURRENT object" - the one whose method/constructor is running.
 *
 *  USES:
 *   1) this.field      -> tell apart a field from a parameter with the same name
 *   2) this(...)       -> call another constructor of the same class
 *   3) this.method()   -> call another method on the same object (usually optional)
 *   4) return this     -> return the current object (enables "method chaining")
 *   5) pass this       -> give the current object to another method
 *
 *  RUN:  java ThisKeyword.java
 */
public class ThisKeyword {

    public static void main(String[] args) {

        // 1) Field vs parameter
        Person p = new Person("Sunny", 22);
        p.introduce();

        // 2) Without "this" the field is never set - a common beginner bug
        BuggyPerson b = new BuggyPerson("Ravi");
        System.out.println("BuggyPerson name: " + b.name);   // null!

        // 4) Method chaining with "return this"
        Pizza pizza = new Pizza()
                .size("Large")
                .addTopping("Cheese")
                .addTopping("Olives");
        pizza.show();

        // 5) Passing "this"
        p.register(new Registry());
    }
}

class Person {
    String name;
    int age;

    Person(String name, int age) {
        this.name = name;   // LEFT: the object's field, RIGHT: the parameter
        this.age = age;
    }

    void introduce() {
        // "this." is optional here because there's no parameter named name/age
        System.out.println("I am " + this.name + ", age " + age);
    }

    void register(Registry registry) {
        registry.add(this);         // pass the current Person object
    }
}

class BuggyPerson {
    String name;

    BuggyPerson(String name) {
        name = name;        // assigns the parameter to ITSELF - the field stays null
    }
}

class Pizza {
    private String size = "Medium";
    private String toppings = "";

    Pizza size(String size) {
        this.size = size;
        return this;        // return the same object so the next call can continue
    }

    Pizza addTopping(String topping) {
        toppings += topping + " ";
        return this;
    }

    void show() {
        System.out.println(size + " pizza with: " + toppings.trim());
    }
}

class Registry {
    void add(Person person) {
        System.out.println("Registered: " + person.name);
    }
}

/*
 * ------------------------- OUTPUT -------------------------
 * I am Sunny, age 22
 * BuggyPerson name: null
 * Large pizza with: Cheese Olives
 * Registered: Sunny
 * ----------------------------------------------------------
 */
