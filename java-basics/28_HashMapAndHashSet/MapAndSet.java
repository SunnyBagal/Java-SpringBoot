/*
 * ============================================================
 *  TOPIC 28: HashMap and HashSet
 * ============================================================
 *
 *  HashMap<K, V>  -> stores KEY -> VALUE pairs (like a dictionary).
 *     - Keys are UNIQUE; putting an existing key REPLACES its value.
 *     - Super fast lookup by key.
 *     - Order is NOT guaranteed (use LinkedHashMap to keep insertion order,
 *       TreeMap to keep keys sorted).
 *
 *  HashSet<E>     -> stores UNIQUE values only (duplicates are ignored).
 *     - Fast "contains" check. Order not guaranteed
 *       (LinkedHashSet = insertion order, TreeSet = sorted).
 *
 *  JSON objects in Spring Boot APIs map naturally to Map<String, Object>!
 *
 *  RUN:  java MapAndSet.java
 */
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class MapAndSet {

    public static void main(String[] args) {

        // ================= HashMap =================
        Map<String, Integer> ages = new HashMap<>();
        ages.put("Sunny", 22);                    // put(key, value)
        ages.put("Priya", 21);
        ages.put("Ravi", 25);
        ages.put("Sunny", 23);                    // same key -> value REPLACED (22 -> 23)

        System.out.println("Sunny's age: " + ages.get("Sunny"));
        System.out.println("Unknown key: " + ages.get("Amit"));            // null if missing
        System.out.println("getOrDefault: " + ages.getOrDefault("Amit", 0)); // safer
        System.out.println("containsKey Priya? " + ages.containsKey("Priya"));
        System.out.println("size: " + ages.size());

        ages.remove("Ravi");
        System.out.println("after remove, has Ravi? " + ages.containsKey("Ravi"));

        // Looping: TreeMap keeps keys in sorted order so the output is predictable
        Map<String, Integer> sorted = new TreeMap<>(ages);
        for (Map.Entry<String, Integer> entry : sorted.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }

        // Classic use: count how many times each word appears
        String sentence = "the cat and the dog and the bird";
        Map<String, Integer> wordCount = new TreeMap<>();
        for (String word : sentence.split(" ")) {
            // merge: if word is new put 1, otherwise add 1 to the old value
            wordCount.merge(word, 1, Integer::sum);
        }
        System.out.println("word count: " + wordCount);

        // keySet() and values()
        System.out.println("keys: " + wordCount.keySet());
        System.out.println("values: " + wordCount.values());

        // ================= HashSet =================
        Set<String> languages = new HashSet<>();
        languages.add("Java");
        languages.add("Python");
        boolean added = languages.add("Java");    // duplicate -> not added, returns false
        System.out.println("Java added twice? " + added);
        System.out.println("set size: " + languages.size());
        System.out.println("contains Python? " + languages.contains("Python"));

        // Removing duplicates from a list
        List<Integer> withDupes = List.of(5, 3, 5, 1, 3, 9);
        Set<Integer> unique = new TreeSet<>(withDupes);   // TreeSet = unique + sorted
        System.out.println("unique sorted: " + unique);

        // Set operations
        Set<Integer> a = new TreeSet<>(List.of(1, 2, 3, 4));
        Set<Integer> b = new TreeSet<>(List.of(3, 4, 5));

        Set<Integer> union = new TreeSet<>(a);
        union.addAll(b);                          // everything in a OR b
        Set<Integer> intersection = new TreeSet<>(a);
        intersection.retainAll(b);                // only items in a AND b
        Set<Integer> difference = new TreeSet<>(a);
        difference.removeAll(b);                  // in a but NOT in b

        System.out.println("union: " + union);
        System.out.println("intersection: " + intersection);
        System.out.println("difference: " + difference);
    }
}

/*
 * ------------------------- OUTPUT -------------------------
 * Sunny's age: 23
 * Unknown key: null
 * getOrDefault: 0
 * containsKey Priya? true
 * size: 3
 * after remove, has Ravi? false
 * Priya -> 21
 * Sunny -> 23
 * word count: {and=2, bird=1, cat=1, dog=1, the=3}
 * keys: [and, bird, cat, dog, the]
 * values: [2, 1, 1, 1, 3]
 * Java added twice? false
 * set size: 2
 * contains Python? true
 * unique sorted: [1, 3, 5, 9]
 * union: [1, 2, 3, 4, 5]
 * intersection: [3, 4]
 * difference: [1, 2]
 * ----------------------------------------------------------
 */
