import java.util.*;

public class BubbleSort
{
	public static void main (String args[])
	{
	 	Scanner sc = new Scanner(System.in);
	 	
	 	System.out.println("Enter the size of array: ");
	 	int n = sc.nextInt();
		float arr[] = new float[n];
		
		System.out.println("Enter the elements: ");
		for(int i = 0; i < n; i++)
		{
			arr[i] = sc.nextFloat();
		}
		
		for(int i = 0; i < n; i++)
		{
			for(int j = 0; j < n-i-1; j++)
			{
				if(arr[j] > arr[j+1])
				{
					float temp = arr[j];
					arr[j] = arr[j+1];
					arr[j+1] = temp;
				}
			}
		}
		for(int i = 0; i < n; i++)
		{
			System.out.print(arr[i] + " ");
		}
	}
}
