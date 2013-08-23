package com.aamend.dsa.search;


import com.aamend.dsa.sort.SortableList;
import com.aamend.dsa.utils.Utils;
import java.util.ArrayList;
import java.util.List;

public class Search {

	private static int MAX_BUCKETS = 5;
	private static int MAX_VALUE = 150;
	private static int MIN_VALUE = 10;
	private static int N = 100;
	private static int LOOKUP = 100;

	private static SortableList list;

	public static void main(String[] args) {

		list = Utils.populateSortableList(N, MIN_VALUE, MAX_VALUE);
		list.quickSort();
		Utils.prettyPrintList(list);
		
		// BINARY SEARCH
		System.out.println(binarySearch());

		// HASH SEARCH
		System.out.println(hashSearch());
		
		// SEQUENTIAL SEARCH
		System.out.println(sequentialSearch());

	}

	public static boolean binarySearch() {

		int low = 0;
		int high = list.size() - 1;

		while (high >= low) {

			int mid = (high + low) / 2;
			int value = list.get(mid);

			if (LOOKUP < value) {
				// Value is in lower array
				high = mid - 1;
			} else if (LOOKUP > value) {
				// Value is in upper array
				low = mid + 1;
			} else {
				return true;
			}
		}

		return false;
	}

	public static boolean hashSearch() {

		// Initialize our buckets
		List<ArrayList<Integer>> buckets = new ArrayList<ArrayList<Integer>>(
				MAX_BUCKETS);
		for (int i = 0; i < MAX_BUCKETS; i++){
			buckets.add(new ArrayList<Integer>());
		}

		// Populate our buckets
		for (int i = 0; i < list.size(); i++){
			// Calculate hash value
			int h = Utils.hash(list.get(i), MAX_BUCKETS, MAX_VALUE);
			buckets.get(h).add(list.get(i));
		}
		
		int k = Utils.hash(LOOKUP, MAX_BUCKETS, MAX_VALUE);
		if (buckets.get(k).isEmpty()){
			return false;
		} else {
			if (buckets.get(k).contains(LOOKUP)){
				return true;
			} else {
				return false;
			}
		}

	}
	
	public static boolean sequentialSearch(){
		
		for (int i = 0; i < list.size(); i++){
			if (list.get(i) == LOOKUP){
				return true;
			}
		}
		
		return false;
	}

}
