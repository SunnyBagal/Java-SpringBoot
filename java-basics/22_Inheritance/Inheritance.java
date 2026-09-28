/*
 * ============================================================
 *  TOPIC 22: INHERITANCE  (OOP pillar #2)
 * ============================================================
 *
 *  WHAT: A class (CHILD / subclass) gets the fields and methods of another
 *        class (PARENT / superclass) using the "extends" keyword.
 *
 *        class Dog extends Animal { ... }     // Dog IS-A Animal
 *
 *  WHY: Reuse code. Put common things in the parent, special things in the child.
 *
 *  KEY IDEAS:
 *   - super(...)      -> call the parent's constructor (must be the first line).
 *   - super.method()  -> call the parent's version of a method.
 *   - @Override       -> child REPLACES a parent method with its own version.
 *                        (The annotation makes the compiler check you did it right.)
 *   - Java allows only ONE parent class (no multiple inheritance of classes).
 *     Use interfaces for that (topic 25).
 *   - private members are NOT directly accessible in the child.
 *   - "final class" cannot be extended; "final method" cannot be overridden.
 *   - Every class secretly extends java.lang.Object, which gives toString(),
 *     equals(), hashCode()...
 *
 *  TYPES: single (A->B), multilevel (A->B->C), hierarchical (A->B, A->C).
 *
 *  RUN:  java Inheritance.java
 */
public class Inheritance {

    public static void main(String[] args) {

        Dog dog = new Dog("Bruno", 3, "Labrador");
        dog.eat();          // inherited from Animal
        dog.makeSound();    // overridden in Dog
        dog.fetch();        // only Dog has this
        System.out.println(dog);     // println calls toString(), which Dog overrides

        System.out.println();
        Cat cat = new Cat("Kitty", 2);
        cat.eat();
        cat.makeSound();

        System.out.println();
        Puppy puppy = new Puppy("Tiny", 0, "Beagle");   // multilevel: Animal -> Dog -> Puppy
        puppy.makeSound();

        // instanceof checks the IS-A relationship
        System.out.println("puppy instanceof Animal? " + (puppy instanceof Animal));
        System.out.println("cat instanceof Animal? " + (cat instanceof Animal));
    }
}

// ---------------- PARENT ----------------
class Animal {
    protected String name;      // protected -> visible to child classes
    protected int age;

    Animal(String name, int age) {
        System.out.println("[Animal constructor] " + name);
        this.name = name;
        this.age = age;
    }

    void eat() {
        System.out.println(name + " is eating.");
    }

    void makeSound() {
        System.out.println(name + " makes a sound.");
    }
}

// ---------------- CHILD 1 ----------------
class Dog extends Animal {
    private String breed;       // extra field only dogs have

    Dog(String name, int age, String breed) {
        super(name, age);       // parent sets up name and age FIRST
        System.out.println("[Dog constructor] " + breed);
        this.breed = breed;
    }

    @Override
    void makeSound() {
        System.out.println(name + " says: Woof!");
    }

    void fetch() {
        System.out.println(name + " fetches the ball.");
    }

    @Override
    public String toString() {  // overriding a method from java.lang.Object
        return "Dog{name=" + name + ", age=" + age + ", breed=" + breed + "}";
    }
}

// ---------------- CHILD 2 (hierarchical) ----------------
class Cat extends Animal {
    Cat(String name, int age) {
        super(name, age);
    }

    @Override
    void makeSound() {
        System.out.println(name + " says: Meow!");
    }
}

// ---------------- GRANDCHILD (multilevel) ----------------
class Puppy extends Dog {
    Puppy(String name, int age, String breed) {
        super(name, age, breed);
    }

    @Override
    void makeSound() {
        super.makeSound();      // first do what Dog does...
        System.out.println(name + " also whimpers (it's a puppy).");   // ...then add more
    }
}

/*
 * ------------------------- OUTPUT -------------------------
 * [Animal constructor] Bruno
 * [Dog constructor] Labrador
 * Bruno is eating.
 * Bruno says: Woof!
 * Bruno fetches the ball.
 * Dog{name=Bruno, age=3, breed=Labrador}
 *
 * [Animal constructor] Kitty
 * Kitty is eating.
 * Kitty says: Meow!
 *
 * [Animal constructor] Tiny
 * [Dog constructor] Beagle
 * Tiny says: Woof!
 * Tiny also whimpers (it's a puppy).
 * puppy instanceof Animal? true
 * cat instanceof Animal? true
 * ----------------------------------------------------------
 */
