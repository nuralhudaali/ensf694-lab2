import java.util.Scanner;
import java.util.Arrays;

public class SearchingAlgorithm {
	
	// Linear Search
	int linearSearch(int[] array, int key) {
		
		for(int i = 0; i < array.length; i++) {
			
			if(array[i] == key) {return i;} // if key matches the value at index i, return the index i
		}
		return -1; // key is not found, -1 is returned
	}
	
	// Improved Linear Search
	int improvedLinearSearch(int[] array, int key) {
		
		int low = 0, mid, high = array.length - 1;
		
		while(low <= high) {
			mid = low + ((high - low) / 2);
			
			if(key == array[mid])
				return mid;
			else if (key > array[mid])
				low = mid + 1;
			else {
				
				for(int i = low; i < mid; i++) {
					if (key == array[i])
						return i;
				}
				return -1;
			}
		}
		return -1;
	}
	
	// Interpolation Search (iterative)
	int interpolationSearchIterative(int[] array, int key) { // takes sorted array
		
		int low = 0, pos, mid, high = array.length - 1;
		while(low <= high) {
			
			pos = (key - array[low])/(array[high] - array[low]);
			mid = low + ((high - low) * pos);
			
			if(key < array[mid])
				high = mid - 1;
			else if(key > array[mid])
				low = mid + 1;
			else
				return mid;
		}
		return -1;
	}
	
	
	public static void main(String[] args) {
		SearchingAlgorithm search = new SearchingAlgorithm();
		Scanner reader = new Scanner(System.in);
		int size = 0, key = 0;
		int[] array;
		
		// *** INPUT *** //
		// Prompt for array size.
		System.out.print("Enter the number of elements in the array: ");
		if(reader.hasNextInt())
			size = reader.nextInt();
		else
			System.out.println("Invalid array size.\n");
		
		array = new int[size]; // initialize array with user-inputted size
		
		// Prompt for array elements.
		System.out.println("Enter the elements in the array: ");
		for(int i = 0; i < size; i++) {
			if(reader.hasNextInt())
				array[i] = reader.nextInt();
			else {
				System.out.println("Invalid array element.\n");
				break;
			}
		}
		
		// Prompt for search key.
		System.out.print("Enter the search key: ");
		if(reader.hasNextInt())
			key = reader.nextInt();
		else
			System.out.println("Invalid key.\n");
		
		// *** OUTPUT *** //
		// Sort the array.
		Arrays.sort(array);
		
		// Use Linear Search.
		long startTime1 = System.nanoTime();
		int index1 = search.linearSearch(array, key);
		long stopTime1 = System.nanoTime();
		System.out.println("\nUsing Linear Search:");
		if(index1 == -1)
			System.out.println("Search key NOT FOUND");
		else
			System.out.println("Search key FOUND at index " + index1);
		
		// Use Interpolation Search.
		long startTime2 = System.nanoTime();
		int index2 = search.interpolationSearchIterative(array, key);
		long stopTime2 = System.nanoTime();
		System.out.println("\nUsing Interpolation Search:");
		if(index2 == -1)
			System.out.println("Search key NOT FOUND");
		else
			System.out.println("Search key FOUND at index " + index2);
		
		// QUESTION 2
		long linearTime = stopTime1 - startTime1;
		long interpolationTime = stopTime2 - startTime2;
		
		System.out.println("\nLinear Search running time: " + linearTime + " nanoseconds");
		System.out.println("Interpolation Search (iterative) running time: " + interpolationTime + " nanoseconds");
		
		/* Explanation:
		 * The Interpolation Search performs better because it makes an educated guess on the position of the
		 * search key in the array before beginning the search. So it narrows down the general location of the key value
		 * and ignores a large portion of the array during the search. On the other hand, the Linear Search starts from
		 * the beginning and goes through each and every element until it finds a match, which can be very inefficient.
		*/
		
		// QUESTION 3
		long startTime3 = System.nanoTime();
		int index3 = search.improvedLinearSearch(array, key);
		long stopTime3 = System.nanoTime();
		long improvedLinearTime = stopTime3 - startTime3;
		
		System.out.println("\nUsing Improved Linear Search:");
		if(index3 == -1)
			System.out.println("Search key NOT FOUND");
		else
			System.out.println("Search key FOUND at index " + index3);
		
		System.out.println("\nImproved Linear Search running time: " + improvedLinearTime + " nanoseconds");
		
		/* Explanation:
		 * The improved Linear Search method uses a practice called "early termination." Although the search still
		 * occurs sequentially, as soon as the search key is found, the search is terminated, preventing unnecessary
		 * iterations from occurring, which decreases the run time.
		 */
		
	}

}
