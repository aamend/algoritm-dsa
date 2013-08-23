package com.aamend.dsa.numeric;

import java.math.BigInteger;

public class Power {

	public static int iterN = 0;
	public static int iterLogN = 0;

	public static void main(String[] args) {

		BigInteger n = BigInteger.valueOf(2);
		long resultN = 1L;

		System.out.println("pow \t 2^pow \t O(n) \t O(logN)");

		for(int pow = 1; pow < 64; pow++){
			iterN = 0;
			iterLogN = 0;
			resultN = powerOn(n, pow).longValue();
			powerLogN(n, pow).longValue();
			System.out.println(pow + "\t" + resultN + "\t" + iterN + "\t"
					+ iterLogN);
		}

	}

	private static BigInteger powerOn(BigInteger n, int pow) {
		BigInteger result = n;
		iterN = 1;
		for (int i = 2; i <= pow; i++) {
			result = result.multiply(n);
			iterN++;
		}
		return result;
	}

	private static BigInteger powerLogN(BigInteger n, int pow) {
		BigInteger result;
		if (pow == 0) {
			result = BigInteger.ONE;
		} else {
			iterLogN++;
			result = powerLogN(n, pow / 2);
			if (pow % 2 == 0) {
				result = result.multiply(result);
			} else {
				result = result.multiply(result).multiply(n);
			}
		}

		return result;
	}

}
