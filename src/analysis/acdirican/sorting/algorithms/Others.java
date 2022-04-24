package analysis.acdirican.sorting.algorithms;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Vector;

import analysis.acdirican.sorting.testdata.TestData;

/**
 * 
 * @author AhmetCengizhan Dirican
 * @version 1.0
 *
 */
public class Others {

	// Function to sort arr[] of size n
	// using bucket sort
	public static void bucketSort(int arr[], int n) {
		if (n <= 0)
			return;

		// 1) Create n empty buckets
		@SuppressWarnings("unchecked")
		Vector<Integer>[] buckets = new Vector[n];

		for (int i = 0; i < n; i++) {
			buckets[i] = new Vector<Integer>();
		}

		// 2) Put array elements in different buckets
		for (int i = 0; i < n; i++) {
			float idx = arr[i] * n;
			buckets[(int) idx].add(arr[i]);
		}

		// 3) Sort individual buckets
		for (int i = 0; i < n; i++) {
			Collections.sort(buckets[i]);
		}

		// 4) Concatenate all buckets into arr[]
		int index = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < buckets[i].size(); j++) {
				arr[index++] = buckets[i].get(j);
			}
		}
	}

	// A function to do counting sort of arr[] according to
	// the digit represented by exp.
	static void countSort(int arr[], int n, int exp) {
		int output[] = new int[n]; // output array
		int i;
		int count[] = new int[10];
		Arrays.fill(count, 0);

		// Store count of occurrences in count[]
		for (i = 0; i < n; i++)
			count[(arr[i] / exp) % 10]++;

		// Change count[i] so that count[i] now contains
		// actual position of this digit in output[]
		for (i = 1; i < 10; i++)
			count[i] += count[i - 1];

		// Build the output array
		for (i = n - 1; i >= 0; i--) {
			output[count[(arr[i] / exp) % 10] - 1] = arr[i];
			count[(arr[i] / exp) % 10]--;
		}

		// Copy the output array to arr[], so that arr[] now
		// contains sorted numbers according to curent digit
		for (i = 0; i < n; i++)
			arr[i] = output[i];
	}

	// The main function to that sorts arr[] of size n using
	// Radix Sort
	static void radixsort(int arr[], int n) {
		// Find the maximum number to know number of digits
		int m = getMax(arr, n);

		// Do counting sort for every digit. Note that
		// instead of passing digit number, exp is passed.
		// exp is 10^i where i is current digit number
		for (int exp = 1; m / exp > 0; exp *= 10)
			countSort(arr, n, exp);
	}
	
	// A utility function to get maximum value in arr[]
	static int getMax(int arr[], int n) {
		int mx = arr[0];
		for (int i = 1; i < n; i++)
			if (arr[i] > mx)
				mx = arr[i];
		return mx;
	}

	static void countSort(char arr[]) {
		int n = arr.length;

		// The output character array that will have sorted arr
		char output[] = new char[n];

		// Create a count array to store count of inidividul
		// characters and initialize count array as 0
		int count[] = new int[256];
		for (int i = 0; i < 256; ++i)
			count[i] = 0;

		// store count of each character
		for (int i = 0; i < n; ++i)
			++count[arr[i]];

		// Change count[i] so that count[i] now contains actual
		// position of this character in output array
		for (int i = 1; i <= 255; ++i)
			count[i] += count[i - 1];

		// Build the output character array
		// To make it stable we are operating in reverse order.
		for (int i = n - 1; i >= 0; i--) {
			System.out.println("pos:" + i + " -> char:" + arr[i] + " count:" + count[arr[i]]);
			output[count[arr[i]] - 1] = arr[i];
			System.out.println(Arrays.toString(output));
			--count[arr[i]];
		}

		// Copy the output array to arr, so that arr now
		// contains sorted characters
		for (int i = 0; i < n; ++i)
			arr[i] = output[i];
	}

	
}
