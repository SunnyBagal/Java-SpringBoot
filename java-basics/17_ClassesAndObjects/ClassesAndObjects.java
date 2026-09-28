/*
 * ============================================================
 *  TOPIC 17: CLASSES AND OBJECTS  (start of OOP)
 * ============================================================
 *
 *  OOP = Object-Oriented Programming. We model real-world things as objects.
 *
 *  CLASS  = a BLUEPRINT / template.        e.g. the design of a car
 *  OBJECT = a real THING built from it.    e.g. your red car, my blue car
 *
 *  A class has:
 *   - FIELDS  (a.k.a. attributes / instance variables) -> what it HAS   (color, speed)
 *   - METHODS (behaviour)                              -> what it DOES  (drive, brake)
 *
 *  Create an object with the "new" keyword:
 *      Car myCar = new Car();
 *      ^type ^reference   ^creates the object in memory (the "heap")
 *
 *  Use the DOT operator to reach fields and methods:  myCar.color, myCar.drive()
 *
 *  Every object has its OWN copy of the fields.
 *
 *  Spring Boot is ALL classes and objects, so this topic is very important!
 *
 *  RUN:  java ClassesAndObjects.java
 */
public class ClassesAndObjects {

    public static void main(String[] args) {

        // Create (instantiate) two separate Car objects from the same blueprint
        Car car1 = new Car();
        car1.brand = "Tata";              // set fields using the dot operator
        car1.color = "Red";
        car1.speed = 0;

        Car car2 = new Car();
        car2.brand = "Mahindra";
        car2.color = "Black";

        // Call methods on each object - each works with ITS OWN data
        car1.accelerate(40);
        car1.accelerate(20);
        car2.accelerate(80);
        car1.displayInfo();
        car2.displayInfo();

        car1.brake();
        car1.displayInfo();

        // Unset fields get DEFAULT values: 0, false, null
        Car car3 = new Car();
        car3.displayInfo();

        // Two variables can point to the SAME object
        Car sameCar = car1;               // no "new" -> no new object is created
        sameCar.color = "Blue";
        System.out.println("car1 color is now: " + car1.color);

        // Printing an object directly calls its toString() method.
        // By default that shows ClassName@hashcode - see topic 22 for overriding it.
    }
}

// A second class in the same file (not public). In real projects, each class
// usually gets its own file: Car.java
class Car {

    // ---------- Fields (state) ----------
    String brand;
    String color;
    int speed;

    // ---------- Methods (behaviour) ----------
    void accelerate(int amount) {
        speed += amount;                  // changes THIS object's speed only
    }

    void brake() {
        speed = 0;
        System.out.println(brand + " stopped.");
    }

    void displayInfo() {
        System.out.println(brand + " | " + color + " | " + speed + " km/h");
    }
}

/*
 * ------------------------- OUTPUT -------------------------
 * Tata | Red | 60 km/h
 * Mahindra | Black | 80 km/h
 * Tata stopped.
 * Tata | Red | 0 km/h
 * null | null | 0 km/h
 * car1 color is now: Blue
 * ----------------------------------------------------------
 */
