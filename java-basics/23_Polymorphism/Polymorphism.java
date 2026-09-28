/*
 * ============================================================
 *  TOPIC 23: POLYMORPHISM  (OOP pillar #3)
 * ============================================================
 *
 *  POLYMORPHISM = "many forms". One thing behaves differently depending on context.
 *
 *  1) COMPILE-TIME polymorphism = METHOD OVERLOADING (topic 15)
 *       Same method name, different parameters. Decided by the compiler.
 *
 *  2) RUNTIME polymorphism = METHOD OVERRIDING
 *       A PARENT-type variable can hold a CHILD object:
 *           Shape s = new Circle(2);
 *       When you call s.area(), Java runs the CHILD's version, decided while
 *       the program runs ("dynamic method dispatch").
 *
 *  WHY IT MATTERS:
 *   Write code once against the parent type, and it works for every child -
 *   even children written later. Spring Boot uses this everywhere: you depend on
 *   an interface (e.g. PaymentService) and Spring plugs in the real class.
 *
 *  RUN:  java Polymorphism.java
 */
public class Polymorphism {

    public static void main(String[] args) {

        // Parent-type references holding different child objects
        Shape s1 = new Circle(2);
        Shape s2 = new Rectangle(3, 4);
        Shape s3 = new Square(5);

        // Same method call, different behaviour -> runtime polymorphism
        System.out.println(s1.name() + " area: " + String.format("%.2f", s1.area()));
        System.out.println(s2.name() + " area: " + s2.area());
        System.out.println(s3.name() + " area: " + s3.area());

        // Real power: one loop handles ALL shapes, no if/else on the type
        Shape[] shapes = {s1, s2, s3, new Circle(1)};
        double total = 0;
        for (Shape shape : shapes) {
            total += shape.area();      // each object runs its own area()
        }
        System.out.println("Total area: " + String.format("%.2f", total));

        // A method that accepts ANY Shape
        printDetails(new Rectangle(1, 2));
        printDetails(new Square(3));

        // Parent reference can only call methods the PARENT knows about
        // s1.getRadius();    // ERROR: Shape has no getRadius()
        // To use child-only methods, check and cast (downcasting):
        if (s1 instanceof Circle c) {   // pattern matching (Java 16+): check + cast in one step
            System.out.println("Radius of s1: " + c.getRadius());
        }
    }

    static void printDetails(Shape shape) {
        System.out.println("printDetails -> " + shape.name() + " with area " + shape.area());
    }
}

class Shape {
    double area() {
        return 0;
    }

    String name() {
        return "Shape";
    }
}

class Circle extends Shape {
    private double radius;

    Circle(double radius) {
        this.radius = radius;
    }

    double getRadius() {
        return radius;
    }

    @Override
    double area() {
        return Math.PI * radius * radius;
    }

    @Override
    String name() {
        return "Circle";
    }
}

class Rectangle extends Shape {
    protected double width, height;

    Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    @Override
    double area() {
        return width * height;
    }

    @Override
    String name() {
        return "Rectangle";
    }
}

class Square extends Rectangle {
    Square(double side) {
        super(side, side);      // a square is a rectangle with equal sides
    }

    @Override
    String name() {             // area() is inherited from Rectangle
        return "Square";
    }
}

/*
 * ------------------------- OUTPUT -------------------------
 * Circle area: 12.57
 * Rectangle area: 12.0
 * Square area: 25.0
 * Total area: 52.71
 * printDetails -> Rectangle with area 2.0
 * printDetails -> Square with area 9.0
 * Radius of s1: 2.0
 * ----------------------------------------------------------
 */
