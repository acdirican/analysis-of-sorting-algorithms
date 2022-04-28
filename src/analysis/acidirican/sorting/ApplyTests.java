package analysis.acidirican.sorting;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import analysis.acdirican.sorting.algorithms.Others;
import analysis.acdirican.sorting.algorithms.SortingAlgorithm;
import analysis.acdirican.sorting.testdata.TestData;
import analysis.acdirican.sorting.tests.AssignmentTest;
import analysis.acdirican.sorting.tests.ComparisonTest;
import analysis.acdirican.sorting.tests.RuntimeTest;
/**
 * 
 * @author AhmetCengizhan Dirican
 * @version 1.0
 *
 */
public class ApplyTests {
	
	/**
	 * The start index of {@link TestData.TEST_SIZES}
	 */
	private static final int start = 0;
	/**
	 * The finish index of {@link TestData.TEST_SIZES}
	 */
	private static final int finish = 5;
	/**
	 * The number of repetition of tests
	 */
	private static final int testRepetitionCount = 20;
	
	/*
	 *Object for command-line progress feedback 
	 */
	private static Progress progressSignaler;
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		System.out.println("Would you like to recreate the test data [yes/no]?");
		String ans = sc.nextLine();
		
		if (ans.equalsIgnoreCase("yes") || ans.equalsIgnoreCase("y")){
			TestData.create();
		}
		
		
		test(TestData.BEST_CASE);
		test(TestData.AVERAGE_CASE);
		test(TestData.WORST_CASE);
	}

	private static void test(int test_case ) throws FileNotFoundException {

		RuntimeTest.debug(false);
		
		startProgress("** Test:" + TestData.getTestName(test_case) + " x " + testRepetitionCount + "");
		double[][] results = RuntimeTest.apply(start, finish, test_case,testRepetitionCount);
		stopProgress("Completed.");
       	print(start, finish,test_case, results);

	}

	private static void print(int start, int finish, int test_case, double[][] results) {
		printLine(120);
		System.out.println("* Results:" + TestData.getTestName(test_case));
		printLine(120);
		
		int[] testDataSizes = TestData.TEST_SIZES;
		
		System.out.printf("%-20s","-Size/Algorithm:");
		for(int a=0; a<SortingAlgorithm.count(); a++) {
			System.out.printf("%-20s", SortingAlgorithm.get(a).getName());
		}
		System.out.println();
		int i=0;
		for (int t = start; t <=finish; t++) {
			System.out.printf("%-20s", testDataSizes[i++]);
			for(int a=0; a<SortingAlgorithm.count(); a++) {
				System.out.printf("%-20.4f", results[a][t]);
			}
			System.out.println();
		}
		printLine(120);
		
	}

	private static void stopProgress(String message) {
		System.out.println(message);
		progressSignaler.stopProgress();
	}

	private static void startProgress(String message) {
		System.out.println("\n" + message);
		progressSignaler = Progress.startProgress();
	}
	
	private static void printLine(int size) {
		for (int i = 0; i < size; i++) {
			System.out.print("-");
		}
		System.out.println();
		
	}
	
}
