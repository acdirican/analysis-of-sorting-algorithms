package analysis.acdirican.sorting.testdata;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter; // Import the FileWriter class
import java.io.IOException; // Import the IOException class to handle errors
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.util.Random;
import java.util.Scanner;

/**
 * This class is to create necessary test data for the analysis of sorting algorithms.
 * 
 * @author Ahmet Cengizhan Dirican
 * @version 1.0
 *
 */
public class TestData {
	/*
	 * Test types
	 */
	public static final int BEST_CASE = 0;
	public static final int AVERAGE_CASE = 1;
	public static final int WORST_CASE = 2;
	public static final String[] TESTS= {"best case","average case","worst case"};
	
	/*
	 * Test data sizes
	 */
	public static int[] TEST_SIZES = { 10, 100, 1000, 10000, 20000, 30000, 40000, 50000, 60000, 70000, 80000, 90000,
			100000, 200000, 300000, 400000, 500000, 600000, 700000, 800000, 900000, 1000000, 5000000, 6000000, 7000000,
			8000000, 9000000, 10000000 };

	public static final int ALL = TEST_SIZES.length;
	
	/*
	 * Test set directories
	 */
	public static final String BEST_CASE_DIR= "best_case";
	public static final String AVERAGE_CASE_DIR= "average_case";
	public static final String WORST_CASE_DIR = "worst_case";
	public static final String[] DIRECTORIES= {BEST_CASE_DIR,AVERAGE_CASE_DIR,WORST_CASE_DIR};

	/*
	 * Random number generator
	 */
	private static final Random RND = new Random();
	
	// Creates random test data file
	public static void createTestData(int type) throws IOException {
		// Check if the test-set directories exist!
		createDirectories();
		
		System.out.println("** Test case :" + TESTS[type]);
		//Create and save test data
		for (int i = 0; i < TEST_SIZES.length; i++) {
			int testSize = TEST_SIZES[i];
			try {
				String fileName = "data_" + testSize + "_.txt";
				String path = getPath(type, fileName);
			
				FileWriter fileWriter = new FileWriter(path);

				fileWriter.write(String.valueOf(testSize) + "\n");
				
				int element = 0;
				for (int j = 0; j < testSize; j++) {
					
					//Specify the value of a single test value depending on the test type
					if (type == BEST_CASE)
						element = j;
					else if (type == AVERAGE_CASE)
						element = RND.nextInt(testSize);
					else if (type == WORST_CASE)
						element = testSize - j;
					else
						throw new RuntimeException("Invalid test case type!");

					fileWriter.write(String.valueOf(element));
					if (j != testSize - 1) {
						fileWriter.write('\n');
					}
				}

				fileWriter.close();
				System.out.println("Data set with size of " + testSize + " was created.");
			} catch (IOException e) {
				System.out.println("Test data could no be created.");
				e.printStackTrace();
			}
		}
	}

	private static void createDirectories() throws IOException {
		// Check if the test-set directories exist!
		for (String dir : DIRECTORIES) {
			if (!Files.exists(Path.of(dir))) {
				Files.createDirectories(Path.of(dir));
			}
		}

	}

	private static String getPath(int type, String fileName) {
		String path;
		if (type == BEST_CASE)
			path = BEST_CASE_DIR +"/" + fileName;
		else if (type == AVERAGE_CASE)
			path = AVERAGE_CASE_DIR + "/" + fileName;
		else if (type == WORST_CASE)
			path = WORST_CASE_DIR + "/" + fileName;
		else
			throw new RuntimeException("Invalid test case type!");
		return path;
	}

	// REad a test file from the disk
	public static int[] getData(int testSize, int type) throws FileNotFoundException {
		String fileName = "data_" + testSize + "_.txt";
		String path;
		if (type == BEST_CASE)
			path = BEST_CASE_DIR +"/" + fileName;
		else if (type == AVERAGE_CASE)
			path = AVERAGE_CASE_DIR + "/" + fileName;
		else if (type == WORST_CASE)
			path = WORST_CASE_DIR + "/" + fileName;
		else
			throw new RuntimeException("Invalid test case type!");

		File file = new File(path);
		Scanner sc = new Scanner(file);
		int size = sc.nextInt();

		int[] out = new int[size];

		for (int i = 0; i < out.length; i++) {
			out[i] = sc.nextInt();
		}
		return out;
	}

	// Clones an array.
	public static int[] cloneArray(int[] arr) {
		int[] out = new int[arr.length];
		for (int i = 0; i < out.length; i++) {
			out[i] = arr[i];
		}
		return out;
	}

	public static String getTestName(int test_case) {
		return TESTS[test_case];
	}

	public static void create() throws IOException {
		System.out.println("*** Test Data is being created:");
		createTestData(BEST_CASE);
		createTestData(AVERAGE_CASE);
		createTestData(WORST_CASE);
		
	}
}
