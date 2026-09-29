/*
 * PRACTICE 10: Count vowels, consonants, digits and spaces
 * Concepts: toCharArray, Character helpers, String.indexOf
 * RUN: java CountVowels.java
 */
public class CountVowels {
    public static void main(String[] args) {
        String text = "Spring Boot 4 is Awesome";
        int vowels = 0, consonants = 0, digits = 0, spaces = 0;

        for (char c : text.toLowerCase().toCharArray()) {
            if ("aeiou".indexOf(c) != -1) {     // is c one of a, e, i, o, u?
                vowels++;
            } else if (Character.isLetter(c)) {
                consonants++;
            } else if (Character.isDigit(c)) {
                digits++;
            } else if (c == ' ') {
                spaces++;
            }
        }
        System.out.println("Text: " + text);
        System.out.println("Vowels: " + vowels + ", Consonants: " + consonants
                + ", Digits: " + digits + ", Spaces: " + spaces);
    }
}

/*
 * ------------------------- OUTPUT -------------------------
 * Text: Spring Boot 4 is Awesome
 * Vowels: 8, Consonants: 11, Digits: 1, Spaces: 4
 * ----------------------------------------------------------
 */
