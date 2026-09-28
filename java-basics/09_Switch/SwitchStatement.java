/*
 * ============================================================
 *  TOPIC 09: SWITCH (choose one of many options)
 * ============================================================
 *
 *  WHAT: A cleaner alternative to a long if-else-if chain when you compare
 *        ONE variable against many fixed values.
 *
 *  WORKS WITH: int, char, String, enum (and byte, short + their wrappers).
 *
 *  TWO STYLES:
 *   1) Classic switch with "case X:" and "break;"
 *        - Without break, execution "falls through" into the next case!
 *   2) Modern switch (Java 14+) with "case X ->"
 *        - No fall-through, no break needed, and it can RETURN a value.
 *        - Prefer this style in new code.
 *
 *  RUN:  java SwitchStatement.java
 */
public class SwitchStatement {

    public static void main(String[] args) {

        // ---------- 1) Classic switch ----------
        int day = 3;
        switch (day) {                        // look at the value of day
            case 1:
                System.out.println("Monday");
                break;                        // break = leave the switch
            case 2:
                System.out.println("Tuesday");
                break;
            case 3:
                System.out.println("Wednesday");  // day is 3, so this runs
                break;                            // and then we exit
            default:                          // runs if no case matched (like "else")
                System.out.println("Some other day");
        }

        // ---------- 2) Fall-through (what happens WITHOUT break) ----------
        int level = 2;
        System.out.print("Unlocked: ");
        switch (level) {
            case 3:
                System.out.print("Gold ");
            case 2:                           // starts here because level = 2 ...
                System.out.print("Silver ");
            case 1:                           // ... and FALLS THROUGH to here (no break)
                System.out.print("Bronze ");
        }
        System.out.println();

        // ---------- 3) Grouping cases ----------
        char letter = 'e';
        switch (letter) {
            case 'a': case 'e': case 'i': case 'o': case 'u':
                System.out.println(letter + " is a vowel");
                break;
            default:
                System.out.println(letter + " is a consonant");
        }

        // ---------- 4) Switch on String ----------
        String command = "stop";
        switch (command) {
            case "start" -> System.out.println("Starting engine...");
            case "stop"  -> System.out.println("Stopping engine...");
            default      -> System.out.println("Unknown command");
        }

        // ---------- 5) Modern switch EXPRESSION (returns a value) ----------
        int month = 2;
        String season = switch (month) {      // the whole switch produces a value
            case 12, 1, 2 -> "Winter";        // many values separated by commas
            case 3, 4, 5  -> "Summer";
            case 6, 7, 8, 9 -> "Monsoon";
            case 10, 11   -> "Autumn";
            default       -> "Invalid month";
        };                                    // note the ; because it's an assignment
        System.out.println("Month " + month + " is " + season);

        // ---------- 6) yield: when a case needs more than one line ----------
        int score = 85;
        String feedback = switch (score / 10) {   // 85 / 10 = 8
            case 10, 9 -> "Excellent";
            case 8 -> {
                String msg = "Very good";
                yield msg + "!";              // yield = the value this case returns
            }
            default -> "Keep practicing";
        };
        System.out.println("Feedback: " + feedback);
    }
}

/*
 * ------------------------- OUTPUT -------------------------
 * Wednesday
 * Unlocked: Silver Bronze
 * e is a vowel
 * Stopping engine...
 * Month 2 is Winter
 * Feedback: Very good!
 * ----------------------------------------------------------
 */
