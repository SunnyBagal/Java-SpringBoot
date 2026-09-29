/*
 * PRACTICE 04: Simple and compound interest
 * Concepts: double arithmetic, Math.pow, String.format
 * RUN: java SimpleInterest.java
 */
public class SimpleInterest {
    public static void main(String[] args) {
        double principal = 10000;   // amount borrowed/invested
        double rate = 8;            // percent per year
        int years = 3;

        // Simple interest = P * R * T / 100
        double simple = principal * rate * years / 100;

        // Compound amount = P * (1 + R/100)^T, interest = amount - P
        double compoundAmount = principal * Math.pow(1 + rate / 100, years);
        double compound = compoundAmount - principal;

        // %.2f -> show 2 digits after the decimal point
        System.out.println(String.format("Simple interest:   %.2f", simple));
        System.out.println(String.format("Compound interest: %.2f", compound));
    }
}

/*
 * ------------------------- OUTPUT -------------------------
 * Simple interest:   2400.00
 * Compound interest: 2597.12
 * ----------------------------------------------------------
 */
