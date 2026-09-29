/*
 * PRACTICE 11: Count how often each character appears
 * Concepts: TreeMap (sorted keys), getOrDefault, first non-repeating char
 * RUN: java CharFrequency.java
 */
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class CharFrequency {
    public static void main(String[] args) {
        String word = "programming";

        // TreeMap keeps the characters in alphabetical order
        Map<Character, Integer> freq = new TreeMap<>();
        for (char c : word.toCharArray()) {
            freq.put(c, freq.getOrDefault(c, 0) + 1);   // 0 if new, then +1
        }
        System.out.println("Frequencies: " + freq);

        // LinkedHashMap keeps INSERTION order -> lets us find the first unique char
        Map<Character, Integer> ordered = new LinkedHashMap<>();
        for (char c : word.toCharArray()) {
            ordered.merge(c, 1, Integer::sum);
        }
        for (Map.Entry<Character, Integer> e : ordered.entrySet()) {
            if (e.getValue() == 1) {
                System.out.println("First non-repeating: " + e.getKey());
                break;
            }
        }
    }
}

/*
 * ------------------------- OUTPUT -------------------------
 * Frequencies: {a=1, g=2, i=1, m=2, n=1, o=1, p=1, r=2}
 * First non-repeating: p
 * ----------------------------------------------------------
 */
