import java.util.Scanner;

public class HeapSort {

    public static void main(String[] args) {
        System.out.println("************************");
        System.out.println("Heap Sort Implementation");
        System.out.println("************************");
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of elements: ");
        int n = scanner.nextInt();
        float[] array = new float[n];

        System.out.println("*******************");
        System.out.println("Enter all " + n + " elements:");
        for (int i = 0; i < n; i++) {
            array[i] = scanner.nextFloat();
        }

        heapSort(array);

        System.out.println("****************");
        System.out.println("Sorted array is:");
        printArray(array);

        scanner.close();
    }

    public static void heapSort(float[] A) {
        buildMaxHeap(A);
        int heapSize = A.length;
        for (int i = A.length - 1; i >= 1; i--) {
            // Swap A[0] with A[i]
            float temp = A[0];
            A[0] = A[i];
            A[i] = temp;

            heapSize--;
            maxHeapify(A, 0, heapSize);
        }
    }

    public static void buildMaxHeap(float[] A) {
        int heapSize = A.length;
        for (int i = (heapSize / 2) - 1; i >= 0; i--) {
            maxHeapify(A, i, heapSize);
        }
    }

    public static void maxHeapify(float[] A, int i, int heapSize) {
        int l = 2 * i + 1;
        int r = 2 * i + 2;
        int largest = i;

        if (l < heapSize && A[l] > A[i]) {
            largest = l;
        }
        if (r < heapSize && A[r] > A[largest]) {
            largest = r;
        }
        if (largest != i) {
            // Swap A[i] with A[largest]
            float temp = A[i];
            A[i] = A[largest];
            A[largest] = temp;

            maxHeapify(A, largest, heapSize);
        }
    }

    public static void printArray(float[] array) {
        for (float value : array) {
            System.out.print(value + " ");
        }
        System.out.println();
    }
}
