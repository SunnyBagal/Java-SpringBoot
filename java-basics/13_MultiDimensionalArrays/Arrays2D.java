/*
 * ============================================================
 *  TOPIC 13: 2D ARRAYS (arrays of arrays - like a table/grid)
 * ============================================================
 *
 *     int[][] grid = {
 *         {1, 2, 3},      <- row 0
 *         {4, 5, 6}       <- row 1
 *     };
 *     grid[row][column]  ->  grid[1][2] is 6
 *
 *  KEY IDEAS:
 *   - grid.length       = number of ROWS
 *   - grid[0].length    = number of COLUMNS in row 0
 *   - Rows can have different lengths ("jagged" arrays).
 *   - Use nested loops: outer for rows, inner for columns.
 *
 *  RUN:  java Arrays2D.java
 */
import java.util.Arrays;

public class Arrays2D {

    public static void main(String[] args) {

        // ---------- 1) Create and access ----------
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        System.out.println("Rows: " + matrix.length + ", Columns: " + matrix[0].length);
        System.out.println("matrix[1][2] = " + matrix[1][2]);   // row 1, column 2 -> 6

        // ---------- 2) Print as a grid with nested loops ----------
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                System.out.print(matrix[row][col] + " ");
            }
            System.out.println();
        }

        // ---------- 3) Create empty and fill ----------
        int[][] table = new int[3][4];            // 3 rows, 4 columns, all 0
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 4; c++) {
                table[r][c] = r * 10 + c;
            }
        }
        System.out.println("table: " + Arrays.deepToString(table));   // deepToString for 2D

        // ---------- 4) Sum of all elements / diagonal ----------
        int sum = 0, diagonal = 0;
        for (int r = 0; r < matrix.length; r++) {
            for (int c = 0; c < matrix[r].length; c++) {
                sum += matrix[r][c];
                if (r == c) {
                    diagonal += matrix[r][c];     // 1 + 5 + 9
                }
            }
        }
        System.out.println("Sum: " + sum + ", Diagonal sum: " + diagonal);

        // ---------- 5) for-each with 2D arrays ----------
        String[][] seats = {{"A1", "A2"}, {"B1", "B2"}};
        for (String[] row : seats) {              // each row is itself a String[]
            for (String seat : row) {
                System.out.print(seat + " ");
            }
        }
        System.out.println();

        // ---------- 6) Jagged array (rows of different sizes) ----------
        int[][] jagged = new int[3][];            // only rows decided, columns later
        jagged[0] = new int[]{1};
        jagged[1] = new int[]{1, 2};
        jagged[2] = new int[]{1, 2, 3};
        System.out.println("jagged: " + Arrays.deepToString(jagged));
    }
}

/*
 * ------------------------- OUTPUT -------------------------
 * Rows: 3, Columns: 3
 * matrix[1][2] = 6
 * 1 2 3
 * 4 5 6
 * 7 8 9
 * table: [[0, 1, 2, 3], [10, 11, 12, 13], [20, 21, 22, 23]]
 * Sum: 45, Diagonal sum: 15
 * A1 A2 B1 B2
 * jagged: [[1], [1, 2], [1, 2, 3]]
 * ----------------------------------------------------------
 */
