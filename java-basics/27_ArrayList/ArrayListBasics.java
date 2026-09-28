/*
 * ============================================================
 *  TOPIC 27: ArrayList (a resizable array)
 * ============================================================
 *
 *  WHAT: Like an array, but it GROWS and SHRINKS automatically.
 *        Part of the Collections Framework (java.util).
 *
 *        List<String> names = new ArrayList<>();
 *        ^interface   ^type inside <>   ^implementation
 *
 *  KEY IDEAS:
 *   - <String> is a GENERIC type: the list only accepts Strings (type-safe).
 *   - Only OBJECTS go inside, not primitives: use Integer not int,
 *     Double not double (see topic 30 - wrapper classes).
 *   - Declare as List (interface), create as ArrayList - good practice.
 *   - Keeps insertion ORDER and ALLOWS duplicates.
 *
 *  ARRAY vs ARRAYLIST:
 *     array:     fixed size,  arr.length,   arr[0],       works with primitives
 *     ArrayList: dynamic size, list.size(), list.get(0),  objects only, many methods
 *
 *  RUN:  java ArrayListBasics.java
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ArrayListBasics {

    public static void main(String[] args) {

        // ---------- 1) Create and add ----------
        List<String> fruits = new ArrayList<>();
        fruits.add("Apple");                  // add at the end
        fruits.add("Banana");
        fruits.add("Mango");
        fruits.add(1, "Orange");              // add at index 1 (others shift right)
        System.out.println("fruits: " + fruits);          // ArrayList prints nicely
        System.out.println("size: " + fruits.size());

        // ---------- 2) Read, update, search ----------
        System.out.println("get(0): " + fruits.get(0));
        fruits.set(2, "Grapes");              // replace element at index 2
        System.out.println("after set: " + fruits);
        System.out.println("contains Mango? " + fruits.contains("Mango"));
        System.out.println("indexOf Grapes: " + fruits.indexOf("Grapes"));

        // ---------- 3) Remove ----------
        fruits.remove("Apple");               // remove by value
        fruits.remove(0);                     // remove by index
        System.out.println("after removes: " + fruits);

        // ---------- 4) Loop over a list ----------
        List<Integer> marks = new ArrayList<>(List.of(88, 72, 95, 60));  // start with values
        int sum = 0;
        for (int m : marks) {                 // Integer auto-unboxes to int
            sum += m;
        }
        System.out.println("marks: " + marks + ", sum: " + sum);

        for (int i = 0; i < marks.size(); i++) {
            System.out.print("[" + i + "]=" + marks.get(i) + " ");
        }
        System.out.println();

        // ---------- 5) Sorting and other helpers ----------
        Collections.sort(marks);              // ascending
        System.out.println("sorted: " + marks);
        marks.sort(Collections.reverseOrder());   // descending
        System.out.println("descending: " + marks);
        System.out.println("max: " + Collections.max(marks) + ", min: " + Collections.min(marks));

        // ---------- 6) removeIf with a lambda (preview of topic 32) ----------
        marks.removeIf(m -> m < 70);          // remove every mark below 70
        System.out.println("after removeIf(< 70): " + marks);

        // ---------- 7) List of your own objects ----------
        List<Book> books = new ArrayList<>();
        books.add(new Book("Clean Code", 450));
        books.add(new Book("Head First Java", 600));
        for (Book b : books) {
            System.out.println(b.title + " - Rs." + b.price);
        }

        // ---------- 8) Immutable list ----------
        List<String> days = List.of("Mon", "Tue");   // cannot add/remove later
        // days.add("Wed");                          // throws UnsupportedOperationException
        System.out.println("days: " + days + ", isEmpty: " + days.isEmpty());

        fruits.clear();                       // remove everything
        System.out.println("after clear: " + fruits + ", isEmpty: " + fruits.isEmpty());
    }
}

class Book {
    String title;
    int price;

    Book(String title, int price) {
        this.title = title;
        this.price = price;
    }
}

/*
 * ------------------------- OUTPUT -------------------------
 * fruits: [Apple, Orange, Banana, Mango]
 * size: 4
 * get(0): Apple
 * after set: [Apple, Orange, Grapes, Mango]
 * contains Mango? true
 * indexOf Grapes: 2
 * after removes: [Grapes, Mango]
 * marks: [88, 72, 95, 60], sum: 315
 * [0]=88 [1]=72 [2]=95 [3]=60
 * sorted: [60, 72, 88, 95]
 * descending: [95, 88, 72, 60]
 * max: 95, min: 60
 * after removeIf(< 70): [95, 88, 72]
 * Clean Code - Rs.450
 * Head First Java - Rs.600
 * days: [Mon, Tue], isEmpty: false
 * after clear: [], isEmpty: true
 * ----------------------------------------------------------
 */
