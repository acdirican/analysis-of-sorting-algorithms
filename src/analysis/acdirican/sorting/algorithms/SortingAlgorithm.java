package analysis.acdirican.sorting.algorithms;

import java.util.function.Consumer;

/**
 * 
 * @author AhmetCengizhan Dirican
 * @version 1.0
 *
 */
public enum SortingAlgorithm {
	BUBLESORT("Bubble Sort", Simple::bubbleSort),
	INSERTIONSORT("Insertion Sort", Simple::insertionSort),
	SELECTIONSORT("Selection Srot", Simple::selectionSort),
	MERGESORT("Merge Sort", MergeSort::sort),
	QUICKSORT("Quick Sort", QuickSort::sort),;

	private final String name;
	private final Consumer<int[]> algorithm;
	
	SortingAlgorithm(String name, Consumer<int[]> algorithm) {
		this.name = name;
		this.algorithm = algorithm;
	}

	public String getName() {
		return name;
	}

	public static SortingAlgorithm get(int a) {
		return SortingAlgorithm.values()[a];
	}

	public Consumer<int[]> getAlgorithm() {
		return algorithm;
	}

	public static int count() {
		return SortingAlgorithm.values().length;
	}
	
	
	
}
