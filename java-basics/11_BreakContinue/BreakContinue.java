/*
 * ============================================================
 *  TOPIC 11: BREAK and CONTINUE (controlling loops)
 * ============================================================
 *
 *  break     -> EXIT the loop immediately (skip everything that's left).
 *  continue  -> SKIP the rest of THIS round and jump to the next round.
 *  labels    -> name an outer loop so break/continue can target it
 *               from inside a nested loop.
 *
 *  RUN:  java BreakContinue.java
 */
public class BreakContinue {

    public static void main(String[] args) {

        // ---------- 1) break: stop when we find what we want ----------
        int[] numbers = {4, 8, 15, 16, 23, 42};
        int target = 16;
        for (int i = 0; i < numbers.length; i++) {
            System.out.println("Checking index " + i + " (value " + numbers[i] + ")");
            if (numbers[i] == target) {
                System.out.println("Found " + target + " at index " + i);
                break;                        // no need to check the rest
            }
        }

        // ---------- 2) continue: skip some items ----------
        System.out.print("Odd numbers 1-10: ");
        for (int i = 1; i <= 10; i++) {
            if (i % 2 == 0) {
                continue;                     // even -> skip the print below, go to next i
            }
            System.out.print(i + " ");
        }
        System.out.println();

        // ---------- 3) break in a while(true) loop ----------
        // while (true) runs forever unless something breaks out of it
        int attempts = 0;
        while (true) {
            attempts++;
            if (attempts == 3) {
                System.out.println("Stopped after " + attempts + " attempts");
                break;
            }
        }

        // ---------- 4) Labeled break: exit BOTH loops at once ----------
        outer:                                // a label (any name followed by :)
        for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= 3; j++) {
                if (i * j == 4) {
                    System.out.println("i*j == 4 at i=" + i + ", j=" + j + " -> leaving both loops");
                    break outer;              // a plain "break" would only exit the inner loop
                }
                System.out.println("i=" + i + ", j=" + j);
            }
        }
    }
}

/*
 * ------------------------- OUTPUT -------------------------
 * Checking index 0 (value 4)
 * Checking index 1 (value 8)
 * Checking index 2 (value 15)
 * Checking index 3 (value 16)
 * Found 16 at index 3
 * Odd numbers 1-10: 1 3 5 7 9
 * Stopped after 3 attempts
 * i=1, j=1
 * i=1, j=2
 * i=1, j=3
 * i=2, j=1
 * i*j == 4 at i=2, j=2 -> leaving both loops
 * ----------------------------------------------------------
 */
