/*
 * ============================================================
 *  TOPIC 15: METHOD OVERLOADING
 * ============================================================
 *
 *  WHAT: Several methods with the SAME NAME but DIFFERENT PARAMETERS
 *        in the same class. Java picks the right one based on the arguments.
 *
 *  Parameters must differ in at least one of:
 *    - number of parameters     add(int, int)    vs add(int, int, int)
 *    - type of parameters       add(int, int)    vs add(double, double)
 *    - order of types           show(int, String) vs show(String, int)
 *
 *  NOT enough: only a different RETURN type -> compile error.
 *
 *  WHY: Same idea, different inputs. You already use it:
 *       System.out.println() is overloaded for int, String, double, boolean...
 *
 *  This is also called COMPILE-TIME POLYMORPHISM (see topic 23).
 *
 *  RUN:  java MethodOverloading.java
 */
public class MethodOverloading {

    static int add(int a, int b) {
        System.out.print("[add(int,int)] ");
        return a + b;
    }

    static int add(int a, int b, int c) {        // different NUMBER of params
        System.out.print("[add(int,int,int)] ");
        return a + b + c;
    }

    static double add(double a, double b) {      // different TYPE of params
        System.out.print("[add(double,double)] ");
        return a + b;
    }

    static String add(String a, String b) {
        System.out.print("[add(String,String)] ");
        return a + b;
    }

    static void show(int id, String name) {
        System.out.println("id first: " + id + " " + name);
    }

    static void show(String name, int id) {      // different ORDER of types
        System.out.println("name first: " + name + " " + id);
    }

    // static double add(int a, int b) { ... }   // ERROR: only return type differs

    public static void main(String[] args) {
        System.out.println(add(2, 3));
        System.out.println(add(2, 3, 4));
        System.out.println(add(2.5, 3.5));
        System.out.println(add("Hello, ", "Java"));
        show(1, "Sunny");
        show("Sunny", 1);

        // If there's no exact match Java may WIDEN the type:
        // add(5, 2.5) -> int 5 widens to double, so add(double,double) is used
        System.out.println(add(5, 2.5));
    }
}

/*
 * ------------------------- OUTPUT -------------------------
 * [add(int,int)] 5
 * [add(int,int,int)] 9
 * [add(double,double)] 6.0
 * [add(String,String)] Hello, Java
 * id first: 1 Sunny
 * name first: Sunny 1
 * [add(double,double)] 7.5
 * ----------------------------------------------------------
 */
