import java.util.*;

public class MergeSort {
    public static void mergeSort(float[] arr, int p, int r) {
        if (p < r) {
            int q = (p + r) / 2;
            mergeSort(arr, p, q);
            mergeSort(arr, q + 1, r);
            merge(arr, p, q, r);
        }
    }

    public static void merge(float[] arr, int p, int q, int r) {
        int n1 = q - p + 1;
        int n2 = r - q;

        float[] L = new float[n1 + 1];
        float[] R = new float[n2 + 1];

        for (int i = 0; i < n1; i++) {
            L[i] = arr[p + i];
        }
        for (int j = 0; j < n2; j++) {
            R[j] = arr[q + j + 1];
        }

        L[n1] = Float.MAX_VALUE;
        R[n2] = Float.MAX_VALUE;

        int i = 0, j = 0;
        for (int k = p; k <= r; k++) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
        }
    }

    public static void main(String[] args) 
    {
        System.out.println("***Merge Sort*** ");
        Scanner sc = new Scanner(System.in);

        System.out.println("**************************");
        System.out.println("Enter number of elements: ");
        int n = sc.nextInt();
        float[] arr = new float[n];

        System.out.println("Enter the elements: ");
        for(int i = 0; i < n; i++)
        {
            arr[i] = sc.nextFloat();
        }

        mergeSort(arr, 0, n - 1);

        System.out.println("*************");
        System.out.println("Sorted array:");
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
