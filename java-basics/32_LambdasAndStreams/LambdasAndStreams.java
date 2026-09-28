/*
 * ============================================================
 *  TOPIC 32: LAMBDAS & STREAMS (modern Java, used A LOT in Spring Boot)
 * ============================================================
 *
 *  LAMBDA = a short, nameless function you can pass around like a value.
 *
 *      (parameters) -> expression
 *      (a, b)       -> a + b
 *      x            -> x * 2              (one parameter: brackets optional)
 *      ()           -> System.out.println("hi")
 *      (x) -> { int y = x * 2; return y; } (multi-line needs { } and return)
 *
 *  A lambda implements a FUNCTIONAL INTERFACE = an interface with exactly ONE
 *  abstract method. Built-in ones in java.util.function:
 *      Predicate<T>     T -> boolean      (test something)
 *      Function<T, R>   T -> R            (transform)
 *      Consumer<T>      T -> void         (do something with it)
 *      Supplier<T>      () -> T           (produce a value)
 *
 *  METHOD REFERENCE = shorter lambda when you just call one method:
 *      s -> s.toUpperCase()     ==   String::toUpperCase
 *      x -> System.out.println(x) == System.out::println
 *
 *  STREAM = a pipeline to process a collection step by step:
 *      list.stream()          // 1. source
 *          .filter(...)       // 2. intermediate operations (lazy, return a stream)
 *          .map(...)
 *          .sorted()
 *          .toList();         // 3. terminal operation (produces the result)
 *  Streams DON'T change the original list.
 *
 *  RUN:  java LambdasAndStreams.java
 */
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LambdasAndStreams {

    public static void main(String[] args) {

        // ================= LAMBDAS =================

        // Our own functional interface, implemented with a lambda
        Calculator add = (a, b) -> a + b;
        Calculator multiply = (a, b) -> a * b;
        System.out.println("add: " + add.calculate(4, 5) + ", multiply: " + multiply.calculate(4, 5));

        // Built-in functional interfaces
        Predicate<Integer> isEven = n -> n % 2 == 0;
        Function<String, Integer> length = s -> s.length();
        Consumer<String> shout = s -> System.out.println(s.toUpperCase() + "!");
        Supplier<String> greeting = () -> "Hello from Supplier";

        System.out.println("isEven(4): " + isEven.test(4));
        System.out.println("length(\"Spring\"): " + length.apply("Spring"));
        shout.accept("lambdas are cool");
        System.out.println(greeting.get());

        // Lambdas with collections
        List<String> names = new ArrayList<>(List.of("Ravi", "asha", "Meera", "John"));
        names.forEach(name -> System.out.print(name + " "));   // forEach takes a Consumer
        System.out.println();
        names.sort(String::compareToIgnoreCase);                // method reference as Comparator
        System.out.println("sorted ignoring case: " + names);

        // ================= STREAMS =================
        List<Integer> numbers = List.of(5, 12, 8, 3, 20, 15, 8);

        // filter -> keep items that match
        List<Integer> evens = numbers.stream()
                .filter(n -> n % 2 == 0)
                .toList();
        System.out.println("evens: " + evens);

        // map -> transform each item
        List<Integer> squares = numbers.stream()
                .map(n -> n * n)
                .toList();
        System.out.println("squares: " + squares);

        // chaining: distinct, sorted, limit
        List<Integer> top3 = numbers.stream()
                .distinct()                                 // remove duplicates
                .sorted(Comparator.reverseOrder())          // biggest first
                .limit(3)                                   // keep first 3
                .toList();
        System.out.println("top 3 unique: " + top3);

        // reduce / sum / count / average
        int sum = numbers.stream().mapToInt(Integer::intValue).sum();
        long countBig = numbers.stream().filter(n -> n > 10).count();
        double avg = numbers.stream().mapToInt(n -> n).average().orElse(0);
        System.out.println("sum: " + sum + ", count > 10: " + countBig + ", average: " + String.format("%.2f", avg));

        // anyMatch / allMatch
        System.out.println("any > 18? " + numbers.stream().anyMatch(n -> n > 18));
        System.out.println("all positive? " + numbers.stream().allMatch(n -> n > 0));

        // Optional: a box that may or may not contain a value (avoids null)
        Optional<Integer> firstBig = numbers.stream().filter(n -> n > 100).findFirst();
        System.out.println("first > 100 present? " + firstBig.isPresent() + ", orElse: " + firstBig.orElse(-1));

        // Streams with objects
        List<Employee> employees = List.of(
                new Employee("Asha", "IT", 70000),
                new Employee("Ravi", "HR", 45000),
                new Employee("Meera", "IT", 85000),
                new Employee("John", "HR", 50000)
        );

        List<String> itNames = employees.stream()
                .filter(e -> e.dept().equals("IT"))
                .map(Employee::name)                        // method reference to a getter
                .toList();
        System.out.println("IT employees: " + itNames);

        Optional<Employee> richest = employees.stream()
                .max(Comparator.comparingDouble(Employee::salary));
        System.out.println("highest paid: " + richest.get().name());

        // Collectors.groupingBy -> Map of dept -> list of names
        Map<String, List<String>> byDept = employees.stream()
                .collect(Collectors.groupingBy(Employee::dept,
                        java.util.TreeMap::new,             // TreeMap = sorted keys
                        Collectors.mapping(Employee::name, Collectors.toList())));
        System.out.println("by department: " + byDept);

        // Collectors.joining -> one String
        String allNames = employees.stream().map(Employee::name).collect(Collectors.joining(", ", "[", "]"));
        System.out.println("joined: " + allNames);

        // IntStream for number ranges
        System.out.println("sum 1..10: " + IntStream.rangeClosed(1, 10).sum());
    }
}

@FunctionalInterface          // compiler checks there is exactly one abstract method
interface Calculator {
    int calculate(int a, int b);
}

// A "record" is a short way to write a data class (see topic 33)
record Employee(String name, String dept, double salary) { }

/*
 * ------------------------- OUTPUT -------------------------
 * add: 9, multiply: 20
 * isEven(4): true
 * length("Spring"): 6
 * LAMBDAS ARE COOL!
 * Hello from Supplier
 * Ravi asha Meera John
 * sorted ignoring case: [asha, John, Meera, Ravi]
 * evens: [12, 8, 20, 8]
 * squares: [25, 144, 64, 9, 400, 225, 64]
 * top 3 unique: [20, 15, 12]
 * sum: 71, count > 10: 3, average: 10.14
 * any > 18? true
 * all positive? true
 * first > 100 present? false, orElse: -1
 * IT employees: [Asha, Meera]
 * highest paid: Meera
 * by department: {HR=[Ravi, John], IT=[Asha, Meera]}
 * joined: [Asha, Ravi, Meera, John]
 * sum 1..10: 55
 * ----------------------------------------------------------
 */
