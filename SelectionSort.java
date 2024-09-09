import java.util.*;

public class SelectionSort
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
        for(int i = 0; i < n-1; i++)
        {
            int j = i;
            for(int k = j+1; k < n; k++)
            {
                if(arr[k] < arr[j])
                {
                    j = k;
                }
            }
            if(i != j)
            {
                float temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        for(int i = 0; i < n; i++)
		    {
			    System.out.print(arr[i] + " ");
		    }
    }

}
