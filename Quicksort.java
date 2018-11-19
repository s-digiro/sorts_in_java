import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class Quicksort {

	static int exchanges = 0;

	// Wrapper Method
	public static void quicksort(int arr[]) {
		quicksort(arr, 0, arr.length - 1);
	}

	// Recursive Method
	private static void quicksort(int arr[], int first, int last) {
		// Base case, array has only 1 or zero item, return (array is sorted)
		if (first < last) {
			// Sort and partition array;
			int pivot = partition(arr, first, last);

			// Recursively call function for each half of the array
			quicksort(arr, first, pivot - 1);
			quicksort(arr, pivot + 1, last);
		}
	}

	private static int partition(int[] arr, int first, int last) {
		// Selects Pivot out of first, last, and middle array values
		int middle = (first + last) / 2;
		if (arr[first] > arr[middle]) {
			int temp = arr[first];
			arr[first] = arr[middle];
			arr[middle] = temp;
			exchanges++;
		}
		if (arr[middle] > arr[last]) {
			int temp = arr[last];
			arr[last] = arr[middle];
			arr[middle] = temp;
			exchanges++;
		}
		if (arr[first] > arr[last]) {
			int temp = arr[first];
			arr[first] = arr[middle];
			arr[middle] = temp;
			exchanges++;
		}
		// Moves middle item to front, so it can act as pivot
		int t = arr[first];
		arr[first] = arr[middle];
		arr[middle] = arr[first];
		exchanges++;

		int pivot = arr[first];

		// Pointers to out of place pieces that we find
		int up = first;
		int down = last;

		// Runs until down < up (everything is on its proper side)
		do {
			// Increments until we find something out of place
			while (arr[up] <= pivot && up < last) {
				up++;
			}
			// Increments until we find something out of place
			while (arr[down] > pivot) {
				down--;
			}
			// Swaps the two out of place pieces if they need to be swaped
			if (up < down) {
				int temp = arr[up];
				arr[up] = arr[down];
				arr[down] = temp;
				exchanges++;
			}
		} while (up < down);

		// Swaps pivot with highest item on low side
		arr[first] = arr[down];
		arr[down] = pivot;
		exchanges++;

		// Returns value of pivot, which will be same value as down
		return down;
	}

	public static void test(int arr[]) {
		String filename = "data.txt";
        File data = new File(filename);
        FileWriter fw;
        try {
            data.createNewFile();
            fw = new FileWriter(filename, true);

            System.out.println(toString(arr));
            fw.write(toString(arr) + "      ");

            long startTime = System.nanoTime();
            quicksort(arr);
            long endTime = System.nanoTime();

            fw.write("Start Time: " + startTime + "     ");
            fw.write("End Time: " + endTime + "     ");
            fw.write("Total Time: " + (endTime - startTime) + "		");
			fw.write("Exchanges: " + exchanges + "\n");

            System.out.println(toString(arr));
            fw.close();

        } catch (Exception e) {
            System.err.println("Oops, something went wrong :( " + e);
        }
    }

	public static String toString(int arr[]) {

        String retval = "[";
        for (int i = 0; i < arr.length - 1; i++) {
            retval += arr[i];
            retval += ", ";
        }
        retval += arr[arr.length - 1];
        retval += "]";

        return retval;
    }

	public static void main(String args[]) {
		int arr[][] = {{10, 9, 8, 7, 6, 5, 4, 3, 2, 1},
                      {77, 34, 105, 1000, 35, 178, 953},
                      {6, -800, 54, 78, 19, 4576, 19203, 23, 12},
                      {1, 4, 6, 3, 2, 7, 9, 1, 6, 3}};
        for (int i = 0; i < arr.length; i++) {
            test(arr[i]);
        }
	}
}
