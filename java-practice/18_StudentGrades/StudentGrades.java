/*
 * PRACTICE 18: Mini project - student report card
 * Concepts: records, List, streams, switch expression (java-basics 09, 32, 33)
 * RUN: java StudentGrades.java
 */
import java.util.Comparator;
import java.util.List;

public class StudentGrades {

    record Student(String name, int marks) {
        // grade computed from marks using a switch expression
        String grade() {
            return switch (marks / 10) {
                case 10, 9 -> "A+";
                case 8 -> "A";
                case 7 -> "B";
                case 6 -> "C";
                case 5, 4 -> "D";
                default -> "F";
            };
        }
    }

    public static void main(String[] args) {
        List<Student> students = List.of(
                new Student("Asha", 92),
                new Student("Ravi", 67),
                new Student("Meera", 85),
                new Student("John", 38)
        );

        System.out.println("Name   Marks Grade");
        for (Student s : students) {
            // %-6s = left-aligned text of width 6, %5d = number of width 5
            System.out.printf("%-6s %5d %s%n", s.name(), s.marks(), s.grade());
        }

        double avg = students.stream().mapToInt(Student::marks).average().orElse(0);
        Student topper = students.stream().max(Comparator.comparingInt(Student::marks)).get();
        long passed = students.stream().filter(s -> s.marks() >= 40).count();

        System.out.printf("Average: %.2f%n", avg);
        System.out.println("Topper: " + topper.name());
        System.out.println("Passed: " + passed + " of " + students.size());
    }
}

/*
 * ------------------------- OUTPUT -------------------------
 * Name   Marks Grade
 * Asha      92 A+
 * Ravi      67 C
 * Meera     85 A
 * John      38 F
 * Average: 70.50
 * Topper: Asha
 * Passed: 3 of 4
 * ----------------------------------------------------------
 */
