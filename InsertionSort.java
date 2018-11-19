import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class InsertionSort {

	static int exchanges = 0;

	public static void selectionSort(int arr[]) {

		// Iterates through array and points to beginning of each subset (i)
		for (int i = 0; i < arr.length; i++) {
			int min = i;
			// Finds the lowest number after i
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[j] < arr[min]) {
					min = j;
				}
			}

			// Swaps min with i
			int temp = arr[min];
			arr[min] = arr[i];
			arr[i] = temp;
		}
	}

	public static void insertionSort(int arr[]) {
		// Points to item just outside organized subset
		for (int i = 1; i < arr.length; i++) {
			// Iterates through each item in subset, starting at end of subset
			for (int j = i; j > 0; j--) {
				// Swaps item into subset if it is lesser than preceding item
				if (arr[j] < arr[j - 1]) {
					int temp = arr[j];
					arr[j] = arr[j - 1];
					arr[j - 1] = temp;
					exchanges++;
				}
			}
		}
	}

	/* Prints out arrays (Can't believe they don't have a two string btw, shame
	 * on you, Java)
	 */
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

	public static void test(int arr[]) {
		String filename = "data.txt";
		File data = new File(filename);
		FileWriter fw;
		try {
			data.createNewFile();
			fw = new FileWriter(filename, true);

			System.out.println(toString(arr));
			fw.write(toString(arr) + "		");

			long startTime = System.nanoTime();
			insertionSort(arr);
			long endTime = System.nanoTime();

			fw.write("Start Time: " + startTime + "		");
			fw.write("End Time: " + endTime + "		");
			fw.write("Total Time: " + (endTime - startTime) + "		");
			fw.write("Exchanges: " + exchanges + "\n");

			System.out.println(toString(arr));
			fw.close();

		} catch (Exception e) {
			System.err.println("Oops, something went wrong :( " + e);
		}
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
