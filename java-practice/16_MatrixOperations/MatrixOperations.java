/*
 * PRACTICE 16: Matrix addition, multiplication and transpose
 * Concepts: 2D arrays, nested loops (java-basics 13)
 * RUN: java MatrixOperations.java
 */
import java.util.Arrays;

public class MatrixOperations {
    public static void main(String[] args) {
        int[][] a = {{1, 2}, {3, 4}};
        int[][] b = {{5, 6}, {7, 8}};
        int n = 2;

        int[][] sum = new int[n][n];
        int[][] product = new int[n][n];
        int[][] transpose = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sum[i][j] = a[i][j] + b[i][j];
                transpose[j][i] = a[i][j];          // rows become columns
                // product cell = row i of a  "dot"  column j of b
                for (int k = 0; k < n; k++) {
                    product[i][j] += a[i][k] * b[k][j];
                }
            }
        }
        System.out.println("A + B     = " + Arrays.deepToString(sum));
        System.out.println("A x B     = " + Arrays.deepToString(product));
        System.out.println("Transpose = " + Arrays.deepToString(transpose));
    }
}

/*
 * ------------------------- OUTPUT -------------------------
 * A + B     = [[6, 8], [10, 12]]
 * A x B     = [[19, 22], [43, 50]]
 * Transpose = [[1, 3], [2, 4]]
 * ----------------------------------------------------------
 */
