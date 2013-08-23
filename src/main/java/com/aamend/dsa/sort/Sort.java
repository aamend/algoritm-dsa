package com.aamend.dsa.sort;


import com.aamend.dsa.utils.Utils;

public class Sort {

	private static int MAX_BUCKETS = 20;
	private static int MAX_VALUE = 1000;
	private static int MIN_VALUE = 10;
	private static int N = 100;

	private static SortableList list;

	public static void main(String[] args) {

		// BUBBLE SORT
		System.out.println("BUBBLE SORT");
		list = Utils.populateSortableList(N, MIN_VALUE, MAX_VALUE);
		Utils.prettyPrintList(list);
		list.bubbleSort();
		Utils.prettyPrintList(list);
		System.out.println("\n");
		
		// INSERTION SORT
		System.out.println("INSERTION SORT");
		list = Utils.populateSortableList(N, MIN_VALUE, MAX_VALUE);
		Utils.prettyPrintList(list);
		list.insertionSort();
		Utils.prettyPrintList(list);
		System.out.println("\n");
		
		// COUNT SORT
		System.out.println("COUNT SORT");
		list = Utils.populateSortableList(N, MIN_VALUE, MAX_VALUE);
		Utils.prettyPrintList(list);
		list.countSort(MAX_VALUE);
		Utils.prettyPrintList(list);
		System.out.println("\n");
		
		// BUCKETS SORT
		System.out.println("BUCKETS SORT");
		list = Utils.populateSortableList(N, MIN_VALUE, MAX_VALUE);
		Utils.prettyPrintList(list);
		list.bucketSort(MAX_BUCKETS, MAX_VALUE);
		Utils.prettyPrintList(list);
		System.out.println("\n");
		
		// MERGE SORT
		System.out.println("MERGE SORT");
		list = Utils.populateSortableList(N, MIN_VALUE, MAX_VALUE);
		Utils.prettyPrintList(list);
		list.mergeSort();
		Utils.prettyPrintList(list);
		System.out.println("\n");
		
		// QUICK SORT
		System.out.println("QUICK SORT");
		list = Utils.populateSortableList(N, MIN_VALUE, MAX_VALUE);
		Utils.prettyPrintList(list);
		list.quickSort();
		Utils.prettyPrintList(list);
		System.out.println("\n");

		// HEAP SORT
		System.out.println("HEAP SORT");
		list = Utils.populateSortableList(N, MIN_VALUE, MAX_VALUE);
		Utils.prettyPrintList(list);
		list.heapSort();
		Utils.prettyPrintList(list);
		System.out.println("\n");
		
		
	}
	
}
