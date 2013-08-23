package com.aamend.dsa.structure;

import com.aamend.dsa.sort.SortableList;
import com.aamend.dsa.utils.Utils;

public class DataStructure {

	private static int MAX_VALUE = 100;
	private static int MIN_VALUE = 10;
	private static int N = 15;

	private static SortableList list;

	public static void main(String[] args) throws BinarySearchTree.BSTException {
		
		list = Utils.populateSortableList(N, MIN_VALUE, MAX_VALUE);
		list.add(15);
		list.add(17);
		list.add(18);

		// Random BST
		System.out.println("\n------------------------------");
		System.out.println("Random BST - might be unbalanced");
		System.out.println("------------------------------\n");
		BinarySearchTree randomBst = new BinarySearchTree();
		for (int i = 0; i < list.size(); i++) {
			randomBst.insert(list.get(i));
		}
		randomBst.print();
		
		
		// Unbalanced BST
		System.out.println("\n------------------------------");
		System.out.println("Ordered BST - fully unbalanced");
		System.out.println("------------------------------\n");
		
		list.quickSort();
		BinarySearchTree unBalancedBst = new BinarySearchTree();
		for (int i = 0; i < list.size(); i++) {
			unBalancedBst.insert(list.get(i));
		}
		unBalancedBst.print();
		
	}


}
