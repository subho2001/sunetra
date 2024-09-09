import java.util.Scanner;

public class MatrixChainMultiplication {

    // Function to compute the optimal order of matrix multiplication
    public static void matrixChainOrder(int[] p, int n, int[][] m, int[][] s) {
        for (int i = 1; i <= n; i++) {
            m[i][i] = 0;
        }
        for (int l = 2; l <= n; l++) {
            for (int i = 1; i <= n - l + 1; i++) {
                int j = i + l - 1;
                m[i][j] = Integer.MAX_VALUE;
                for (int k = i; k <= j - 1; k++) {
                    int q = m[i][k] + m[k + 1][j] + p[i - 1] * p[k] * p[j];
                    if (q < m[i][j]) {
                        m[i][j] = q;
                        s[i][j] = k;
                    }
                }
            }
        }
    }

    // Function to print the optimal parenthesization
    public static void printOptimalParens(int[][] s, int i, int j) {
        if (i == j) {
            System.out.print("A" + i);
        } else {
            System.out.print("(");
            printOptimalParens(s, i, s[i][j]);
            printOptimalParens(s, s[i][j] + 1, j);
            System.out.print(")");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of matrices: ");
        int n = scanner.nextInt();

        int[] p = new int[n + 1];
        System.out.println("Enter the dimensions:");
        for (int i = 0; i <= n; i++) {
            System.out.print("p[" + i + "]: ");
            p[i] = scanner.nextInt();
        }

        // Allocate memory for matrices m and s
        int[][] m = new int[n + 1][n + 1];
        int[][] s = new int[n + 1][n + 1];

        matrixChainOrder(p, n, m, s);

        System.out.println("Minimum number of multiplications is " + m[1][n]);
        System.out.print("Optimal parenthesization is: ");
        printOptimalParens(s, 1, n);
        System.out.println();

        // Deallocate memory (Java garbage collector handles this automatically)
    }
}
