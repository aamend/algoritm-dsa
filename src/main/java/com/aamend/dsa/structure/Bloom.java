package com.aamend.dsa.structure;

import org.apache.hadoop.util.bloom.BloomFilter;
import org.apache.hadoop.util.bloom.Key;
import org.apache.hadoop.util.hash.Hash;

public class Bloom {

	public static final int MAX_NUMBER = Integer.MAX_VALUE;
	public static final int NUM_ELEMENTS = MAX_NUMBER;
	public static final int LOOKUP = 981239812;
	public static final float BLOOM_FALSE_POSITIVE = 0.999F;

	public static void main(String[] args) {
		doesNotFitInMemory();
	}

	private static void doesNotFitInMemory() {

		// Create our Filter
		BloomFilter filter = new BloomFilter(getOptimalBloomFilterSize(
				NUM_ELEMENTS, 0.7F), 1, Hash.MURMUR_HASH);

		// Train our filter
		for (int i = 0; i < NUM_ELEMENTS; i++) {
			if (i != LOOKUP) {
				Key key = new Key(String.valueOf(i).getBytes());
				filter.add(key);
			}
		}

		System.out.println("Bloom trained");

		if (!filter.membershipTest(new Key(String.valueOf(LOOKUP).getBytes()))) {
			System.err.println("Was not able to retrieve LOOKUP = " + LOOKUP);
		} else {
			System.out.println("Was able to retrieve LOOKUP = " + LOOKUP);
		}
	}

	public static int getOptimalBloomFilterSize(int numRecords,
			float falsePosRate) {
		return (int) (-numRecords * (float) Math.log(falsePosRate) / Math.pow(
				Math.log(2), 2));
	}

	public static int getOptimalK(float numMembers, float vectorSize) {
		return (int) Math.round(vectorSize / numMembers * Math.log(2));
	}

}
