package analysis.acdirican.sorting.tests;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

import analysis.acdirican.sorting.testdata.TestData;

/**
 * 
 * @author AhmetCengizhan Dirican
 * @version 1.0
 *
 */
public class ComparisonTest {
	

	/*
	 * This function takes mid element as pivot, places the pivot element at its
	 * correct position in sorted array, and places all smaller (smaller than pivot)
	 * to left of pivot and all greater elements to right of pivot
	 */
	private static long[] partition(int arr[], int low, int high) {
		int i = low, j = high;
		int tmp;
		int pivot = arr[(low + high) / 2];
		
		long comps = 1; //before loop
		while (i <= j) {
			
			comps++; //before loop
			while (arr[i] < pivot) {
				i++; 
				comps++; //loop control
			}
			
			comps++;//before loop
			while (arr[j] > pivot) {
				j--;
				comps++; //loop control
			}
			
			comps++;
			if (i <= j) {
				tmp = arr[i];
				arr[i] = arr[j];
				arr[j] = tmp;
				i++;
				j--;
			}
			
			comps++; //loop control
		};
		return new long[] {i, comps};
	}

	/*
	 * The main function that implements QuickSort() arr[] --> Array to be sorted,
	 * low --> Starting index, high --> Ending index
	 */
	private static long quickSort(int arr[], int low, int high) {
		int comp=1;
		if (low < high) {
			/*
			 * pi is partitioning index, arr[pi] is now at right place
			 */
			long[] piAndComps = partition(arr, low, high);
			
			int pi = (int) piAndComps[0];
			comp += piAndComps[1];

			// Recursively sort elements before
			// partition and after partition
			comp += quickSort(arr, low, pi - 1);
			comp += quickSort(arr, pi + 1, high);
		}
		return comp;
	}

	// Starter for quick sort
	public static long quickSort(int arr[]) {
		return quickSort(arr, 0, arr.length - 1);
	}

	// Merges two subarrays of arr[].
	// First subarray is arr[l..m]
	// Second subarray is arr[m+1..r]
	private static long merge(int arr[], int l, int m, int r) {
		// Find sizes of two subarrays to be merged
		int n1 = m - l + 1;
		int n2 = r - m;

		/* Create temp arrays */
		int L[] = new int[n1];
		int R[] = new int[n2];
		
		
		/* Copy data to temp arrays */
		long comp = 1; //i < n1
		for (int i = 0; i < n1; ++i) {
			L[i] = arr[l + i];
			
			//i < n1
			comp++;
		}
		comp++;//j < n2
		for (int j = 0; j < n2; ++j) {
			R[j] = arr[m + 1 + j];
			
			//j < n2
			comp++;
		}

		/* Merge the temp arrays */

		// Initial indexes of first and second subarrays
		int i = 0, j = 0;

		// Initial index of merged subarry array
		int k = l;
		comp +=2; //i < n1 && j < n2
		while (i < n1 && j < n2) {
			comp++;
			if (L[i] <= R[j]) {
				arr[k] = L[i];
				i++;
			} else {
				arr[k] = R[j];
				j++;
			}
			k++;
			
			comp +=2;//i < n1 && j < n2
		}

		/* Copy remaining elements of L[] if any */
		comp++;//i < n1
		while (i < n1) {
			arr[k] = L[i];
			i++;
			k++;
			
			comp++;//i < n1
		}

		/* Copy remaining elements of R[] if any */
		comp++;//j < n2
		while (j < n2) {
			arr[k] = R[j];
			j++;
			k++;
			
			comp++; //j < n2
		}
		
		return comp;
	}

	// Main function that sorts arr[l..r] using
	// merge()
	private static long mergeSort(int arr[], int l, int r) {
		long comp = 1;
		if (l < r) {
			// Find the middle point
			int m = (l + r) / 2;

			// Sort first and second halves
			comp += mergeSort(arr, l, m);
			comp += mergeSort(arr, m + 1, r);

			// Merge the sorted halves
			comp += merge(arr, l, m, r);
		}
		return comp;
	}

	// Starter for merge sort
	public static long mergeSort(int arr[]) {
		return mergeSort(arr, 0, arr.length - 1);
	}
	
	//Selection sort to compute the number of comparisons
	public static long selectionSort(int arr[]) 
    { 
		long  comp=0;
        int n = arr.length; 
          // One by one move boundary of unsorted subarray 
        
        comp++; //first loop control
        for (int i = 0; i < n-1; i++) 
        { 
            // Find the minimum element in unsorted array 
            int minIndex = i; 
            
            comp++; // first loop control
            for (int j = i+1; j < n; j++) {
            	
            	comp++; //if
                if (arr[j] < arr[minIndex]) {
                    minIndex = j; 
                }
                
                comp++; // loop control
            }
            // Swap the found minimum element with the first  element 
            int temp = arr[minIndex]; 
            arr[minIndex] = arr[i]; 
            arr[i] = temp; 
            
            comp++; //loop control
        } 
        
        return comp;
    } 
	
