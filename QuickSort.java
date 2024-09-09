import java.util.*;

public class QuickSort {
    public static void qSort(float[] arr, int m, int n) {
        if (m < n) {
            float key = arr[m];
            int i = m;
            int j = n + 1;

            while (true) {
                do {
                    i++;
                } while (i <= n && arr[i] < key);

                do {
                    j--;
                } while (arr[j] > key);

                if (i < j) {
                    interchange(arr, i, j);
                } else {
                    break;
                }
            }

            interchange(arr, m, j);

            qSort(arr, m, j - 1);
            qSort(arr, j + 1, n);
        }
    }

    public static void interchange(float[] arr, int i, int j) {
        float temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        System.out.println("*** Quick Sort ***");
        Scanner sc = new Scanner(System.in);

        System.out.println("********************************");
        System.out.println("Enter total number of elements: ");
        int n = sc.nextInt();
        float[] arr = new float[n];

        for(int i = 0; i < n;  i++)
        {
            arr[i] = sc.nextFloat();
        }

        qSort(arr, 0, n - 1);

        System.out.println("*************");
        System.out.println("Sorted array:");
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
