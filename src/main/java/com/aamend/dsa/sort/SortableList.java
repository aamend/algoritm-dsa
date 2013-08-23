package com.aamend.dsa.sort;

import com.aamend.dsa.utils.Utils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SortableList extends LinkedList<Integer> {

	private static final long serialVersionUID = 1L;

	public void bubbleSort() {

		for (int j = this.size() - 1; j >= 0; j--) {
			for (int i = 0; i < j; i++) {
				if (this.get(i) > this.get(i + 1)) {
					// Swap them
					swap(i, i+1);
				}
			}
		}
	}

	public void quickSort() {
		List<Integer> sorted = _quickSort(this, 0, this.size() - 1);
		for (int i = 0; i < sorted.size(); i++) {
			this.set(i, sorted.get(i));
		}
	}

	private List<Integer> _quickSort(List<Integer> list, int left, int right) {

		if (right - left > 0) {
			int pivot = _partition(list, left, right);
			_quickSort(list, left, pivot - 1);
			_quickSort(list, pivot + 1, right);
		}
		return list;
	}

	private int _partition(List<Integer> list, int left, int right) {

		int pivotValue = list.get(right);
		// Do not need to move pivot on right as we chose the right most element

		int store = left;
		for (int i = store; i < right; i++) {
			if (list.get(i) <= pivotValue) {
				// Lower or equal to the pivot, store left
				swap(store, i);
				store++;
			}
		}

		list.set(right, list.get(store));
		list.set(store, pivotValue);

		return store;

	}

	public void bucketSort(int maxBuckets, int maxValue) {

		// Create your buckets
		List<SortableList> buckets = new ArrayList<SortableList>();
		for (int i = 0; i < maxBuckets; i++) {
			buckets.add(new SortableList());
		}

		// Populate your buckets
		for (int i = 0; i < this.size(); i++) {
			int value = this.get(i);
			int k = Utils.hash(value, maxBuckets, maxValue);
			buckets.get(k).add(value);
		}

		// Sort each bucket and re-populate list
		int idx = 0;
		for (int k = 0; k < buckets.size(); k++) {
			SortableList kBuList = buckets.get(k);
			if (kBuList != null && !kBuList.isEmpty()) {
				kBuList.insertionSort();
				for (int value : kBuList) {
					this.set(idx, value);
					idx++;
				}
			}

		}

	}

	public void countSort(int max) {

		// Setup our list of counters
		List<Integer> counters = new ArrayList<Integer>();
		for (int i = 0; i <= max; i++) {
			counters.add(0);
		}

		// Increment counters
		for (int i = 0; i < this.size(); i++) {
			int value = this.get(i);
			int counter = counters.get(value) + 1;
			counters.set(value, counter);
		}

		// Re-populate initial array with ordered values
		int idx = 0;
		for (int i = 0; i < counters.size(); i++) {
			int counter = counters.get(i);
			for (int j = 0; j < counter; j++) {
				this.set(idx, i);
				idx++;
			}
		}

	}

	public void insertionSort() {

		// Consider first value as already sorted
		for (int i = 1; i < this.size(); i++) {

			int valueToInsert = this.get(i);
			int j = i - 1;

			// browse left elements
			while (j >= 0) {

				if (this.get(j) > valueToInsert) {
					// Shift value on the right
					this.set(j + 1, this.get(j));
				} else {
					// Insertion is at the right place
					break;
				}

				j--;
			}

			// Do not forget to re-insert value at the right place
			this.set(j + 1, valueToInsert);

		}

	}

	public void mergeSort() {
		List<Integer> list = _mergeSort(this);
		for (int i = 0; i < list.size(); i++) {
			this.set(i, list.get(i));
		}
	}

	private List<Integer> _mergeSort(List<Integer> list) {

		if (list.size() <= 1) {
			return list;
		}

		int mid = list.size() / 2;

		List<Integer> left = list.subList(0, mid);
		List<Integer> right = list.subList(mid, list.size());

		left = _mergeSort(left);
		right = _mergeSort(right);

		return _merge(left, right);
	}

	private List<Integer> _merge(List<Integer> left, List<Integer> right) {

		List<Integer> merge = new ArrayList<Integer>(left.size() + right.size());

		int leftCount = 0;
		int rightCount = 0;

		while (leftCount < left.size() || rightCount < right.size()) {
			// While we do have at least one element in left or right

			if (leftCount == left.size()) {
				// take right
				merge.add(right.get(rightCount));
				rightCount++;
			} else if (rightCount == right.size()) {
				// take left
				merge.add(left.get(leftCount));
				leftCount++;
			} else {
				// item available in left AND right, choose smallest one
				if (left.get(leftCount) < right.get(rightCount)) {
					// take left
					merge.add(left.get(leftCount));
					leftCount++;
				} else {
					// take right
					merge.add(right.get(rightCount));
					rightCount++;
				}
			}

		}

		return merge;
	}

	public void heapSort() {

		// Create our heap
		buildHeap();
		for (int i = this.size() - 1; i >= 0; i--) {
			// 1. Swap maximum with the right most element
			swap(0, i);
			// 2. Reorganize heap from 0 to this level (right most element are now sorted)
			heapify(0, i);
		}
	}

	private void buildHeap() {
		// From last known parent to 0 (root level)...
		for (int i = this.size() / 2 - 1; i >= 0; i--) {
			// ... make sure all its children are compliant with heap structure
			heapify(i, this.size());
		}
	}

	private void heapify(int parentId, int heapSize) {

		int largest = parentId;

		// Get left and right children
		int leftChild = 2 * parentId + 1;
		int rightChild = 2 * parentId + 2;

		if (leftChild < heapSize && this.get(parentId) < this.get(leftChild)) {
			// Left element is greater than its parent
			// This might be the one that will be promoted
			// Need to compare with right element
			if (rightChild < heapSize && this.get(leftChild) < this.get(rightChild)) {
				// Right element is greater than left
				// This is the right which will get promoted
				largest = rightChild;
			} else {
				largest = leftChild;
			}
		} 

		// Heap constraint violated, need to reorganize heap children
		if (largest != parentId) {

			// 1. Swap largest child with parent
			swap(parentId, largest);

			// 2. Reorganize heap from lower level
			heapify(largest, heapSize); // Go to lower level
		}
	}
	
	
	// Utils

	private void swap(int id1, int id2) {
		int tmp = this.get(id1);
		this.set(id1, this.get(id2));
		this.set(id2, tmp);
	}
}
