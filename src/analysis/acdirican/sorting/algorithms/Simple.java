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
public class Simple {

	/* function to sort arr using shellSort */
	public static int shellSort(int arr[]) {
		int n = arr.length;

		// Start with a big gap, then reduce the gap
		for (int gap = n / 2; gap > 0; gap /= 2) {
			System.out.println("gap:" + gap);
			// Do a gapped insertion sort for this gap size.
			// The first gap elements a[0..gap-1] are already
			// in gapped order keep adding one more element
			// until the entire array is gap sorted
			for (int i = gap; i < n; i += 1) {
				// add a[i] to the elements that have been gap
				// sorted save a[i] in temp and make a hole at
				// position i
				int temp = arr[i];

				// shift earlier gap-sorted elements up until
				// the correct location for a[i] is found
				int j;
				System.out.println(i + ", " + i);
				for (j = i; j >= gap && arr[j - gap] > temp; j -= gap) {
					arr[j] = arr[j - gap];
					System.out.println("i:" + Arrays.toString(arr));
					System.out.println(i + ", " + j);
				}

				// put temp (the original a[i]) in its correct
				// location
				arr[j] = temp;
				System.out.println("f:" + Arrays.toString(arr));
			}
		}
		return 0;
	}

	
	// Selection sort
	public static void selectionSort(int arr[]) {
		int n = arr.length;
		// One by one move boundary of unsorted subarray
		for (int i = 0; i < n - 1; i++) {
			// Find the minimum element in unsorted array
			int minIndex = i;
			for (int j = i + 1; j < n; j++) {
				if (arr[j] < arr[minIndex]) {
					minIndex = j;
				}
			}
			// Swap the found minimum element with the first element
			int temp = arr[minIndex];
			arr[minIndex] = arr[i];
			arr[i] = temp;
		}
	}

	// Bubble sort algorithm
	public static void bubbleSort(int arr[]) {
		int n = arr.length;
		for (int i = 0; i < n - 1; i++) {
			for (int j = 0; j < n - i - 1; j++) {
				if (arr[j] > arr[j + 1]) {
					// swap arr[j+1] and arr[j]
					int temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
				}
			}
		}
	}


	/* Function to sort array using insertion sort */
	public static void  insertionSort(int arr[]) {
		int n = arr.length;
		for (int i = 1; i < n; ++i) {
			int key = arr[i];
			int j = i - 1;

			/*
			 * Move elements of arr[0..i-1], that are greater than key, to one position
			 * ahead of their current position
			 */
			while (j >= 0 && arr[j] > key) {
				arr[j + 1] = arr[j];
				j = j - 1;
			}
			arr[j + 1] = key;
		}
	}

}