	//Bubble sort to compute the number of comparisons
	public static long bubbleSort(int arr[]) 
    { 
		long  comp=0;
        int n = arr.length; 
        
        comp++; //first loop control
        for (int i = 0; i < n-1; i++) {
        	
        	comp++; //first loop control
            for (int j = 0; j < n-i-1; j++) {
            	
            	comp++; //if
                if (arr[j] > arr[j+1]) 
                { 
                    // swap arr[j+1] and arr[j] 
                    int temp = arr[j]; 
                    arr[j] = arr[j+1]; 
                    arr[j+1] = temp; 
                } 
                
                comp++; //loop control
            }
            
            comp++; //loop control
        }
        
        return comp;
    } 
  
	// Test code
	  // Test code
    public static void main(String args[]) throws FileNotFoundException 
{ 		int[] a = {1,2,3,4,5,6,7,8,9,9};
		int[] b = {9,9,8,7,6,5,4,3,2,1};
		int[] c = {2,6,8,7,4,9,5,3,1,9};
		quickSort(a);
		System.out.println(Arrays.toString(a));
		quickSort(b);
		System.out.println(Arrays.toString(b));
		quickSort(c);
		System.out.println(Arrays.toString(c));
	
    	testMergeAndQuick(TestData.ALL, TestData.AVERAGE_CASE);
    	System.out.println("---------------------");
    	testQuickAndBubble(13, TestData.AVERAGE_CASE);
    	
    }
    
    
    public static void testMergeAndQuick(int n, int test_case) throws FileNotFoundException {

    	//Get the data sizes used to create test files.
    	int[] testDataSizes = TestData.TEST_SIZES;
    	
    	//Arrays to keep test results
    	long[] mergeSortTestResults =  new long [testDataSizes.length];
    	long[] quickSortTestResults =  new long [testDataSizes.length];
        
    	//Tests
    	for (int i = 0; i < n; i++) {
    		//The data of the current test
			int testSize= testDataSizes[i];
			
			System.out.println("Testing with " + testSize + " data.");
			
			//The array to be tested
			int testArr[] = TestData.getData(testSize, test_case);
	
			//Test for Selection sort
			int[] arr = TestData.cloneArray(testArr);
			quickSortTestResults[i] = quickSort(arr);

		    //Test for Bubble sort
			TestData.cloneArray(testArr);
			mergeSortTestResults[i] = mergeSort(arr);
		}
    
    	//Printing the test results
       System.out.println("Size\tMergeSort\tQuickSort");
       for (int i = 0; i < n; i++) {
    	   int size= testDataSizes[i];
    	   System.out.printf("%d\t", size );
    	   System.out.printf("%d\t", mergeSortTestResults[i] );
    	   System.out.printf("%d\n", quickSortTestResults[i]);
       }
       
		
	} 
    
    public static void testQuickAndBubble(int n, int test_case) throws FileNotFoundException {

    	//Get the data sizes used to create test files.
    	int[] testDataSizes = TestData.TEST_SIZES;
    	
    	//Array to keep test results
    	long[] quickSortTestResults =  new long [testDataSizes.length];
    	long[] bubbleSortTestResults =  new long [testDataSizes.length];
        
    	//Tests
    	for (int i = 0; i < n; i++) {
    		//The data of the current test
			int testSize= testDataSizes[i];
			
			System.out.println("Testing with " + testSize + " data.");
			
			//The array to be tested
			int testArr[] = TestData.getData(testSize, test_case);
	
			//Test for Selection sort
			int[] arr = TestData.cloneArray(testArr);
		    bubbleSortTestResults[i] =bubbleSort(arr);
		    
		    //Test for Bubble sort
			arr = TestData.cloneArray(testArr);
		    quickSortTestResults[i] =  quickSort(arr);
		}
      
    	//Printing the test results
        System.out.println("Size\tQuickSort\tBubbleSort");
        for (int i = 0; i < n; i++) {
     	   int size= testDataSizes[i];
     	   System.out.printf("%d\t", size );
     	   System.out.printf("%d\t", quickSortTestResults[i] );
     	   System.out.printf("%d\n", bubbleSortTestResults[i]);
        }
		
	}

	private static void testBubleAndSelection(int n, int test_case) throws FileNotFoundException {

    	//Get the data sizes used to create test files.
    	int[] testDataSizes = TestData.TEST_SIZES;
    	
    	//Arrays to keep test results
    	long[] bubbleSortTestResults =  new long [testDataSizes.length];
    	long[] selectionSortTestResults =  new long [testDataSizes.length];
        
    	//Tests
    	for (int i = 0; i < n; i++) {
    		//The data of the current test
			int testSize= testDataSizes[i];
			
			System.out.println("Testing with " + testSize + " data.");
			
			//The array to be tested
			int testArr[] = TestData.getData(testSize, test_case);
	
			//Test for Selection sort
			int[] arr = TestData.cloneArray(testArr);
			selectionSortTestResults[i] = selectionSort(arr);

		    //Test for Bubble sort
			TestData.cloneArray(testArr);
			bubbleSortTestResults[i] = bubbleSort(arr);
		}
    
    	//Printing the test results
       System.out.println("Size\tBubbleSort\tSelectionSort");
       for (int i = 0; i < n; i++) {
    	   int size= testDataSizes[i];
    	   System.out.printf("%d\t", size );
    	   System.out.printf("%d\t", bubbleSortTestResults[i] );
    	   System.out.printf("%d\n", selectionSortTestResults[i]);
       }
       
		
	} 

}
