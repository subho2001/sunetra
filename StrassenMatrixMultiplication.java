import java.util.Scanner;

public class StrassenMatrixMultiplication {

    // Function to multiply two matrices using Strassen's algorithm
    public static int[][] strassenMultiply(int[][] A, int[][] B) {
        int n = A.length;

        // Base case: If the matrices are 1x1
        if (n == 1) {
            int[][] C = new int[1][1];
            C[0][0] = A[0][0] * B[0][0];
            return C;
        } else {
            // Step 1: Divide matrices A and B into four submatrices
            int[][] A11 = new int[n / 2][n / 2];
            int[][] A12 = new int[n / 2][n / 2];
            int[][] A21 = new int[n / 2][n / 2];
            int[][] A22 = new int[n / 2][n / 2];

            int[][] B11 = new int[n / 2][n / 2];
            int[][] B12 = new int[n / 2][n / 2];
            int[][] B21 = new int[n / 2][n / 2];
            int[][] B22 = new int[n / 2][n / 2];

            // Divide matrix A into A11, A12, A21, A22
            divideMatrix(A, A11, 0, 0);
            divideMatrix(A, A12, 0, n / 2);
            divideMatrix(A, A21, n / 2, 0);
            divideMatrix(A, A22, n / 2, n / 2);

            // Divide matrix B into B11, B12, B21, B22
            divideMatrix(B, B11, 0, 0);
            divideMatrix(B, B12, 0, n / 2);
            divideMatrix(B, B21, n / 2, 0);
            divideMatrix(B, B22, n / 2, n / 2);

            // Step 2: Compute the seven products recursively
            int[][] P1 = strassenMultiply(addMatrices(A11, A22), addMatrices(B11, B22));
            int[][] P2 = strassenMultiply(addMatrices(A21, A22), B11);
            int[][] P3 = strassenMultiply(A11, subtractMatrices(B12, B22));
            int[][] P4 = strassenMultiply(A22, subtractMatrices(B21, B11));
            int[][] P5 = strassenMultiply(addMatrices(A11, A12), B22);
            int[][] P6 = strassenMultiply(subtractMatrices(A21, A11), addMatrices(B11, B12));
            int[][] P7 = strassenMultiply(subtractMatrices(A12, A22), addMatrices(B21, B22));

            // Step 3: Compute the four quadrants of the result matrix C
            int[][] C11 = addMatrices(subtractMatrices(addMatrices(P1, P4), P5), P7);
            int[][] C12 = addMatrices(P3, P5);
            int[][] C21 = addMatrices(P2, P4);
            int[][] C22 = addMatrices(subtractMatrices(subtractMatrices(P1, P2), P3), P6);

            // Step 4: Combine the four quadrants into the result matrix C
            int[][] C = new int[n][n];
            copySubMatrix(C11, C, 0, 0);
            copySubMatrix(C12, C, 0, n / 2);
            copySubMatrix(C21, C, n / 2, 0);
            copySubMatrix(C22, C, n / 2, n / 2);

            return C;
        }
    }

    // Function to divide a matrix into a submatrix
    public static void divideMatrix(int[][] parent, int[][] child, int rowStart, int colStart) {
        int size = child.length;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                child[i][j] = parent[rowStart + i][colStart + j];
            }
        }
    }

    // Function to add two matrices
    public static int[][] addMatrices(int[][] A, int[][] B) {
        int n = A.length;
        int[][] C = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                C[i][j] = A[i][j] + B[i][j];
            }
        }
        return C;
    }

    // Function to subtract two matrices
    public static int[][] subtractMatrices(int[][] A, int[][] B) {
        int n = A.length;
        int[][] C = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                C[i][j] = A[i][j] - B[i][j];
            }
        }
        return C;
    }

    // Function to copy a submatrix into a matrix at a specified position
    public static void copySubMatrix(int[][] source, int[][] dest, int rowStart, int colStart) {
        int size = source.length;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                dest[rowStart + i][colStart + j] = source[i][j];
            }
        }
    }

    // Function to print a matrix
    public static void printMatrix(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the size of matrices (must be a power of 2): ");
        int n = scanner.nextInt();

        int[][] A = new int[n][n];
        int[][] B = new int[n][n];

        System.out.println("Enter matrix A:");
        inputMatrix(A, scanner);
        System.out.println("Enter matrix B:");
        inputMatrix(B, scanner);

        System.out.println("\nMatrix A:");
        printMatrix(A);
        System.out.println("\nMatrix B:");
        printMatrix(B);

        int[][] C = strassenMultiply(A, B);

        System.out.println("\nResultant Matrix C (Using Strassen's Algorithm):");
        printMatrix(C);
    }

    // Function to input a matrix from user
    public static void inputMatrix(int[][] matrix, Scanner scanner) {
        int n = matrix.length;
        System.out.println("Enter " + n + " rows and " + n + " columns:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }
    }
}
