package com.aamend.dsa.utils;
import com.aamend.dsa.sort.SortableList;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Utils {

	public static int generateInteger(int min, int max) {
		Random rand = new Random();
		return rand.nextInt(max - min) + min;
	}

	public static void prettyPrintList(List<Integer> list) {
        for (int i : list){
            System.out.print(i + " ");
        }
        System.out.println("");
	}

	public static List<Integer> populateLinkedList(int max, int minValue,
			int maxValue) {
		LinkedList<Integer> list = new LinkedList<Integer>();
		for (int i = 0; i < max; i++) {
			list.add(generateInteger(minValue, maxValue));
		}
		return list;
	}

	public static SortableList populateSortableList(int max, int minValue,
			int maxValue) {
		SortableList list = new SortableList();
		for (int i = 0; i < max; i++) {
			list.add(generateInteger(minValue, maxValue));
		}
		return list;
	}
	
	public static List<Integer> populateArrayList(int max, int minValue,
			int maxValue) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < max; i++) {
			list.add(generateInteger(minValue, maxValue));
		}
		return list;
	}
	
	public static int[] populateArray(int max, int minValue,
			int maxValue) {
		int[] array = new int[max];
		for (int i = 0; i < max; i++) {
			array[i] = generateInteger(minValue, maxValue);
		}
		return array;
	}

	public static int hash(int i, int maxBuckets, int maxNumber) {

		if (i == maxNumber){
			i--;
		}
		return (i * maxBuckets / maxNumber);

	}
	
}
