import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class MergeSort {

	static int exchanges = 0;

	public static int[] mergeSort(int[] arr) {
		// Base case, array is considered sorted if it is less than size 2
		if (arr.length <= 1) {
			return arr;
		} else {
			// Splits array into left half and right half
			int middle = arr.length / 2;
			int[] left = new int[middle];
			for (int i = 0; i < middle; i++) {
				left[i] = arr[i];
			}
			int[] right = new int[arr.length - middle];
			for (int i = 0; middle + i < arr.length; i++) {
				right[i] = arr[middle + i];
			}

			// Recursively Sort left and right halves
			left = mergeSort(left);
			right = mergeSort(right);

			// Return merge of left and right
			return merge(left, right);
		}
	}

	// Merge Algorithm
	private static int[] merge(int[] left, int[] right) {
		// Creates retval array
		int length = left.length + right.length;
		int[] retval = new int[length];

		// These are pointers to our spot in the left arr, right arr, and retval
		// aray respectively
		int l = 0;
		int r = 0;
		int p = 0;
		// While we are not at the end of either left or right array
		while (l < left.length && r < right.length) {
			// if left front < right front, add it to final array
			if (left[l] <= right[r]) {
				// Increment pointer for final array and left array
				retval[p] = left[l];
				p++;
				l++;
			// if right front < left front, add it to final array
			} else if (right[r] < left[l]) {
				// Increment pointer for final array and right array
				retval[p] = right[r];
				p++;
				r++;
			}
		}

		// Add any remaining items from both lists (one will be empty though)
		for (int i = l; i < left.length; i++) {
			retval[p] = left[l];
			p++;
			l++;
		}
		for (int i = r; i < right.length; i++) {
			retval[p] = right[r];
			p++;
			r++;
		}

		// Return final list
		return retval;
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
            arr = mergeSort(arr);
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
