package analysis.acdirican.sorting.tests;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Vector;
import java.util.function.Consumer;

import analysis.acdirican.sorting.algorithms.Others;
import analysis.acdirican.sorting.algorithms.SortingAlgorithm;
import analysis.acdirican.sorting.testdata.TestData;
import analysis.acdirican.sorting.algorithms.MergeSort;
import analysis.acdirican.sorting.algorithms.QuickSort;
import analysis.acdirican.sorting.algorithms.Simple;

/**
 * 
 * @author AhmetCengizhan Dirican
 * @version 1.0
 *
 */
public class RuntimeTest {

	private static boolean DEBUG = true;
	
	public static double[][] apply(int start, int finish, int test_case, int testRepetionCount) throws FileNotFoundException {
	
		// Array to keep test results
		int n= finish-start+1;
		double[][] testResults = new double[SortingAlgorithm.count()][n];

		for (int a=0; a<SortingAlgorithm.count(); a++) {
			SortingAlgorithm algorithm = SortingAlgorithm.get(a);
			
			if(DEBUG) {
				System.out.println("Testing:" + algorithm.getName());
			}
			
			// Tests
			int i=0;
			for (int t = start; t <=finish; t++) {
				testResults[a][i++] = applyTest(algorithm.getAlgorithm(), t, test_case, testRepetionCount);
			}
			
		}
		
		return testResults;
		
	}

	private static double applyTest(Consumer<int[]> algorithm, int i, int test_case, int testRepetionCount) throws FileNotFoundException {
		// The data of the current test
		int[] testDataSizes = TestData.TEST_SIZES;
		int testSize = testDataSizes[i];
		
		if(DEBUG) {
			System.out.println("Testing with " + testSize + " data.");
		}

		// The array to be tested
		int testArr[] = TestData.getData(testSize, test_case);

		long startTime = 0, finishTime = 0;
		double elepsedTime = 0;

		// Test for Selection sort
		for (int j = 0; j < testRepetionCount; j++) {
			int[] arr = TestData.cloneArray(testArr);

			startTime = System.nanoTime();

			algorithm.accept(arr);

			finishTime = System.nanoTime();

			elepsedTime += (finishTime - startTime) / 1000000.0;
		}
		
		return elepsedTime/testRepetionCount;
	}

	public static void debug(boolean value) {
		DEBUG = value;		
	}

}
