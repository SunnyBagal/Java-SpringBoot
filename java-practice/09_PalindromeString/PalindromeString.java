/*
 * PRACTICE 09: Check if a string is a palindrome (two-pointer technique)
 * Concepts: charAt, toLowerCase, replaceAll, while loop with two indexes
 * RUN: java PalindromeString.java
 */
public class PalindromeString {

    static boolean isPalindrome(String s) {
        // Keep only letters/digits and ignore case: "Race Car!" -> "racecar"
        String clean = s.toLowerCase().replaceAll("[^a-z0-9]", "");
        int left = 0, right = clean.length() - 1;
        while (left < right) {
            if (clean.charAt(left) != clean.charAt(right)) {
                return false;       // mismatch -> not a palindrome
            }
            left++;                 // move inwards from both ends
            right--;
        }
        return true;
    }

    public static void main(String[] args) {
        String[] words = {"madam", "Race Car!", "java", "A man, a plan, a canal: Panama"};
        for (String w : words) {
            System.out.println("\"" + w + "\" -> " + isPalindrome(w));
        }
    }
}

/*
 * ------------------------- OUTPUT -------------------------
 * "madam" -> true
 * "Race Car!" -> true
 * "java" -> false
 * "A man, a plan, a canal: Panama" -> true
 * ----------------------------------------------------------
 */
