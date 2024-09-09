import java.util.*;

public class InsertionSort
{
    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter size of the array: ");

        int n = sc.nextInt();
        float arr[] = new float[n];

	    System.out.println("Enter the elements: ");
        for(int i = 0; i < n; i++)
        {
            arr[i] = sc.nextFloat();
        }
        
        //Sorting Portion
        for(int j = 1; j < n; j++)
        {
            float key = arr[j];
            int i = j - 1;
            while(i >= 0 && key < arr[i])
            {
                arr[i+1] = arr[i];
                i = i - 1;
            }
            arr[i+1] = key;
        }

        //print the sorted list
        for(int i = 0; i < n; i++)
		    {
			    System.out.print(arr[i] + " ");
		    }
    }

}
