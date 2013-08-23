package com.aamend.dsa.examples;

import java.security.NoSuchAlgorithmException;
import java.util.BitSet;

import junit.framework.Assert;

public class IsNotIn {

	public static final int MAX_NUMBER = Integer.MAX_VALUE;
	public static final int NUM_ELEMENTS = MAX_NUMBER;
	public static final int LOOKUP = 981239812;

	public static void main(String[] args) throws NoSuchAlgorithmException {
		fitInMemory();
	}

	private static void fitInMemory() {

		// Create our BitSet
		BitSet bitSet = new BitSet(MAX_NUMBER);
		// populate our "file" of 32bits integer
		// here it is sorted
		for (int i = 0; i < NUM_ELEMENTS; i++) {
			if (i != LOOKUP) {
				bitSet.set(i, true);
			}
		}
		
		int firstMissing = bitSet.nextClearBit(0);
		Assert.assertTrue(firstMissing == LOOKUP);
		System.out.println("Missing number is "+firstMissing);
	}

}
